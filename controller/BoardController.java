package controller;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import view.Board;

public class BoardController {
    Board board;

    public BoardController(Board board, ViewsController viewsController){
        this.board = board;

        // Back to menu button
        board.addBackListener(viewsController.getChangeStateListener());
        board.getMenuButton().setActionCommand("Menu");



    }


}
