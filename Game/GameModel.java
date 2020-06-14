package Game;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameModel {
    private Field[][] board;
    private final int sideSize = 8;//how many squares are on board
    private AtomicBoolean MyTurn;
    private String oponentMove;
    private String MyMove = "";
    private int MyVictories = 0;

    public void setBoard(Field[][] board) {
        this.board = board;
    }

    public void setMyTurn(AtomicBoolean myTurn) {
        MyTurn = myTurn;
    }

    public void setOponentMove(String oponentMove) {
        this.oponentMove = oponentMove;
    }

    public void setMyVictories(int myVictories) {
        MyVictories = myVictories;
    }

    public void setDuringCombo(boolean duringCombo) {
        this.duringCombo = duringCombo;
    }

    public void setFieldInCombo(Field fieldInCombo) {
        FieldInCombo = fieldInCombo;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public void setGameFinished(boolean gameFinished) {
        isGameFinished = gameFinished;
    }

    public void setPlayerWhitePoints(int playerWhitePoints) {
        this.playerWhitePoints = playerWhitePoints;
    }

    public void setPlayerBrownPoints(int playerBrownPoints) {
        this.playerBrownPoints = playerBrownPoints;
    }

    public void setOponent(Field oponent) {
        this.oponent = oponent;
    }

    private boolean duringCombo;

    public Field[][] getBoard() {
        return board;
    }

    public AtomicBoolean getMyTurn() {
        return MyTurn;
    }

    public String getOponentMove() {
        return oponentMove;
    }

    public String getMyMove() {
        return MyMove;
    }

    public int getMyVictories() {
        return MyVictories;
    }

    public boolean isDuringCombo() {
        return duringCombo;
    }

    public Field getFieldInCombo() {
        return FieldInCombo;
    }

    public int getPlayerWhitePoints() {
        return playerWhitePoints;
    }

    public int getPlayerBrownPoints() {
        return playerBrownPoints;
    }

    public Field getOponent() {
        return oponent;
    }

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
    private Field oponent;

    public GameModel() {
        MyTurn = new AtomicBoolean(false);
        board = new Field[sideSize][];
        for (int i = 0; i < sideSize; i++) {
            board[i] = new Field[sideSize];
            for (int j = 0; j < sideSize; j++) {
                board[i][j] = createField(i, j);
            }
        }
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

    public int getSideSize() {
        return sideSize;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void setFieldColor(Field f, int i, int j) {
        if (i % 2 == 0)
            if (j % 2 == 1)
                f.setColor(Color.DARK_GRAY);
            else
                f.setColor(Color.WHITE);
        else if (j % 2 == 0)
            f.setColor(Color.DARK_GRAY);
        else
            f.setColor(Color.WHITE);
    }

    private void setFieldPawn(Field f, int i, int j) {
        if (f.getColor() == Color.DARK_GRAY) {
            if (i < 2)
                f.setPawn(Pawn.BROWNNORMAL);
            if (i > sideSize - 3)
                f.setPawn(Pawn.WHITENORMAL);
        }
    }

    public Field createField(int i, int j) {
        Field f = new Field(i, j);
        setFieldColor(f, i, j);
        setFieldPawn(f, i, j);
        return f;
    }


    public Field getField(int i, int j) {
        return board[i][j];
    }
    

    private boolean isEndField(Field newField) {
        return ((newField.isWhite() && newField.getRow() == 0) ||
                (newField.isBrown() && newField.getRow() == sideSize - 1));
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

    public void ResetBoard(){
        if(noMoreOpositePawns()) {
            endGame();
            if(didIWon())
                MyVictories++;
        }
        board = new Field[sideSize][];
        for (int i = 0; i < sideSize; i++) {
            board[i] = new Field[sideSize];
            for (int j = 0; j < sideSize; j++) {
                board[i][j] = createField(i, j);
            }
        }
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

    public boolean isNormalMoveAvaliable(Field oldField, Field newField) {
        return isFieldBlack(newField) &&
                isCurrentPlayerPawn(oldField) &&
                isFieldEmpty(newField) &&
                isMoveDiagonally(oldField, newField) &&
                isNumerOfJumpedFieldsValid(oldField, newField);
    }

    private boolean isFieldBlack(Field newField) {
        return newField.getColor() == Color.DARK_GRAY;
    }

    private boolean isCurrentPlayerPawn(Field oldField) {
        return (currentPlayer == Player.WHITE && (oldField.getPawn() == Pawn.WHITENORMAL || oldField.getPawn() == Pawn.WHITEQUIEEN)) ||
                (currentPlayer == Player.BROWN && (oldField.getPawn() == Pawn.BROWNNORMAL || oldField.getPawn() == Pawn.BROWNQUIEEN));
    }

    private boolean isFieldEmpty(Field newField) {
        return newField.getPawn() == Pawn.EMPTY;
    }

    private boolean isMoveDiagonally(Field oldField, Field newField) {
        return (Math.abs(oldField.getRow() - newField.getRow()) == Math.abs(oldField.getCol() - newField.getCol()));
    }

    private boolean isNumerOfJumpedFieldsValid(Field oldField, Field newField) {
        if (oldField.isQueen()) {
            return isPathClear(oldField, newField);
        } else { // normal pawn
            return isMoveForward(oldField, newField) && (isDistanceOne(oldField, newField));
        }
    }

    private boolean isQueenCaptureVallid(Field oldField, Field newField) {
        if(oldField.equals(newField)) return false;
        int differenceRow = oldField.getRow() < newField.getRow() ? 1 : -1;
        int differenceCol = oldField.getCol() < newField.getCol() ? 1 : -1;
        int oponentRow = newField.getRow() - differenceRow;
        int oponentCol = newField.getCol() - differenceCol;
        if ( (oponentRow-differenceRow > sideSize-1) && (oponentCol-differenceCol > sideSize-1) &&(!isPathClear(oldField, board[oponentRow - differenceRow][oponentCol - differenceCol])))
            return false;
        if (isNotOponent(oponentRow, oponentCol))
            return false;
        return true;
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
            if (board[currRow][currCol].isOccupied())
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

    private boolean isNormalCaptureVallid(Field oldField, Field newField) {
        int differenceRow = oldField.getRow() < newField.getRow() ? 1 : -1;
        int differenceCol = oldField.getCol() < newField.getCol() ? 1 : -1;
        int oponentRow = oldField.getRow() + differenceRow;
        int oponentCol = oldField.getCol() + differenceCol;

        if (isNotOponent(oponentRow, oponentCol))
            return false;
        if (oponentRow + differenceRow != newField.getRow() || oponentCol + differenceCol != newField.getCol())
            return false;
        this.isCapture = true;
        this.oponent = board[oponentRow][oponentCol];
        return true;
    }

    public void destroyOponentsPawn() {
        this.oponent.setPawn(Pawn.EMPTY);
        this.oponent.setOccupied(false);
        if (this.currentPlayer == Player.WHITE)
            this.playerWhitePoints += 1;
        else
            this.playerBrownPoints += 1;
    }

    private boolean isNotOponent(int oponentRow, int oponentCol) {
        if (currentPlayer == Player.WHITE) {
            if (board[oponentRow][oponentCol].getPawn() == Pawn.BROWNQUIEEN || board[oponentRow][oponentCol].getPawn() == Pawn.BROWNNORMAL)
                return false;
        } else {
            if (board[oponentRow][oponentCol].getPawn() == Pawn.WHITEQUIEEN || board[oponentRow][oponentCol].getPawn() == Pawn.WHITENORMAL)
                return false;
        }
        return true;
    }

    public void tryMakeQueen(Field newField) {
        if (isEndField(newField)) {
            createQueen(newField);
        }
    }

    private void createQueen(Field newField) {
        if (newField.isWhite())
            newField.setPawn(Pawn.WHITEQUIEEN);
        else if (newField.isBrown())
            newField.setPawn(Pawn.BROWNQUIEEN);
    }

    private boolean noMoreOpositePawns() {
        int white = countPawnsofPlayer(Player.WHITE);
        int brown = countPawnsofPlayer(Player.BROWN);
        if(white==0||brown==0)
            return true;
        return false;
    }

    private int countPawnsofPlayer(Player player) {
        int result = 0;
        for(int i=0; i<sideSize;i++){
            for(int j=0; j<sideSize;j++){
                if(board[i][j].isWhite()&&player==Player.WHITE)
                    result++;
                else if(board[i][j].isBrown()&&player==Player.BROWN)
                    result++;
            }
        }
        return result;
    }

    private void findOponent(Field oldField, Field newField) {
        int differenceRow = oldField.getRow() < newField.getRow() ? 1 : -1;
        int differenceCol = oldField.getCol() < newField.getCol() ? 1 : -1;
        int oponentRow = newField.getRow() - differenceRow;
        int oponentCol = newField.getCol() - differenceCol;
        this.isCapture = true;
        this.oponent = board[oponentRow][oponentCol];
    }

    private Set<Field> getAvaliableNormalMoves(Field oldField) {
        Set<Field> avaliableNormalMoves = new HashSet<Field>();

        if(this.duringCombo){
            return avaliableNormalMoves;
        }

        for (Field[] fields : board
        ) {
            for (Field field : fields
            ) {
                if (isNormalMoveAvaliable(oldField, field)) {
                    avaliableNormalMoves.add(field);
                }
            }
        }
        return avaliableNormalMoves;
    }

    public Set<Field> getAvaliableMoves(Field field){
        Set<Field> avaliableMoves = getAvaliableCapturesMoves(field);
        avaliableMoves.addAll(getAvaliableNormalMoves(field));
        return avaliableMoves;
    }

    private Set<Field> getAvaliableCapturesMoves(Field oldField) {
        Set<Field> avaliableCaptureMoves = new HashSet<Field>();
        if (this.duringCombo) {
            if (!oldField.equals(this.FieldInCombo)) {
                return avaliableCaptureMoves;
            }
        }
        for (Field[] fields : board
        ) {
            for (Field field : fields
            ) {
                if (isCaptureMoveAvliable(oldField, field)) {
                    avaliableCaptureMoves.add(field);
                }
            }
        }
        return avaliableCaptureMoves;
    }

    private boolean isCaptureMoveAvliable(Field oldField, Field newField) {
        return isFieldBlack(newField) &&
                isCurrentPlayerPawn(oldField) &&
                isFieldEmpty(newField) &&
                isMoveDiagonally(oldField, newField) &&
                isCaptureValid(oldField, newField);

    }

    private boolean isCaptureValid(Field oldField, Field newField) {
        if (oldField.isQueen()) {
            return isQueenCaptureVallid(oldField, newField);
        } else { // normal pawn
            return isNormalCaptureVallid(oldField, newField) && isMoveForward(oldField, newField);
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
        if(this.MyMove == "")
            MyMove = (String)(oldField.getRow()+","+oldField.getCol()+";"+newField.getRow()+","+newField.getCol());
        else if (this.MyMove != "")
            MyMove += (String)(";"+newField.getRow()+","+newField.getCol());
    }


    public void makeOponentMove(){
        String[] parts = this.oponentMove.split(";");
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
        return (getAvaliableCapturesMoves(newField).size() > 0);
    }

    public boolean makeMove(Field oldField, Field newField) {
        if (getAvaliableCapturesMoves(oldField).contains(newField)) {
            findOponent(oldField,newField);
            makeCapture(oldField, newField);
            this.duringCombo = isDuringCombo(newField);
            if (this.duringCombo) {
                this.FieldInCombo = newField;
            } else {
                changePlayer();
            }
        } else if (getAvaliableNormalMoves(oldField).contains(newField)) {
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
