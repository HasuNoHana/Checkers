package view;

import controller.ViewStateHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings extends JFrame {
    private class SettingsButtons extends JPanel{
        private JButton userSettingsButton;
        private JButton checkersSettingsButton;
        private JButton menuSettingsButton;
        private JButton chatSettingsButton;
        private JButton backButton;


        SettingsButtons(){
            setLayout(new GridLayout(5, 1, 20, 20));


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
    public Settings(){
        super("Settings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());

        add(new Settings.SettingsButtons());

        JPanel users = new JPanel();
        users.setBackground(Color.GREEN);

        JPanel checkers = new JPanel();
        checkers.setBackground(Color.RED);

        JPanel menu = new JPanel();
        menu.setBackground(Color.YELLOW);

        JPanel chat = new JPanel();
        chat.setBackground(Color.BLUE);

        settingsPanel = new JPanel(new CardLayout());
        settingsPanel.add(users, "user");
        settingsPanel.add(checkers, "checkers");
        settingsPanel.add(menu, "menu");
        settingsPanel.add(chat, "chat");

        add(settingsPanel);
    }
}
