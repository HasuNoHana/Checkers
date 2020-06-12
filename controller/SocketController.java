package controller;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.*;
import view.Views;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketController {
    private ArrayList<JButton> changingButtonsConnected;
    private ArrayList<JButton> changingButtonsNotConnecteed;
    private Views views;
    private Models models;
    public SocketController(Models models, Views views){
        this.models = models;
        this.views = views;
        changingButtonsConnected = new ArrayList<>();
        changingButtonsNotConnecteed = new ArrayList<>();


        // Main menu
        views.mainMenuFrame.getChatPanel().addSendListener(e -> {
            ChatMessage message = new ChatMessage(views.mainMenuFrame.getChatPanel().getMessField().getText(), models.me.getName());
            sendMessage(Constants.ConnectionConstants.CHAT_MESSAGE, message.getText());
            views.mainMenuFrame.getChatPanel().addMessToChat(message);
            views.mainMenuFrame.getChatPanel().getMessField().setText("");
        });

        addChangingButtonConnected(views.mainMenuFrame.getMenuPanel().getStartButton());
        addChangingButtonConnected(views.mainMenuFrame.getChatPanel().getSendButton());

        // BoardFrame
        for( JButton emote : views.boardFrame.getEmotesPanel().getEmotes() ){
            emote.addActionListener(e -> {
                String message = emote.getText();
                sendMessage(Constants.ConnectionConstants.EMOTE_MESSAGE, message);
                views.boardFrame.addMessToChat(message);
            });
            addChangingButtonConnected(emote);
        }

        // ConnectionFrame

        //addChangingButtonConnected(connectionFrame.getEndConnectionButton());
        addChangingButtonNotConnected(views.connectionFrame.getHostButton());
        addChangingButtonNotConnected(views.connectionFrame.getJoinButton());

        views.connectionFrame.getHostButton().addActionListener(e -> {
            views.connectionFrame.getJoinButton().setEnabled(false);
            runServer();
        });

        views.connectionFrame.getJoinButton().addActionListener(e -> {
            views.connectionFrame.getHostButton().setEnabled(false);
            runClient();
        });

        // End connectionFrame button
        views.connectionFrame.getEndConnectionButton().addActionListener(e -> {
            closeConnection();
            views.connectionFrame.getStatus().setBackground(Color.RED);
            views.connectionFrame.getStatusLabel().setText("No connectionFrame.");
        });

    }
    public void runServer(){
        Thread serverRunner = new Thread(() -> {
            if(!models.connectionStatus.isSocketTaken()){
                try{
                    models.connectionStatus.setSocketTaken(true);
//                        ConnectionStatus.connectionInfo.setText("Server is waiting...");
//                        System.out.println("SERVER WAITING");
                    models.connectionStatus.setServerSocket(new ServerSocket(models.connectionStatus.getPort()));
                    models.connectionStatus.setSocket(models.connectionStatus.getServerSocket().accept());
                    runHandler();
                }catch (Exception e){

                    // Todo: exception handling
                    models.connectionStatus.setEnemyThere(false);
                    models.connectionStatus.setSocketTaken(false);
                    deactivateChangingButtons();
                    System.out.println("INFO: Could not host.");
                }
            }
        });
        serverRunner.start();
    }
    public void runClient(){
        Thread clientRunner = new Thread(() -> {
            if(!models.connectionStatus.isSocketTaken()){
                try{
                    models.connectionStatus.setSocketTaken(true);
                    models.connectionStatus.setInetAddress(InetAddress.getByName(models.connectionStatus.getIp()));
//                        ConnectionStatus.connectionInfo.setText("Searchng for a server...");
//                        System.out.println("CLIENT WAITING");
                    models.connectionStatus.setSocket(new Socket(models.connectionStatus.getInetAddress(), models.connectionStatus.getPort()));
                    runHandler();
                }catch (Exception e){
                    // Todo: exception handling
                    models.connectionStatus.setEnemyThere(false);
                    models.connectionStatus.setSocketTaken(false);
                    deactivateChangingButtons();
//                        ConnectionStatus.connectionInfo.setText("Host not found!");
                    System.out.println("INFO: Host not found.");
                }
            }
        });
        clientRunner.start();
    }

    private void runHandler(){
        if(models.connectionStatus.isSocketTaken()){
            try{
                deactivateChangingButtons();
//                ConnectionStatus.connectionInfo.setText("Connected!");
//                System.out.println("CONNECTED");
                models.connectionStatus.setDataInputStream(new DataInputStream(models.connectionStatus.getSocket().getInputStream()));
                models.connectionStatus.setDataOutputStream(new DataOutputStream(models.connectionStatus.getSocket().getOutputStream()));
                models.connectionStatus.setEnemyThere(true);

                canReadMess = true;

                readMessage();

                sendMessage(Constants.ConnectionConstants.USER_NAME, models.me.getName());

                activateChangingButtons();

            }catch (Exception e){
                // Todo: exception handling
                models.connectionStatus.setEnemyThere(false);
                models.connectionStatus.setSocketTaken(false);
                System.out.println("INFO: Streams problem.");
            }
        }
    }

    public void closeConnection(){
        if(models.connectionStatus.isSocketTaken()){
            // Todo: exception handling
            deactivateChangingButtons();
            canReadMess = false;
            models.connectionStatus.setEnemyThere(false);
            try
            {
                models.connectionStatus.getServerSocket().close();
            }catch(Exception e){
                System.out.println("INFO: Serversocket already closed.");
            }
            try
            {
                models.connectionStatus.getSocket().close();
            }catch(Exception e){
                System.out.println("INFO: Socket already closed.");
            }
            try
            {
                models.connectionStatus.getDataInputStream().close();
            }catch(Exception e){
                System.out.println("INFO: Input stream already closed.");
            }
            try
            {
                models.connectionStatus.getDataOutputStream().close();
            }catch(Exception e){
                System.out.println("INFO: Output stream already closed.");
            }
            models.connectionStatus.setSocketTaken(false);
            models.enemy.setName("");
        }
    }

    public void sendMessage(String command){
        this.sendMessage(command, "");
    }

    public void sendMessage(String command, String message){
//        System.out.println("WRITE: "+message);
        if(!models.connectionStatus.isSocketTaken()||!models.connectionStatus.isEnemyThere()){
            return;
        }
        //String finalMessage = Constants.ConnectionConstants.MESSAGE_START+"\n";
        String finalMessage = "";
        switch (command){
            case Constants.ConnectionConstants.CHAT_MESSAGE:
                finalMessage = finalMessage + Constants.ConnectionConstants.CHAT_MESSAGE+"\n";
                finalMessage = finalMessage + message+"\n";
                break;
            case Constants.ConnectionConstants.EMOTE_MESSAGE:
                finalMessage = finalMessage + Constants.ConnectionConstants.EMOTE_MESSAGE+"\n";
                finalMessage = finalMessage + message+"\n";
                break;
            case Constants.ConnectionConstants.USER_NAME:
                finalMessage = finalMessage + Constants.ConnectionConstants.USER_NAME+"\n";
                finalMessage = finalMessage + message+"\n";
                break;
            case Constants.ConnectionConstants.BOARD_MOVE:
                finalMessage = finalMessage + Constants.ConnectionConstants.BOARD_MOVE+"\n";
                finalMessage = finalMessage + message+"\n";
                break;
            case Constants.ConnectionConstants.AVATAR_IMAGE:
                finalMessage = finalMessage + Constants.ConnectionConstants.AVATAR_IMAGE+"\n";
                finalMessage = finalMessage + message+"\n";
                break;
            default:
                return;
        }
        //finalMessage = finalMessage+Constants.ConnectionConstants.MESSAGE_END;
//        System.out.println(finalMessage);
        try {
            models.connectionStatus.getDataOutputStream().writeUTF(finalMessage);
        } catch (IOException e) {
            // Todo: exception handling
            System.out.println("INFO: ConnectionFrame closed.");
//            ConnectionStatus.connectionInfo.setText("ConnectionFrame closed.");
            closeConnection();
        }
    }
    private boolean canReadMess = false;
    private void readMessage(){
        Thread readMessage = new Thread(() -> {
            while (canReadMess) {
                try {
                    String message = models.connectionStatus.getDataInputStream().readUTF();
//                        System.out.println("READ: "+message);
                    String[] lines = message.split("\n");
                    if(lines.length>1){
                        StringBuilder text = new StringBuilder();
                        for( int i = 1; i < lines.length; ++i){
                            text.append(lines[i]);
                        }
//                        text.toString().strip();
//                            System.out.println("t:"+text);
                        switch (lines[0]){
                            case Constants.ConnectionConstants.CHAT_MESSAGE:
                                views.mainMenuFrame.getChatPanel().addMessToChat(text.toString());
                                break;
                            case Constants.ConnectionConstants.EMOTE_MESSAGE:
                                views.boardFrame.addMessToChat(text.toString());
                                break;
                            case Constants.ConnectionConstants.USER_NAME:
                                models.enemy.setName(text.toString());
                                views.mainMenuFrame.getMenuPanel().setEnemyName(models.enemy.getName());
                                break;
                            case Constants.ConnectionConstants.BOARD_MOVE:

                                break;
                            case Constants.ConnectionConstants.AVATAR_IMAGE:

                                break;
                            default:
                                return;
                        }
                    }
                } catch (IOException e) {
                    // Todo: exception handling

                    System.out.println("INFO: ConnectionFrame closed.");
//                        ConnectionStatus.connectionInfo.setText("ConnectionFrame closed.");
                    closeConnection();
                }
            }
        });
        readMessage.start();
    }

    public void addChangingButtonConnected( JButton button ){
        changingButtonsConnected.add(button);
    }
    public void addChangingButtonNotConnected( JButton button ){
        changingButtonsNotConnecteed.add(button);
    }

    // Todo: zamien to na private
    public void activateChangingButtons(){
        for( JButton button : changingButtonsConnected ){
            button.setEnabled(true);
        }
        for( JButton button : changingButtonsNotConnecteed ){
            button.setEnabled(false);
        }
    }

    public void deactivateChangingButtons(){
        for( JButton button : changingButtonsConnected ){
            button.setEnabled(false);
        }
        for( JButton button : changingButtonsNotConnecteed ){
            button.setEnabled(true);
        }
    }
}
