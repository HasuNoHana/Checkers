package view;

import controller.ViewStateHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
public class BoardFrame extends JFrame {
    private JPanel board;
    private JTextArea smallChat;
    private class EmotesPanel extends JPanel{
        private ArrayList<JButton> emotes;
        private JTextArea textArea;
        private class EmoteActionListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append(e.getActionCommand()+"\n");
            }
        }
        public EmotesPanel(JTextArea textArea){
            this.textArea = textArea;
            setLayout(new GridLayout());
            emotes = new ArrayList<JButton>();
            emotes.add(new JButton(":)"));
            emotes.add(new JButton(":)"));
            emotes.add(new JButton(":("));
            emotes.add(new JButton(":/"));
            emotes.add(new JButton(":<"));
            emotes.add(new JButton(":>"));
            emotes.add(new JButton(":O"));
            emotes.add(new JButton(":P"));


            for( JButton button : emotes ){
                button.addActionListener(new EmoteActionListener());
                button.setActionCommand(button.getText());
                add(button);
            }
        }
    }
    private EmotesPanel emotesPanel;
    private JButton menuButton;


    public BoardFrame(){
        super("Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 1;

        smallChat = new JTextArea();
        smallChat.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(smallChat);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.1;
        constraints.weighty = 0.9;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridy = 0;
        add(scrollPane, constraints);

        board = new JPanel();
        board.setBackground(Color.RED);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.9;
        constraints.weighty = 0.9;
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.gridy = 0;
        add(board, constraints);


        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        add(new JLabel("Checkers"), constraints);

        emotesPanel = new EmotesPanel(smallChat);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.9;
        constraints.weighty = 0.1;
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        add(emotesPanel, constraints);

        menuButton = new JButton("Menu");
        menuButton.addActionListener(ViewStateHandler.changeStateListener);
        menuButton.setActionCommand("Menu");
        menuButton.setIcon(new ImageIcon(this.getClass().getResource("../graphics/back.png")));
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.gridx = 2;
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        add(menuButton, constraints);


    }
}
