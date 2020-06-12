package model;

import javax.swing.*;
import java.util.ArrayList;

public class FramesArray {
    private final ArrayList<JFrame> frames;
    public FramesArray(){
        frames = new ArrayList<>();
    }
    public ArrayList<JFrame> getFrames(){
        return frames;
    }
    public void add( JFrame frame ){
        frame.setSize(oldModel.Constants.FramesConstants.DEFAULT_WIDTH, oldModel.Constants.FramesConstants.DEFAULT_HEIGHT);
        frame.setLocation(oldModel.Constants.FramesConstants.DEFAULT_X_POSITION, Constants.FramesConstants.DEFAULT_Y_POSITION);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frames.add(frame);
    }
    public void addFirst( JFrame frame ){
        frame.setSize(oldModel.Constants.FramesConstants.DEFAULT_WIDTH, oldModel.Constants.FramesConstants.DEFAULT_HEIGHT);
        frame.setLocation(oldModel.Constants.FramesConstants.DEFAULT_X_POSITION, Constants.FramesConstants.DEFAULT_Y_POSITION);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frames.add(0, frame);
    }

    public int len(){
        return frames.size();
    }
}