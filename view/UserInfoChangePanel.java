package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UserInfoChangePanel extends JPanel {
    private final JTextField newNameField;
    private final JButton changeNameButton;
    private final JTextField infoField;

    public UserInfoChangePanel(){
        this.setLayout(new GridLayout(2,1));

        JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout());

        JLabel newNameLabel = new JLabel("Type new username:");
        upperPanel.add(newNameLabel);

        this.newNameField = new JTextField(Constants.UserConstants.MAX_USERNAME_LENGTH);
        upperPanel.add(this.newNameField);

        this.changeNameButton = new JButton("Change");
        upperPanel.add(this.changeNameButton);

        this.add(upperPanel);

        JPanel lowerPanel = new JPanel();
        lowerPanel.setLayout(new FlowLayout());

        JLabel infoLabel = new JLabel("Info:");
        lowerPanel.add(infoLabel);

        this.infoField = new JTextField(Constants.UserConstants.MAX_INFO_LENGTH);
        this.infoField.setEditable(false);
        lowerPanel.add(this.infoField);

        this.add(lowerPanel);
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
