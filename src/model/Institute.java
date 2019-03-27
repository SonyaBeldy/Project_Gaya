package model;

public class Institute extends Outpost {

    public Institute () {
        super(6, 4, "src/view/resourses/images/buildings/institute.png");
    }

    public Institute (int gold, int ore, String url) {
        super(gold, ore, url);
    }

}
