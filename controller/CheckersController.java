package controller;

import model.*;
import view.CheckersView;
import view.PicturePanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;

public class CheckersController implements MouseListener {
    private final CheckersModel model;
    private final CheckersView view;
    private final SocketController socketController;
    private boolean fieldIsClicked;
    private PicturePanel oldFieldPanel;
    private Field lastEnteredField;
    private Thread waitingThread;

    private int MyVictories = 0;
    private boolean duringCombo;

    private Field FieldInCombo;

    public void setCurrentPlayer(Player currentPlayer) {
        this.model.setCurrentPlayer(currentPlayer);
    }

    public CheckersController(CheckersModel model, CheckersView view, SocketController socketController) {
        this.model = model;
        this.view = view;
        this.socketController = socketController;
        this.play();
    }

    public Field getField(int i, int j) {
        return model.getField(i, j);
    }

    public void play() {
        view.updateView(model);
        this.addListiners();
        if(!this.model.isMyTurn.get()){
            waitForEnemy();
        }
    }

    private void addListiners() {
        for (int i = 0; i < Constants.GameConstants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.GameConstants.BOARD_SIZE; j++) {
                view.fieldsPanels[i][j].addMouseListener(this);
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(!this.model.isMyTurn.get())
            return;
        if (!this.fieldIsClicked) {
            PicturePanel oldFieldPanel = (PicturePanel) mouseEvent.getComponent();
            clickPanel(oldFieldPanel);
        } else {
            PicturePanel newFieldPanel = (PicturePanel) mouseEvent.getComponent();
            Field oldField = this.getField(this.oldFieldPanel.getRow(), this.oldFieldPanel.getCol());
            Field newField = this.getField(newFieldPanel.getRow(), newFieldPanel.getCol());

            boolean moveMade = makeMove(oldField,newField);
            if (moveMade) {
                unclickPanel();
                view.updateView(model);
                highlyCurrentField(mouseEvent);
            }
            else { // chose new field
                reclickPanel(newFieldPanel);
            }
        }
    }

    private void clickPanel(PicturePanel oldFieldPanel) {
        this.oldFieldPanel = oldFieldPanel;
        this.fieldIsClicked = true;
        this.oldFieldPanel.click();
    }

    private void reclickPanel(PicturePanel newFieldPanel) {
        this.oldFieldPanel.unclick();
        this.oldFieldPanel = newFieldPanel;
        this.oldFieldPanel.click();
    }

    private void unclickPanel() {
        this.oldFieldPanel.unclick();
        this.oldFieldPanel = null;
        this.fieldIsClicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        highlyCurrentField(mouseEvent);
    }

    private void highlyCurrentField(MouseEvent mouseEvent) {
        if(this.model.isMyTurn.get()){
            this.lastEnteredField = getCurrentField(mouseEvent);
            for (Field field:getAvailableMoves(lastEnteredField)) {
                view.getPicturePanel(field.getRow(),field.getCol()).setBackground(new Color(0xb7fffa));
            }
        }
    }

    private Field getCurrentField(MouseEvent mouseEvent) {
        PicturePanel oldFieldPanel = (PicturePanel) mouseEvent.getComponent();
        return getField(oldFieldPanel.getRow(), oldFieldPanel.getCol());
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        if(this.model.isMyTurn.get()){
            for (Field field:getAvailableMoves(lastEnteredField)) {
                PicturePanel currentPicturePanel = view.getPicturePanel(field.getRow(), field.getCol());
                currentPicturePanel.setBackground(currentPicturePanel.getBackground());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    public void setCapture(boolean capture) {
        model.setCapture(capture);
    }

    private boolean isEndField(Field newField) {
        return ((newField.isWhite() && newField.getRow() == 0) ||
                (newField.isBrown() && newField.getRow() == Constants.GameConstants.BOARD_SIZE - 1));
    }

    public void gameWon(){
        this.model.setEnemyFirst(this.model.isEnemyFirst());
        this.model.setCurrentPlayer(Player.WHITE);
        resetBoard();
    }

    public void resetBoard(){
        if(noMoreOpositePawns()) {
            if(didIWon())
                MyVictories++;
        }
        socketController.sendMessage(Constants.ConnectionConstants.GAME_OVER);
        model.resetBoard();
        model.setOpponentMove("");
        this.model.setMyMove("");
        view.updateView(model);
        this.model.isMyTurn.set(this.model.isEnemyFirst());
        model.setThreadRunning(!this.model.isMyTurn.get());
        waitForEnemy();
    }

    private boolean didIWon() {
        int white = countPawnsofPlayer(Player.WHITE);
        int brown = countPawnsofPlayer(Player.BROWN);
        if(model.getCurrentPlayer()==Player.WHITE)
            return white > brown;
        else
            return brown > white;
    }

    public void movePawn(Field oldField, Field newField) {
        newField.setPawn(oldField.getPawn());
        oldField.setPawn(Pawn.EMPTY);
        updateMyMove(oldField,newField);
    }

    public boolean isNormalMoveAvailable(Field oldField, Field newField) {
        return isFieldBlack(newField) &&
                isCurrentPlayerPawn(oldField) &&
                isFieldEmpty(newField) &&
                isMoveDiagonally(oldField, newField) &&
                isNumberOfJumpedFieldsValid(oldField, newField);
    }

    private boolean isFieldBlack(Field newField) {
        return newField.getColor() == Color.DARK_GRAY;
    }

    private boolean isCurrentPlayerPawn(Field oldField) {
        return (model.getCurrentPlayer() == Player.WHITE && (oldField.getPawn() == Pawn.WHITE_NORMAL || oldField.getPawn() == Pawn.WHITE_QUEEN)) ||
                (model.getCurrentPlayer() == Player.BROWN && (oldField.getPawn() == Pawn.BROWN_NORMAL || oldField.getPawn() == Pawn.BROWN_QUEEN));
    }

    private boolean isFieldEmpty(Field newField) {
        return newField.getPawn() == Pawn.EMPTY;
    }

    private boolean isMoveDiagonally(Field oldField, Field newField) {
        return (Math.abs(oldField.getRow() - newField.getRow()) == Math.abs(oldField.getCol() - newField.getCol()));
    }

    private boolean isNumberOfJumpedFieldsValid(Field oldField, Field newField) {
        if (oldField.isQueen()) {
            return isPathClear(oldField, newField);
        } else { // normal pawn
            return isMoveForward(oldField, newField) && (isDistanceOne(oldField, newField));
        }
    }

    private boolean isQueenCaptureValid(Field oldField, Field newField) {
        if(oldField.equals(newField)) return false;
        int differenceRow = oldField.getRow() < newField.getRow() ? 1 : -1;
        int differenceCol = oldField.getCol() < newField.getCol() ? 1 : -1;
        int opponentRow = newField.getRow() - differenceRow;
        int opponentCol = newField.getCol() - differenceCol;
        if ( (opponentRow-differenceRow > Constants.GameConstants.BOARD_SIZE-1) && (opponentCol-differenceCol > Constants.GameConstants.BOARD_SIZE-1) &&(!isPathClear(oldField, model.getBoard()[opponentRow - differenceRow][opponentCol - differenceCol])))
            return false;
        return !isNotOpponent(opponentRow, opponentCol);
    }

    private boolean isPathClear(Field oldField, Field newField) {
        if(oldField.equals(newField))
            return true;
        int differenceRow = oldField.getRow() < newField.getRow() ? 1 : -1;
        int differenceCol = oldField.getCol() < newField.getCol() ? 1 : -1;
        int currRow = oldField.getRow(), currCol = oldField.getCol();

        while (currCol != newField.getCol()) {
            currRow += differenceRow;
            currCol += differenceCol;
            if (model.getBoard()[currRow][currCol].isOccupied())
                return false;
        }
        return true;
    }

    private boolean isMoveForward(Field oldField, Field newField) {
        if (model.getCurrentPlayer() == Player.WHITE) {
            return (newField.getRow() < oldField.getRow());
        } else {
            return (newField.getRow() > oldField.getRow());
        }
    }

    private boolean isDistanceOne(Field oldField, Field newField) {
        return (Math.abs(oldField.getCol() - newField.getCol()) + Math.abs(oldField.getRow() - newField.getRow()) == 2);
    }

    private boolean isNormalCaptureValid(Field oldField, Field newField) {
        int differenceRow = oldField.getRow() < newField.getRow() ? 1 : -1;
        int differenceCol = oldField.getCol() < newField.getCol() ? 1 : -1;
        int opponentRow = oldField.getRow() + differenceRow;
        int opponentCol = oldField.getCol() + differenceCol;

        if (isNotOpponent(opponentRow, opponentCol))
            return false;
        if (opponentRow + differenceRow != newField.getRow() || opponentCol + differenceCol != newField.getCol())
            return false;
        this.model.setCapture(true);
        this.model.setOpponent(model.getBoard()[opponentRow][opponentCol]);
        return true;
    }

    public void destroyOpponentsPawn() {
        this.model.getOpponent().setPawn(Pawn.EMPTY);
        this.model.getOpponent().setOccupied(false);
    }

    private boolean isNotOpponent(int opponentRow, int opponentCol) {
        if (this.model.getCurrentPlayer() == Player.WHITE) {
            return model.getBoard()[opponentRow][opponentCol].getPawn() != Pawn.BROWN_QUEEN && model.getBoard()[opponentRow][opponentCol].getPawn() != Pawn.BROWN_NORMAL;
        } else {
            return model.getBoard()[opponentRow][opponentCol].getPawn() != Pawn.WHITE_QUEEN && model.getBoard()[opponentRow][opponentCol].getPawn() != Pawn.WHITE_NORMAL;
        }
    }

    public void tryMakeQueen(Field newField) {
        if (isEndField(newField)) {
            createQueen(newField);
        }
    }

    private void createQueen(Field newField) {
        if (newField.isWhite())
            newField.setPawn(Pawn.WHITE_QUEEN);
        else if (newField.isBrown())
            newField.setPawn(Pawn.BROWN_QUEEN);
    }

    private boolean noMoreOpositePawns() {
        int white = countPawnsofPlayer(Player.WHITE);
        int brown = countPawnsofPlayer(Player.BROWN);
        return white == 0 || brown == 0;
    }

    private int countPawnsofPlayer(Player player) {
        int result = 0;
        for(int i=0; i<Constants.GameConstants.BOARD_SIZE;i++){
            for(int j=0; j<Constants.GameConstants.BOARD_SIZE;j++){
                if(model.getBoard()[i][j].isWhite()&&player==Player.WHITE)
                    result++;
                else if(model.getBoard()[i][j].isBrown()&&player==Player.BROWN)
                    result++;
            }
        }
        return result;
    }

    private void findOpponent(Field oldField, Field newField) {
        int differenceRow = oldField.getRow() < newField.getRow() ? 1 : -1;
        int differenceCol = oldField.getCol() < newField.getCol() ? 1 : -1;
        int opponentRow = newField.getRow() - differenceRow;
        int opponentCol = newField.getCol() - differenceCol;
        this.model.setCapture(true);
        this.model.setOpponent(model.getBoard()[opponentRow][opponentCol]);
    }

    private Set<Field> getAvailableNormalMoves(Field oldField) {
        Set<Field> availableNormalMoves = new HashSet<>();

        if(this.duringCombo){
            return availableNormalMoves;
        }

        for (Field[] fields : model.getBoard()) {
            for (Field field : fields
            ) {
                if (isNormalMoveAvailable(oldField, field)) {
                    availableNormalMoves.add(field);
                }
            }
        }
        return availableNormalMoves;
    }

    public Set<Field> getAvailableMoves(Field field){
        Set<Field> availableMoves = getAvailableCapturesMoves(field);
        availableMoves.addAll(getAvailableNormalMoves(field));
        return availableMoves;
    }

    private Set<Field> getAvailableCapturesMoves(Field oldField) {
        Set<Field> availableCaptureMoves = new HashSet<>();
        if (this.duringCombo) {
            if (!oldField.equals(this.FieldInCombo)) {
                return availableCaptureMoves;
            }
        }
        for (Field[] fields : model.getBoard()) {
            for (Field field : fields
            ) {
                if (isCaptureMoveAvailable(oldField, field)) {
                    availableCaptureMoves.add(field);
                }
            }
        }
        return availableCaptureMoves;
    }

    private boolean isCaptureMoveAvailable(Field oldField, Field newField) {
        return isFieldBlack(newField) &&
                isCurrentPlayerPawn(oldField) &&
                isFieldEmpty(newField) &&
                isMoveDiagonally(oldField, newField) &&
                isCaptureValid(oldField, newField);

    }

    private boolean isCaptureValid(Field oldField, Field newField) {
        if (oldField.isQueen()) {
            return isQueenCaptureValid(oldField, newField);
        } else { // normal pawn
            return isNormalCaptureValid(oldField, newField) && isMoveForward(oldField, newField);
            //if want capture backward
            //delete ismoveforward
        }
    }

    private void makeCapture(Field oldField, Field newField) {
        destroyOpponentsPawn();
        movePawn(oldField, newField);
        setCapture(false);
    }

    private void updateMyMove(Field oldField, Field newField) {
        if(this.model.getMyMove().equals(""))
            this.model.setMyMove(oldField.getRow()+","+oldField.getCol()+";"+newField.getRow()+","+newField.getCol());
        else{
            this.model.setMyMove(this.model.getMyMove()+ ";"+newField.getRow()+","+newField.getCol());
        }
    }


    public synchronized void makeOpponentMove(){
        this.setCurrentPlayer(Player.BROWN);
        String[] parts = this.model.getOpponentMove().split(";");
        for (int i = 1; i < parts.length; i++){
            String[] place = parts[i-1].split(",");
            int oldrow = Constants.GameConstants.BOARD_SIZE-1-Integer.parseInt(place[0]);
            int oldcol = Constants.GameConstants.BOARD_SIZE-1-Integer.parseInt(place[1]);

            place = parts[i].split(",");
            int newrow = Constants.GameConstants.BOARD_SIZE-1-Integer.parseInt(place[0]);
            int newcol = Constants.GameConstants.BOARD_SIZE-1-Integer.parseInt(place[1]);
            Field oldField = getField(oldrow, oldcol);
            Field newField = getField(newrow, newcol);
            if (getAvailableCapturesMoves(oldField).contains(newField)) {
                findOpponent(oldField,newField);
                makeCapture(oldField, newField);
            } else if (getAvailableNormalMoves(oldField).contains(newField)) {
                movePawn(oldField, newField);
            }
            tryMakeQueen(newField);
            if(noMoreOpositePawns()){
                gameWon();

            }
            this.view.updateView(this.model);
        }
        this.model.setMyMove("");
        this.model.setOpponentMove("");
        this.setCurrentPlayer(Player.WHITE);
    }

    private boolean isDuringCombo(Field newField) {
        return (getAvailableCapturesMoves(newField).size() > 0);
    }

    public boolean makeMove(Field oldField, Field newField) {
        if (getAvailableCapturesMoves(oldField).contains(newField)) {
            findOpponent(oldField,newField);
            makeCapture(oldField, newField);
            this.duringCombo = isDuringCombo(newField);
            if (this.duringCombo) {
                this.FieldInCombo = newField;
            } else {
                changePlayer();
            }
        } else if (getAvailableNormalMoves(oldField).contains(newField)) {
            movePawn(oldField, newField);
            changePlayer();
        } else {
            return false;
        }
        tryMakeQueen(newField);
        if(noMoreOpositePawns()){
            gameWon();
        }
        return true;
    }

    public void changePlayer() {
        if(!this.model.getMyMove().equals("")){
            socketController.sendMessage(Constants.ConnectionConstants.BOARD_MOVE, this.model.getMyMove());
            this.model.setMyMove("");
        }
        this.model.isMyTurn.set(false);
        this.waitForEnemy();
    }

    public void waitForEnemy(){
        if(waitingThread != null ){
            waitingThread.stop();
        }
        waitingThread = new Thread(() -> {
            if(!this.model.getOpponentMove().equals("")){
                makeOpponentMove();
            }
            while (!this.model.isMyTurn.get()&&this.model.isThreadRunning()){ }
            if(this.model.isThreadRunning()){
                makeOpponentMove();
            }
            this.model.setThreadRunning(true);
        });
        waitingThread.start();
    }

}

