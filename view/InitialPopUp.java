package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class InitialPopUp extends JFrame {
    private final JButton nextButton;
    private final UsernameChange usernameChange;
    public InitialPopUp(UsernameChange usernameChange){
        super("Welcome");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        this.usernameChange = usernameChange;

        add(usernameChange);

        nextButton = new JButton("Next");
        nextButton.setEnabled(false);

        add(nextButton);

    }
    public UsernameChange getUsernameChange(){
        return this.usernameChange;
    }
    public void addNextListener(ActionListener actionListener){
        this.nextButton.addActionListener(actionListener);
    }
    public JButton getNextButton(){
        return nextButton;
    }
}
