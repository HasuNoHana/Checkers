package model;

import javax.swing.*;
import java.util.ArrayList;

public class FramesArray {
    private static ArrayList<JFrame> frames = new ArrayList<JFrame>();
    private final static int DEFAULT_WIDTH = 800;
    private final static int DEFAULT_HEIGHT = 600;
    private final static int DEFAULT_X_POSITION = 500;
    private final static int DEFAULT_Y_POSITION = 200;
    private FramesArray(){

    }
    public static JFrame get( final int index ){
        return frames.get(index);
    }
    public static void add( JFrame frame ){
        frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        frame.setLocation(DEFAULT_X_POSITION, DEFAULT_Y_POSITION);
        frames.add(frame);
    }
    public static void changeVisibleFrame(String source){
        JFrame previous = null;
        JFrame next = null;
        for( JFrame frame : FramesArray.frames ){

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

    private static void changeFrame(JFrame previous, JFrame next){
        int fSizeY = previous.getSize().height;
        int fPosX = previous.getLocation().x;
        int fPosY = previous.getLocation().y;
        int fSizeX = previous.getSize().width;
        next.setLocation(fPosX, fPosY);
        next.setSize(fSizeX, fSizeY);
        next.setVisible(true);
        previous.setVisible(false);
    }
}
