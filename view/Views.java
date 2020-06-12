package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import javax.swing.*;

public class Views {
    public UsernameChange usernameChangeInitial;
    public UsernameChange usernameChangeSettings;
    public ChatPanel textChatPanel;
    public JTextArea emoteChatArea;

    public InitialPopUp initialPopUp;
    public MainMenu mainMenu;
    public Board board;
    public Connection connection;
    public Settings settings;
    public Views(){
        usernameChangeInitial = new UsernameChange();
        usernameChangeSettings = new UsernameChange();
        textChatPanel = new ChatPanel();

        initialPopUp = new InitialPopUp(usernameChangeInitial);
        mainMenu = new MainMenu(textChatPanel);
        board = new Board();
        connection = new Connection();
        settings = new Settings(usernameChangeSettings);
    }
}
