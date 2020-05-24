package view;

import controller.ViewStateHandler;
import model.Constants;
import model.Menu;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
public class SettingsFrame extends JFrame {
    private class SettingsButtons extends JPanel{
        private JButton userSettingsButton;
        private JButton checkersSettingsButton;
        private JButton menuSettingsButton;
        private JButton chatSettingsButton;
        private JButton backButton;


        SettingsButtons(){
            setLayout(new GridLayout(5, 1, Constants.LayoutsConstants.H_GAP, Constants.LayoutsConstants.V_GAP));


            userSettingsButton = new JButton("User");
            userSettingsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CardLayout cl = (CardLayout) settingsPanel.getLayout();
                    cl.show(settingsPanel, "user");
                }
            });
            add(userSettingsButton);

            checkersSettingsButton = new JButton("Checkers");
            checkersSettingsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CardLayout cl = (CardLayout) settingsPanel.getLayout();
                    cl.show(settingsPanel, "checkers");
                }
            });
            add(checkersSettingsButton);

            menuSettingsButton = new JButton("Menu");
            menuSettingsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CardLayout cl = (CardLayout) settingsPanel.getLayout();
                    cl.show(settingsPanel, "menu");
                }
            });
            add(menuSettingsButton);

            chatSettingsButton = new JButton("Chat");
            chatSettingsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CardLayout cl = (CardLayout) settingsPanel.getLayout();
                    cl.show(settingsPanel, "chat");
                }
            });
            add(chatSettingsButton);

            backButton = new JButton("Back");
            backButton.addActionListener(ViewStateHandler.changeStateListener);
            backButton.setActionCommand("Menu");
            backButton.setIcon(new ImageIcon(this.getClass().getResource("../graphics/back.png")));
            add(backButton);
        }
    }

    private JPanel settingsPanel;
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
