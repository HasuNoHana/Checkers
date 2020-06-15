package controller;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.FramesArray;
import view.Views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewsController {
    private class ChangeStateListener implements ActionListener {
        public ChangeStateListener(){ }
        @Override
        public void actionPerformed(ActionEvent e) {
            String source = e.getActionCommand();
            if(source.toLowerCase().equals("exit")){
                System.exit(0);
            }
            changeVisibleFrame(source);
        }
    }
    private final FramesArray framesArray;
    private final ChangeStateListener changeStateListener;

    public ViewsController(FramesArray framesArray, Views views){
        this.framesArray = framesArray;
        changeStateListener = new ChangeStateListener();

        this.framesArray.add(views.mainMenuFrame);
        this.framesArray.add(views.boardFrame);
        this.framesArray.add(views.connectionFrame);
        this.framesArray.add(views.settingsFrame);

        this.framesArray.addFirst(views.initialPopUpFrame);
    }
    public void showFirstView(){
        this.framesArray.getFrames().get(0).setVisible(true);
    }

    public void changeVisibleFrame(String source){
        JFrame previous = null;
        JFrame next = null;
        for( JFrame frame : this.framesArray.getFrames() ){

            if(frame.isShowing()){
                previous = frame;
            }
            if(source.equals(frame.getTitle())){
                next = frame;
            }
            if(previous!=null&&next != null){
                changeFrame(previous, next);
                break;
            }
        }
    }

    private void changeFrame(JFrame previous, JFrame next){
        int fSizeY = previous.getSize().height;
        int fPosX = previous.getLocation().x;
        int fPosY = previous.getLocation().y;
        int fSizeX = previous.getSize().width;
        next.setLocation(fPosX, fPosY);
        next.setSize(fSizeX, fSizeY);
        next.setVisible(true);
        try {
            Thread.sleep(50);
        }catch (java.lang.InterruptedException e){
            System.exit(0);
        }
        previous.setVisible(false);
    }

    public ChangeStateListener getChangeStateListener() {
        return changeStateListener;
    }
}
