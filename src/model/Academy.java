package model;

import javafx.scene.image.Image;

public class Academy extends Laboratory {


    public Academy() {
        super(6, 6, "./view/resourses/images/buildings/1.png", new int[]{0, 2, 1}, 1);
        setFitWidth(41);
        setFitHeight(38);
        setImage(new Image("./view/resourses/images/buildings/1.png"));
    }

    public Academy (int gold, int ore, String url, int[] income, int ind) {
        super(gold, ore, "./view/resourses/images/buildings/1.png", income, ind);
        setFitWidth(54);
        setFitHeight(58);
    }
}
