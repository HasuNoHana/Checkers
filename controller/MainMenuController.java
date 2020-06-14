package controller;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import view.MainMenuFrame;

public class MainMenuController {
    public MainMenuController(MainMenuFrame mainMenuFrame, ViewsController viewsController){
        mainMenuFrame.getMenuPanel().addExitListener(viewsController.getChangeStateListener());
        mainMenuFrame.getMenuPanel().getExitButton().setActionCommand("Exit");

        mainMenuFrame.getMenuPanel().addSettingsListener(viewsController.getChangeStateListener());
        mainMenuFrame.getMenuPanel().getSettingsButton().setActionCommand("SettingsFrame");

        mainMenuFrame.getMenuPanel().addConnectListener(viewsController.getChangeStateListener());
        mainMenuFrame.getMenuPanel().getConnectButton().setActionCommand("Connection");

        mainMenuFrame.getMenuPanel().addStartListener(viewsController.getChangeStateListener());
        mainMenuFrame.getMenuPanel().getStartButton().setActionCommand("BoardFrame");
        mainMenuFrame.getMenuPanel().getStartButton().setEnabled(false);
    }
}
