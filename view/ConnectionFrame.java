package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ConnectionFrame extends JFrame {
    private final JButton hostButton;
    private final JButton joinButton;
    private final JButton endConnectionButton;
    private final JButton backButton;

    private ChangeConnectionInfoPanel changeConnectionInfoPanel;
    private JPanel status;
    private JLabel statusLabel;

    public ConnectionFrame(){
        super("Connection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4,1, Constants.LayoutsConstants.H_GAP, Constants.LayoutsConstants.V_GAP));

        hostButton = new JButton("Host");
        hostButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                hostButton.setFont(new Font(hostButton.getName(), Font.PLAIN, hostButton.getHeight()*3/4));
            }
        });
        buttonsPanel.add(hostButton);

        joinButton = new JButton("Join");
        joinButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                joinButton.setFont(new Font(joinButton.getName(), Font.PLAIN, joinButton.getHeight()*3/4));
            }
        });
        buttonsPanel.add(joinButton);

        endConnectionButton = new JButton("End");
        endConnectionButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                endConnectionButton.setFont(new Font(endConnectionButton.getName(), Font.PLAIN, endConnectionButton.getHeight()*3/4));
            }
        });
        buttonsPanel.add(endConnectionButton);

        backButton = new JButton("");
        backButton.setIcon(new ImageIcon(this.getClass().getResource("../graphics/back.png")));
        buttonsPanel.add(backButton);

        add(buttonsPanel);

        changeConnectionInfoPanel = new ChangeConnectionInfoPanel();

        status = new JPanel(new GridLayout());
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
    public void setStatus( boolean bool ){
        if(bool){
            status.setBackground(Color.GREEN);
            statusLabel.setText("Connected.");
        }else{
            status.setBackground(Color.RED);
            statusLabel.setText("No connection.");
        }
    }
}
