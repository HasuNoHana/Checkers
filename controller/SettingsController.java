package controller;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.FramesArray;
import model.Menu;
import view.Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsController {
    private Settings settings;

    public SettingsController(Settings settings, Menu menu, FramesArray framesArray, ViewsController viewsController){
        this.settings = settings;

        // User information change
        this.settings.addBackListener(viewsController.getChangeStateListener());
        this.settings.getBackButton().setActionCommand("Menu");

        // Todo: Change checkers look

        // Menu look and feel change
        for(UIManager.LookAndFeelInfo lookAndFeelInfo : menu.getInfos() ){
            settings.getMenuLookPanel().addButton(lookAndFeelInfo, e -> {
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
