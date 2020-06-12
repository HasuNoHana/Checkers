package controller;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.Models;
import model.Constants;
import view.Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public Controller(Models models, Views views){
        this.models = models;
        this.views = views;

        this.initUserNameChange();

        this.socketController = new SocketController(models, views);
        this.viewsController = new ViewsController(models.framesArray, views);
        this.initialPopupController = new InitialPopupController(views, viewsController, models.me);
        this.mainMenuController = new MainMenuController(views.mainMenu, models.me, models.enemy, viewsController);
        this.settingsController = new SettingsController(views.settings, models.menu, models.framesArray, viewsController);
        this.boardController = new BoardController(views.board, viewsController);
        this.connectionController = new ConnectionController(views.connection, models.connectionStatus, viewsController);




        //socketController.activateChangingButtons();












         viewsController.showFirstView();
    }


    private void initUserNameChange(){
        views.usernameChangeInitial.addButtonListener(e -> {
            int len = views.usernameChangeInitial.getNameField().getText().length();
            if(len>0 && len< Constants.UserConstants.MAX_USERNAME_LENGTH){
                String text = views.usernameChangeInitial.getNameField().getText();
                models.me.setName(text);
                socketController.sendMessage(Constants.ConnectionConstants.USER_NAME, text);
                views.mainMenu.getMenuPanel().setPlayerName(models.me.getName());
                views.usernameChangeInitial.setInfo("Name changed to: "+text+".");
                views.usernameChangeInitial.getNameField().setText("");
            }else{
                views.usernameChangeInitial.setInfo("Name length must be between 1 and " + Constants.UserConstants.MAX_USERNAME_LENGTH +".");
            }
        });
        views.usernameChangeSettings.addButtonListener(e -> {
            int len = views.usernameChangeSettings.getNameField().getText().length();
            if(len>0 && len< Constants.UserConstants.MAX_USERNAME_LENGTH){
                String text = views.usernameChangeSettings.getNameField().getText();
                models.me.setName(text);
                socketController.sendMessage(Constants.ConnectionConstants.USER_NAME, text);
                views.mainMenu.getMenuPanel().setPlayerName(models.me.getName());
                views.usernameChangeSettings.setInfo("Name changed to: "+text+".");
                views.usernameChangeSettings.getNameField().setText("");
            }else{
                views.usernameChangeSettings.setInfo("Name length must be between 1 and " + Constants.UserConstants.MAX_USERNAME_LENGTH +".");
            }
        });
    }
}
