package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InitialPopUpFrame extends JFrame {
    private final JButton nextButton;
    public InitialPopUpFrame(UserInfoChangePanel userInfoChangePanel){
        super("Welcome");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        add(userInfoChangePanel);

        nextButton = new JButton("Next");
        nextButton.setEnabled(false);

        add(nextButton);

    }
    public void addNextListener(ActionListener actionListener){
        this.nextButton.addActionListener(actionListener);
    }
    public JButton getNextButton(){
        return nextButton;
    }
}
