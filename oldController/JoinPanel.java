package oldController;

import oldModel.ConnectionStatus;
import oldModel.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinPanel extends JPanel{
    private final JPanel upperPanel;
    private final JPanel middlePanel;
    private final JPanel lowerPanel;

    private final JLabel infoLabel;
    private final JLabel ipInfoLabel;
    private final JLabel portInfoLabel;

    private final JButton joinButton;

    private boolean isPortReady = false;
    private boolean isIPReady = false;

    public JoinPanel(){
        this.setLayout(new GridLayout(3,1, Constants.LayoutsConstants.H_GAP ,Constants.LayoutsConstants.V_GAP));

        upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(2, 1));

        JPanel upperUpperPanel = new JPanel();
        upperUpperPanel.setLayout(new FlowLayout());

        JLabel ipLabel = new JLabel("Type new IP:");
        upperUpperPanel.add(ipLabel);

        JTextField ipField = new JTextField(Constants.ConnectionConstants.IP_LENGTH);
        upperUpperPanel.add(ipField);

        JButton changeIPButton = new JButton("Change");
        changeIPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int len = ipField.getText().length();
                if(len>0 && len<30){
                    String text = ipField.getText();
                    ConnectionStatus.setIP(text);
                    infoLabel.setText("IP changed to: "+text+".");
                    ipField.setText("");
                    ipInfoLabel.setText("PORT: "+text);
                    isIPReady = true;
                    if(isPortReady&&isIPReady){
                        joinButton.setEnabled(true);
                    }
                }else{
                    infoLabel.setText("WRONG IP");
                    isIPReady = false;
                    joinButton.setEnabled(false);
                }
            }
        });
        upperUpperPanel.add(changeIPButton);


        JPanel lowerUpperPanel = new JPanel();

        lowerUpperPanel.setLayout(new FlowLayout());

        lowerUpperPanel.setLayout(new FlowLayout());

        JLabel portLabel = new JLabel("Type new port:");
        lowerUpperPanel.add(portLabel);

        JTextField portField = new JTextField(Constants.ConnectionConstants.PORT_LENGTH);
        lowerUpperPanel.add(portField);

        JButton changePortButton = new JButton("Change");
        changePortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int len = portField.getText().length();
                if(len>0 && len<10){
                    String text = portField.getText();
                    int port;
                    try {
                        port = Integer.parseInt(text);
                        ConnectionStatus.setPort(port);
                        infoLabel.setText("PORT changed to: "+text+".");
                        portField.setText("");
                        portInfoLabel.setText("PORT: "+text);
                        isPortReady = true;
                        if(isPortReady&&isIPReady){
                            joinButton.setEnabled(true);
                        }
                    }catch (Exception exception){
                        infoLabel.setText("Port must be a number!");
                        isPortReady = false;
                        joinButton.setEnabled(false);
                    }
                }else{
                    infoLabel.setText("Wrong port length!");
                    joinButton.setEnabled(false);
                }
            }
        });
        lowerUpperPanel.add(changePortButton);

        upperPanel.add(upperUpperPanel);
        upperPanel.add(lowerUpperPanel);

        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(2,1));

        joinButton = new JButton("Try to join!");
        joinButton.setEnabled(true);
        joinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectionHandler.connectionHandler.runClient();
            }
        });
        middlePanel.add(joinButton);

        lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridLayout(3,1, Constants.LayoutsConstants.H_GAP, Constants.LayoutsConstants.V_GAP));

        infoLabel = new JLabel("", SwingConstants.CENTER);
        ipInfoLabel = new JLabel("IP: "+ConnectionStatus.ip, SwingConstants.CENTER);
        portInfoLabel = new JLabel("PORT: "+ConnectionStatus.port, SwingConstants.CENTER);

        lowerPanel.add(infoLabel);
        lowerPanel.add(ipInfoLabel);
        lowerPanel.add(portInfoLabel);

        this.add(upperPanel);
        this.add(middlePanel);
        this.add(lowerPanel);
    }
}
