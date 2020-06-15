package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class ChatPanel extends JPanel {


    private class MessageBubble extends JComponent{
        private final String message;
        private final boolean isRight;

        public MessageBubble(String message, boolean isRight){
            super();
            this.message = message;
            this.isRight = isRight;
        }
        public String getMessage() {
            return message;
        }
        public void paintComponent(Graphics g, int posY) {
            super.paintComponent(g);
            int width = getWidth();
            FontMetrics fm = g.getFontMetrics();
            int height = getHeight();
            if (message != null) {
                Rectangle2D rectangle2D = fm.getStringBounds(message, g);
                int messWidth = (int)rectangle2D.getWidth();
                int messHeight = (int)rectangle2D.getHeight();
                int bubbleWidth = messWidth + gapWidth*2;
                int bubbleHeight = messHeight + gapWidth*2;
                if(this.isRight){
                    g.setColor(yourBubbleColor);
                    g.fillRoundRect(width-bubbleWidth-gapWidth, posY, bubbleWidth, bubbleHeight, 10, 10);
                }else{
                    g.setColor(enemyBubbleColor);
                    g.fillRoundRect(gapWidth, posY, bubbleWidth, bubbleHeight, 10, 10);
                }
                if(this.isRight){
                    g.setColor(yourTextColor);
                    g.drawString(message, width-messWidth-gapWidth*2, posY+height/2+messHeight/2);
                }else{
                    g.setColor(enemyTextColor);
                    g.drawString(message, gapWidth*2, posY+height/2+messHeight/2);
                }
            }
        }

    }

    private final ArrayList<MessageBubble> messageBubbles;
    private int messNum = 0;
    private int gapWidth = Constants.ChatConstants.BUBBLE_GAP;
    private Color yourBubbleColor = Constants.ChatConstants.YOUR_BUBBLE_COLOR;
    private Color yourTextColor = Constants.ChatConstants.YOUR_TEXT_COLOR;
    private Color enemyBubbleColor = Constants.ChatConstants.ENEMY_BUBBLE_COLOR;
    private Color enemyTextColor = Constants.ChatConstants.ENEMY_TEXT_COLOR;

    public ChatPanel(int gap){
        this();
        this.gapWidth = gap;
    }

    public ChatPanel(){
        messageBubbles = new ArrayList<>();
    }

    private void addMessage(String message, boolean isRight){
        if(message.length()>0){
            if(messNum< Constants.ChatConstants.MAX_MESS_NUM){
                messNum += 1;
            }else{
                messageBubbles.remove(0);
            }
            MessageBubble messageBubble = new MessageBubble(message, isRight);
            messageBubble.setSize(100, 100);
            messageBubbles.add(messageBubble);
            this.paintComponent(this.getGraphics());
        }
    }

    public void paintComponent(Graphics g) {
        FontMetrics fontMetrics = g.getFontMetrics();
        Font f = g.getFont();
        float pt = f.getSize();
        int height = (int)(this.getHeight()*0.05f);
        while (fontMetrics.getHeight() < height) {
            pt += 2;
            f = f.deriveFont(pt);
            fontMetrics = g.getFontMetrics(f);
        }
        g.setFont(f);
        super.paintComponent(g);
        int fromBottomLine = this.getHeight()-10;
        for( int i = messageBubbles.size()-1; i>=0; --i ){
            MessageBubble messageBubble = messageBubbles.get(i);
            String message = messageBubble.getMessage();
            messageBubble.setSize(getWidth(), gapWidth*2+(int)fontMetrics.getStringBounds(message, g).getHeight());
            int bubbleHeight = messageBubble.getHeight();
            messageBubble.paintComponent(g, fromBottomLine-bubbleHeight);
            fromBottomLine -= bubbleHeight;
            fromBottomLine -= 10;
        }
    }

    public void clearChat(){
        messageBubbles.clear();
        this.paintComponent(this.getGraphics());
    }

    public void addYourMessage(String message){
        this.addMessage(message, true);
    }
    public void addEnemyMessage(String message){
        this.addMessage(message, false);
    }

    public void setYourBubbleColor(Color yourBubbleColor) {
        this.yourBubbleColor = yourBubbleColor;
    }

    public void setYourTextColor(Color yourTextColor) {
        this.yourTextColor = yourTextColor;
    }

    public void setEnemyBubbleColor(Color enemyBubbleColor) {
        this.enemyBubbleColor = enemyBubbleColor;
    }

    public void setEnemyTextColor(Color enemyTextColor) {
        this.enemyTextColor = enemyTextColor;
    }
}
