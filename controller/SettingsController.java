package controller;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.FramesArray;
import model.Models;
import view.Views;

import javax.swing.*;

public class SettingsController {

    public SettingsController(Views views, Models models, FramesArray framesArray, ViewsController viewsController){

        // User information change
        views.settingsFrame.addBackListener(viewsController.getChangeStateListener());
        views.settingsFrame.getBackButton().setActionCommand("Menu");

        // Todo: Change checkers look

        // Menu look and feel change
        for(UIManager.LookAndFeelInfo lookAndFeelInfo : models.menu.getInfos() ){
            views.settingsFrame.getMenuLookPanel().addButton(lookAndFeelInfo, e -> {
                try{
                    UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                    for(int i = 0; i< framesArray.len(); ++i){
                        SwingUtilities.updateComponentTreeUI(framesArray.getFrames().get(i));
                    }
                }catch(Exception exception){
                    // Todo: obsluga exception
                    exception.printStackTrace();
                }
            });
        }

        // Chat colors
        views.settingsFrame.getChat().getEnemyChangeButton().addActionListener(e -> {
            views.mainMenuFrame.getMenuChatPanel().getChatPanel().setEnemyBubbleColor(views.settingsFrame.getChat().getEnemyBubbleColor());
            views.mainMenuFrame.getMenuChatPanel().getChatPanel().setEnemyTextColor(views.settingsFrame.getChat().getEnemyTextColor());
            views.mainMenuFrame.getMenuChatPanel().getChatPanel().setYourBubbleColor(views.settingsFrame.getChat().getYourBubbleColor());
            views.mainMenuFrame.getMenuChatPanel().getChatPanel().setYourTextColor(views.settingsFrame.getChat().getYourTextColor());
        });
        views.settingsFrame.getChat().getYourChangeButton().addActionListener(e -> {
            views.mainMenuFrame.getMenuChatPanel().getChatPanel().setYourBubbleColor(views.settingsFrame.getChat().getYourBubbleColor());
            views.mainMenuFrame.getMenuChatPanel().getChatPanel().setYourTextColor(views.settingsFrame.getChat().getYourTextColor());
            views.mainMenuFrame.getMenuChatPanel().getChatPanel().setEnemyBubbleColor(views.settingsFrame.getChat().getEnemyBubbleColor());
            views.mainMenuFrame.getMenuChatPanel().getChatPanel().setEnemyTextColor(views.settingsFrame.getChat().getEnemyTextColor());
        });



    }
}
