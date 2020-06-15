package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
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

public class PawnsColorPanel extends JPanel {
    public ArrayList<JButton> buttons;
    public PawnsColorPanel(){
        setLayout(new GridLayout(Constants.GameConstants.PAWN_COLORS.length/2, 2));

        buttons = new ArrayList<>();

        for( String color : Constants.GameConstants.PAWN_COLORS ){
            JButton button = new JButton();
            button.setActionCommand(color);
            button.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                    super.componentResized(e);
                    BufferedImage img = null;
                    try {
                        File imageFile = new File(".\\graphics\\"+color.substring(0, 1).toUpperCase()+color.substring(1, color.length()).toLowerCase()+"\\Normal.png");
                        img = ImageIO.read(imageFile);
                    } catch (IOException exception) {
                        System.exit(1);
                    }
                    if( button.getWidth()>0 && button.getHeight()>0 ){
                        int len = Math.min(button.getHeight(), button.getWidth());
                        Image dimg = img.getScaledInstance(len, len, Image.SCALE_SMOOTH);
                        ImageIcon imageIcon = new ImageIcon(dimg);
                        button.setIcon(imageIcon);
                    }
                }
            });
            buttons.add(button);
            this.add(button);
        }
    }

    public ArrayList<JButton> getButtons(){ return buttons; }
}
