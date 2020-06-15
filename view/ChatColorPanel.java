package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.Constants;

import javax.swing.*;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.colorchooser.ColorSelectionModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class ChatColorPanel extends JPanel {
    private class MessageBubble extends JComponent{
        private final String message;
        public Color bubbleColor;
        public Color textColor;
        private MessageBubble(JColorChooser bubbleColorChooser, JColorChooser textColorChooser){
            super();
            this.message = "Test message.";
            setPreferredSize(new Dimension(120, 40));
            bubbleColor = bubbleColorChooser.getColor();
            textColor = textColorChooser.getColor();
        }
        public void paint(Graphics g) {
            FontMetrics fm = g.getFontMetrics();
            if (message != null) {
                Rectangle2D rectangle2D = fm.getStringBounds(message, g);
                int messWidth = (int)rectangle2D.getWidth();
                int messHeight = (int)rectangle2D.getHeight();
                int bubbleWidth = messWidth + Constants.ChatConstants.BUBBLE_GAP *2;
                int bubbleHeight = messHeight + Constants.ChatConstants.BUBBLE_GAP*2;
                g.setColor(bubbleColor);
                g.fillRoundRect(0, 0, bubbleWidth, bubbleHeight, 10, 10);
                g.setColor(textColor);
                g.drawString(message, Constants.ChatConstants.BUBBLE_GAP, bubbleHeight/2+messHeight/2);
            }
        }

    }
    private final JColorChooser yourBubbleColorChooser;
    private final JColorChooser yourTextColorChooser;
    private final JColorChooser enemyBubbleColorChooser;
    private final JColorChooser enemyTextColorChooser;

    private final JButton yourChangeButton;
    private final JButton enemyChangeButton;

    public ChatColorPanel(){
        setLayout(new GridLayout());

        JTabbedPane upperTabbedPane = new JTabbedPane();


        // You
        JTabbedPane yourTabbedPane = new JTabbedPane();

        yourBubbleColorChooser = new JColorChooser(Constants.ChatConstants.YOUR_BUBBLE_COLOR);
        AbstractColorChooserPanel[] panels = yourBubbleColorChooser.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels) {
            if(!accp.getDisplayName().equals("RGB")){
                yourBubbleColorChooser.removeChooserPanel(accp);
            }
        }
        yourTextColorChooser = new JColorChooser(Constants.ChatConstants.YOUR_TEXT_COLOR);
        panels = yourTextColorChooser.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels) {
            if(!accp.getDisplayName().equals("RGB")){
                yourTextColorChooser.removeChooserPanel(accp);
            }
        }

        MessageBubble yourMessageBubble1 = new MessageBubble(yourBubbleColorChooser, yourTextColorChooser);
        MessageBubble yourMessageBubble2 = new MessageBubble(yourBubbleColorChooser, yourTextColorChooser);

        yourBubbleColorChooser.setPreviewPanel(yourMessageBubble1);
        yourTextColorChooser.setPreviewPanel(yourMessageBubble2);

        ColorSelectionModel model = yourBubbleColorChooser.getSelectionModel();
        model.addChangeListener(evt -> {
            ColorSelectionModel model12 = (ColorSelectionModel) evt.getSource();
            yourMessageBubble1.bubbleColor = model12.getSelectedColor();
            yourMessageBubble2.bubbleColor = model12.getSelectedColor();
        });

        model = yourTextColorChooser.getSelectionModel();
        model.addChangeListener(evt -> {
            ColorSelectionModel model1 = (ColorSelectionModel) evt.getSource();
            yourMessageBubble1.textColor = model1.getSelectedColor();
            yourMessageBubble2.textColor = model1.getSelectedColor();
        });

        yourTabbedPane.addTab("Bubble", null, yourBubbleColorChooser, "Choose bubble color.");
        yourTabbedPane.setMnemonicAt(0, KeyEvent.VK_0);
        yourTabbedPane.addTab("Text", null, yourTextColorChooser, "Choose text color.");
        yourTabbedPane.setMnemonicAt(1, KeyEvent.VK_1);

        // Enemy
        JTabbedPane enemyTabbedPane = new JTabbedPane();

        enemyBubbleColorChooser = new JColorChooser(Constants.ChatConstants.ENEMY_BUBBLE_COLOR);
        panels = enemyBubbleColorChooser.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels) {
            if(!accp.getDisplayName().equals("RGB")){
                enemyBubbleColorChooser.removeChooserPanel(accp);
            }
        }
        enemyTextColorChooser = new JColorChooser(Constants.ChatConstants.ENEMY_TEXT_COLOR);
        panels = enemyTextColorChooser.getChooserPanels();
        for (AbstractColorChooserPanel accp : panels) {
            if(!accp.getDisplayName().equals("RGB")){
                enemyTextColorChooser.removeChooserPanel(accp);
            }
        }

        MessageBubble enemyMessageBubble1 = new MessageBubble(enemyBubbleColorChooser, enemyTextColorChooser);
        MessageBubble enemyMessageBubble2 = new MessageBubble(enemyBubbleColorChooser, enemyTextColorChooser);

        enemyBubbleColorChooser.setPreviewPanel(enemyMessageBubble1);
        enemyTextColorChooser.setPreviewPanel(enemyMessageBubble2);

        model = enemyBubbleColorChooser.getSelectionModel();
        model.addChangeListener(evt -> {
            ColorSelectionModel model13 = (ColorSelectionModel) evt.getSource();
            enemyMessageBubble1.bubbleColor = model13.getSelectedColor();
            enemyMessageBubble2.bubbleColor = model13.getSelectedColor();
        });

        model = enemyTextColorChooser.getSelectionModel();
        model.addChangeListener(evt -> {
            ColorSelectionModel model1 = (ColorSelectionModel) evt.getSource();
            enemyMessageBubble1.textColor = model1.getSelectedColor();
            enemyMessageBubble2.textColor = model1.getSelectedColor();
        });

        enemyTabbedPane.addTab("Bubble", null, enemyBubbleColorChooser, "Choose bubble color.");
        enemyTabbedPane.setMnemonicAt(0, KeyEvent.VK_0);
        enemyTabbedPane.addTab("Text", null, enemyTextColorChooser, "Choose text color.");
        enemyTabbedPane.setMnemonicAt(1, KeyEvent.VK_1);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        JPanel yourChangePanel = new JPanel(new GridBagLayout());
        constraints.weighty = 0.95;
        constraints.gridy = 0;
        yourChangePanel.add(yourTabbedPane, constraints);
        yourChangeButton = new JButton("Change");
        constraints.weighty = 0.05;
        constraints.gridy = 1;
        yourChangePanel.add(yourChangeButton, constraints);

        upperTabbedPane.addTab("You", null, yourChangePanel, "Choose your chat colors.");
        upperTabbedPane.setMnemonicAt(0, KeyEvent.VK_0);

        JPanel enemyChangePanel = new JPanel(new GridBagLayout());
        constraints.weighty = 0.95;
        constraints.gridy = 0;
        enemyChangePanel.add(enemyTabbedPane, constraints);
        enemyChangeButton = new JButton("Change");
        constraints.weighty = 0.05;
        constraints.gridy = 1;
        enemyChangePanel.add(enemyChangeButton, constraints);


        upperTabbedPane.addTab("Enemy", null, enemyChangePanel, "Choose enemy chat colors.");
        upperTabbedPane.setMnemonicAt(1, KeyEvent.VK_1);

        add(upperTabbedPane);
    }

    public JButton getYourChangeButton(){ return yourChangeButton; }
    public JButton getEnemyChangeButton(){ return enemyChangeButton; }

    public Color getYourBubbleColor(){ return yourBubbleColorChooser.getColor(); }
    public Color getYourTextColor(){ return yourTextColorChooser.getColor(); }
    public Color getEnemyBubbleColor(){ return enemyBubbleColorChooser.getColor(); }
    public Color getEnemyTextColor(){ return enemyTextColorChooser.getColor(); }

}
