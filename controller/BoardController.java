package controller;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import view.BoardFrame;

public class BoardController {
    BoardFrame boardFrame;

    public BoardController(BoardFrame boardFrame, ViewsController viewsController){
        this.boardFrame = boardFrame;

        // Back to menu button
        boardFrame.addBackListener(viewsController.getChangeStateListener());
        boardFrame.getMenuButton().setActionCommand("Menu");
    }


}
