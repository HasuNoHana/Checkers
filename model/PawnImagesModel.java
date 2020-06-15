package model;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
import java.util.HashMap;

public class PawnImagesModel {
    private final HashMap<String, HashMap<String, ImageRepository>> imagesRepos;

    public PawnImagesModel() {
        this.imagesRepos = new HashMap<>();
        for( String colorsOuter : Constants.GameConstants.PAWN_COLORS ){
            imagesRepos.put(colorsOuter, new HashMap<>());
            for( String colorsInner : Constants.GameConstants.PAWN_COLORS ){
                imagesRepos.get(colorsOuter).put(colorsInner, new ImageRepository(colorsOuter, colorsInner));
            }
        }
    }
    public ImageRepository getImagesRepo(String color1, String color2){
        return imagesRepos.get(color1).get(color2);
    }
}
