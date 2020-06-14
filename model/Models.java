package model;

import Game.CheckersModel;

/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
public class Models {
    public FramesArray framesArray;
    public ConnectionStatus connectionStatus;
    public PawnImagesModel pawnImagesModel;
    public CheckersModel checkersModel;
    public User me;
    public User enemy;
    public Menu menu;
    public Models(){
        // Models
        framesArray = new FramesArray();
        connectionStatus = new ConnectionStatus();
        pawnImagesModel = new PawnImagesModel();

        checkersModel = new CheckersModel();

        me = new User("");
        enemy = new User("");
        menu = new Menu();
    }
}
