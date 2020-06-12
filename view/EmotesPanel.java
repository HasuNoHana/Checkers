package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EmotesPanel extends JPanel {
    private final ArrayList<JButton> emotes;
    public EmotesPanel(){
        setLayout(new GridLayout());
        emotes = new ArrayList<>();
        for( String emote : Constants.ChatConstants.EMOTES){
            emotes.add(new JButton(emote));
        }
        for( JButton button : emotes ){
            add(button);
        }
    }
    public ArrayList<JButton> getEmotes(){
        return emotes;
    }
}
