package view;

import model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UsernameChange extends JPanel {
    private JLabel newNameLabel;
    private JLabel infoLabel;
    private JTextField newNameField;
    private JButton changeNameButton;
    private JTextField infoField;
    private JPanel upperPanel;
    private JPanel lowerPanel;
    public UsernameChange(){
        this.setLayout(new GridLayout(2,1));

        this.upperPanel = new JPanel();
        this.upperPanel.setLayout(new FlowLayout());

        this.newNameLabel = new JLabel("Type new username:");
        this.upperPanel.add(this.newNameLabel);

        this.newNameField = new JTextField(Constants.UserConstants.MAX_USERNAME_LENGTH);
        this.upperPanel.add(this.newNameField);

        this.changeNameButton = new JButton("Change");
        this.upperPanel.add(this.changeNameButton);

        this.add(this.upperPanel);

        this.lowerPanel = new JPanel();
        this.lowerPanel.setLayout(new FlowLayout());

        this.infoLabel = new JLabel("Info:");
        this.lowerPanel.add(this.infoLabel);

        this.infoField = new JTextField(Constants.UserConstants.MAX_INFO_LENGTH);
        this.infoField.setEditable(false);
        this.lowerPanel.add(this.infoField);

        this.add(this.lowerPanel);
    }

    public void addButtonListener(ActionListener actionListener){
        this.changeNameButton.addActionListener(actionListener);
    }

    public JTextField getNameField(){
        return this.newNameField;
    }

    public void setInfo(String newInfo){
        this.infoField.setText(newInfo);
    }

}
