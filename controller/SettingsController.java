package controller;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.FramesArray;
import model.Menu;
import view.SettingsFrame;

import javax.swing.*;

public class SettingsController {
    private SettingsFrame settingsFrame;

    public SettingsController(SettingsFrame settingsFrame, Menu menu, FramesArray framesArray, ViewsController viewsController){
        this.settingsFrame = settingsFrame;

        // User information change
        this.settingsFrame.addBackListener(viewsController.getChangeStateListener());
        this.settingsFrame.getBackButton().setActionCommand("Menu");

        // Todo: Change checkers look

        // Menu look and feel change
        for(UIManager.LookAndFeelInfo lookAndFeelInfo : menu.getInfos() ){
            settingsFrame.getMenuLookPanel().addButton(lookAndFeelInfo, e -> {
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

        // Todo: Change chat look


    }
}
