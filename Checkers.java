import Game.*;
import controller.Controller;
import model.Models;
import view.Views;

import javax.swing.*;
import java.awt.*;

/*
* todo:
*  - dodanie samej gry xd
*  - dodac zmiane wygladu pionkow
*  - testy jednostkowe
* */


public class Checkers {
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {

//            JFrame frame = new JFrame();
//
//            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//            frame.setSize(600, 600);
//
//            ImageRepository imageRepository = new ImageRepository();
//            GameModel gameModel = new GameModel();
//            GameView board = new GameView(imageRepository);
//            CheckersController checkersController = new CheckersController(gameModel, board, imageRepository);
//
//            frame.add(board);
//
////            PicturePanel picturePanel = new PicturePanel(0,0, imageRepository);
////
////            Field field = new Field(0,0, Color.GREEN);
////            field.setPawn(Pawn.WHITENORMAL);
////            picturePanel.setField(field);
////
////            frame.add(picturePanel);
//
//            frame.setVisible(true);




            // Model
            Models models = new Models();

            // View
            Views views = new Views();

            // Controller
            Controller controller = new Controller(models, views);

            controller.start();
        });
    }
}