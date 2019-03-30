package model;

import controller.Game;

public class Player {
    private Race race;
    private int[] resources = new int[5];
    private int[] income = new int[5];
    private int score = 10;
    private int[] science = new int[6];
    private int[] buildsCount =  new int[5];

    public Player(Race race){
        this.race = race;
        setResources(race);
    }

    public void setResources(Race race) {
        resources = race.getResources();
        income = race.getIncome();
        science = race.getScience();
        buildsCount = race.getBuildsCount();
    }

    public void updateResEndTurn() {
        for (int i = 0; i < resources.length; i++) {
            resources[i] += income[i];
        }
    }

    public int[] getScience() {
        return science;
    }

    public int getScore() {
        return score;
    }

    public int[] getResources() {
        return resources;
    }

    public int[] getIncome() {
        return income;
    }

    public int[] getBuildsCount() {
        return buildsCount;
    }
}
