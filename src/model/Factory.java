package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Factory {

    private int l = 5;

    public ArrayList<String[][]> fill_barrier_matrix(int count, ArrayList<String[][]> bariers) throws FileNotFoundException {
        FileReader reader = new FileReader("src/view/resourses/hexs");
        Scanner scanner = new Scanner(reader);
        for (int i = 0; i < count; i ++) {
            String[][] b = new String[l][l];
            for (int j = 0; j < l; j++) {
                for (int k = 0; k < l; k++) {
                    b[j][k] = scanner.next();
                }
            }
            bariers.add(b);
        }
        return bariers;
    }

    public String getPlanet (String s) {
        switch (s){
            case "ye":
                s = "./view/resourses/images/planets/yellow.png";
                break;
            case "br":
                s = "./view/resourses/images/planets/brown.png";
                break;
            case "bl":
                s = "./view/resourses/images/planets/blue.png";
                break;
            case "pu":
                s = "./view/resourses/images/planets/purple.png";
                break;
            case "or":
                s = "./view/resourses/images/planets/orange.png";
                break;
            case "re":
                s = "./view/resourses/images/planets/red.png";
                break;
            case "b":
                s = "./view/resourses/images/planets/black.png";
                break;
            case "wh":
                s = "./view/resourses/images/planets/white.png";
                break;
            case "gr":
                s = "./view/resourses/images/planets/green.png";
                break;
                default:
                    s = "none";
                    break;
        }
        return s;
    }
}
