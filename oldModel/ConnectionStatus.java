package oldModel;

import oldView.MainMenuFrame;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
public class ConnectionStatus {
    public static String ip = "localhost";
    public static int port = 1234;

    public static DataInputStream dataInputStream;
    public static DataOutputStream dataOutputStream;

    public static InetAddress inetAddress;

    public static Socket socket;

    public static ServerSocket serverSocket;

    public static boolean isSocketTaken = false;

    private static boolean isEnemyThere = false;

    public static JLabel connectionInfo = new JLabel("", SwingConstants.CENTER);

    public static void setIsEnemyThere(boolean bool){
        isEnemyThere = bool;
        MainMenuFrame.setIsEnemyRead(isEnemyThere);
    }
    public static boolean getIsEnemyThere(){
        return isEnemyThere;
    }

    public static void setIP( String newIP ){
        ip = newIP;
    }

    public static void setPort( int newPort ){
        port = newPort;
    }

}
