package oldView;

import oldController.ViewStateHandler;
import oldModel.Constants;
import oldModel.Menu;
import oldModel.User;

import javax.swing.*;
import java.awt.*;

/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
public class SettingsFrame extends JFrame {
    private class SettingsButtons extends JPanel{
        private final JButton userSettingsButton;
        private final JButton checkersSettingsButton;
        private final JButton menuSettingsButton;
        private final JButton chatSettingsButton;
        private final JButton backButton;


        SettingsButtons(){
            setLayout(new GridLayout(5, 1, Constants.LayoutsConstants.H_GAP, Constants.LayoutsConstants.V_GAP));


            userSettingsButton = new JButton("User");
            userSettingsButton.addActionListener(e -> {
                CardLayout cl = (CardLayout) settingsPanel.getLayout();
                cl.show(settingsPanel, "user");
            });
            add(userSettingsButton);

            checkersSettingsButton = new JButton("Checkers");
            checkersSettingsButton.addActionListener(e -> {
                CardLayout cl = (CardLayout) settingsPanel.getLayout();
                cl.show(settingsPanel, "checkers");
            });
            add(checkersSettingsButton);

            menuSettingsButton = new JButton("Menu");
            menuSettingsButton.addActionListener(e -> {
                CardLayout cl = (CardLayout) settingsPanel.getLayout();
                cl.show(settingsPanel, "menu");
            });
            add(menuSettingsButton);

            chatSettingsButton = new JButton("Chat");
            chatSettingsButton.addActionListener(e -> {
                CardLayout cl = (CardLayout) settingsPanel.getLayout();
                cl.show(settingsPanel, "chat");
            });
            add(chatSettingsButton);

            backButton = new JButton("Back");
            backButton.addActionListener(ViewStateHandler.changeStateListener);
            backButton.setActionCommand("Menu");
            backButton.setIcon(new ImageIcon(this.getClass().getResource("../graphics/back.png")));
            add(backButton);
        }
    }

    private final JPanel settingsPanel;
    public SettingsFrame(){
        super("Settings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());

        add(new SettingsFrame.SettingsButtons());

        JPanel users = User.userPanel;

        JPanel checkers = new JPanel();
        checkers.setBackground(Color.RED);

        JPanel menu = Menu.menuLookPanel;

        JPanel chat = new JPanel();
        chat.setBackground(Color.BLUE);

        settingsPanel = new JPanel(new CardLayout());
        settingsPanel.add(users, "user");
        settingsPanel.add(checkers, "checkers");
        settingsPanel.add(menu, "menu");
        settingsPanel.add(chat, "chat");

        CardLayout cl = (CardLayout) settingsPanel.getLayout();
        cl.show(settingsPanel, "user");

        add(settingsPanel);
    }
}
