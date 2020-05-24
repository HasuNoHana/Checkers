package view;

import controller.ViewStateHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
public class ConnectionScreenFrame extends JFrame {
    private final JButton hostButton;
    private final JButton joinButton;
    private final JButton backButton;

    private static JPanel cards;
    public ConnectionScreenFrame(){
        super("Connection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3,1));


        hostButton = new JButton("Host");
        hostButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) cards.getLayout();
            cl.show(cards, "host");
            MainMenuFrame.setIsEnemyRead(false);
        });
        buttonsPanel.add(hostButton);

        joinButton = new JButton("Join");
        joinButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) cards.getLayout();
            cl.show(cards, "join");
            MainMenuFrame.setIsEnemyRead(true);
        });
        buttonsPanel.add(joinButton);

        backButton = new JButton("");
        backButton.addActionListener(ViewStateHandler.changeStateListener);
        backButton.setActionCommand("Menu");
        backButton.setIcon(new ImageIcon(this.getClass().getResource("../graphics/back.png")));
        buttonsPanel.add(backButton);

        add(buttonsPanel);

        JPanel host = new JPanel();
        host.setBackground(Color.GREEN);
        JPanel join = new JPanel();
        join.setBackground(Color.RED);

        cards = new JPanel(new CardLayout());
        cards.add(host, "host");
        cards.add(join, "join");

        add(cards);
    }
}
