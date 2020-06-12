package controller;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.Constants;
import model.User;
import view.InitialPopUpFrame;
import view.Views;

public class InitialPopupController {
    InitialPopUpFrame initialPopUpFrame;
    ViewsController viewsController;
    public InitialPopupController(Views views, ViewsController viewsController, User user){
        this.initialPopUpFrame = views.initialPopUpFrame;

        this.initialPopUpFrame.setSize(Constants.FramesConstants.DEFAULT_WIDTH/2, Constants.FramesConstants.DEFAULT_HEIGHT/4);
        this.initialPopUpFrame.setLocation(Constants.FramesConstants.DEFAULT_X_POSITION+Constants.FramesConstants.DEFAULT_WIDTH/4, Constants.FramesConstants.DEFAULT_Y_POSITION+Constants.FramesConstants.DEFAULT_HEIGHT/4);

        this.viewsController = viewsController;

        initialPopUpFrame.addNextListener(viewsController.getChangeStateListener());
        initialPopUpFrame.addNextListener(e -> {
            views.userInfoChangePanelInitial.setInfo("");
            initialPopUpFrame.setSize(Constants.FramesConstants.DEFAULT_WIDTH, Constants.FramesConstants.DEFAULT_HEIGHT);
            initialPopUpFrame.setLocation(Math.max(initialPopUpFrame.getX()-Constants.FramesConstants.DEFAULT_WIDTH/4, 0), Math.max(initialPopUpFrame.getY()-Constants.FramesConstants.DEFAULT_HEIGHT*3/8, 0));
        });
        initialPopUpFrame.getNextButton().setEnabled(false);
        initialPopUpFrame.getNextButton().setActionCommand("Menu");

        views.userInfoChangePanelInitial.addButtonListener(e -> {
            if(!views.userInfoChangePanelInitial.getNameField().getText().equals("")){
                initialPopUpFrame.getNextButton().setEnabled(true);
            }
        });

    }
}
