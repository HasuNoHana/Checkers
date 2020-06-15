package controller;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import view.BoardFrame;

public class BoardController {
    public BoardController(BoardFrame boardFrame, ViewsController viewsController){

        // Back to menu button
        boardFrame.addBackListener(viewsController.getChangeStateListener());
        boardFrame.getMenuButton().setActionCommand("Menu");

    }
}
