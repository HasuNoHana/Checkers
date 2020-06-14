package view;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import model.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class MenuLookPanel extends JPanel {
    private ArrayList<JButton> buttons;
    private GridBagConstraints constraints;
    protected MenuLookPanel(){
        this.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1.0;
        constraints.weightx = 1.0;
        constraints.insets = new Insets(Constants.LayoutsConstants.V_GAP/2,Constants.LayoutsConstants.H_GAP/2,Constants.LayoutsConstants.V_GAP/2,Constants.LayoutsConstants.H_GAP/2);
        constraints.gridx = 0;
        buttons = new ArrayList<>();
    }
    public void addButton(UIManager.LookAndFeelInfo lookAndFeelInfo, ActionListener actionListener){
        JButton button = new JButton(lookAndFeelInfo.getName());
        button.addActionListener(actionListener);
        button.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                button.setFont(new Font(button.getName(), Font.PLAIN, button.getHeight()*3/8));
            }
        });
        buttons.add(button);
        constraints.gridy = buttons.size()-1;
        this.add(button, constraints);
        this.setLayout(new GridLayout(buttons.size(),1, Constants.LayoutsConstants.H_GAP, Constants.LayoutsConstants.V_GAP));
    }
}