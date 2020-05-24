package controller;

import model.FramesArray;
import view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
public class ViewStateHandler{

    private static class ChangeStateListener implements ActionListener {
        public ChangeStateListener(){ }

        @Override
        public void actionPerformed(ActionEvent e) {
            String source = e.getActionCommand();
            if(source.toLowerCase().equals("exit")){
                System.exit(0);
            }
            FramesArray.changeVisibleFrame(source);
        }
    }
    public static ChangeStateListener changeStateListener = new ChangeStateListener();

    private ViewStateHandler(){
        FramesArray.add(new InitialPopUpFrame());
        FramesArray.add(new MainMenuFrame());
        FramesArray.add(new SettingsFrame());
        FramesArray.add(new BoardFrame());
        FramesArray.add(new ConnectionScreenFrame());
    }

    public static ViewStateHandler viewStateHandler = new ViewStateHandler();

    public static void start(){
        if(FramesArray.len()>0){
            FramesArray.get(0).setVisible(true);
        }
    }

}
