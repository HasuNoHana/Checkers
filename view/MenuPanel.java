package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel {
    private final JLabel logo;
    private final JPanel enemyReady;
    private final JLabel yourName;
    private final JLabel enemyName;

    private final JButton startButton;
    private final JButton connectButton;
    private final JButton settingsButton;
    private final JButton exitButton;
    MenuPanel(){
        setLayout(new GridLayout(6, 1, Constants.LayoutsConstants.H_GAP, Constants.LayoutsConstants.V_GAP));

        logo = new JLabel();
        logo.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                BufferedImage img = null;
                try {
                    File imageFile = new File(".\\graphics\\logo.png");
                    img = ImageIO.read(imageFile);
                } catch (IOException exception) {
                    System.err.println("INFO: Could not open logo file.");
                    System.exit(1);
                }
                if( logo.getWidth()>0 && logo.getHeight()>0 ){
                    Image dimg = img.getScaledInstance(logo.getWidth(), logo.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(dimg);
                    logo.setIcon(imageIcon);
                }
            }
        });
        add(logo);

        JPanel usersInfo = new JPanel(new GridLayout(1, 2));

        enemyReady = new JPanel(new GridLayout());
        enemyReady.add(new JLabel("Is enemy ready?", SwingConstants.CENTER));
        enemyReady.setBackground(Color.RED);

        usersInfo.add(enemyReady);

        JPanel playerNames = new JPanel(new GridLayout(2, 1));
        yourName = new JLabel("You: ", SwingConstants.CENTER);
        playerNames.add(yourName);
        enemyName = new JLabel("Enemy: ", SwingConstants.CENTER);
        playerNames.add(enemyName);
        usersInfo.add(playerNames);
        add(usersInfo);

        startButton = new JButton("Start");
        startButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                startButton.setFont(new Font(startButton.getName(), Font.PLAIN, settingsButton.getHeight()*3/4));
            }
        });
        startButton.setEnabled(false);
        add(startButton);

        connectButton = new JButton("Connect");
        connectButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                connectButton.setFont(new Font(connectButton.getName(), Font.PLAIN, connectButton.getHeight()*3/4));
            }
        });
        add(connectButton);

        settingsButton = new JButton("Settings");
        settingsButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                settingsButton.setFont(new Font(settingsButton.getName(), Font.PLAIN, settingsButton.getHeight()*3/4));
            }
        });
        add(settingsButton);

        exitButton = new JButton("Exit");
        exitButton.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                exitButton.setFont(new Font(exitButton.getName(), Font.PLAIN, exitButton.getHeight()*3/4));
            }
        });
        add(exitButton);

        setBackground(Color.white);
    }
    public void addStartListener(ActionListener actionListener){
        this.startButton.addActionListener(actionListener);
    }
    public void addConnectListener(ActionListener actionListener){
        this.connectButton.addActionListener(actionListener);
    }
    public void addSettingsListener(ActionListener actionListener){
        this.settingsButton.addActionListener(actionListener);
    }
    public void addExitListener(ActionListener actionListener){
        this.exitButton.addActionListener(actionListener);
    }
    public JButton getStartButton() {
        return startButton;
    }

    public JButton getConnectButton() {
        return connectButton;
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }
    public void setPlayerName(String name){
        this.yourName.setText("You: "+name);
    }
    public void setEnemyName(String name){
        this.enemyName.setText("Enemy: "+name);
    }
    public void setEnemyReady(boolean isReady){
        if(isReady){
            enemyReady.setBackground(Color.GREEN);
        }else{
            enemyReady.setBackground(Color.RED);
            this.setEnemyName("");
        }
    }
}
