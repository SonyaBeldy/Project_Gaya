package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mine extends ImageView {
    int gold = 2, ore = 1;

    public Mine() {
        super("src/view/resourses/images/buildings/mine.png");
    }

    public Mine (int gold, int ore, String url) {
        this.gold = gold;
        this.ore = ore;
        setImage(new Image(url));
    }

    public int getOre() {
        return ore;
    }

    public int getGold() {
        return gold;
    }
}
