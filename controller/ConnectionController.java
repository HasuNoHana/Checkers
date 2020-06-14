package controller;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.ConnectionStatus;
import view.ConnectionFrame;

public class ConnectionController {
    ConnectionFrame connectionFrame;
    public ConnectionController(ConnectionFrame connectionFrame, ConnectionStatus connectionStatus, ViewsController viewsController){
        this.connectionFrame = connectionFrame;

        // Add back button listener
        connectionFrame.addBackListener(viewsController.getChangeStateListener());
        connectionFrame.getBackButton().setActionCommand("Menu");

        // Change IP and port panel
        connectionFrame.getChangeConnectionInfoPanel().getIpInfoLabel().setText("IP: "+connectionStatus.getIp());
        connectionFrame.getChangeConnectionInfoPanel().getPortInfoLabel().setText("Port: "+ connectionStatus.getPort());

        connectionFrame.getChangeConnectionInfoPanel().getChangePortButton().addActionListener(e -> {
            int len = connectionFrame.getChangeConnectionInfoPanel().getPortField().getText().length();
            if(len>0 && len<10){
                String text = connectionFrame.getChangeConnectionInfoPanel().getPortField().getText();
                int port;
                try {
                    port = Integer.parseInt(text);
                    connectionStatus.setPort(port);
                    connectionFrame.getChangeConnectionInfoPanel().getInfoLabel().setText("Port changed to: "+text+".");
                    connectionFrame.getChangeConnectionInfoPanel().getPortField().setText("");
                    connectionFrame.getChangeConnectionInfoPanel().getPortInfoLabel().setText("Port: "+text);
                    connectionFrame.getChangeConnectionInfoPanel().setPortReady(true);
                    if(connectionFrame.getChangeConnectionInfoPanel().isPortReady()&& connectionFrame.getChangeConnectionInfoPanel().isIPReady()){
                        connectionFrame.getHostButton().setEnabled(true);
                        connectionFrame.getJoinButton().setEnabled(true);
                    }
                }catch (Exception exception){
                    connectionFrame.getChangeConnectionInfoPanel().getInfoLabel().setText("Port must be a number!");
                    connectionFrame.getChangeConnectionInfoPanel().setPortReady(false);
                    connectionFrame.getHostButton().setEnabled(false);
                    connectionFrame.getJoinButton().setEnabled(false);
                }
            }else{
                connectionFrame.getChangeConnectionInfoPanel().getInfoLabel().setText("Wrong port length!");
            }
        });
        connectionFrame.getChangeConnectionInfoPanel().getChangeIPButton().addActionListener(e -> {
            int len = connectionFrame.getChangeConnectionInfoPanel().getIpField().getText().length();
            if(len>0 && len<30){
                String text = connectionFrame.getChangeConnectionInfoPanel().getIpField().getText();
                connectionStatus.setIp(text);
                connectionFrame.getChangeConnectionInfoPanel().getInfoLabel().setText("IP changed to: "+text+".");
                connectionFrame.getChangeConnectionInfoPanel().getIpField().setText("");
                connectionFrame.getChangeConnectionInfoPanel().getIpInfoLabel().setText("IP: "+text);
                connectionFrame.getChangeConnectionInfoPanel().setIPReady(true);
                if(connectionFrame.getChangeConnectionInfoPanel().isPortReady()&& connectionFrame.getChangeConnectionInfoPanel().isIPReady()){
                    connectionFrame.getHostButton().setEnabled(true);
                    connectionFrame.getJoinButton().setEnabled(true);
                }
            }else{
                connectionFrame.getChangeConnectionInfoPanel().getInfoLabel().setText("Wrong IP!");
                connectionFrame.getChangeConnectionInfoPanel().setIPReady(false);
            }
        });


    }

}
