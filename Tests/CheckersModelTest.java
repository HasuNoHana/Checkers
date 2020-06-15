package Tests;

import controller.CheckersController;
import controller.SocketController;
import model.*;
import org.junit.jupiter.api.Test;
import view.CheckersView;
import view.Views;

import static org.junit.jupiter.api.Assertions.*;

public class CheckersModelTest {


    @Test
    public void boardInitializationTest() {
        CheckersModel model = new CheckersModel();


        Field oldField = model.getField(1, 0);
        Field newField = model.getField(2, 0);


        assertTrue(oldField.isOccupied());
        assertFalse(newField.isOccupied());
    }

    @Test
    public void moveDownNotAvailable() {
        Models models = new Models();
        Views views = new Views();
        SocketController socketController = new SocketController(models, views);
        PawnImagesModel pawnImagesModel = new PawnImagesModel();
        CheckersModel model = new CheckersModel();
        CheckersView view = new CheckersView(pawnImagesModel.getImagesRepo(Constants.GameConstants.PAWN_COLORS[0], Constants.GameConstants.PAWN_COLORS[1]));
        CheckersController checkersController = new CheckersController(model, view, socketController);
        Field oldField = model.getField(1, 0);
        Field newField = model.getField(2, 0);

        assertFalse(checkersController.isNormalMoveAvailable(oldField, newField));
    }

    @Test
    public void moveDiagonalAvailable() {
        Models models = new Models();
        Views views = new Views();
        SocketController socketController = new SocketController(models, views);
        PawnImagesModel pawnImagesModel = new PawnImagesModel();
        CheckersModel model = new CheckersModel();
        CheckersView view = new CheckersView(pawnImagesModel.getImagesRepo(Constants.GameConstants.PAWN_COLORS[0], Constants.GameConstants.PAWN_COLORS[1]));
        CheckersController checkersController = new CheckersController(model, view, socketController);

        Field oldField = model.getField(6, 1);
        Field newField = model.getField(5, 0);

        assertTrue(checkersController.isNormalMoveAvailable(oldField, newField));
    }

    @Test
    public void moveDiagonalyTwoFields() {
        Models models = new Models();
        Views views = new Views();
        SocketController socketController = new SocketController(models, views);
        PawnImagesModel pawnImagesModel = new PawnImagesModel();
        CheckersModel model = new CheckersModel();
        CheckersView view = new CheckersView(pawnImagesModel.getImagesRepo(Constants.GameConstants.PAWN_COLORS[0], Constants.GameConstants.PAWN_COLORS[1]));
        CheckersController checkersController = new CheckersController(model, view, socketController);

        checkersController.changePlayer();
        Field oldField = model.getField(1, 0);
        Field newField = model.getField(3, 2);

        assertFalse(checkersController.isNormalMoveAvailable(oldField, newField));
    }

    @Test
    public void moveForwardTwoPlaces() {
        Models models = new Models();
        Views views = new Views();
        SocketController socketController = new SocketController(models, views);
        PawnImagesModel pawnImagesModel = new PawnImagesModel();
        CheckersModel model = new CheckersModel();
        CheckersView view = new CheckersView(pawnImagesModel.getImagesRepo(Constants.GameConstants.PAWN_COLORS[0], Constants.GameConstants.PAWN_COLORS[1]));
        CheckersController checkersController = new CheckersController(model, view, socketController);

        checkersController.changePlayer();
        Field oldField = model.getField(1, 0);
        Field newField = model.getField(3, 0);

        assertFalse(checkersController.isNormalMoveAvailable(oldField, newField));
    }

    @Test
    public void moveStepOnAllay() {
        Models models = new Models();
        Views views = new Views();
        SocketController socketController = new SocketController(models, views);
        PawnImagesModel pawnImagesModel = new PawnImagesModel();
        CheckersModel model = new CheckersModel();
        CheckersView view = new CheckersView(pawnImagesModel.getImagesRepo(Constants.GameConstants.PAWN_COLORS[0], Constants.GameConstants.PAWN_COLORS[1]));
        CheckersController checkersController = new CheckersController(model, view, socketController);

        checkersController.changePlayer();
        Field oldField = model.getField(0, 1);
        Field newField = model.getField(1, 0);

        assertFalse(checkersController.isNormalMoveAvailable(oldField, newField));
    }

