package model;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectionStatus {
    public ConnectionStatus(){
        this.ip = "localhost";
        this.port = 1234;
        this.isEnemyThere = new AtomicBoolean(false);
        this.isSocketTaken = new AtomicBoolean(false);
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
    private final AtomicBoolean isEnemyThere;

    public synchronized boolean isEnemyThere() {
        return isEnemyThere.get();
    }

    public synchronized void setEnemyThere(boolean enemyThere) {
        isEnemyThere.set(enemyThere);
    }

    // Is socket taken?
    private final AtomicBoolean isSocketTaken;

    public boolean isSocketTaken() {
        return isSocketTaken.get();
    }

    public boolean setAndGetSocketTaken(boolean socketTaken) {
        return isSocketTaken.getAndSet(socketTaken);
    }

    public void setIsSocketTaken(boolean socketTaken){ isSocketTaken.set(socketTaken);}
}
