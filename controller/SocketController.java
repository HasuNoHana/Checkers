package controller;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.*;
import view.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketController {
    private final ArrayList<JButton> changingButtonsConnected;
    private final ArrayList<JButton> changingButtonsNotConnected;
    private final Views views;
    private final Models models;
    public SocketController(Models models, Views views){
        this.models = models;
        this.views = views;
        changingButtonsConnected = new ArrayList<>();
        changingButtonsNotConnected = new ArrayList<>();


        // Main menu
        views.mainMenuFrame.getMenuChatPanel().addSendListener(e -> {
            String message = views.mainMenuFrame.getMenuChatPanel().getMessField().getText();
            if(!message.equals("")){
                message = message.substring(0, Math.min(message.length(), Constants.ChatConstants.MAX_MESS_LEN));
                sendMessage(Constants.ConnectionConstants.CHAT_MESSAGE, message);
                views.mainMenuFrame.getMenuChatPanel().addYourMessToChat(message);
                views.mainMenuFrame.getMenuChatPanel().getMessField().setText("");
            }
        });
        views.mainMenuFrame.getMenuChatPanel().getMessField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String message = views.mainMenuFrame.getMenuChatPanel().getMessField().getText();
                    if(!message.equals("")&&models.connectionStatus.isEnemyThere()){
                        message = message.substring(0, Math.min(message.length(), Constants.ChatConstants.MAX_MESS_LEN));
                        sendMessage(Constants.ConnectionConstants.CHAT_MESSAGE, message);
                        views.mainMenuFrame.getMenuChatPanel().addYourMessToChat(message);
                        views.mainMenuFrame.getMenuChatPanel().getMessField().setText("");
                    }
                }
            }
        });

        addChangingButtonConnected(views.mainMenuFrame.getMenuPanel().getStartButton());
        addChangingButtonConnected(views.mainMenuFrame.getMenuChatPanel().getSendButton());

        // BoardFrame
        for( JButton emote : views.boardFrame.getEmotesPanel().getEmotes() ){
            emote.addActionListener(e -> {
                String message = emote.getText();
                sendMessage(Constants.ConnectionConstants.EMOTE_MESSAGE, message);
                views.boardFrame.addYourMessToChat(message);
            });
            addChangingButtonConnected(emote);
        }

        // Connection

        addChangingButtonNotConnected(views.connectionFrame.getHostButton());
        addChangingButtonNotConnected(views.connectionFrame.getJoinButton());
        addChangingButtonNotConnected(views.connectionFrame.getChangeConnectionInfoPanel().getChangeIPButton());
        addChangingButtonNotConnected(views.connectionFrame.getChangeConnectionInfoPanel().getChangePortButton());

        views.connectionFrame.getHostButton().addActionListener(e -> {
            views.connectionFrame.getJoinButton().setEnabled(false);
            runServer();
        });

        views.connectionFrame.getJoinButton().addActionListener(e -> {
            views.connectionFrame.getHostButton().setEnabled(false);
            runClient();
        });

        views.connectionFrame.getEndConnectionButton().addActionListener(e -> {
            closeConnection();
            views.connectionFrame.getStatus().setBackground(Color.RED);
            views.connectionFrame.getStatusLabel().setText("No connection.");
        });

    }
    public void runServer(){
        Thread serverRunner = new Thread(() -> {
            if(!models.connectionStatus.setAndGetSocketTaken(true)){
                try{
                    models.connectionStatus.setServerSocket(new ServerSocket(models.connectionStatus.getPort()));
                    models.connectionStatus.setSocket(models.connectionStatus.getServerSocket().accept());
                    runHandler();
                }catch (Exception e){
                    models.connectionStatus.setEnemyThere(false);
                    models.connectionStatus.setIsSocketTaken(false);
                    deactivateChangingButtons();
                    System.out.println("INFO: Could not host.");
                }
            }
        });
        serverRunner.start();
    }
    public void runClient(){
        Thread clientRunner = new Thread(() -> {
            if(!models.connectionStatus.setAndGetSocketTaken(true)){
                try{
                    models.connectionStatus.setInetAddress(InetAddress.getByName(models.connectionStatus.getIp()));
                    models.connectionStatus.setSocket(new Socket(models.connectionStatus.getInetAddress(), models.connectionStatus.getPort()));
                    runHandler();
                }catch (Exception e){
                    models.connectionStatus.setEnemyThere(false);
                    models.connectionStatus.setIsSocketTaken(false);
                    deactivateChangingButtons();
                    System.out.println("INFO: Host not found.");
                }
            }
        });
        clientRunner.start();
    }

    private void runHandler(){
        if(models.connectionStatus.setAndGetSocketTaken(true)){
            try{
                deactivateChangingButtons();
                models.connectionStatus.setDataInputStream(new DataInputStream(models.connectionStatus.getSocket().getInputStream()));
                models.connectionStatus.setDataOutputStream(new DataOutputStream(models.connectionStatus.getSocket().getOutputStream()));
                models.connectionStatus.setEnemyThere(true);

                views.mainMenuFrame.getMenuPanel().setEnemyReady(true);
                views.connectionFrame.setStatus(true);

                canReadMess = true;

                readMessage();

                sendMessage(Constants.ConnectionConstants.USER_NAME, models.me.getName());

                activateChangingButtons();

            }catch (Exception e){
                models.connectionStatus.setEnemyThere(false);
                models.connectionStatus.setIsSocketTaken(false);
                System.out.println("INFO: Could not create streams.");
            }
        }
    }

    public synchronized void closeConnection(){
        if(models.connectionStatus.isSocketTaken()){
            // Todo: exception handling
            deactivateChangingButtons();
            canReadMess = false;
            views.mainMenuFrame.getMenuChatPanel().getChatPanel().clearChat();
            views.mainMenuFrame.getMenuPanel().setEnemyReady(false);
            views.connectionFrame.setStatus(false);

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
            models.connectionStatus.setIsSocketTaken(false);
            models.enemy.setName("");
        }
    }

    public void sendMessage(String command){
        this.sendMessage(command, "");
    }

    public void sendMessage(String command, String message){
        if(!models.connectionStatus.isSocketTaken()||!models.connectionStatus.isEnemyThere()){
            return;
        }
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
            default:
                return;
        }
        try {
            models.connectionStatus.getDataOutputStream().writeUTF(finalMessage);
        } catch (IOException e) {
            System.out.println("INFO: Could not send message.");
            closeConnection();
        }
    }
    private boolean canReadMess = false;
    private void readMessage(){
        Thread readMessage = new Thread(() -> {
            while (canReadMess) {
                try {
                    String message = models.connectionStatus.getDataInputStream().readUTF();
                    String[] lines = message.split("\n");
                    if(lines.length>1){
                        StringBuilder text = new StringBuilder();
                        for( int i = 1; i < lines.length; ++i){
                            text.append(lines[i]);
                        }
                        switch (lines[0]){
                            case Constants.ConnectionConstants.CHAT_MESSAGE:
                                views.mainMenuFrame.getMenuChatPanel().addEnemyMessToChat(text.toString());
                                break;
                            case Constants.ConnectionConstants.EMOTE_MESSAGE:
                                views.boardFrame.addEnemyMessToChat(text.toString());
                                break;
                            case Constants.ConnectionConstants.USER_NAME:
                                models.enemy.setName(text.toString());
                                views.mainMenuFrame.getMenuPanel().setEnemyName(models.enemy.getName());
                                break;
                            case Constants.ConnectionConstants.BOARD_MOVE:

                                break;
                            default:
                                return;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("INFO: Connection closed.");
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
        changingButtonsNotConnected.add(button);
    }

    // Todo: zamien na private
    public void activateChangingButtons(){
        for( JButton button : changingButtonsConnected ){
            button.setEnabled(true);
        }
        for( JButton button : changingButtonsNotConnected ){
            button.setEnabled(false);
        }
    }

    public void deactivateChangingButtons(){
        for( JButton button : changingButtonsConnected ){
            button.setEnabled(false);
        }
        for( JButton button : changingButtonsNotConnected ){
            button.setEnabled(true);
        }
    }
}
