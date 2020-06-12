package controller;

import model.Constants;
import model.User;
import view.InitialPopUp;
import view.Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InitialPopupController {
    InitialPopUp initialPopUp;
    ViewsController viewsController;
    public InitialPopupController(Views views, ViewsController viewsController, User user){
        this.initialPopUp = views.initialPopUp;

        this.initialPopUp.setSize(Constants.FramesConstants.DEFAULT_WIDTH/2, Constants.FramesConstants.DEFAULT_HEIGHT/4);
        this.initialPopUp.setLocation(Constants.FramesConstants.DEFAULT_X_POSITION+Constants.FramesConstants.DEFAULT_WIDTH/4, Constants.FramesConstants.DEFAULT_Y_POSITION+Constants.FramesConstants.DEFAULT_HEIGHT/4);

        this.viewsController = viewsController;

        initialPopUp.addNextListener(viewsController.getChangeStateListener());
        initialPopUp.addNextListener(e -> {
            views.usernameChangeInitial.setInfo("");
            initialPopUp.setSize(Constants.FramesConstants.DEFAULT_WIDTH, Constants.FramesConstants.DEFAULT_HEIGHT);
            initialPopUp.setLocation(Math.max(initialPopUp.getX()-Constants.FramesConstants.DEFAULT_WIDTH/4, 0), Math.max(initialPopUp.getY()-Constants.FramesConstants.DEFAULT_HEIGHT*3/8, 0));
        });
        initialPopUp.getNextButton().setEnabled(false);
        initialPopUp.getNextButton().setActionCommand("Menu");

        views.usernameChangeInitial.addButtonListener(e -> {
            if(!views.usernameChangeInitial.getNameField().getText().equals("")){
                initialPopUp.getNextButton().setEnabled(true);
            }
        });

    }
}
