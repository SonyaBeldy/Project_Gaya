package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Start {

    public void generateQueue(String str, ArrayList<Player> players){
        Scanner scanner = new Scanner(str);
        while (scanner.hasNext()) {
            String raceName = scanner.next();
            Race race = Race.Terrans;
            switch (raceName){
                case "HADSHHALLAS":
                    race = Race.HadschHallas;
                    break;
                case"XENOC":
                    race = Race.Xenoc;
                    break;
            }
            players.add(new Player(Race.Terrans));
        }
    }

    public void generateTech(int tech[]){
        ArrayList<Integer> all = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            all.add(i);
        }
        int k = 8;
        for (int i = 0; i < tech.length; i++) {
            k--;
            int n = (int) (Math.random() * k);
            tech[i] = all.get(n);
            all.remove(n);
            System.out.println(tech[i] + " ");

        }


    }

    public void generateHighTech(int highTech[]){
        ArrayList<Integer> all = new ArrayList<>();
        for (int i = 1; i < 16; i++) {
            all.add(i);
        }
        int k = 14;
        for (int i = 0; i < highTech.length; i++) {
            int n = (int) (Math.random() * k);
            highTech[i] = all.get(n);
            all.remove(n);
            k--;
        }
        for (int i = 0; i < highTech.length; i++) {
            System.out.print(highTech[i] + " ");
        }
    }

    public void generateGoals(int goals[]) {
        int k = 9;
        ArrayList<Integer> all = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 5, 6, 6, 7, 7));
        ArrayList<Integer> all2 = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));

        for (int i = 0; i < goals.length - 2; i++) {
            int n = (int) (Math.random() * k);
            goals[i] = all.get(n);
            all.remove(n);
            k--;
        }
        k = 5;
        for (int i = goals.length - 2; i < goals.length; i++) {
            int n = (int) (Math.random() * k);
            goals[i] = all2.get(n);
            all2.remove(n);
            k--;
        }



        }
}
