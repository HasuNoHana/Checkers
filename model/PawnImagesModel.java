package model;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;

public class PawnImagesModel {
    private final HashMap<String, HashMap<String, ImageRepository>> imagesRepos;
    private final HashMap<String, Image> images;

    public PawnImagesModel() {
        this.imagesRepos = new HashMap<>();
        for( String colorsOuter : Constants.GameConstants.PAWN_COLORS ){
            imagesRepos.put(colorsOuter, new HashMap<>());
            for( String colorsInner : Constants.GameConstants.PAWN_COLORS ){
                imagesRepos.get(colorsOuter).put(colorsInner, new ImageRepository(colorsOuter, colorsInner));
            }
        }
        images = new HashMap<>();
        for( String color : Constants.GameConstants.PAWN_COLORS ){
            String path = "../graphics/" + color.substring(0, 1).toUpperCase() + color.substring(1, color.length()).toLowerCase() + "/Normal.png";
            images.put(color, getImageFromPath(path));
        }

    }
    public HashMap<String, Image> getImages() {
        return images;
    }
    public ImageRepository getImagesRepo(String color1, String color2){
        return imagesRepos.get(color1).get(color2);
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
}
