package model;

import javafx.scene.image.Image;

public class Laboratory extends Outpost {

    public Laboratory() {
        super(5, 3, "./view/resourses/images/buildings/2.png", new int[]{0, 1, 1, 1}, 2);
        setFitWidth(30);
        setFitHeight(31);
        setImage(new Image("./view/resourses/images/buildings/2.png"));
    }

    public Laboratory(int gold, int ore, String url, int[] income, int ind) {
        super(gold, ore, url, income, ind);
        setFitWidth(37);
        setFitWidth(37);
        setFitHeight(38);
    }

    public void destroy(Player player) {
        player.getIncome()[2] -= income[player.getBuildsCount()[ind - 1]];
        player.getBuildsCount()[ind]--;
    }

    public void add(Player player) {
        player.getBuildsCount()[ind - 1]++;
        player.getIncome()[2] += income[player.getBuildsCount()[ind - 1]];
        player.getResources()[0] -= gold;
        player.getResources()[1] -= ore;
    }
}
