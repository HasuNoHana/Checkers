package model;

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
    public ConnectionStatus(){
        this.ip = "localhost";
        this.port = 1234;
        this.isEnemyThere = false;
        this.isSocketTaken = false;
    }
    // IP ADDRESS
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    // PORT
    private  int port;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    // Input stream
    private  DataInputStream dataInputStream;

    public DataInputStream getDataInputStream() {
        return dataInputStream;
    }

    public void setDataInputStream(DataInputStream dataInputStream) {
        this.dataInputStream = dataInputStream;
    }

    // Output stream
    private  DataOutputStream dataOutputStream;

    public DataOutputStream getDataOutputStream() {
        return dataOutputStream;
    }

    public void setDataOutputStream(DataOutputStream dataOutputStream) {
        this.dataOutputStream = dataOutputStream;
    }

    // Internet address
    private  InetAddress inetAddress;

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    // Socket
    private Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    // Server socket
    private  ServerSocket serverSocket;

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    // Is enemy connected?
    private boolean isEnemyThere;

    public boolean isEnemyThere() {
        return isEnemyThere;
    }

    public void setEnemyThere(boolean enemyThere) {
        isEnemyThere = enemyThere;
    }

    // Is socket taken?
    private boolean isSocketTaken;

    public boolean isSocketTaken() {
        return isSocketTaken;
    }

    public void setSocketTaken(boolean socketTaken) {
        isSocketTaken = socketTaken;
    }
}
