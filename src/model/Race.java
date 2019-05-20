package model;

public enum  Race {
    Terrans,
    Tacklons,
    HadschHallas,
    Xenoc;

    private int[] terrCost;
    private int[] science;
    private int[] resources;
    private int[] income;
    private int[] buildsCount;
    private int[] energy;
    private int mineStartCount;

    Race() {

        switch (name()) {
            case "Terrans":
                resources = new int[]{15, 4, 3, 1, 8}; //gold, ore, science, kki,
                income = new int[]{0, 1, 1, 0, 0, 0}; //
                terrCost = new int[]{2, 0, 3, 2, 1, 1, 3}; //b, bl, br, or, re, wh, ye
                science = new int[]{0, 0, 0, 1, 0, 0};
                buildsCount = new int[]{0, 0, 0, 0, 0};
                energy = new int[]{0, 4, 4, 0};
                mineStartCount = 2;
                break;
            case "Tacklons":
                resources = new int[]{15, 4, 3, 1, 8};
                income = new int[]{0, 1, 1, 0, 0, 0};
                terrCost = new int[]{1, 3, 0, 2, 3, 2, 1};
                science = new int[]{0, 0, 0, 0, 0, 0};
                buildsCount = new int[]{0, 0, 0, 0, 2};
                energy = new int[]{0, 2, 4, 0};
                mineStartCount = 2;
                break;
            case "HadschHallas":
                resources = new int[]{15, 4, 3, 1, 8};
                income = new int[]{0, 1, 1, 0, 0, 0};
                terrCost = new int[]{3, 1, 3, 1, 0, 2, 2};
                science = new int[]{0, 0, 0, 0, 1, 0};
                buildsCount = new int[]{0, 0, 0, 0, 2};
                energy = new int[]{0, 2, 4, 0};

                break;
            case "Xenoc":
                resources = new int[]{15, 4, 3, 1, 8};
                income = new int[]{0, 1, 1, 0, 0, 0};
                terrCost = new int[]{2, 3, 1, 1, 2, 3, 0};
                science = new int[]{0, 0, 1, 0, 0, 0};
                buildsCount = new int[]{0, 0, 0, 0, 0};
                energy = new int[]{0, 2, 4, 0};
                mineStartCount = 3;
                break;
        }
    }

    public int[] getEnergy() {
        return energy;
    }

    public int[] getTerrCost() {
        return terrCost;
    }

    public int[] getResources() {
        return resources;
    }

    public int[] getIncome() {
        return income;
    }

    public int[] getScience() {
        return science;
    }

    public int[] getBuildsCount() {
        return buildsCount;
    }
}
