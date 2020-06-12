package controller;

import model.ConnectionStatus;
import view.Connection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConnectionController {
    Connection connection;
    public ConnectionController(Connection connection, ConnectionStatus connectionStatus, ViewsController viewsController){
        this.connection = connection;

        // Add back button listener
        connection.addBackListener(viewsController.getChangeStateListener());
        connection.getBackButton().setActionCommand("Menu");

        // Change IP and port panel
        connection.getChangeConnectionInfoPanel().getIpInfoLabel().setText("IP: "+connectionStatus.getIp());
        connection.getChangeConnectionInfoPanel().getPortInfoLabel().setText("Port: "+Integer.toString(connectionStatus.getPort()));

        connection.getChangeConnectionInfoPanel().getChangePortButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int len = connection.getChangeConnectionInfoPanel().getPortField().getText().length();
                if(len>0 && len<10){
                    String text = connection.getChangeConnectionInfoPanel().getPortField().getText();
                    int port;
                    try {
                        port = Integer.parseInt(text);
                        connectionStatus.setPort(port);
                        connection.getChangeConnectionInfoPanel().getInfoLabel().setText("Port changed to: "+text+".");
                        connection.getChangeConnectionInfoPanel().getPortField().setText("");
                        connection.getChangeConnectionInfoPanel().getPortInfoLabel().setText("Port: "+text);
                        connection.getChangeConnectionInfoPanel().setPortReady(true);
                        if(connection.getChangeConnectionInfoPanel().isPortReady()&&connection.getChangeConnectionInfoPanel().isIPReady()){
                            connection.getHostButton().setEnabled(true);
                            connection.getJoinButton().setEnabled(true);
                        }
                    }catch (Exception exception){
                        connection.getChangeConnectionInfoPanel().getInfoLabel().setText("Port must be a number!");
                        connection.getChangeConnectionInfoPanel().setPortReady(false);
                        connection.getHostButton().setEnabled(false);
                        connection.getJoinButton().setEnabled(false);
                    }
                }else{
                    connection.getChangeConnectionInfoPanel().getInfoLabel().setText("Wrong port length!");
                    connection.getHostButton().setEnabled(false);
                    connection.getJoinButton().setEnabled(false);
                }
            }
        });
        connection.getChangeConnectionInfoPanel().getChangeIPButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int len = connection.getChangeConnectionInfoPanel().getIpField().getText().length();
                if(len>0 && len<30){
                    String text = connection.getChangeConnectionInfoPanel().getIpField().getText();
                    connectionStatus.setIp(text);
                    connection.getChangeConnectionInfoPanel().getInfoLabel().setText("IP changed to: "+text+".");
                    connection.getChangeConnectionInfoPanel().getIpField().setText("");
                    connection.getChangeConnectionInfoPanel().getIpInfoLabel().setText("IP: "+text);
                    connection.getChangeConnectionInfoPanel().setIPReady(true);
                    if(connection.getChangeConnectionInfoPanel().isPortReady()&&connection.getChangeConnectionInfoPanel().isIPReady()){
                        connection.getHostButton().setEnabled(true);
                        connection.getJoinButton().setEnabled(true);
                    }
                }else{
                    connection.getChangeConnectionInfoPanel().getInfoLabel().setText("WRONG IP");
                    connection.getChangeConnectionInfoPanel().setIPReady(false);
                    connection.getHostButton().setEnabled(false);
                    connection.getJoinButton().setEnabled(false);
                }
            }
        });


    }

}
