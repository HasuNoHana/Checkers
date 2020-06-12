import controller.Controller;
import model.Models;
import view.Views;

import java.awt.EventQueue;

/*
* todo:
*  - dodanie samej gry xd
*  - dodanie roznych eventow do obslugi dodatkowych funkcjonalnosci?
*  - dodania awatara gracza
*  - dodac monitory
*  - dodac wlasny look and feel
*  - dodac zmiane wygladu pionkow
*  - dodac zmiane wygladu czatu
*  - dodac ladniejsze emoji
*  - dodac lepszy wyglad czatu
*  - upiekszyc wyglad menu
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
        });
    }
}