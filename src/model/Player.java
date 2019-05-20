package model;

public class Player {
    private Race race;
    private int[] resources = new int[5];
    private int[] income = new int[6];
    private int score = 10;
    private int[] science = new int[6];
    private int[] buildsCount = new int[5];
    private int terraCost = 3;
    private int terraCount[] = new int[7];
    private int range = 1;
    private int gaiaformersCount = 0;
    private int[] tech = new int[9];
    private int[] energy = new int[4];
    private int mineStartCount = 2;

    public Player(Race race) {
        this.race = race;
        setResources(race);
    }

    public void setResources(Race race) {
        resources = race.getResources();
        income = race.getIncome();
        science = race.getScience();
        buildsCount = race.getBuildsCount();
        energy = race.getEnergy();
        terraCount = race.getTerrCost();
    }

    public void updateResEndTurn() {
        for (int i = 0; i < resources.length; i++) {
            resources[i] += income[i];
        }
        energyTransfer(income[5]);
    }

    public void updateResourses(ScienceImg img) {
        System.out.println("RRR T " + img.getType() + " " + img.getLevel());
        switch (img.getType()) {
            case 1:
                System.out.println("uptade");
                switch (img.getLevel()) {
                    case 0:
                        terraCost = 3;
                        break;
                    case 1:
                        resources[1] += 3;
                        break;
                    case 2:
                        terraCost = 2;
                        break;
                    case 3:
                        energyTransfer(3);
                        terraCost = 1;
                        break;
                    case 4:
                        resources[1] += 2;
                        break;
                    case 5:
                        break;
                }
                break;
            case 2:
                switch (img.getLevel()) {
                    case 0:
                        range = 1;
                        break;
                    case 1:
                        resources[3]++;
                        break;
                    case 2:
                        range = 2;
                        break;
                    case 3:
                        energyTransfer(3);
                        resources[3]++;
                        break;
                    case 4:
                        range = 3;
                        break;
                    case 5:
                        range = 4;
                        break;
                }
                break;
            case 3:
                switch (img.getLevel()) {
                    case 1:
                        resources[3]++;
                        break;
                    case 2:
                        resources[3]++;
                        break;
                    case 3:
                        energyTransfer(3);
                        resources[3] += 2;
                        break;
                    case 4:
                        resources[3] += 3;
                        break;
                    case 5:
                        resources[3] += 4;
                        break;
                }
                break;
            case 4:
                switch (img.getLevel()) {
                    case 1:
                        gaiaformersCount++;
                        break;
                    case 2:
                        resources[4] += 3;
                        energy[1] += 3;
                        break;
                    case 3:
                        energyTransfer(3);
                        gaiaformersCount++;
                        break;
                    case 4:
                        gaiaformersCount++;
                        break;
                    case 5:
                        //очки
                        break;
                }
                break;
            case 5:
                switch (img.getLevel()) {
                    case 1:
                        income[0] += 2;
                        income[5] ++;
                        break;
                    case 2:
                        income[1]++;
                        income[5] ++;
                        break;
                    case 3:
                        income[0]++;
                        income[5] ++;
                        break;
                    case 4:
                        income[0]++;
                        income[1]++;
                        income[5] ++;
                        break;
                    case 5:
                        income[0] -= 4;
                        income[1] -= 2;
                        resources[0] += 6;
                        resources[1] += 3;
                        energyTransfer(6);
                        break;
                }
                break;
            case 6:
                switch (img.getLevel()) {
                    case 1:
                        income[2]++;
                        break;
                    case 2:
                        income[2]++;
                        break;
                    case 3:
                        income[2]++;
                        break;
                    case 4:
                        income[2]++;
                        break;
                    case 5:
                        income[2] -= 4;
                        resources[2] += 9;
                        break;
                }
                break;

        }
    }

    public void energyTransfer(int count) {
        for (int i = 1; i < 3; i++) {
            while ((energy[i] != 0) && (count != 0)) {
                energy[i]--;
                energy[i + 1]++;
                count--;
            }
        }
        for (int i = 0; i < energy.length; i++) {
            System.out.print(energy[i] + " ");
        }
    }


    public Race getRace() {
        return race;
    }

    public void setMineStartCount(int mineStartCount) {
        this.mineStartCount = mineStartCount;
    }

    public int[] getTerraCount() {
        return terraCount;
    }

    public int getMineStartCount() {
        return mineStartCount;
    }

    public int[] getEnergy() {
        return energy;
    }

    public int[] getTech() {
        return tech;
    }

    public int getTerraCost() {
        return terraCost;
    }

    public int getRange() {
        return range;
    }

    public int getGaiaformersCount() {
        return gaiaformersCount;
    }

    public int[] getScience() {
        return science;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
