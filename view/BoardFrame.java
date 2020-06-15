package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.ImageRepository;
import model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BoardFrame extends JFrame{
    private final CheckersView game;
    private final ChatPanel smallChat;
    private final EmotesPanel emotesPanel;
    private final JButton menuButton;

    private final JPanel upperPanel;
    private final JPanel lowerPanel;


    public BoardFrame(){
        super("BoardFrame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        upperPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.5;
        constraints.weighty = 0.5;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;

        smallChat = new ChatPanel(Constants.ChatConstants.BUBBLE_GAP_EMOTES);

        constraints.gridx = 0;
        constraints.weightx = 0.1;
        upperPanel.add(smallChat, constraints);

        JPanel board = new JPanel();
        board.setLayout(new GridLayout());

        ImageRepository imageRepository = new ImageRepository();
        game = new CheckersView(imageRepository);

        board.add(game);

//        // Todo: gra tutaj bedzie zamiast jlabel
//        JLabel checkers = new JLabel();
//        checkers.setSize(1000, 1000);
//        board.add(checkers);
//        checkers.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                super.componentResized(e);
//                BufferedImage img = null;
//                try {
//                    File imageFile = new File(".\\graphics\\checkers.png");
//                    img = ImageIO.read(imageFile);
//                } catch (IOException exception) {
//                    exception.printStackTrace();
//                    System.exit(1);
//                }
//                if( checkers.getWidth()>0 && checkers.getHeight()>0 ){
//                    Image dimg = img.getScaledInstance(checkers.getWidth(), checkers.getHeight(), Image.SCALE_SMOOTH);
//                    ImageIcon imageIcon = new ImageIcon(dimg);
//                    checkers.setIcon(imageIcon);
//                }
//            }
//        });
//        board.setBackground(Color.RED);

        constraints.gridx = 1;
        constraints.weightx = 0.9;
        upperPanel.add(board, constraints);


        lowerPanel = new JPanel(new BorderLayout());

        emotesPanel = new EmotesPanel();

        lowerPanel.add(emotesPanel, BorderLayout.CENTER);

        menuButton = new JButton("Menu");
        menuButton.setIcon(new ImageIcon(this.getClass().getResource("../graphics/back.png")));
        lowerPanel.add(menuButton, BorderLayout.LINE_END);

        add(upperPanel, BorderLayout.CENTER);
        add(lowerPanel, BorderLayout.SOUTH);
    }

    public void addYourMessToChat(String message){
        this.smallChat.addYourMessage(message);
    }
    public void addEnemyMessToChat(String message){
        this.smallChat.addEnemyMessage(message);
    }

    public EmotesPanel getEmotesPanel(){ return  emotesPanel; }

    public void addBackListener(ActionListener actionListener){
        this.menuButton.addActionListener(actionListener);
    }
    public JButton getMenuButton(){
        return this.menuButton;
    }
    public CheckersView getGameView(){ return game; }
}
