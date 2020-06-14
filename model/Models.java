package model;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
public class Models {
    public FramesArray framesArray;
    public ConnectionStatus connectionStatus;
    public User me;
    public User enemy;
    public Menu menu;
    public Models(){
        // Models
        framesArray = new FramesArray();
        connectionStatus = new ConnectionStatus();

        me = new User("");
        enemy = new User("");
        menu = new Menu();
    }
}
