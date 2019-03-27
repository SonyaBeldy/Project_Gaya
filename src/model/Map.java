package model;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Map {

    AnchorPane main;
    private ScalePane scalePane;
    private ArrayList<Hex> hexs = new ArrayList<>();
    private Point[][] matrix_hex = new Point[5][5]; //координаты в плоскости
    private ArrayList<String[][]> barriers_matrix = new ArrayList<>();
    Factory factory = new Factory();

    public Map (AnchorPane main, ScalePane scalePane) throws FileNotFoundException {
        this.scalePane = scalePane;
        this.main = main;
        matrix_hex = createMatrix(matrix_hex);
        barriers_matrix = factory.fill_barrier_matrix(7, barriers_matrix);
        createMap();
    }

    private void createMap() {
        double size = 31;
        double xc = main.getPrefWidth()/2-size*4, yc = main.getPrefHeight()/2 - size*2;
        System.out.println("pref " + xc + " " + yc);
        double size2 = size + 0.3;
        hexs.add(createHexagon(2, size, xc, yc));
        hexs.add(createHexagon(1, size, xc - size2*7.5, yc - Math.sqrt(3)/2* size2));
        hexs.add(createHexagon(0, size, xc - size2*3  , yc - Math.sqrt(3)/2* size2*8));
        hexs.add(createHexagon(4, size, xc + size2*4.5, yc - Math.sqrt(3)/2* size2*7));
        hexs.add(createHexagon(5, size, xc + size2*7.5 , yc + Math.sqrt(3)/2*size2));
        hexs.add(createHexagon(6, size, xc + size2*3 , yc + Math.sqrt(3)/2*  size2*8));
        hexs.add(createHexagon(3, size, xc - size2*4.5 , yc + Math.sqrt(3)/2*size2*7));
    }

    Hex createHexagon(int id, double size, double x, double y){ // вызов factory, инициальзация всех гексов
        double w = size * 2;
        double horiz = w * 3/4;
        double h = Math.sqrt(3)/2 * w;
        double vert = h;
        int[] mas = {3, 4, 5, 4, 3};
        double y0 = y;
        double xm = -2, zm = 0, zm0 = 0;

        Point[][] m = new Point[5][5];
        ArrayList<Hexagon> hexagons = new ArrayList<>();
        for (int i = 0; i < 5; i ++) {
            for (int j = 0; j < mas[i]; j++) {
                Hexagon hexagon = new Hexagon("./view/resourses/images/hexagon2.png", new Point(xm, zm));
                hexagon.setStyle("-fx-opacity: 0.5");
                hexagon.setFitHeight(h);
                hexagon.setFitWidth(w);
                hexagon.setLayoutX(x);
                hexagon.setLayoutY(y);
                m[i][j] = new Point(xm, zm);
                scalePane.getItems().add(hexagon);
                hexagon.addEventFilter(MouseEvent.MOUSE_ENTERED, event -> hexagon.setStyle("-fx-opacity: 0.6"));
                hexagon.addEventFilter(MouseEvent.MOUSE_EXITED, event -> hexagon.setStyle("-fx-opacity: 0.5"));
                hexagon.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> System.out.println("x " + hexagon.getPoint().x + " y " + hexagon.getPoint().y));
                y = y + vert;

                Point point = cube_to_matrix(new Point(xm, zm), 2);
                zm += 1;
                //System.out.println("p " + point.x + " " + point.y);
                String s = (barriers_matrix.get(id)[(int) point.x][(int) point.y]);
                s = factory.getPlanet(s);
                if(!s.equals("none")) {
                    hexagon.setPlanet(s);
                    //System.out.println("s " + s);
                    hexagon.setImage(new Image(s));
                }
                hexagons.add(hexagon);
            }
            if (zm0 > -2) {
                zm0 --;
            }
            zm = zm0;
            xm += 1;
            if (i < 4) {
                y = y0 - vert * (mas[i + 1] - mas[0]) / 2;
                x = x + horiz;
            }
        }
        Hex hex = new Hex(id, m, hexagons);
        return hex;
    }

    Point cube_to_matrix (Point point, int r) {
        return new Point(point.y + r, point.x + r + Math.min(0, point.y)); // r + n, q + N + min(0, r)
    }

    public Point[][] createMatrix(Point[][] mas){
        int x = -2, z = -2;
        int x0 = x;
        for (int i = 0; i < 5; i++) {
            System.out.println();
            for (int j = 0; j < 5; j++) {
                System.out.println(x + " " + z);
                mas[i][j] = new Point(x, z);
                x++;
            }
            z++;
            x = x0;
        }
        return mas;
    }

    public ArrayList<String[][]> getBarriers_matrix() {
        return barriers_matrix;
    }

    public Point[][] getMatrix_hex() {
        return matrix_hex;
    }
}
