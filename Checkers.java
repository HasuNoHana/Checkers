import controller.Controller;
import controller.MainMenuController;
import controller.SocketController;
import controller.ViewsController;
import model.*;
import model.Menu;
import oldController.ViewStateHandler;
import view.*;

import java.awt.*;

/*
* todo:
*  - polaczenie client-server
*  - dodanie samej gry xd
*  - lepsze rozlozenie struktur w frame'ach
*  - dodanie roznych eventow do obslugi dodatkowych funkcjonalnosci
*  - dodania awatara gracza
*
* */

public class Checkers {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                initCheckers();
            }
        });
    }
    public static void initCheckers(){
        // Model
        Models models = new Models();

        // View
        Views views = new Views();

        // Controller
        Controller controller = new Controller(models, views);
    }
}