import controller.Controller;
import model.Models;
import view.Views;

import java.awt.*;

/*
* todo:
*  - testy jednostkowe
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