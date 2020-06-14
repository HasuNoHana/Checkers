import controller.Controller;
import model.Models;
import view.ChatColorPanel;
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
*  - dodac zmiane wygladu czatu - czyli teraz dodac obsluge przyciskow do zmiany
*  - dodac ladniejsze emoji
*  - upiekszyc wyglad menu
*  - testy jednostkowe
* */

import javax.swing.*;

public class Checkers {
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {

//            JFrame frame = new JFrame();
//            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//            frame.setSize(800, 600);
//            frame.setLocation(200, 100);
//            JColorChooser colorChooser = new JColorChooser();
//
//            frame.add(colorChooser);
//
//
//
//            frame.setVisible(true);
//
//            ChatColorPanel chatColorPanel = new ChatColorPanel();
//
//            frame.add(chatColorPanel);



            // Model
            Models models = new Models();

            // View
            Views views = new Views();

            // Controller
            Controller controller = new Controller(models, views);
        });
    }
}