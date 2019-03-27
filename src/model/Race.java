package model;

public enum  Race {
    Terrans(15, 4, 3, 1, 8, 0, 1, 1, 0, 0),
    Tacklons(0, 0, 0, 0, 0, 0, 0 , 0, 0, 0),
    HadschHallas(0, 0, 0, 0, 0, 0, 0 , 0, 0, 0),
    Xenoc(0, 0, 0, 0, 0, 0, 0 , 0, 0, 0);

    private int[] terrCost;
    private int[] resources;
    private int[] income;
    private int gaiaformersCount;

    Race(int gold, int ore, int science, int kki, int energy, int g_d, int o_d, int s_d, int k_d, int e_d) {

        resources = new int[]{gold, ore, science, kki, energy};
        income = new int[]{g_d, o_d, s_d, k_d, e_d};

        switch (this) {
            case Terrans:
                terrCost = new int[]{2, 0, 3, 2, 1, 1, 3};
                break;
        }
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
}
