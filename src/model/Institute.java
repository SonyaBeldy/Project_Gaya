package model;

import javafx.scene.image.Image;

public class Institute extends Outpost {

    public Institute () {
        super(6, 4, "./view/resourses/images/buildings/3.png", new int[]{0, 1}, 3);
        setFitWidth(44);
        setFitHeight(48);
        setImage(new Image("./view/resourses/images/buildings/3.png"));
    }

    public Institute (int gold, int ore, String url, int[] income, int ind) {
        super(gold, ore, url, income, ind);
        setFitWidth(51);
        setFitHeight(48);
    }

}
