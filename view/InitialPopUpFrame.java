package view;

import controller.ViewStateHandler;
import model.Constants;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialPopUpFrame extends JFrame {
    private final JButton nextButton;
    private final JPanel upperPanel;
    private final JPanel lowerPanel;
    private final JLabel infoLabel;
    public InitialPopUpFrame(){
        super("Welcome");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(2,1));

        upperPanel = new JPanel();
        upperPanel.setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Type new username:");
        upperPanel.add(nameLabel);

        JTextField nameField = new JTextField(Constants.UserConstants.MAX_USERNAME_LENGTH);
        upperPanel.add(nameField);

        JButton changeNameButton = new JButton("Change");
        changeNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int len = nameField.getText().length();
                if(len>0 && len<Constants.UserConstants.MAX_USERNAME_LENGTH){
                    String text = nameField.getText();
                    User.me.setName(text);
                    infoLabel.setText("Name changed to: "+text+".");
                    nameField.setText("");
                    nextButton.setEnabled(true);
                }else{
                    infoLabel.setText("Name length must be between 1 and " + Constants.UserConstants.MAX_USERNAME_LENGTH +".");
                }
            }
        });
        upperPanel.add(changeNameButton);

        nextButton = new JButton("Next");
        nextButton.addActionListener(ViewStateHandler.changeStateListener);
        nextButton.setActionCommand("Menu");
        nextButton.setEnabled(false);
        upperPanel.add(nextButton);

        add(upperPanel);

        lowerPanel = new JPanel();

        infoLabel = new JLabel("", SwingConstants.CENTER);

        lowerPanel.add(infoLabel);

        this.add(lowerPanel);

    }
}
