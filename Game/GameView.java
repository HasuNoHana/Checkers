package Game;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    public PicturePanel[][] fieldsPanels;
    private final int BOARDSIZE = 8;

    public GameView(ImageRepository imageRepository) {
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

    public void updateView(GameModel model) {
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
//                PicturePanel field = new PicturePanel(i,j);
                fieldsPanels[i][j].setField(model.getField(i,j));
            }
        }
        this.repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        // Relies on being the only component
        // in a layout that will center it without
        // expanding it to fill all the space.
        Dimension d = this.getParent().getSize();
//        int newSize = d.width > d.height ? d.height : d.width;
//        newSize = newSize == 0 ? 100 : newSize;
        return new Dimension((int)d.getWidth(), (int)d.getHeight());
    }

    public PicturePanel getPicturePanel(int i,int j){
        return this.fieldsPanels[i][j];
    }
}