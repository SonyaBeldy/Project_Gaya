package model;

import javafx.scene.image.ImageView;

public class ScienceImg extends ImageView {
    private int type;
    private int level;
    public ScienceImg(int type, int level) {
        this.type = type;
        this.level = level;
    }

    public int getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }
}
