package model;

import javafx.scene.image.Image;

public class Academy extends Laboratory {


    public Academy(Player player) {
        super(6, 6, "./view/resourses/images/buildings/" + player.getRace() + "/" + "1.png", new int[]{0, 2, 1}, 1);
        setFitWidth(41);
        setFitHeight(38);
        setImage(new Image("./view/resourses/images/buildings/" + player.getRace() + "/" + "1.png"));
    }

    public Academy (int gold, int ore, String url, int[] income, int ind) {
        super(gold, ore, "./view/resourses/images/buildings/1.png", income, ind);
        setFitWidth(54);
        setFitHeight(58);
    }

    public void add(Player player) {
        player.getBuildsCount()[ind - 1]++;
        //player.getIncome()[0] += income[player.getBuildsCount()[ind - 1]];
        player.getResources()[0] -= gold;
        player.getResources()[1] -= ore;
    }

    public boolean isBuildIsOver(Player player) {
        if (player.getBuildsCount()[ind - 1] < income.length - 1) {
            return false;
        }
        return true;
    }
}
