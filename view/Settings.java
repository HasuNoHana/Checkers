package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Settings extends JFrame{
    private final JButton userSettingsButton;
    private final JButton checkersSettingsButton;
    private final JButton menuSettingsButton;
    private final JButton chatSettingsButton;
    private final JButton backButton;

    private final JPanel buttonsPanel;
    private final JPanel settingsPanel;
    private final MenuLookPanel menu;
    private final UsernameChange users;
    private final JPanel checkers;
    private final JPanel chat;

    public Settings( UsernameChange usernameChange ){
        super("Settings");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());

        settingsPanel = new JPanel(new CardLayout());

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(5, 1, Constants.LayoutsConstants.H_GAP, Constants.LayoutsConstants.V_GAP));


        userSettingsButton = new JButton("User");
        userSettingsButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) settingsPanel.getLayout();
            cl.show(settingsPanel, "user");
        });
        buttonsPanel.add(userSettingsButton);

        checkersSettingsButton = new JButton("Checkers");
        checkersSettingsButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) settingsPanel.getLayout();
            cl.show(settingsPanel, "checkers");
        });
        buttonsPanel.add(checkersSettingsButton);

        menuSettingsButton = new JButton("Menu");
        menuSettingsButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) settingsPanel.getLayout();
            cl.show(settingsPanel, "menu");
        });
        buttonsPanel.add(menuSettingsButton);

        chatSettingsButton = new JButton("Chat");
        chatSettingsButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) settingsPanel.getLayout();
            cl.show(settingsPanel, "chat");
        });
        buttonsPanel.add(chatSettingsButton);

        backButton = new JButton("Back");
        backButton.setActionCommand("Menu");
        backButton.setIcon(new ImageIcon(this.getClass().getResource("../graphics/back.png")));
        buttonsPanel.add(backButton);

        add(buttonsPanel);

        users = usernameChange;

        checkers = new JPanel();
        checkers.setBackground(Color.RED);

        menu = new MenuLookPanel();

        chat = new JPanel();
        chat.setBackground(Color.BLUE);

        settingsPanel.add(users, "user");
        settingsPanel.add(checkers, "checkers");
        settingsPanel.add(menu, "menu");
        settingsPanel.add(chat, "chat");

        CardLayout cl = (CardLayout) settingsPanel.getLayout();
        cl.show(settingsPanel, "user");

        add(settingsPanel);
    }

    public MenuLookPanel getMenuLookPanel(){
        return this.menu;
    }
    public void addBackListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }
    public JButton getBackButton(){
        return this.backButton;
    }
}
