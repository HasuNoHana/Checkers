package view;

import model.ImageRepository;
import model.CheckersModel;

import javax.swing.*;
import java.awt.*;

public class CheckersView extends JPanel {
    public PicturePanel[][] fieldsPanels;
    private final int BOARDSIZE = 8;

    public CheckersView(ImageRepository imageRepository) {
        setLayout(new GridLayout(BOARDSIZE,BOARDSIZE));
        this.fieldsPanels = new PicturePanel[BOARDSIZE][BOARDSIZE];
        for(int i=0; i<BOARDSIZE;i++){
            for(int j=0; j<BOARDSIZE;j++){
                PicturePanel field = new PicturePanel(i,j, imageRepository);
                add(field);
                fieldsPanels[i][j] = field;
            }
        }
    }

    public void updateView(CheckersModel model) {
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                fieldsPanels[i][j].setField(model.getField(i,j));
            }
        }
        this.repaint();
    }

    public void changeImageRepository( ImageRepository imageRepository ){
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                fieldsPanels[i][j].setImageRepository(imageRepository);
            }
        }
    }

    public PicturePanel getPicturePanel(int i,int j){
        return this.fieldsPanels[i][j];
    }
}