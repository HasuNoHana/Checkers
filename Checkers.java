import controller.Controller;
import model.Models;
import view.Views;

import java.awt.EventQueue;

/*
* todo:
*  - dodanie samej gry xd
*  - dodac monitory
*  - dodac zmiane wygladu pionkow
*  - testy jednostkowe
*  - dodac etykiety do przyciskow
* */


public class Checkers {
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
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