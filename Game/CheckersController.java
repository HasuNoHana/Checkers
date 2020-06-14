package Game;

import model.Constants;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class CheckersController implements MouseListener {
    private CheckersModel model;
    private CheckersView view;
    private boolean fieldIsClicked;
    private PicturePanel oldFieldPanel;
    private Field lastEnteredField;

    private AtomicBoolean MyTurn;
    private String opponentMove;
    private String MyMove = "";
    private int MyVictories = 0;
    private boolean duringCombo;

    private Field FieldInCombo;
    private boolean endGame = false;
    private boolean isGameFinished;
    private int playerWhitePoints = 0;
    private int playerBrownPoints = 0;

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    private Player currentPlayer = Player.WHITE;
    private boolean isCapture = false;
    private Field opponent;

    public CheckersController(CheckersModel g, CheckersView mf) {
        model = g;
        view = mf;
        play();
    }

    public Field getField(int i, int j) {
        return model.getField(i, j);
    }

    public void play() {
        view.updateView(model);
        this.addListiners();
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
        if(isEndGame())
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
        this.lastEnteredField = getCurrentField(mouseEvent);
        for (Field field:getAvailableMoves(lastEnteredField)
             ) {
            view.getPicturePanel(field.getRow(),field.getCol()).setBackground(new Color(0xb7fffa));
        }
    }

    private Field getCurrentField(MouseEvent mouseEvent) {
        PicturePanel oldFieldPanel = (PicturePanel) mouseEvent.getComponent();
        return getField(oldFieldPanel.getRow(), oldFieldPanel.getCol());
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        for (Field field:getAvailableMoves(lastEnteredField)
        ) {
            PicturePanel currentPicturePanel = view.getPicturePanel(field.getRow(), field.getCol());
            currentPicturePanel.setBackground(currentPicturePanel.background);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }





    public boolean isCapture() {
        return isCapture;
    }

    public void setCapture(boolean capture) {
        isCapture = capture;
    }

    public boolean isGameFinished() {
        return isGameFinished;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }


    private boolean isEndField(Field newField) {
        return ((newField.isWhite() && newField.getRow() == 0) ||
                (newField.isBrown() && newField.getRow() == Constants.GameConstants.BOARD_SIZE - 1));
    }

//    public boolean canPawnMove(Field Field) {
//        //TODO
//
//        return false;
//    }

    public boolean isEndGame() {
        return endGame;
    }

    public void setMyMove(String myMove) {
        MyMove = myMove;
    }

    public void resetBoard(){
        if(noMoreOpositePawns()) {
            endGame();
            if(didIWon())
                MyVictories++;
        }
        model.resetBoard();
    }

    private boolean didIWon() {
        int white = countPawnsofPlayer(Player.WHITE);
        int brown = countPawnsofPlayer(Player.BROWN);
        if(currentPlayer==Player.WHITE)
            return white > brown;
        else
            return brown > white;
    }

    private void endGame() {
        this.endGame = true;
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
        return (currentPlayer == Player.WHITE && (oldField.getPawn() == Pawn.WHITE_NORMAL || oldField.getPawn() == Pawn.WHITE_QUEEN)) ||
                (currentPlayer == Player.BROWN && (oldField.getPawn() == Pawn.BROWN_NORMAL || oldField.getPawn() == Pawn.BROWN_QUEEN));
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
        return !isNotOponent(opponentRow, opponentCol);
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
        if (currentPlayer == Player.WHITE) {
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

        if (isNotOponent(opponentRow, opponentCol))
            return false;
        if (opponentRow + differenceRow != newField.getRow() || opponentCol + differenceCol != newField.getCol())
            return false;
        this.isCapture = true;
        this.opponent = model.getBoard()[opponentRow][opponentCol];
        return true;
    }

    public void destroyOponentsPawn() {
        this.opponent.setPawn(Pawn.EMPTY);
        this.opponent.setOccupied(false);
        if (this.currentPlayer == Player.WHITE)
            this.playerWhitePoints += 1;
        else
            this.playerBrownPoints += 1;
    }

    private boolean isNotOponent(int oponentRow, int oponentCol) {
        if (currentPlayer == Player.WHITE) {
            return model.getBoard()[oponentRow][oponentCol].getPawn() != Pawn.BROWN_QUEEN && model.getBoard()[oponentRow][oponentCol].getPawn() != Pawn.BROWN_NORMAL;
        } else {
            return model.getBoard()[oponentRow][oponentCol].getPawn() != Pawn.WHITE_QUEEN && model.getBoard()[oponentRow][oponentCol].getPawn() != Pawn.WHITE_NORMAL;
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
        this.isCapture = true;
        this.opponent = model.getBoard()[opponentRow][opponentCol];
    }

    private Set<Field> getAvailableNormalMoves(Field oldField) {
        Set<Field> availableNormalMoves = new HashSet<Field>();

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
        Set<Field> availableCaptureMoves = new HashSet<Field>();
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
        destroyOponentsPawn();
        movePawn(oldField, newField);
        setCapture(false);
    }

    private void updateMyMove(Field oldField, Field newField) {
        if(this.MyMove.equals(""))
            MyMove = oldField.getRow()+","+oldField.getCol()+";"+newField.getRow()+","+newField.getCol();
        else{
            MyMove += ";"+newField.getRow()+","+newField.getCol();
        }
    }


    public void makeOpponentMove(){
        String[] parts = this.opponentMove.split(";");
        for (int i = 1; i < parts.length; i++){
            String[] place = parts[i-1].split(",");
            int oldrow = Integer.parseInt(place[0]);
            int oldcol = Integer.parseInt(place[1]);

            place = parts[i].split(",");
            int newrow = Integer.parseInt(place[0]);
            int newcol = Integer.parseInt(place[1]);
            makeMove(getField(oldrow,oldcol),getField(newrow,newcol));
        }
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
        if(noMoreOpositePawns())
            endGame();
        return true;
    }

    public void changePlayer() {
        currentPlayer = currentPlayer == Player.WHITE ? Player.BROWN : Player.WHITE;//uwaga na zmiany
//        socetControler.sendMessage(Message boardMessage, this.MyMove);
        MyMove="";
        //OCZEKUJ NA PRZECIWNIKA
        //String oponentMove = ...
//        this.oponentMove = oponentMove;
//        makeOponentMove();
    }

}

