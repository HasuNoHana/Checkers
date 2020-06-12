import controller.Controller;
import model.Models;
import view.Views;

import java.awt.*;

/*
* todo:
*  - dodanie samej gry xd
*  - lepsze rozlozenie struktur w frame'ach
*  - dodanie roznych eventow do obslugi dodatkowych funkcjonalnosci?
*  - dodania awatara gracza
*
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