package controller;

import model.ConnectionStatus;
import model.Constants;
import model.User;
import view.MainMenuFrame;

import java.io.*;
import java.net.*;

public class Client
{
    private Client(){

    }
    public static Client client = new Client();

    public void run()
    {
        if(!ConnectionStatus.isSocketTaken){
            try{
                ConnectionStatus.isSocketTaken = true;
                ConnectionStatus.inetAddress = InetAddress.getByName(ConnectionStatus.ip);

                ConnectionStatus.socket = new Socket(ConnectionStatus.inetAddress, ConnectionStatus.port);

                ConnectionStatus.dataInputStream = new DataInputStream(ConnectionStatus.socket.getInputStream());
                ConnectionStatus.dataOutputStream = new DataOutputStream(ConnectionStatus.socket.getOutputStream());
                ConnectionStatus.setIsEnemyThere(true);
                this.readMessage();
                this.sendMessage(User.me.getNameLabel().getText(), Constants.ConnectionConstants.USER_NAME);
            }catch (Exception e){
                ConnectionStatus.setIsEnemyThere(false);
                ConnectionStatus.isSocketTaken = false;
                e.printStackTrace();
            }
        }
    }

}