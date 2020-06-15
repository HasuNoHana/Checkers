package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuChatPanel extends JPanel {
    private final JButton sendButton;
    private final JTextField messField;
    private final ChatPanel chatPanel;
    public MenuChatPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        chatPanel = new ChatPanel();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;

        add(chatPanel, constraints);


        messField = new JTextField(10);
        sendButton = new JButton("Send");
        sendButton.setEnabled(false);


        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new GridBagLayout());
        GridBagConstraints innerConstrains = new GridBagConstraints();
        innerConstrains.fill = GridBagConstraints.BOTH;
        innerConstrains.weightx = 0.9;
        innerConstrains.weighty = 1;
        innerConstrains.gridx = 0;
        innerConstrains.gridy = 0;
        messagePanel.add(messField, innerConstrains);
        innerConstrains.fill = GridBagConstraints.BOTH;
        innerConstrains.weightx = 0.1;
        innerConstrains.weighty = 1;
        innerConstrains.gridx = 1;
        innerConstrains.gridy = 0;
        messagePanel.add(sendButton, innerConstrains);

        constraints.weightx = 1;
        constraints.weighty = 0.025;
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(messagePanel, constraints);
    }
    public void addSendListener(ActionListener actionListener){
        this.sendButton.addActionListener(actionListener);
    }
    public void addYourMessToChat(String message){this.chatPanel.addYourMessage(message);}
    public void addEnemyMessToChat(String message){this.chatPanel.addEnemyMessage(message);}
    public ChatPanel getChatPanel(){ return this.chatPanel; }
    public JTextField getMessField(){ return messField; }
    public JButton getSendButton(){ return sendButton; }
}
