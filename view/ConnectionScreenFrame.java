package view;

import controller.ConnectionHandler;
import controller.HostPanel;
import controller.JoinPanel;
import controller.ViewStateHandler;
import model.ConnectionStatus;
import model.Constants;

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
        buttonsPanel.setLayout(new GridLayout(4,1, Constants.LayoutsConstants.H_GAP, Constants.LayoutsConstants.V_GAP));



        hostButton = new JButton("Host");
        hostButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) cards.getLayout();
            cl.show(cards, "host");
        });
        buttonsPanel.add(hostButton);

        joinButton = new JButton("Join");
        joinButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) cards.getLayout();
            cl.show(cards, "join");
        });
        buttonsPanel.add(joinButton);

        JPanel endPanel = new JPanel(new GridLayout(2,1));


        endConnectionButton = new JButton("End");
        endConnectionButton.addActionListener(e -> {
            CardLayout cl = (CardLayout) cards.getLayout();
            cl.show(cards, "end");
            ConnectionStatus.connectionInfo.setText(" ");
            ConnectionHandler.connectionHandler.endHandler();
        });
        endPanel.add(endConnectionButton);
        endPanel.add(ConnectionStatus.connectionInfo);

        buttonsPanel.add(endPanel);

        backButton = new JButton("");
        backButton.addActionListener(ViewStateHandler.changeStateListener);
        backButton.setActionCommand("Menu");
        backButton.setIcon(new ImageIcon(this.getClass().getResource("../graphics/back.png")));
        buttonsPanel.add(backButton);

        add(buttonsPanel);

        JPanel host = new HostPanel();

        JPanel join = new JoinPanel();

        JPanel end = new JPanel();
        end.setLayout(new GridLayout(1,1));
        end.add(new JLabel("Connection ended."), SwingConstants.CENTER);

        cards = new JPanel(new CardLayout());
        cards.add(host, "host");
        cards.add(join, "join");
        cards.add(end, "end");

        add(cards);
    }
}
