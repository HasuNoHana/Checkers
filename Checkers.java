import controller.ViewStateHandler;
import model.Constants;

import javax.swing.*;
import java.awt.*;
import java.lang.ref.Cleaner;

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
                ViewStateHandler.start();
            }
        });
    }
}