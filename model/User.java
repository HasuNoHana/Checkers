package model;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class User {
    public User(String userName){
        this.userName = userName;
        BufferedImage img = null;
        try {
            File imageFile = new File(".\\graphics\\logo.png");
            img = ImageIO.read(imageFile);
        } catch (IOException exception) {
            // todo: obsluga exception
            exception.printStackTrace();
            System.exit(1);
        }
        Image dimg = img.getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH);
        this.userAvatar = new ImageIcon(dimg);
    }

    // Avatar
    private ImageIcon userAvatar;

    public void setUserAvatar(ImageIcon userAvatar) {
        this.userAvatar = userAvatar;
    }

    public ImageIcon getUserAvatar() {
        return this.userAvatar;
    }

    // Username
    private String userName;

    public void setName(String name){
        if(name.length()< Constants.UserConstants.MAX_USERNAME_LENGTH){
            this.userName = name;
        }
    }
    public String getName(){
        return this.userName;
    }
}
