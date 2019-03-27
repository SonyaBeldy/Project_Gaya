package model;

public class Outpost extends Mine {

    public Outpost () {
        super(6, 2, "src/view/resourses/images/buildings/outpost.png");
    }

    public Outpost (int gold, int ore, String url) {
        super(gold, ore, url);
    }

    public Class<?> getPrevious(){
        return getClass().getSuperclass();
    }

}
