package view;

import controller.ViewStateHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
                textArea.append(" "+e.getActionCommand()+"\n");
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
        board.setLayout(new GridLayout());
        JLabel checkers = new JLabel();
        checkers.setSize(1000, 1000);
        board.add(checkers);
        checkers.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                BufferedImage img = null;
                try {
                    File imageFile = new File(".\\graphics\\checkers.png");
                    img = ImageIO.read(imageFile);
                } catch (IOException exception) {
                    exception.printStackTrace();
                    System.exit(1);
                }
                Image dimg = img.getScaledInstance(checkers.getWidth(), checkers.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(dimg);
                checkers.setIcon(imageIcon);
            }
        });
        board.setBackground(Color.RED);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.9;
        constraints.weighty = 0.9;
        constraints.gridx = 1;
        constraints.gridwidth = 2;
        constraints.gridy = 0;
        add(board, constraints);

        JLabel logo = new JLabel();
        logo.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                BufferedImage img = null;
                try {
                    File imageFile = new File(".\\graphics\\logo.png");
                    img = ImageIO.read(imageFile);
                } catch (IOException exception) {
                    exception.printStackTrace();
                    System.exit(1);
                }
                Image dimg = img.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(dimg);
                logo.setIcon(imageIcon);
            }
        });
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        add(logo, constraints);

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
