package view;

import model.ChatMessage;
import model.Constants;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionListener;

public class ChatPanel extends JPanel {
    private final JButton sendButton;
    private final JTextField messField;
    private final JPanel messagePanel;
    private final JTextArea textArea;
    public ChatPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        textArea = new JTextArea(8, 20);
        DefaultCaret caret = (DefaultCaret)textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;

        add(scrollPane, constraints);


        messField = new JTextField(10);
        sendButton = new JButton("Send");
        sendButton.setEnabled(false);


        messagePanel = new JPanel();
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
    public void addMessToChat(ChatMessage message){
        this.textArea.append(message.getText());
    }
    public void addMessToChat(String message){
        this.textArea.append(message+"\n");
    }
    public JTextField getMessField(){ return messField; }
    public JButton getSendButton(){ return sendButton; }
}
