package checkers;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private final JPanel gamePanel;
    public PicturePanel[][] fieldsPanels;
    private int BOARDSIZE = 8;

    public GameView() {
        super("Checkers");
        this.setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(BOARDSIZE,BOARDSIZE));
        this.fieldsPanels = new PicturePanel[BOARDSIZE][BOARDSIZE];
        for(int i=0; i<BOARDSIZE;i++){
            for(int j=0; j<BOARDSIZE;j++){
                PicturePanel field = new PicturePanel(i,j);
                gamePanel.add(field);
                fieldsPanels[i][j] = field;
            }
        }
        add(gamePanel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void updateView(GameModel model) {
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                PicturePanel field = new PicturePanel(i,j);
                fieldsPanels[i][j].setField(model.getField(i,j));
            }
        }
        gamePanel.repaint();
    }

    public PicturePanel getPicturePanel(int i,int j){
        return this.fieldsPanels[i][j];
    }
}