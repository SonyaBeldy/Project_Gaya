package model;

import javafx.scene.image.Image;

public class Outpost extends Mine {


    public Outpost() {
        super(6, 2, "./view/resourses/images/buildings/4.png", new int[]{0, 3, 4, 4, 5}, 4);
        setFitWidth(27);
        setFitHeight(38);
        setImage(new Image("./view/resourses/images/buildings/4.png"));
    }

    public Outpost(int gold, int ore, String url, int[] income, int ind) {
        super(gold, ore, url, income, ind);
        setFitWidth(34);
        setFitHeight(45);

    }

    public void destroy(Player player) {
        player.getIncome()[0] -= income[player.getBuildsCount()[ind - 1]];
        player.getBuildsCount()[ind - 1]--;
    }

    public void add(Player player) {
        System.out.println("ind " + ind);
        player.getBuildsCount()[ind - 1]++;
        player.getIncome()[0] += income[player.getBuildsCount()[ind - 1]];
        player.getResources()[0] -= gold;
        player.getResources()[1] -= ore;
    }

    public boolean isBuildIsOver(Player player) {
        if (player.getBuildsCount()[ind - 1] < income.length - 1) {
            return false;
        }
        return true;
    }


    public Class<?> getPrevious() {
        return getClass().getSuperclass();
    }

}
