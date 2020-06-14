package view;

import model.Constants;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    public BoardPanel(){
        setLayout(new GridLayout(Constants.GameConstants.BOARD_SIZE, Constants.GameConstants.BOARD_SIZE));
    }
}
