package controller;

import model.FramesArray;
import view.Board;
import view.ConnectionScreen;
import view.MainMenu;
import view.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ViewStateHandler{

    private static class ChangeStateListener implements ActionListener {
        public ChangeStateListener(){ }

        @Override
        public void actionPerformed(ActionEvent e) {
            String source = e.getActionCommand();
            if(source.toLowerCase().equals("exit")){
                System.exit(0);
            }
            FramesArray.changeVisibleFrame(source);
        }
    }
    public static ChangeStateListener changeStateListener = new ChangeStateListener();

    public ViewStateHandler(){
        EventQueue.invokeLater(
                new Runnable() {
//                    int[] xPos = {100, 700, 100, 700};
//                    int[] yPos = {0, 0, 450, 450};
//                    int xSize = 600;
//                    int ySize = 450;
                    ChangeStateListener changeStateListener = new ChangeStateListener();

                    @Override
                    public void run() {
                        FramesArray.add(new MainMenu());
                        FramesArray.add(new Settings());
                        FramesArray.add(new Board());
                        FramesArray.add(new ConnectionScreen());

                        FramesArray.get(0).setVisible(true);

//                        JFrame testButton = new JFrame("TestButton");
//                        testButton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                        testButton.setSize(100, 200);
//                        testButton.setLocation(1400, 0);
//                        testButton.setLayout(new GridLayout(2, 1));
//                        JButton button = new JButton("Next");
//                        JButton button2 = new JButton("Exit");
//                        final int[] num = {0};
//                        button.addActionListener(new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//
//                                int fSizeY = FramesArray.get(num[0]).getSize().height;
//                                int fPosX = FramesArray.get(num[0]).getLocation().x;
//                                int fPosY = FramesArray.get(num[0]).getLocation().y;
//                                int fSizeX = FramesArray.get(num[0]).getSize().width;
//                                FramesArray.get(num[0]).setVisible(false);
//                                num[0] = (num[0] +1)%4;
//                                System.out.println(num[0]);
//                                FramesArray.get(num[0]).setLocation(fPosX, fPosY);
//                                FramesArray.get(num[0]).setSize(fSizeX, fSizeY);
//                                FramesArray.get(num[0]).setVisible(true);
//                            }
//                        });
//                        button2.addActionListener(new ActionListener() {
//                            @Override
//                            public void actionPerformed(ActionEvent e) {
//                                System.exit(0);
//                            }
//                        });
//
//                        testButton.add(button);
//                        testButton.add(button2);
//                        testButton.setVisible(true);
//                        FramesArray.get(0).setVisible(true);

                    }
                }
        );
    }
}
