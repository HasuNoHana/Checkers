package controller;

import model.Constants;
import view.Board;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardController {
    Board board;

    public BoardController(Board board, ViewsController viewsController){
        this.board = board;

        // Back to menu button
        board.addBackListener(viewsController.getChangeStateListener());
        board.getMenuButton().setActionCommand("Menu");



    }


}
