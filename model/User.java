package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
public class User{
    private static class UserPanel extends JPanel{
        private FlowLayout layout;
        private JLabel nameLabel;
        private JTextField nameField;
        private JButton changeNameButton;

        public UserPanel(){
            layout = new FlowLayout();

            setLayout(this.layout);
            nameLabel = new JLabel("Type new username:");
            this.add(nameLabel);

            nameField = new JTextField(Constants.UserConstants.MAX_USERNAME_LENGTH);
            this.add(nameField);

            changeNameButton = new JButton("Change");
            changeNameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int len = nameField.getText().length();
                    if(len>0 && len<Constants.UserConstants.MAX_USERNAME_LENGTH){
                        String text = nameField.getText();
                        User.me.setName(text);
                        nameField.setText("");
                    }
                }
            });
            this.add(changeNameButton);
        }
    }

    public static UserPanel userPanel = new UserPanel();

    private User(){
        this.userName = "";
        this.port = 0;
        this.userNameLabel = new JLabel(this.userName, SwingConstants.CENTER);
    }
    private String userName;
    private int port;
    private JLabel userNameLabel;

    public void setName(String name){
        if(name.length()<Constants.UserConstants.MAX_USERNAME_LENGTH){
            this.userName = name;
            this.userNameLabel.setText("Player: " + this.userName);
        }
    }
    public JLabel getNameLabel(){
        return this.userNameLabel;
    }
    public static User me = new User();
    public static User enemy = new User();
}
