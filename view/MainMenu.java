package view;

import controller.ViewStateHandler;
import model.FramesArray;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame{
    private class MenuPanel extends JPanel{
        private JLabel logo;
        private JPanel enemyRead;
        private JButton exitButton;
        private JButton startButton;
        private JButton settingsButton;
        private JButton connectButton;
        MenuPanel(){
            setLayout(new GridLayout(6, 1, 20, 20));

//            JLabel logoLabel = new JLabel();
//            add(logoLabel);
//            ImageIcon icon = new ImageIcon(this.getClass().getResource("../graphics/logo.png"));
//            Image img = icon.getImage();
//            Image resizedImage = img.getScaledInstance(logoLabel.getWidth(), logoLabel.getHeight(),  java.awt.Image.SCALE_SMOOTH);
//            logoLabel.setIcon(new ImageIcon(resizedImage));
            logo = new JLabel(new ImageIcon(this.getClass().getResource("../graphics/logo.png")));
            add(logo);

            enemyRead = new JPanel();
            enemyRead.setBackground(Color.RED);
            add(enemyRead);

            startButton = new JButton("Start");
            startButton.addActionListener(ViewStateHandler.changeStateListener);
            startButton.setActionCommand("Board");
            add(startButton);

            connectButton = new JButton("Connect");
            connectButton.addActionListener(ViewStateHandler.changeStateListener);
            connectButton.setActionCommand("Connection");
            add(connectButton);

            settingsButton = new JButton("Settings");
            settingsButton.addActionListener(ViewStateHandler.changeStateListener);
            settingsButton.setActionCommand("Settings");
            add(settingsButton);

            exitButton = new JButton("Exit");
            exitButton.addActionListener(ViewStateHandler.changeStateListener);
            exitButton.setActionCommand("Exit");
            add(exitButton);

            setBackground(Color.white);
        }

        void setEnemyReady(){
            if(isEnemyRead){
                enemyRead.setBackground(Color.GREEN);
            }else{
                enemyRead.setBackground(Color.RED);
            }
        }
    }
    private class ChatPanel extends JPanel{
        private final int MAX_MESS_LEN = 15;
        ChatPanel(){
            setLayout(new GridBagLayout());
            GridBagConstraints constraints = new GridBagConstraints();

            JTextArea textArea = new JTextArea(8, 20);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            constraints.fill = GridBagConstraints.BOTH;
            constraints.weightx = 1;
            constraints.weighty = 1;
            constraints.gridx = 0;
            constraints.gridy = 0;

            add(scrollPane, constraints);



            final JTextField messField = new JTextField(10);
            final JButton sendButton = new JButton("Send");
            sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int len = messField.getText().length();
                    String text = "[MESS]: \t";
                    System.out.println(len);
                    int i = 0;
                    while(i<messField.getText().length()/MAX_MESS_LEN){
                        text = text.concat(messField.getText().substring(i*MAX_MESS_LEN, (i+1)*MAX_MESS_LEN));
                        text = text.concat("\n\t");
                        len -= MAX_MESS_LEN;
                        ++i;
                    }
                    System.out.println(i);
                    System.out.println(len);
                    text = text.concat(messField.getText().substring(i*MAX_MESS_LEN, i*MAX_MESS_LEN+len));
                    textArea.append(text+"\n\n");
                    messField.setText("");
                }
            });


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
    }
    private static boolean isEnemyRead = false;
    private static MenuPanel menuPanel;
    private static ChatPanel chatPanel;
    public MainMenu(){
        super("Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout());

        menuPanel = new MenuPanel();
        add(menuPanel);

        chatPanel = new ChatPanel();
        add(chatPanel);
    }

    public static void setIsEnemyRead(boolean bool){
        isEnemyRead = bool;
        System.out.println(isEnemyRead);
        menuPanel.setEnemyReady();
    }
}
