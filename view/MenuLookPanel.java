package view;

import model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuLookPanel extends JPanel {
    private ArrayList<JButton> buttons;
    protected MenuLookPanel(){
        this.setLayout(new GridLayout(10,1, Constants.LayoutsConstants.H_GAP, Constants.LayoutsConstants.V_GAP));
        buttons = new ArrayList<JButton>();
    }
    public void addButton(UIManager.LookAndFeelInfo lookAndFeelInfo, ActionListener actionListener){
        JButton button = new JButton(lookAndFeelInfo.getName());
        button.addActionListener(actionListener);
        this.add(button);
        buttons.add(button);
        this.setLayout(new GridLayout(buttons.size(),1, Constants.LayoutsConstants.H_GAP, Constants.LayoutsConstants.V_GAP));
    }
}