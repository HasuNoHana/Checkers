package model;

public class Models {
    public FramesArray framesArray;
    public ConnectionStatus connectionStatus;
    public User me;
    public User enemy;
    public Menu menu;
    public Chat bigChat;
    public Chat smallChat;
    public Models(){
        // Models
        framesArray = new FramesArray();
        connectionStatus = new ConnectionStatus();

        me = new User("");
        enemy = new User("");
        menu = new Menu();
        bigChat = new Chat();
        smallChat = new Chat();
    }
}
