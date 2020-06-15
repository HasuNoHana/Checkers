package controller;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.Models;
import model.Constants;
import view.Views;

public class Controller {
    Models models;
    Views views;
    ViewsController viewsController;
    InitialPopupController initialPopupController;
    MainMenuController mainMenuController;
    SettingsController settingsController;
    BoardController boardController;
    ConnectionController connectionController;
    SocketController socketController;
    CheckersController checkersController;

    public Controller(Models models, Views views){
        this.models = models;
        this.views = views;

        this.initUserNameChange();

        this.socketController = new SocketController(models, views);
        this.viewsController = new ViewsController(models.framesArray, views);
        this.initialPopupController = new InitialPopupController(views, viewsController, models.me);
        this.mainMenuController = new MainMenuController(views.mainMenuFrame, viewsController);
        this.settingsController = new SettingsController(views, models, models.framesArray, viewsController);
        this.boardController = new BoardController(views.boardFrame, viewsController);
        this.connectionController = new ConnectionController(views.connectionFrame, models.connectionStatus, viewsController);
        this.checkersController = new CheckersController(models.checkersModel, views.boardFrame.getGameView(), socketController);

        this.initGameReset();

    }

    public void start(){
        viewsController.showFirstView();
    }

    private void initUserNameChange(){
        views.userInfoChangePanelInitial.addButtonListener(e -> {
            int len = views.userInfoChangePanelInitial.getNameField().getText().length();
            if(len>0 && len< Constants.UserConstants.MAX_USERNAME_LENGTH){
                String text = views.userInfoChangePanelInitial.getNameField().getText();
                models.me.setName(text);
                socketController.sendMessage(Constants.ConnectionConstants.USER_NAME, text);
                views.mainMenuFrame.getMenuPanel().setPlayerName(models.me.getName());
                views.userInfoChangePanelInitial.setInfo("Name changed to: "+text+".");
                views.userInfoChangePanelInitial.getNameField().setText("");
            }else{
                views.userInfoChangePanelInitial.setInfo("Name length must be between 1 and " + Constants.UserConstants.MAX_USERNAME_LENGTH +".");
            }
        });
        views.userInfoChangePanelSettings.addButtonListener(e -> {
            int len = views.userInfoChangePanelSettings.getNameField().getText().length();
            if(len>0 && len< Constants.UserConstants.MAX_USERNAME_LENGTH){
                String text = views.userInfoChangePanelSettings.getNameField().getText();
                models.me.setName(text);
                socketController.sendMessage(Constants.ConnectionConstants.USER_NAME, text);
                views.mainMenuFrame.getMenuPanel().setPlayerName(models.me.getName());
                views.userInfoChangePanelSettings.setInfo("Name changed to: "+text+".");
                views.userInfoChangePanelSettings.getNameField().setText("");
            }else{
                views.userInfoChangePanelSettings.setInfo("Name length must be between 1 and " + Constants.UserConstants.MAX_USERNAME_LENGTH +".");
            }
        });
    }

    private void initGameReset(){
        this.views.resetBoardButton.addActionListener(e -> checkersController.resetBoard());
    }
}
