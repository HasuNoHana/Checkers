package Game;

import model.Constants;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CheckersModel {

    public boolean isEnemyFirst() {
        return isEnemyFirst;
    }

    public void setEnemyFirst(boolean enemyFirst) {
        isEnemyFirst = enemyFirst;
    }

    private boolean isEnemyFirst = false;

    public boolean isThreadRunning() {
        return isThreadRunning;
    }

    public void setThreadRunning(boolean threadRunning) {
        isThreadRunning = threadRunning;
    }

    private boolean isThreadRunning = true;

    public String getMyMove() {
        return MyMove;
    }

    public void setMyMove(String myMove) {
        MyMove = myMove;
    }

    private String MyMove = "";

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public boolean isCapture() {
        return isCapture;
    }

    public void setCapture(boolean capture) {
        isCapture = capture;
    }

    public Field getOpponent() {
        return opponent;
    }

    public void setOpponent(Field opponent) {
        this.opponent = opponent;
    }

    private Player currentPlayer = Player.WHITE;
    private boolean isCapture = false;
    private Field opponent;

    public AtomicBoolean isMyTurn;

    public void setOpponentMove(String opponentMove){
        this.opponentMove = opponentMove;
    }

    public String getOpponentMove() {
        return opponentMove;
    }

    private String opponentMove = "";

    private Field[][] board;

    public String getYourColor() {
        return yourColor;
    }

    public void setYourColor(String yourColor) {
        this.yourColor = yourColor;
    }

    public String getEnemyColor() {
        return enemyColor;
    }

    public void setEnemyColor(String enemyColor) {
        this.enemyColor = enemyColor;
    }

    private String yourColor = Constants.GameConstants.PAWN_COLORS[0];
    private String enemyColor = Constants.GameConstants.PAWN_COLORS[1];

    public CheckersModel() {
        isMyTurn = new AtomicBoolean(false);
        board = new Field[Constants.GameConstants.BOARD_SIZE][];
        for (int i = 0; i < Constants.GameConstants.BOARD_SIZE; i++) {
            board[i] = new Field[Constants.GameConstants.BOARD_SIZE];
            for (int j = 0; j < Constants.GameConstants.BOARD_SIZE; j++) {
                board[i][j] = createField(i, j);
            }
        }
    }
    public Field createField(int i, int j) {
        Field f = new Field(i, j);
        setFieldColor(f, i, j);
        setFieldPawn(f, i, j);
        return f;
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
                f.setPawn(Pawn.BROWN_NORMAL);
            if (i > Constants.GameConstants.BOARD_SIZE - 3)
                f.setPawn(Pawn.WHITE_NORMAL);
        }
    }

    public void resetBoard(){
        board = new Field[Constants.GameConstants.BOARD_SIZE][];
        for (int i = 0; i < Constants.GameConstants.BOARD_SIZE; i++) {
            board[i] = new Field[Constants.GameConstants.BOARD_SIZE];
            for (int j = 0; j < Constants.GameConstants.BOARD_SIZE; j++) {
                board[i][j] = createField(i, j);
            }
        }
        this.MyMove = "";
        this.opponentMove = "";
        this.isCapture = false;
    }

    public Field[][] getBoard() {
        return board;
    }

    public Field getField(int i, int j) {
        return board[i][j];
    }
}
