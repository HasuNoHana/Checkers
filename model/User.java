package model;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
public class User{
    private static class UserPanel extends JPanel{
        private final FlowLayout layout;
        private final JLabel nameLabel;
        private final JTextField nameField;
        private final JButton changeNameButton;

        public UserPanel(){
            layout = new FlowLayout();

            setLayout(this.layout);
            nameLabel = new JLabel("Type new username:");
            this.add(nameLabel);

            nameField = new JTextField(Constants.UserConstants.MAX_USERNAME_LENGTH);
            this.add(nameField);

            changeNameButton = new JButton("Change");
            changeNameButton.addActionListener(e -> {
                int len = nameField.getText().length();
                if(len>0 && len<Constants.UserConstants.MAX_USERNAME_LENGTH){
                    String text = nameField.getText();
                    User.me.setName(text);
                    nameField.setText("");
                }
            });
            this.add(changeNameButton);
        }
    }

    public static UserPanel userPanel = new UserPanel();

    private User(){
        this.userName = "";
        BufferedImage img = null;
        try {
            File imageFile = new File(".\\graphics\\logo.png");
            img = ImageIO.read(imageFile);
        } catch (IOException exception) {
            exception.printStackTrace();
            System.exit(1);
        }
        Image dimg = img.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
        this.userAvatar = new ImageIcon(dimg);
        this.userNameLabel = new JLabel(this.userName, SwingConstants.CENTER);
    }
    private String userName;

    public void setUserAvatar(ImageIcon userAvatar) {
        this.userAvatar = userAvatar;
    }

    private ImageIcon userAvatar;
    private final JLabel userNameLabel;

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
