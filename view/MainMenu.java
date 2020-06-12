package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    private MenuPanel menuPanel;
    private ChatPanel chatPanel;
    public MainMenu( ChatPanel chatPanel ){
        super("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());

        menuPanel = new MenuPanel();
        add(menuPanel);

        this.chatPanel = chatPanel;
        add(chatPanel);
    }

    public MenuPanel getMenuPanel(){
        return menuPanel;
    }
    public ChatPanel getChatPanel(){
        return chatPanel;
    }
}
