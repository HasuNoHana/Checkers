package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import javax.swing.*;

public class Views {
    public UserInfoChangePanel userInfoChangePanelInitial;
    public UserInfoChangePanel userInfoChangePanelSettings;
    public ChatPanel textChatPanel;
    public JTextArea emoteChatArea;

    public InitialPopUpFrame initialPopUpFrame;
    public MainMenuFrame mainMenuFrame;
    public BoardFrame boardFrame;
    public ConnectionFrame connectionFrame;
    public SettingsFrame settingsFrame;
    public Views(){
        userInfoChangePanelInitial = new UserInfoChangePanel();
        userInfoChangePanelSettings = new UserInfoChangePanel();
        textChatPanel = new ChatPanel();

        initialPopUpFrame = new InitialPopUpFrame(userInfoChangePanelInitial);
        mainMenuFrame = new MainMenuFrame(textChatPanel);
        boardFrame = new BoardFrame();
        connectionFrame = new ConnectionFrame();
        settingsFrame = new SettingsFrame(userInfoChangePanelSettings);
    }
}
