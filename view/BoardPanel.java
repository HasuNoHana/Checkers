package view;

import model.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BoardPanel extends JPanel {
    private ArrayList<ArrayList<JLabel>> piecePanels;
    private BufferedImage img = null;

    public BoardPanel(){
        setLayout(new GridLayout(Constants.GameConstants.BOARD_SIZE, Constants.GameConstants.BOARD_SIZE));

        try {
            File imageFile = new File(".\\graphics\\EmptyImage.png");
            img = ImageIO.read(imageFile);
        } catch (IOException exception) {
            System.err.println("INFO: Could not open EmptyImage.");
            System.exit(1);
        }

        this.piecePanels = new ArrayList<>();
        for( int i = 0; i<Constants.GameConstants.BOARD_SIZE; ++i ){
            this.piecePanels.add(new ArrayList<>());
            for( int j = 0; j<Constants.GameConstants.BOARD_SIZE; ++j ){
                JLabel label = new JLabel(i+" "+j);
                label.addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        super.componentResized(e);
                        if( label.getWidth()>0 && label.getHeight()>0 ){
                            Image dimg = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
                            ImageIcon imageIcon = new ImageIcon(dimg);
                            label.setIcon(imageIcon);
                        }
                    }
                });
                this.piecePanels.get(i).add(label);
                this.add(label);
            }
        }
    }
}
