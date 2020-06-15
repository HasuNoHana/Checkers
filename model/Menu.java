package model;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import javax.swing.*;

public class Menu {
    public Menu(){
        infos = UIManager.getInstalledLookAndFeels();
    }

    // Menu looks and feels
    private final UIManager.LookAndFeelInfo[] infos;

    public UIManager.LookAndFeelInfo[] getInfos() {
        return infos;
    }
}
