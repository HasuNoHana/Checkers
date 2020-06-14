package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class SettingsFrame extends JFrame{
    private final JButton userSettingsButton;
    private final JButton checkersSettingsButton;
    private final JButton menuSettingsButton;
    private final JButton chatSettingsButton;
    private final JButton backButton;

    private final JPanel buttonsPanel;
    private final JPanel settingsPanel;
    private final MenuLookPanel menu;
    private final UserInfoChangePanel users;
    private final JPanel board;
    private final ChatColorPanel chat;

    public SettingsFrame(UserInfoChangePanel userInfoChangePanel){
        super("SettingsFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1.0;

        settingsPanel = new JPanel(new CardLayout());

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraintsInner = new GridBagConstraints();
        constraintsInner.fill = GridBagConstraints.BOTH;
        constraintsInner.weighty = 0.9;
        constraintsInner.weightx = 0.9;
        constraintsInner.insets = new Insets(5,5,5,5);
        constraintsInner.gridx = 0;


        userSettingsButton = new JButton("User");
        userSettingsButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) settingsPanel.getLayout();
            cl.show(settingsPanel, "user");
        });
        userSettingsButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                userSettingsButton.setFont(new Font(userSettingsButton.getName(), Font.PLAIN, userSettingsButton.getHeight()*3/8));
            }
        });
        constraintsInner.gridy = 0;
        buttonsPanel.add(userSettingsButton, constraintsInner);

        checkersSettingsButton = new JButton("Board");
        checkersSettingsButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) settingsPanel.getLayout();
            cl.show(settingsPanel, "board");
        });
        checkersSettingsButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                checkersSettingsButton.setFont(new Font(checkersSettingsButton.getName(), Font.PLAIN, checkersSettingsButton.getHeight()*3/8));
            }
        });
        constraintsInner.gridy = 1;
        buttonsPanel.add(checkersSettingsButton, constraintsInner);

        menuSettingsButton = new JButton("Menu");
        menuSettingsButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) settingsPanel.getLayout();
            cl.show(settingsPanel, "menu");
        });
        menuSettingsButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                menuSettingsButton.setFont(new Font(menuSettingsButton.getName(), Font.PLAIN, menuSettingsButton.getHeight()*3/8));
            }
        });
        constraintsInner.gridy = 2;
        buttonsPanel.add(menuSettingsButton, constraintsInner);

        chatSettingsButton = new JButton("Chat");
        chatSettingsButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) settingsPanel.getLayout();
            cl.show(settingsPanel, "chat");
        });
        chatSettingsButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                chatSettingsButton.setFont(new Font(chatSettingsButton.getName(), Font.PLAIN, chatSettingsButton.getHeight()*3/8));
            }
        });
        constraintsInner.gridy = 3;
        buttonsPanel.add(chatSettingsButton, constraintsInner);

        backButton = new JButton("Back");
        backButton.setActionCommand("Menu");
        backButton.setIcon(new ImageIcon(this.getClass().getResource("../graphics/back.png")));
        backButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                backButton.setFont(new Font(backButton.getName(), Font.PLAIN, backButton.getHeight()*3/8));
            }
        });
        constraintsInner.gridy = 4;
        buttonsPanel.add(backButton, constraintsInner);


        constraints.weightx = 0.2;
        constraints.gridx = 0;
        add(buttonsPanel, constraints);

        JPanel usersPanel = new JPanel(new FlowLayout());
        users = userInfoChangePanel;
        usersPanel.add(users, SwingConstants.CENTER);

        board = new JPanel();
        board.setBackground(Color.RED);

        menu = new MenuLookPanel();

        chat = new ChatColorPanel();

        settingsPanel.add(usersPanel, "user");
        settingsPanel.add(board, "board");
        settingsPanel.add(menu, "menu");
        settingsPanel.add(chat, "chat");

        CardLayout cl = (CardLayout) settingsPanel.getLayout();
        cl.show(settingsPanel, "user");

        constraints.weightx = 0.8;
        constraints.gridx = 1;
        add(settingsPanel, constraints);
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
    public ChatColorPanel getChat() {
        return chat;
    }
}
