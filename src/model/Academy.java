package model;

public class Academy extends Laboratory {

    public Academy() {
        super(6, 6, "src/view/resourses/images/buildings/academy.png");
    }

    public Academy (int gold, int ore, String url) {
        super(gold, ore, url);
    }
}
