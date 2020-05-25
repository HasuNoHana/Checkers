package view;

import controller.ConnectionHandler;
import controller.ViewStateHandler;

import javax.swing.*;
import java.awt.*;

/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
public class ConnectionScreenFrame extends JFrame {
    private final JButton hostButton;
    private final JButton joinButton;
    private final JButton endConnectionButton;
    private final JButton backButton;

    private static JPanel cards;
    public ConnectionScreenFrame(){
        super("Connection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4,1));


        hostButton = new JButton("Host");
        hostButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) cards.getLayout();
            cl.show(cards, "host");
            ConnectionHandler.connectionHandler.runServer();
        });
        buttonsPanel.add(hostButton);

        joinButton = new JButton("Join");
        joinButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) cards.getLayout();
            cl.show(cards, "join");
            ConnectionHandler.connectionHandler.runClient();
        });
        buttonsPanel.add(joinButton);

        endConnectionButton = new JButton("End");
        endConnectionButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) cards.getLayout();
            cl.show(cards, "end");
            ConnectionHandler.connectionHandler.endHandler();
        });
        buttonsPanel.add(endConnectionButton);

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

        JPanel end = new JPanel();
        end.setBackground(Color.BLUE);

        cards = new JPanel(new CardLayout());
        cards.add(host, "host");
        cards.add(join, "join");
        cards.add(end, "end");

        add(cards);
    }
}
