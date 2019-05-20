package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ScienceImg extends ImageView {
    private int type;
    private int level;
    public ScienceImg(int type, int level) {
        this.type = type;
        this.level = level;
    }

    public void levelUp(){
        level++;
        setImage(new Image("./view/resourses/images/sience/" + type + "." + level + ".png"));
    }


    public int getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
