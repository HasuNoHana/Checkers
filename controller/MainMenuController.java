package controller;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.ChatMessage;
import model.User;
import view.MainMenu;

public class MainMenuController {
    private MainMenu mainMenu;
    private ViewsController viewsController;
    private User me;
    public MainMenuController(MainMenu mainMenu, User meUsr, User enemy, ViewsController viewsController){
        this.mainMenu = mainMenu;
        this.viewsController = viewsController;
        this.me = meUsr;

        this.mainMenu.getMenuPanel().addExitListener(viewsController.getChangeStateListener());
        this.mainMenu.getMenuPanel().getExitButton().setActionCommand("Exit");

        this.mainMenu.getMenuPanel().addSettingsListener(viewsController.getChangeStateListener());
        this.mainMenu.getMenuPanel().getSettingsButton().setActionCommand("Settings");

        this.mainMenu.getMenuPanel().addConnectListener(viewsController.getChangeStateListener());
        this.mainMenu.getMenuPanel().getConnectButton().setActionCommand("Connection");

        this.mainMenu.getMenuPanel().addStartListener(viewsController.getChangeStateListener());
        this.mainMenu.getMenuPanel().getStartButton().setActionCommand("Board");
        this.mainMenu.getMenuPanel().getStartButton().setEnabled(false);
    }

    public void addChatMessage(ChatMessage message){
        this.mainMenu.getChatPanel().addMessToChat(message);
    }
    public void addChatMessage(String message){
        this.mainMenu.getChatPanel().addMessToChat(message);
    }

}
