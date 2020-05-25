package controller;

import model.ConnectionStatus;
import model.Constants;
import model.Menu;
import model.User;
import view.MainMenuFrame;

import java.io.*;
import java.util.*;
import java.net.*;

public class Server
{
    private Server(){

    }

    public static Server server = new Server();

    public void run()
    {
        if(!ConnectionStatus.isSocketTaken){
            try{
                ConnectionStatus.isSocketTaken = true;
                ConnectionStatus.serverSocket = new ServerSocket(1234);

                ConnectionStatus.socket = ConnectionStatus.serverSocket.accept();
                System.out.println("polaczono");
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