package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    private MenuPanel menuPanel;
    private MenuChatPanel menuChatPanel;
    public MainMenuFrame(MenuChatPanel menuChatPanel){
        super("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());

        menuPanel = new MenuPanel();
        add(menuPanel);

        this.menuChatPanel = menuChatPanel;
        add(menuChatPanel);
    }

    public MenuPanel getMenuPanel(){
        return menuPanel;
    }
    public MenuChatPanel getMenuChatPanel(){
        return menuChatPanel;
    }
}
