package model;

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
        occupied = pawn != Pawn.EMPTY;
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
    return (this.pawn==Pawn.BROWN_QUEEN || this.pawn == Pawn.WHITE_QUEEN);
    }

    public boolean isWhite() {
        return (this.pawn==Pawn.WHITE_NORMAL || this.pawn == Pawn.WHITE_QUEEN);
    }

    public boolean isBrown() {
        return (this.pawn==Pawn.BROWN_NORMAL || this.pawn == Pawn.BROWN_QUEEN);
    }
}
