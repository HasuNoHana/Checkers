package controller;

import model.FramesArray;
import view.BoardFrame;
import view.ConnectionScreenFrame;
import view.MainMenuFrame;
import view.SettingsFrame;

import java.awt.*;
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

    public ViewStateHandler(){
        EventQueue.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        FramesArray.add(new MainMenuFrame());
                        FramesArray.add(new SettingsFrame());
                        FramesArray.add(new BoardFrame());
                        FramesArray.add(new ConnectionScreenFrame());

                        FramesArray.get(1).setVisible(true);
                    }
                }
        );
    }
}
