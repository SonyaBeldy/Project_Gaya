package model;

public class Laboratory extends Outpost {

    public Laboratory() {
        super(5, 3, "src/view/resourses/images/buildings/laboratory.png");
    }

    public Laboratory (int gold, int ore, String url) {
        super(gold, ore, url);
    }
}
