package oldModel;

import javax.swing.*;
import java.util.ArrayList;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
public class FramesArray {
    private static final ArrayList<JFrame> frames = new ArrayList<>();
    private FramesArray(){

    }
    public static JFrame get( final int index ){
        return frames.get(index);
    }
    public static void add( JFrame frame ){
        frame.setSize(Constants.FramesConstants.DEFAULT_WIDTH, Constants.FramesConstants.DEFAULT_HEIGHT);
        frame.setLocation(Constants.FramesConstants.DEFAULT_X_POSITION, Constants.FramesConstants.DEFAULT_Y_POSITION);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frames.add(frame);
    }
    public static int len(){
        return frames.size();
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
        try {
            Thread.sleep(50);
        }catch (java.lang.InterruptedException e){
            System.exit(0);
        }
        previous.setVisible(false);
    }
}
