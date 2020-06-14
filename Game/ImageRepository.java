package Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.HashMap;

public class ImageRepository {
    private final HashMap<Pawn, Image> pawnDispatch;

    public ImageRepository(){
        this("White", "Brown");
    }

    public ImageRepository(String Player1Color, String Player2Color) {
        String whiteNormalPath = "../graphics/" + Player1Color + "/Normal.png";
        String whiteQueenPath = "../graphics/" + Player1Color + "/Queen.png";
        String brownNormalPath = "../graphics/" + Player2Color + "/Normal.png";
        String brownQueenPath = "../graphics/" + Player2Color + "/Queen.png";
        String EmptyPath = "../graphics/EmptyImage.png";


        this.pawnDispatch = new HashMap<>();
        pawnDispatch.put(Pawn.WHITE_NORMAL, getImageFromPath(whiteNormalPath));
        pawnDispatch.put(Pawn.WHITE_QUEEN, getImageFromPath(whiteQueenPath));
        pawnDispatch.put(Pawn.BROWN_NORMAL, getImageFromPath(brownNormalPath));
        pawnDispatch.put(Pawn.BROWN_QUEEN, getImageFromPath(brownQueenPath));
        pawnDispatch.put(Pawn.EMPTY, getImageFromPath(EmptyPath));
    }

    private Image getImageFromPath(String imagePath) {
        Image image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            System.err.println("Blad odczytu obrazka" + imagePath);
        }
        return image;
    }

    public Image getPawnImage(Pawn pawn) {
        return this.pawnDispatch.get(pawn);
    }

}
