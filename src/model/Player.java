package model;

public class Player {
    Race race;
    private int[] resources;
    private int[] income;
    int score = 10;

    public Player(Race race){
        this.race = race;
        setResources(race);
    }

    public void setResources(Race race) {
        resources = race.getResources();
        income = race.getIncome();
    }

    public int[] getResources() {
        return resources;
    }

    public int[] getIncome() {
        return income;
    }
}
