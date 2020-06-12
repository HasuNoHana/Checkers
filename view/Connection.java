package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Connection extends JFrame {
    private final JButton hostButton;
    private final JButton joinButton;
    private final JButton endConnectionButton;
    private final JButton backButton;

    private ChangeConnectionInfoPanel changeConnectionInfoPanel;
    private JPanel status;
    private JLabel statusLabel;

    public Connection(){
        super("Connection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4,1, Constants.LayoutsConstants.H_GAP, Constants.LayoutsConstants.V_GAP));

        hostButton = new JButton("Host");
        buttonsPanel.add(hostButton);

        joinButton = new JButton("Join");
        buttonsPanel.add(joinButton);

        endConnectionButton = new JButton("End");
        buttonsPanel.add(endConnectionButton);

        backButton = new JButton("");
        backButton.setIcon(new ImageIcon(this.getClass().getResource("../graphics/back.png")));
        buttonsPanel.add(backButton);

        add(buttonsPanel);

        changeConnectionInfoPanel = new ChangeConnectionInfoPanel();

        status = new JPanel(new FlowLayout());
        statusLabel = new JLabel("No connection.", SwingConstants.CENTER);
        status.add(statusLabel);
        status.setBackground(Color.RED);

        changeConnectionInfoPanel.add(status);

        add(changeConnectionInfoPanel);
    }

    public void addBackListener(ActionListener actionListener){
        this.backButton.addActionListener(actionListener);
    }
    public JButton getBackButton(){ return backButton; }
    public JButton getHostButton(){ return hostButton; }
    public JButton getJoinButton(){ return joinButton; }
    public JButton getEndConnectionButton(){ return endConnectionButton; }
    public ChangeConnectionInfoPanel getChangeConnectionInfoPanel(){ return changeConnectionInfoPanel; }
    public JPanel getStatus(){ return status; }
    public JLabel getStatusLabel(){ return statusLabel; }
}
