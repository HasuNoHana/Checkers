package controller;

import model.ConnectionStatus;
import model.Constants;
import model.User;
import view.MainMenuFrame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandler {
    private ConnectionHandler(){

    }
    public static ConnectionHandler connectionHandler = new ConnectionHandler();

    public void runServer(){
        System.out.println("SERVER URUCHOMIONY");
        Thread serverRunner = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("SERVER WATEK");
                if(!ConnectionStatus.isSocketTaken){
                    try{
                        ConnectionStatus.isSocketTaken = true;
                        System.out.println("SERVER CZEKA");
                        ConnectionStatus.serverSocket = new ServerSocket(1234);
                        ConnectionStatus.socket = ConnectionStatus.serverSocket.accept();
                        System.out.println("SERVER POLACZONY");
                        runHandler();
                    }catch (Exception e){
                        ConnectionStatus.setIsEnemyThere(false);
                        ConnectionStatus.isSocketTaken = false;
                        e.printStackTrace();
                    }
                }
            }
        });
        serverRunner.start();
    }
    public void runClient(){
        System.out.println("KLIENT URUCHOMIONY");
        Thread clientRunner = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("KLIENT WATEK");
                if(!ConnectionStatus.isSocketTaken){
                    try{
                        ConnectionStatus.isSocketTaken = true;
                        ConnectionStatus.inetAddress = InetAddress.getByName(ConnectionStatus.ip);
                        System.out.println("KLIENT CZEKA");
                        ConnectionStatus.socket = new Socket(ConnectionStatus.inetAddress, ConnectionStatus.port);
                        System.out.println("KLIENT POLACZONY");
                        runHandler();
                    }catch (Exception e){
                        ConnectionStatus.setIsEnemyThere(false);
                        ConnectionStatus.isSocketTaken = false;
                        e.printStackTrace();
                    }
                }
            }
        });
        clientRunner.start();
    }

    private static void runHandler(){
        System.out.println("URUCHOMIONE");
        System.out.println(ConnectionStatus.isSocketTaken);
        if(ConnectionStatus.isSocketTaken){
            try{
                System.out.println("POLACZONO");

                ConnectionStatus.dataInputStream = new DataInputStream(ConnectionStatus.socket.getInputStream());
                ConnectionStatus.dataOutputStream = new DataOutputStream(ConnectionStatus.socket.getOutputStream());
                ConnectionStatus.setIsEnemyThere(true);

                canReadMess = true;

                connectionHandler.readMessage();

                connectionHandler.sendMessage(Constants.ConnectionConstants.USER_NAME, User.me.getName());

            }catch (Exception e){
                ConnectionStatus.setIsEnemyThere(false);
                ConnectionStatus.isSocketTaken = false;
                e.printStackTrace();
            }
        }
    }

    public void endHandler(){
        if(ConnectionStatus.isSocketTaken){
            canReadMess = false;
            ConnectionStatus.setIsEnemyThere(false);
            try
            {
                ConnectionStatus.serverSocket.close();
            }catch(IOException e){
                e.printStackTrace();
            }
            try
            {
                ConnectionStatus.socket.close();
            }catch(IOException e){
                e.printStackTrace();
            }
            try
            {
                ConnectionStatus.dataInputStream.close();
            }catch(IOException e){
                e.printStackTrace();
            }
            try
            {
                ConnectionStatus.dataOutputStream.close();
            }catch(IOException e){
                e.printStackTrace();
            }
            ConnectionStatus.isSocketTaken = false;
            User.enemy.setName("");
        }
    }

    public void sendMessage(String command){
        this.sendMessage(command, "");
    }

    public void sendMessage(String command, String message){
        if(!ConnectionStatus.isSocketTaken||!ConnectionStatus.getIsEnemyThere()){
            return;
        }
        String finalMessage = Constants.ConnectionConstants.MESSAGE_START+"\n";
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
        finalMessage = finalMessage+Constants.ConnectionConstants.MESSAGE_END;
//        System.out.println(finalMessage);
        try {
            ConnectionStatus.dataOutputStream.writeUTF(finalMessage);
        } catch (IOException e) {
            endHandler();
            e.printStackTrace();
        }
    }
    private static boolean canReadMess = false;
    private void readMessage(){
        Thread readMessage = new Thread(new Runnable()
        {
            @Override
            public void run() {
                while (canReadMess) {
                    try {
                        String message = ConnectionStatus.dataInputStream.readUTF();
//                        System.out.println(message);
                        String[] lines = message.split("\n");
                        if(lines.length>2&&lines[0].equals(Constants.ConnectionConstants.MESSAGE_START)&&lines[lines.length-1].equals(Constants.ConnectionConstants.MESSAGE_END)){
                            String text = "";
                            for( int i = 2; i < lines.length-1; ++i){
                                text += lines[i];
                            }
                            text.strip();
//                            System.out.println(text);
//                            System.out.println(lines[1]);
                            switch (lines[1]){
                                case Constants.ConnectionConstants.CHAT_MESSAGE:
                                    MainMenuFrame.addMessage(text);
                                    break;
                                case Constants.ConnectionConstants.EMOTE_MESSAGE:

                                    break;
                                case Constants.ConnectionConstants.USER_NAME:
                                    User.enemy.setName(text);
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
                        endHandler();
                        e.printStackTrace();
                    }
                }
            }
        });
        readMessage.start();
    }
}
