package Tests;

import Game.*;

import org.junit.jupiter.api.Test;

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

//    @Test
//    public void moveDownNotAvaliable() {
//        GameModel model = new GameModel();
//        Field oldField = model.getField(1, 0);
//        Field newField = model.getField(2, 0);
//
//        assertFalse(model.isNormalMoveAvailable(oldField, newField));
//    }
//
//    @Test
//    public void moveDiagonalAvaliable() {
//        GameModel model = new GameModel();
//        Field oldField = model.getField(1, 0);
//        Field newField = model.getField(2, 1);
//        model.changePlayer();
//
//        assertTrue(model.isNormalMoveAvailable(oldField, newField));
//    }
//
//    @Test
//    public void moveCurrentPlayerPawn() {
//        GameModel model = new GameModel();
//        Field oldField = model.getField(1, 0);
//        Field newField = model.getField(2, 1);
//        assertFalse(model.isNormalMoveAvailable(oldField, newField));
//
//        model.changePlayer();
//
//        assertTrue(model.isNormalMoveAvailable(oldField, newField));
//    }
//
//    @Test
//    public void moveDiagonalyTwoFields() {
//        GameModel model = new GameModel();
//        model.changePlayer();
//        Field oldField = model.getField(1, 0);
//        Field newField = model.getField(3, 2);
//
//        assertFalse(model.isNormalMoveAvailable(oldField, newField));
//    }
//
//    @Test
//    public void moveForwardTwoPlaces() {
//        GameModel model = new GameModel();
//        model.changePlayer();
//        Field oldField = model.getField(1, 0);
//        Field newField = model.getField(3, 0);
//
//        assertFalse(model.isNormalMoveAvailable(oldField, newField));
//    }
//
//    @Test
//    public void moveStepOnAllay() {
//        GameModel model = new GameModel();
//        model.changePlayer();
//        Field oldField = model.getField(0, 1);
//        Field newField = model.getField(1, 0);
//
//        assertFalse(model.isNormalMoveAvailable(oldField, newField));
//    }
//
//    @Test
//    public void movePawn() {
//        GameModel model = new GameModel();
//        model.changePlayer();
//        Field oldField = model.getField(1, 0);
//        Field newField = model.getField(2, 1);
//        assertTrue(oldField.isOccupied());
//        assertFalse(newField.isOccupied());
//
//        model.movePawn(oldField,newField);
//
//        assertFalse(oldField.isOccupied());
//        assertTrue(newField.isOccupied());
//    }
//
//    @Test
//    public void makeIncorectMoveTwoFieldDiagonaly() {
//        GameModel model = new GameModel();
//        model.changePlayer();
//        Field oldField = model.getField(1, 0);
//        Field newField = model.getField(3, 2);
//        assertTrue(oldField.isOccupied());
//        assertFalse(newField.isOccupied());
//
//        boolean moveDone = model.makeMove(oldField,newField);
//
//        assertFalse(moveDone);
//        assertTrue(oldField.isOccupied());
//        assertFalse(newField.isOccupied());
//    }
//
//    @Test
//    public void makeCorectMoveOneFieldDiagonaly() {
//        GameModel model = new GameModel();
//        model.changePlayer();
//        Field oldField = model.getField(1, 0);
//        Field newField = model.getField(2, 1);
//        assertTrue(oldField.isOccupied());
//        assertFalse(newField.isOccupied());
//
//        boolean moveDone = model.makeMove(oldField,newField);
//
//        assertTrue(moveDone);
//        assertFalse(oldField.isOccupied());
//        assertTrue(newField.isOccupied());
//    }
//
//    @Test
//    public void makeQueen() {
//        GameModel model = new GameModel();
//        Field queenField = model.getField(0,1);
//        model.movePawn(queenField,model.getField(2,1));
//        model.movePawn(model.getField(1,0),model.getField(3,0));
//        model.movePawn(model.getField(6,1),model.getField(1,0));
//        assertFalse(queenField.isOccupied());
//
//        boolean moveDone = model.makeMove(model.getField(1, 0), queenField);
//
//        assertTrue(moveDone);
//        assertTrue(queenField.isOccupied());
//        assertTrue(queenField.isQueen());
//    }
//
//    @Test
//    public void captureOneTime() {
//        GameModel model = new GameModel();
//        model.changePlayer();
//        Field oponnentField = model.getField(2,1);
//        model.movePawn(model.getField(6,1),oponnentField);
//
//        Field oldField = model.getField(1, 0);
//        Field newField = model.getField(3, 2);
//        assertTrue(oldField.isOccupied());
//        assertFalse(newField.isOccupied());
//        assertTrue(model.getCurrentPlayer()==Player.BROWN);
//
//        boolean moveDone = model.makeMove(oldField,newField);
//
//        assertTrue(moveDone);
//        assertFalse(oponnentField.isOccupied());
//        assertFalse(oldField.isOccupied());
//        assertTrue(newField.isOccupied());
//        assertFalse(model.getCurrentPlayer()==Player.BROWN);
//    }
//
//    @Test
//    public void haveNextMoveAfterCapture() {
//        GameModel model = new GameModel();
//        model.changePlayer();
//        Field oponnentField = model.getField(2,1);
//        model.movePawn(model.getField(6,1),oponnentField);
//        model.movePawn(model.getField(6,3),model.getField(4,3));
//
//        Field oldField = model.getField(1, 0);
//        Field newField = model.getField(3, 2);
//        assertTrue(oldField.isOccupied());
//        assertFalse(newField.isOccupied());
//        assertTrue(model.getCurrentPlayer()==Player.BROWN);
//
//        boolean moveDone = model.makeMove(oldField,newField);
//
//        assertTrue(moveDone);
//        assertFalse(oponnentField.isOccupied());
//        assertFalse(oldField.isOccupied());
//        assertTrue(newField.isOccupied());
//        assertTrue(model.getCurrentPlayer()==Player.BROWN);
//    }
//
//    @Test
//    public void moveWrongPawnDuringCombo() {
//        GameModel model = new GameModel();
//        model.changePlayer();
//        Field oponnentField = model.getField(2,1);
//        model.movePawn(model.getField(6,1),oponnentField);
//        model.movePawn(model.getField(6,3),model.getField(4,3));
//
//        Field oldField = model.getField(1, 0);
//        Field newField = model.getField(3, 2);
//        assertTrue(oldField.isOccupied());
//        assertFalse(newField.isOccupied());
//        assertTrue(model.getCurrentPlayer()==Player.BROWN);
//
//        boolean moveDone = model.makeMove(oldField,newField);
//
//        assertTrue(moveDone);
//        assertFalse(oponnentField.isOccupied());
//        assertFalse(oldField.isOccupied());
//        assertTrue(newField.isOccupied());
//        assertTrue(model.getCurrentPlayer()==Player.BROWN);
//
//        moveDone = model.makeMove(model.getField(1,4),model.getField(2,5));
//
//        assertFalse(moveDone);
//    }
//
//    @Test
//    public void captureWrongPawnDuringCombo() {
//        GameModel model = new GameModel();
//        model.changePlayer();
//        Field oponnentField = model.getField(2,1);
//        model.movePawn(model.getField(6,1),oponnentField);
//        model.movePawn(model.getField(6,3),model.getField(4,3));
//
//        Field oldField = model.getField(1, 0);
//        Field newField = model.getField(3, 2);
//        assertTrue(oldField.isOccupied());
//        assertFalse(newField.isOccupied());
//        assertTrue(model.getCurrentPlayer()==Player.BROWN);
//
//        boolean moveDone = model.makeMove(oldField,newField);
//
//        assertTrue(moveDone);
//        assertFalse(oponnentField.isOccupied());
//        assertFalse(oldField.isOccupied());
//        assertTrue(newField.isOccupied());
//        assertTrue(model.getCurrentPlayer()==Player.BROWN);
//
//        model.movePawn(model.getField(6,5),model.getField(2,5));
//        moveDone = model.makeMove(model.getField(1,4),model.getField(3,6));
//
//        assertFalse(moveDone);
//    }
//
////    @Test
////    public void createQueen() {
////        GameModel model = new GameModel();
////        model.changePlayer();
////        assertFalse(true);
////        Field oponnentField = model.getField(2,1);
////        model.movePawn(model.getField(6,1),oponnentField);
////        model.movePawn(model.getField(6,3),model.getField(4,3));
////
////        Field oldField = model.getField(1, 0);
////        Field newField = model.getField(3, 2);
////        assertTrue(oldField.isOccupied());
////        assertFalse(newField.isOccupied());
////        assertTrue(model.getCurrentPlayer()==Player.BROWN);
////
////        boolean moveDone = model.makeMove(oldField,newField);
////
////        assertTrue(moveDone);
////        assertFalse(oponnentField.isOccupied());
////        assertFalse(oldField.isOccupied());
////        assertTrue(newField.isOccupied());
////        assertTrue(model.getCurrentPlayer()==Player.BROWN);
////
////        model.movePawn(model.getField(6,5),model.getField(2,5));
////        moveDone = model.makeMove(model.getField(1,4),model.getField(3,6));
////
////        assertFalse(moveDone);
////    }
//
//    @Test
//    public void QueenSingleCapture() {
//        GameModel model = new GameModel();
//        model.changePlayer();
//        assertTrue(model.getCurrentPlayer()==Player.BROWN);
//        model.movePawn(model.getField(1,2),model.getField(2,1));
//        model.movePawn(model.getField(0,3),model.getField(5,4));
//        model.changePlayer();
//        assertTrue(model.getCurrentPlayer()==Player.WHITE);
//        model.movePawn(model.getField(6,3),model.getField(1,2));
//        Field oldField = model.getField(1, 2);
//        Field newField = model.getField(0, 3);
//        assertTrue(oldField.isOccupied());
//        assertFalse(newField.isOccupied());
//        boolean moveDone = model.makeMove(oldField,newField);
//        model.changePlayer();
//        oldField = model.getField(0, 3);
//        newField = model.getField(2, 5);
//        Field oponentField = model.getField(1, 4);
//        assertTrue(model.getCurrentPlayer()==Player.WHITE);
//
//        moveDone = model.makeMove(oldField,newField);
//
//        assertTrue(moveDone);
//
//        assertFalse(oldField.isOccupied());
//        assertTrue(newField.isOccupied());
//        assertFalse(oponentField.isOccupied());
//    }
//
//    @Test
//    public void QueenTwoFieldsAwayCapture() {
//        GameModel model = new GameModel();
//        model.changePlayer();
//        model.movePawn(model.getField(1,2),model.getField(2,1));
//        model.movePawn(model.getField(0,3),model.getField(5,4));
//        model.changePlayer();
//        model.movePawn(model.getField(6,3),model.getField(1,2));
//        Field oldField = model.getField(1, 2);
//        Field newField = model.getField(0, 3);
//        assertTrue(oldField.isOccupied());
//        assertFalse(newField.isOccupied());
//        boolean moveDone = model.makeMove(oldField,newField);
//        model.changePlayer();
//        oldField = model.getField(0, 3);
//        newField = model.getField(3, 0);
//        Field oponentField = model.getField(2, 1);
//        assertTrue(model.getCurrentPlayer()==Player.WHITE);
//
//        moveDone = model.makeMove(oldField,newField);
//
//        assertTrue(moveDone);
//        assertFalse(oponentField.isOccupied());
//        assertFalse(oldField.isOccupied());
//        assertTrue(newField.isOccupied());
//    }
//
//    @Test
//    public void QueenCaptureQueen() {
//        GameModel model = new GameModel();
//        model.changePlayer();
//        model.movePawn(model.getField(1,2),model.getField(2,1));
//        model.movePawn(model.getField(0,3),model.getField(5,4));
//        model.changePlayer();
//        model.movePawn(model.getField(6,3),model.getField(1,2));
//        Field oldField = model.getField(1, 2);
//        Field newField = model.getField(0, 3);
//        assertTrue(oldField.isOccupied());
//        assertFalse(newField.isOccupied());
//        boolean moveDone = model.makeMove(oldField,newField);
//        model.changePlayer();
//        oldField = model.getField(0, 3);
//        newField = model.getField(3, 0);
//        Field oponentField = model.getField(2, 1);
//        assertTrue(model.getCurrentPlayer()==Player.WHITE);
//
//        moveDone = model.makeMove(oldField,newField);
//
//        assertTrue(moveDone);
//        assertFalse(oponentField.isOccupied());
//        assertFalse(oldField.isOccupied());
//        assertTrue(newField.isOccupied());
//    }









}
