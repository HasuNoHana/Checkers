package model;


import javax.swing.*;
import java.awt.*;

/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
public class Menu {
    private Menu(){}
    private static final UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
    private static class MenuLookPanel extends JPanel{
        protected MenuLookPanel(){
            this.setLayout(new GridLayout(infos.length,1, Constants.LayoutsConstants.H_GAP, Constants.LayoutsConstants.V_GAP));
            for(UIManager.LookAndFeelInfo info : infos ){
                this.addButton(info.getName(), info.getClassName());
            }
        }
        private void addButton(String name, final String plafName){
            JButton button = new JButton(name);
            this.add(button);
            button.addActionListener(e -> {
                try{
                    UIManager.setLookAndFeel(plafName);
                    for( int i = 0; i<FramesArray.len(); ++i){
                        SwingUtilities.updateComponentTreeUI(FramesArray.get(i));
                    }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            });
        }
    }
    public static final MenuLookPanel menuLookPanel = new MenuLookPanel();
}
