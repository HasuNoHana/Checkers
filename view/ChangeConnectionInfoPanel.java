package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.Constants;

import javax.swing.*;
import java.awt.*;

public class ChangeConnectionInfoPanel extends JPanel {
    private final JPanel upperPanel;
    private final JPanel lowerPanel;

    private final JLabel infoLabel;
    private final JLabel ipInfoLabel;
    private final JLabel portInfoLabel;

    private final JTextField ipField;
    private final JTextField portField;

    private final JButton changeIPButton;
    private final JButton changePortButton;

    private boolean isPortReady = false;
    private boolean isIPReady = false;

    public ChangeConnectionInfoPanel(){
        this.setLayout(new GridLayout(4,1, Constants.LayoutsConstants.H_GAP ,Constants.LayoutsConstants.V_GAP));

        upperPanel = new JPanel();
        upperPanel.setLayout(new GridLayout(2, 1));

        JPanel upperUpperPanel = new JPanel();
        upperUpperPanel.setLayout(new FlowLayout());

        JLabel ipLabel = new JLabel("Type new IP:");
        upperUpperPanel.add(ipLabel);

        ipField = new JTextField(Constants.ConnectionConstants.IP_LENGTH);
        upperUpperPanel.add(ipField);

        changeIPButton = new JButton("Change");
        upperUpperPanel.add(changeIPButton);


        JPanel lowerUpperPanel = new JPanel();

        lowerUpperPanel.setLayout(new FlowLayout());

        lowerUpperPanel.setLayout(new FlowLayout());

        JLabel portLabel = new JLabel("Type new port:");
        lowerUpperPanel.add(portLabel);

        portField = new JTextField(Constants.ConnectionConstants.PORT_LENGTH);
        lowerUpperPanel.add(portField);

        changePortButton = new JButton("Change");
        lowerUpperPanel.add(changePortButton);

        upperPanel.add(upperUpperPanel);
        upperPanel.add(lowerUpperPanel);

        lowerPanel = new JPanel();
        lowerPanel.setLayout(new GridLayout(3,1, Constants.LayoutsConstants.H_GAP, Constants.LayoutsConstants.V_GAP));

        infoLabel = new JLabel("", SwingConstants.CENTER);
        ipInfoLabel = new JLabel("IP: ", SwingConstants.CENTER);
        portInfoLabel = new JLabel("PORT: ", SwingConstants.CENTER);

        lowerPanel.add(infoLabel);
        lowerPanel.add(ipInfoLabel);
        lowerPanel.add(portInfoLabel);

        this.add(new JPanel());
        this.add(upperPanel);
        this.add(lowerPanel);
    }

    public JButton getChangeIPButton() {
        return changeIPButton;
    }

    public JButton getChangePortButton() {
        return changePortButton;
    }

    public JLabel getInfoLabel() {
        return infoLabel;
    }

    public JLabel getIpInfoLabel() {
        return ipInfoLabel;
    }

    public JLabel getPortInfoLabel() {
        return portInfoLabel;
    }

    public JTextField getIpField() {
        return ipField;
    }

    public JTextField getPortField() {
        return portField;
    }

    public boolean isPortReady() {
        return isPortReady;
    }

    public void setPortReady(boolean portReady) {
        isPortReady = portReady;
    }

    public boolean isIPReady() {
        return isIPReady;
    }

    public void setIPReady(boolean IPReady) {
        isIPReady = IPReady;
    }
}