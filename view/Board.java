package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board extends JFrame{
    private final JPanel board;
    private final JTextArea smallChat;
    private final EmotesPanel emotesPanel;
    private final JButton menuButton;


    public Board(){
        super("Board");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1;
        constraints.weighty = 1;

        smallChat = new JTextArea();

        DefaultCaret caret = (DefaultCaret)smallChat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
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

        // Todo: gra tutaj bedzie zamiast jlabel
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
                if( checkers.getWidth()>0 && checkers.getHeight()>0 ){
                    Image dimg = img.getScaledInstance(checkers.getWidth(), checkers.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(dimg);
                    checkers.setIcon(imageIcon);
                }
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
                if( logo.getWidth()>0 && logo.getHeight()>0 ){
                    Image dimg = img.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(dimg);
                    logo.setIcon(imageIcon);
                }
            }
        });
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.gridx = 0;
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        add(logo, constraints);

        emotesPanel = new EmotesPanel();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.9;
        constraints.weighty = 0.1;
        constraints.gridx = 1;
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        add(emotesPanel, constraints);

        menuButton = new JButton("Menu");
        menuButton.setIcon(new ImageIcon(this.getClass().getResource("../graphics/back.png")));
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.1;
        constraints.weighty = 0.1;
        constraints.gridx = 2;
        constraints.gridwidth = 1;
        constraints.gridy = 1;
        add(menuButton, constraints);
    }

    public JTextArea getSmallChat(){
        return smallChat;
    }

    public void addMessToChat(String message){
        this.smallChat.append(message+"\n");
    }

    public EmotesPanel getEmotesPanel(){ return  emotesPanel; }

    public void addBackListener(ActionListener actionListener){
        this.menuButton.addActionListener(actionListener);
    }
    public JButton getMenuButton(){
        return this.menuButton;
    }
}
