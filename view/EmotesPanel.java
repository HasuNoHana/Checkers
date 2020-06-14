package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class EmotesPanel extends JPanel {
    private final ArrayList<JButton> emotes;
    public EmotesPanel(){
        setLayout(new GridLayout());
        emotes = new ArrayList<>();
        for( String emote : Constants.ChatConstants.EMOTES){
            JButton emoteButton = new JButton(emote);
            emoteButton.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    super.componentResized(e);
                    emoteButton.setFont(new Font(emoteButton.getName(), Font.PLAIN, emoteButton.getHeight()*3/8));
                }
            });
            emotes.add(emoteButton);
        }
        for( JButton button : emotes ){
            add(button);
        }
    }
    public ArrayList<JButton> getEmotes(){
        return emotes;
    }
}
