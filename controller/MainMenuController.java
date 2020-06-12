package controller;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.ChatMessage;
import model.User;
import view.MainMenuFrame;

public class MainMenuController {
    private MainMenuFrame mainMenuFrame;
    private ViewsController viewsController;
    private User me;
    public MainMenuController(MainMenuFrame mainMenuFrame, User meUsr, User enemy, ViewsController viewsController){
        this.mainMenuFrame = mainMenuFrame;
        this.viewsController = viewsController;
        this.me = meUsr;

        this.mainMenuFrame.getMenuPanel().addExitListener(viewsController.getChangeStateListener());
        this.mainMenuFrame.getMenuPanel().getExitButton().setActionCommand("Exit");

        this.mainMenuFrame.getMenuPanel().addSettingsListener(viewsController.getChangeStateListener());
        this.mainMenuFrame.getMenuPanel().getSettingsButton().setActionCommand("SettingsFrame");

        this.mainMenuFrame.getMenuPanel().addConnectListener(viewsController.getChangeStateListener());
        this.mainMenuFrame.getMenuPanel().getConnectButton().setActionCommand("ConnectionFrame");

        this.mainMenuFrame.getMenuPanel().addStartListener(viewsController.getChangeStateListener());
        this.mainMenuFrame.getMenuPanel().getStartButton().setActionCommand("BoardFrame");
        this.mainMenuFrame.getMenuPanel().getStartButton().setEnabled(false);
    }

    public void addChatMessage(ChatMessage message){
        this.mainMenuFrame.getChatPanel().addMessToChat(message);
    }
    public void addChatMessage(String message){
        this.mainMenuFrame.getChatPanel().addMessToChat(message);
    }

}
