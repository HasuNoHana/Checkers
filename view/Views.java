package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import javax.swing.*;

public class Views {
    public JButton resetBoardButton;

    public UserInfoChangePanel userInfoChangePanelInitial;
    public UserInfoChangePanel userInfoChangePanelSettings;
    public MenuChatPanel textMenuChatPanel;

    public InitialPopUpFrame initialPopUpFrame;
    public MainMenuFrame mainMenuFrame;
    public BoardFrame boardFrame;
    public ConnectionFrame connectionFrame;
    public SettingsFrame settingsFrame;
    public Views(){
        resetBoardButton = new JButton("");

        userInfoChangePanelInitial = new UserInfoChangePanel();
        userInfoChangePanelSettings = new UserInfoChangePanel();
        textMenuChatPanel = new MenuChatPanel();

        initialPopUpFrame = new InitialPopUpFrame(userInfoChangePanelInitial);
        mainMenuFrame = new MainMenuFrame(textMenuChatPanel);
        boardFrame = new BoardFrame();
        connectionFrame = new ConnectionFrame();
        settingsFrame = new SettingsFrame(userInfoChangePanelSettings);
    }
}
