package checkers;

import java.awt.*;

public class Field {
    private boolean occupied = false;
    private Pawn pawn = Pawn.EMPTY;
    private Color color;
    private final int col;
    private final int row;

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Pawn getPawn() {
        return pawn;
    }

    public Color getColor() {
        return color;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void setPawn(Pawn pawn) {
        this.pawn = pawn;
        if (pawn == Pawn.EMPTY)
            occupied = false;
        else
            occupied = true;
    }

    public Field(int row, int col) {
        this.col = col;
        this.row = row;
    }

    public Field(int row, int col, Color c){
        this.col = col;
        this.row = row;
        color = c;
    }

    @Override
    public String toString() {
        return pawn + " row "+ row + " col "+col;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isQueen() {
    return (this.pawn==Pawn.BROWNQUIEEN || this.pawn == Pawn.WHITEQUIEEN);
    }

    public boolean isWhite() {
        return (this.pawn==Pawn.WHITENORMAL || this.pawn == Pawn.WHITEQUIEEN);
    }

    public boolean isBrown() {
        return (this.pawn==Pawn.BROWNNORMAL || this.pawn == Pawn.BROWNQUIEEN);
    }
}
