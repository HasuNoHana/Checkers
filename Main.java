import controller.Client;
import controller.Server;
import controller.ViewStateHandler;
import model.Constants;

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

public class Main{
    public static void main(String[] args){
        ViewStateHandler.start();
//        Server.server.run();
//
//        Client.client.run();
//
//        Client.client.sendMessage(Constants.ConnectionConstants.CHAT_MESSAGE, "tak");

    }
}