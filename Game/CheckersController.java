package Game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CheckersController implements MouseListener {
    private GameModel model;
    private GameView view;
    private boolean fieldIsClicked;
    private PicturePanel oldFieldPanel;
    private Field lastEnteredField;
    private ImageRepository imageRepository;

    public CheckersController(GameModel g, GameView mf, ImageRepository imageRepository) {
        model = g;
        view = mf;
        imageRepository = imageRepository;
        play();
    }

    public int getBoardSideSize() {
        return model.getSideSize();
    }

    public Field getField(int i, int j) {
        return model.getField(i, j);
    }

    public void play() {
        view.updateView(model);
        this.addListiners();
    }

    private void addListiners() {
        int BOARDSIZE = this.getBoardSideSize();
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                view.fieldsPanels[i][j].addMouseListener(this);
            }
        }
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if(model.isEndGame())
            return;
        if (!this.fieldIsClicked) {
            PicturePanel oldFieldPanel = (PicturePanel) mouseEvent.getComponent();
            clickPanel(oldFieldPanel);
        } else {
            PicturePanel newFieldPanel = (PicturePanel) mouseEvent.getComponent();
            Field oldField = this.getField(this.oldFieldPanel.getRow(), this.oldFieldPanel.getCol());
            Field newField = this.getField(newFieldPanel.getRow(), newFieldPanel.getCol());

            boolean moveMade = model.makeMove(oldField,newField);
            if (moveMade) {
                unclickPanel();
                view.updateView(model);
                highlyCurrentField(mouseEvent);
            }
            else { // chose new field
                reclickPanel(newFieldPanel);
            }
        }
    }

    private void clickPanel(PicturePanel oldFieldPanel) {
        this.oldFieldPanel = oldFieldPanel;
        this.fieldIsClicked = true;
        this.oldFieldPanel.click();
    }

    private void reclickPanel(PicturePanel newFieldPanel) {
        this.oldFieldPanel.unclick();
        this.oldFieldPanel = newFieldPanel;
        this.oldFieldPanel.click();
    }

    private void unclickPanel() {
        this.oldFieldPanel.unclick();
        this.oldFieldPanel = null;
        this.fieldIsClicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        highlyCurrentField(mouseEvent);
    }

    private void highlyCurrentField(MouseEvent mouseEvent) {
        this.lastEnteredField = getCurrentField(mouseEvent);
        for (Field field:model.getAvaliableMoves(lastEnteredField)
             ) {
            view.getPicturePanel(field.getRow(),field.getCol()).setBackground(new Color(0xb7fffa));
        }
    }

    private Field getCurrentField(MouseEvent mouseEvent) {
        PicturePanel oldFieldPanel = (PicturePanel) mouseEvent.getComponent();
        return getField(oldFieldPanel.getRow(), oldFieldPanel.getCol());
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        for (Field field:model.getAvaliableMoves(lastEnteredField)
        ) {
            PicturePanel currentPicturePanel = view.getPicturePanel(field.getRow(), field.getCol());
            currentPicturePanel.setBackground(currentPicturePanel.background);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

}

