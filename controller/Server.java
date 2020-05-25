package controller;

import model.Constants;
import view.MainMenuFrame;

import java.io.*;
import java.util.*;
import java.net.*;

public class Server
{
    private static DataInputStream dis;
    private static DataOutputStream dos;
    private Server(){

    }

    public static Server server = new Server();

    public void run()
    {
        try{
            ServerSocket serverSocket = new ServerSocket(1234);

            Socket socket;

            socket = serverSocket.accept();
            System.out.println("polaczono");
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());

            this.readMessage();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void end(){
        try
        {
            dis.close();
            dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendMessage(String command){
        this.sendMessage(command, "");
    }

    public void sendMessage(String command, String message){
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
        System.out.println(finalMessage);
        try {
            dos.writeUTF(finalMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage(){
        Thread readMessage = new Thread(new Runnable()
        {
            @Override
            public void run() {

                while (true) {
                    try {
                        String message = dis.readUTF();
                        System.out.println(message);
                        String[] lines = message.split("\n");
                        if(lines.length>2&&lines[0].equals(Constants.ConnectionConstants.MESSAGE_START)&&lines[lines.length-1].equals(Constants.ConnectionConstants.MESSAGE_END)){
                            String text = "";
                            for( int i = 2; i < lines.length-1; ++i){
                                text += lines[i];
                            }
                            switch (lines[1]){
                                case Constants.ConnectionConstants.CHAT_MESSAGE:
                                    MainMenuFrame.addMessage(text);
                                    break;
                                case Constants.ConnectionConstants.EMOTE_MESSAGE:

                                    break;
                                case Constants.ConnectionConstants.USER_NAME:

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
                        e.printStackTrace();
                    }
                }
            }
        });
        readMessage.start();
    }
}