package model;

import java.util.ArrayList;

public class Hex {
    ArrayList<Hexagon> hexagons;
    Point[][] map;
    int id;
    public Hex(int id, Point[][] map, ArrayList<Hexagon> hexagons) {
        this.id = id;
        this.map = map;
        this.hexagons = hexagons;
    }

    public void getCenter() {

    }

    public void setHexagons(ArrayList<Hexagon> hexagons) {
        this.hexagons = hexagons;
    }

    public ArrayList<Hexagon> getHexagons() {
        return hexagons;
    }

    public Point[][] getMap() {
        return map;
    }
}
