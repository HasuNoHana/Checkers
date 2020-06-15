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

        // Checkers look

        for( JButton button : views.settingsFrame.getBoard().getButtons() ){
            button.addActionListener(e -> {
                if(!button.getActionCommand().equals(models.checkersModel.getEnemyColor())){
                    models.checkersModel.setYourColor(button.getActionCommand());
                    views.boardFrame.getGameView().changeImageRepository(models.pawnImagesModel.getImagesRepo(button.getActionCommand(), models.checkersModel.getEnemyColor()));
                    views.boardFrame.getGameView().updateView(models.checkersModel);
                }
            });
        }

        // Menu look and feel change
        for(UIManager.LookAndFeelInfo lookAndFeelInfo : models.menu.getInfos() ){
            views.settingsFrame.getMenuLookPanel().addButton(lookAndFeelInfo, e -> {
                try{
                    UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
                    for(int i = 0; i< framesArray.len(); ++i){
                        SwingUtilities.updateComponentTreeUI(framesArray.getFrames().get(i));
                    }
                }catch(Exception exception){
                    System.err.println("INFO: Could not open LookAndFeel.");
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