    @Test
    public void movePawn() {
        Models models = new Models();
        Views views = new Views();
        SocketController socketController = new SocketController(models, views);
        PawnImagesModel pawnImagesModel = new PawnImagesModel();
        CheckersModel model = new CheckersModel();
        CheckersView view = new CheckersView(pawnImagesModel.getImagesRepo(Constants.GameConstants.PAWN_COLORS[0], Constants.GameConstants.PAWN_COLORS[1]));
        CheckersController checkersController = new CheckersController(model, view, socketController);

        checkersController.changePlayer();
        Field oldField = model.getField(1, 0);
        Field newField = model.getField(2, 1);
        assertTrue(oldField.isOccupied());
        assertFalse(newField.isOccupied());

        checkersController.movePawn(oldField,newField);

        assertFalse(oldField.isOccupied());
        assertTrue(newField.isOccupied());
    }

    @Test
    public void makeIncorectMoveTwoFieldDiagonaly() {
        Models models = new Models();
        Views views = new Views();
        SocketController socketController = new SocketController(models, views);
        PawnImagesModel pawnImagesModel = new PawnImagesModel();
        CheckersModel model = new CheckersModel();
        CheckersView view = new CheckersView(pawnImagesModel.getImagesRepo(Constants.GameConstants.PAWN_COLORS[0], Constants.GameConstants.PAWN_COLORS[1]));
        CheckersController checkersController = new CheckersController(model, view, socketController);

        checkersController.changePlayer();
        Field oldField = model.getField(1, 0);
        Field newField = model.getField(3, 2);
        assertTrue(oldField.isOccupied());
        assertFalse(newField.isOccupied());

        boolean moveDone = checkersController.makeMove(oldField,newField);

        assertFalse(moveDone);
        assertTrue(oldField.isOccupied());
        assertFalse(newField.isOccupied());
    }

    @Test
    public void makeCorectMoveOneFieldDiagonaly() {
        Models models = new Models();
        Views views = new Views();
        SocketController socketController = new SocketController(models, views);
        PawnImagesModel pawnImagesModel = new PawnImagesModel();
        CheckersModel model = new CheckersModel();
        CheckersView view = new CheckersView(pawnImagesModel.getImagesRepo(Constants.GameConstants.PAWN_COLORS[0], Constants.GameConstants.PAWN_COLORS[1]));
        CheckersController checkersController = new CheckersController(model, view, socketController);

        Field oldField = model.getField(6, 1);
        Field newField = model.getField(5, 0);
        assertTrue(oldField.isOccupied());
        assertFalse(newField.isOccupied());

        boolean moveDone = checkersController.makeMove(oldField,newField);

        assertTrue(moveDone);
        assertFalse(oldField.isOccupied());
        assertTrue(newField.isOccupied());
    }

    @Test
    public void makeQueen() {
        Models models = new Models();
        Views views = new Views();
        SocketController socketController = new SocketController(models, views);
        PawnImagesModel pawnImagesModel = new PawnImagesModel();
        CheckersModel model = new CheckersModel();
        CheckersView view = new CheckersView(pawnImagesModel.getImagesRepo(Constants.GameConstants.PAWN_COLORS[0], Constants.GameConstants.PAWN_COLORS[1]));
        CheckersController checkersController = new CheckersController(model, view, socketController);

        Field queenField = model.getField(0,1);
        checkersController.movePawn(queenField,model.getField(2,1));
        checkersController.movePawn(model.getField(1,0),model.getField(3,0));
        checkersController.movePawn(model.getField(6,1),model.getField(1,0));
        assertFalse(queenField.isOccupied());

        boolean moveDone = checkersController.makeMove(model.getField(1, 0), queenField);

        assertTrue(moveDone);
        assertTrue(queenField.isOccupied());
        assertTrue(queenField.isQueen());
    }

    @Test
    public void captureOneTime() {
        Models models = new Models();
        Views views = new Views();
        SocketController socketController = new SocketController(models, views);
        PawnImagesModel pawnImagesModel = new PawnImagesModel();
        CheckersModel model = new CheckersModel();
        CheckersView view = new CheckersView(pawnImagesModel.getImagesRepo(Constants.GameConstants.PAWN_COLORS[0], Constants.GameConstants.PAWN_COLORS[1]));
        CheckersController checkersController = new CheckersController(model, view, socketController);

        checkersController.getBoard()[5][2].setPawn(Pawn.BROWN_NORMAL);
        Field oponnentField = checkersController.getField(5,2);

        Field oldField = checkersController.getField(6, 1);
        Field newField = checkersController.getField(5, 2);
        assertTrue(oldField.isOccupied());
        assertFalse(newField.isOccupied());

        boolean moveDone = model.makeMove(oldField,newField);

        assertTrue(moveDone);
        assertFalse(oponnentField.isOccupied());
        assertFalse(oldField.isOccupied());
        assertTrue(newField.isOccupied());
        
    }
    
