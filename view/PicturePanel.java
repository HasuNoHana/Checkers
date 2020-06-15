package view;

import model.Field;
import model.ImageRepository;
import model.Pawn;

import javax.swing.*;
import java.awt.*;

public class PicturePanel extends JPanel {
    private final int row;
    private final int col;
    private ImageRepository imageRepository;
    Color background;
    private Pawn pawn;
    private double pawnScale = 0.85;


    public PicturePanel(int row, int col, ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
        this.row =row;
        this.col = col;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = imageRepository.getPawnImage(pawn);
        int width = (int) ( this.getWidth() * this.pawnScale);
        int height = (int) (this.getHeight() * this.pawnScale);
        image = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        int x = (this.getWidth() - image.getWidth(null)) / 2;
        int y = (this.getHeight() - image.getHeight(null)) / 2;
        g.drawImage(image, x, y,this);
    }

    public void setField(Field field) {
        this.background = field.getColor();
        this.setBackground(background);
        this.pawn = field.getPawn();
    }

    public void setImageRepository( ImageRepository imageRepository ){
        this.imageRepository = imageRepository;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return col;
    }

    public void click() {
        this.setBackground(new Color(0x7bc8f6));
    }

    public void unclick() {
        this.setBackground(background);
    }
}