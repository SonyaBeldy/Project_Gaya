package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mine extends Build {
    int gold = 2;
    int ore = 1;
    int[] income = {0, 1, 1, 0, 1, 1, 1, 1, 1};
    int ind = 5;

    public Mine() {
        setImage(new Image("./view/resourses/images/buildings/5.png"));
        setFitWidth(27);
        setFitHeight(27);
    }

    public Mine(int gold, int ore, String url, int[] income, int ind) {
        this.gold = gold;
        this.ore = ore;
        this.income = income;
        this.ind = ind;
        setImage(new Image(url));
        setFitWidth(34);
        setFitHeight(34);
    }

    public void destroy(Player player) {
        player.getIncome()[1] -= income[player.getBuildsCount()[ind - 1]];
        player.getBuildsCount()[ind - 1]--;
    }

    public void add(Player player) {
        player.getBuildsCount()[ind - 1]++;
        player.getIncome()[1] += income[player.getBuildsCount()[ind - 1]];
        player.getResources()[0] -= gold;
        player.getResources()[1] -= ore;
    }

    public boolean isBuildIsOver(Player player) {
        if (player.getBuildsCount()[ind - 1] < income.length - 1) {
            return false;
        }
        return true;
    }

    public int getOre() {
        return ore;
    }

    public int getGold() {
        return gold;
    }

    public int[] getIncome() {
        return income;
    }

    public int getInd() {
        return ind;
    }
}