    @Test
    public void QueenSingleCapture() {
        Models models = new Models();
        Views views = new Views();
        SocketController socketController = new SocketController(models, views);
        PawnImagesModel pawnImagesModel = new PawnImagesModel();
        CheckersModel model = new CheckersModel();
        CheckersView view = new CheckersView(pawnImagesModel.getImagesRepo(Constants.GameConstants.PAWN_COLORS[0], Constants.GameConstants.PAWN_COLORS[1]));
        CheckersController checkersController = new CheckersController(model, view, socketController);
        
        checkersController.getBoard()[0][3].setPawn(Pawn.WHITEQUIEEN);
        Field oldField = checkersController.getField(0, 3);
        Field oponentField = checkersController.getField(1, 4);
        Field newField = checkersController.getField(2, 5);

        moveDone = checkersController.makeMove(oldField,newField);

        assertTrue(moveDone);

        assertFalse(oldField.isOccupied());
        assertTrue(newField.isOccupied());
        assertFalse(oponentField.isOccupied());
    }

    @Test
    public void QueenTwoFieldsAwayCapture() {
        Models models = new Models();
        Views views = new Views();
        SocketController socketController = new SocketController(models, views);
        PawnImagesModel pawnImagesModel = new PawnImagesModel();
        CheckersModel model = new CheckersModel();
        CheckersView view = new CheckersView(pawnImagesModel.getImagesRepo(Constants.GameConstants.PAWN_COLORS[0], Constants.GameConstants.PAWN_COLORS[1]));
        CheckersController checkersController = new CheckersController(model, view, socketController);

        checkersController.changePlayer();
        checkersController.movePawn(model.getField(1,2),model.getField(2,1));
        checkersController.movePawn(model.getField(0,3),model.getField(5,4));
        checkersController.changePlayer();
        checkersController.movePawn(model.getField(6,3),model.getField(1,2));
        Field oldField = model.getField(1, 2);
        Field newField = model.getField(0, 3);
        assertTrue(oldField.isOccupied());
        assertFalse(newField.isOccupied());
        boolean moveDone = checkersController.makeMove(oldField,newField);
        checkersController.changePlayer();
        oldField = model.getField(0, 3);
        newField = model.getField(3, 0);
        Field oponentField = model.getField(2, 1);
        assertTrue(model.getCurrentPlayer()==Player.WHITE);

        moveDone = checkersController.makeMove(oldField,newField);

        assertTrue(moveDone);
        assertFalse(oponentField.isOccupied());
        assertFalse(oldField.isOccupied());
        assertTrue(newField.isOccupied());
    }

    @Test
    public void QueenCaptureQueen() {
        Models models = new Models();
        Views views = new Views();
        SocketController socketController = new SocketController(models, views);
        PawnImagesModel pawnImagesModel = new PawnImagesModel();
        CheckersModel model = new CheckersModel();
        CheckersView view = new CheckersView(pawnImagesModel.getImagesRepo(Constants.GameConstants.PAWN_COLORS[0], Constants.GameConstants.PAWN_COLORS[1]));
        CheckersController checkersController = new CheckersController(model, view, socketController);

        checkersController.changePlayer();
        checkersController.movePawn(model.getField(1,2),model.getField(2,1));
        checkersController.movePawn(model.getField(0,3),model.getField(5,4));
        checkersController.changePlayer();
        checkersController.movePawn(model.getField(6,3),model.getField(1,2));
        Field oldField = model.getField(1, 2);
        Field newField = model.getField(0, 3);
        assertTrue(oldField.isOccupied());
        assertFalse(newField.isOccupied());
        boolean moveDone = checkersController.makeMove(oldField,newField);
        checkersController.changePlayer();
        oldField = model.getField(0, 3);
        newField = model.getField(3, 0);
        Field oponentField = model.getField(2, 1);
        assertTrue(model.getCurrentPlayer()==Player.WHITE);

        moveDone = checkersController.makeMove(oldField,newField);

        assertTrue(moveDone);
        assertFalse(oponentField.isOccupied());
        assertFalse(oldField.isOccupied());
        assertTrue(newField.isOccupied());
    }
}
