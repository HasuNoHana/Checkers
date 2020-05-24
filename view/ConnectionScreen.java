package view;

import controller.ViewStateHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectionScreen extends JFrame {
    private JButton hostButton;
    private JButton joinButton;
    private JButton backButton;

    private static JPanel cards;
    public ConnectionScreen(){
        super("Connection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3,1));


        hostButton = new JButton("Host");
        hostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) cards.getLayout();
                cl.show(cards, "host");
                MainMenu.setIsEnemyRead(false);
            }
        });
        buttonsPanel.add(hostButton);

        joinButton = new JButton("Join");
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) cards.getLayout();
                cl.show(cards, "join");
                MainMenu.setIsEnemyRead(true);
            }
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
