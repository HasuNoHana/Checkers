package checkers;

import java.awt.*;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                /*
                The icons are made by Nikita Golubev from www.flaticon.com
                Page: https://www.flaticon.com/authors/nikita-golubev
                 */
                CheckersController controller = new CheckersController(new GameModel(), new GameView());
            }
        });
    }
}
