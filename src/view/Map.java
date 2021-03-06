package view;

import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Map {

    AnchorPane main;
    private ScalePane scalePane;
    private ArrayList<Hex> hexs = new ArrayList<>();
    private Point[][] matrix_hex = new Point[5][5]; //координаты в плоскости
    private ArrayList<String[][]> planetMatrix = new ArrayList<>();
    Factory factory = new Factory();
    private Build[][][] buildMatrix= new Build[5][5][7];

    public Map (AnchorPane main, ScalePane scalePane) throws FileNotFoundException {
        this.scalePane = scalePane;
        this.main = main;
        matrix_hex = createMatrix(matrix_hex);
        planetMatrix = factory.fill_barrier_matrix(7, planetMatrix);
        createMap();
    }

    public void setCheckedFalse(){
        for (int i = 0; i < hexs.size(); i++) {
            for (int j = 0; j < hexs.get(i).getHexagons().size(); j++) {
                hexs.get(i).getHexagons().get(j).setChecked(false);
            }
        }
    }

    public void setNormOpacity () {
        for (int i = 0; i < hexs.size(); i++) {
            for (int j = 0; j < hexs.get(i).getHexagons().size(); j++) {
                Hexagon hexagon = hexs.get(i).getHexagons().get(j);
                hexagon.addEventFilter(MouseEvent.MOUSE_ENTERED, event -> hexagon.setStyle("-fx-opacity: 0.9"));
                hexagon.addEventFilter(MouseEvent.MOUSE_EXITED, event -> hexagon.setStyle("-fx-opacity: 0.7"));
                hexagon.setOpacity(0.7);
                hexagon.setEffect(null);

            }
        }
    }


    private void createMap() {
        double size = 31;
        double xc = main.getPrefWidth()/2-size*4, yc = main.getPrefHeight()/2 - size*2;
        double size2 = size + 0.3;
        hexs.add(createHexagon(2, size, xc, yc));
        hexs.add(createHexagon(1, size, xc - size2*7.5, yc - Math.sqrt(3)/2* size2));
        hexs.add(createHexagon(0, size, xc - size2*3  , yc - Math.sqrt(3)/2* size2*8));
        hexs.add(createHexagon(4, size, xc + size2*4.5, yc - Math.sqrt(3)/2* size2*7));
        hexs.add(createHexagon(5, size, xc + size2*7.5 , yc + Math.sqrt(3)/2*size2));
        hexs.add(createHexagon(6, size, xc + size2*3 , yc + Math.sqrt(3)/2*  size2*8));
        hexs.add(createHexagon(3, size, xc - size2*4.5 , yc + Math.sqrt(3)/2*size2*7));

    }

    public Hexagon findHexagon(Point point) {
        for (int i = 0; i < hexs.size(); i++) {
            for (int j = 0; j < hexs.get(i).getHexagons().size(); j++) {
                Hexagon hexagon = hexs.get(i).getHexagons().get(j);
                if ((point.x > hexagon.getLayoutX()) && (point.x < (hexagon.getLayoutX() + hexagon.getFitWidth())) && (point.y > hexagon.getLayoutY()) && (point.y < (hexagon.getLayoutY() + hexagon.getFitHeight()))) {
                    return hexagon;
                }

            }
        }
        return null;
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
                Hexagon hexagon = new Hexagon("./view/resourses/images/hexagon2.png", new Point(xm, zm), i);
                hexagon.setStyle("-fx-opacity: 0.7");
                hexagon.setFitHeight(h);
                hexagon.setFitWidth(w);
                hexagon.setLayoutX(x);
                hexagon.setLayoutY(y);
                hexagon.setHexId(id);
                m[i][j] = new Point(xm, zm);
                scalePane.getItems().add(hexagon);
                hexagon.addEventFilter(MouseEvent.MOUSE_ENTERED, event -> hexagon.setStyle("-fx-opacity: 0.9"));
                hexagon.addEventFilter(MouseEvent.MOUSE_EXITED, event -> hexagon.setStyle("-fx-opacity: 0.7"));
                //hexagon.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> System.out.println("x " + hexagon.getPoint().x + " y " + hexagon.getPoint().y));
                y = y + vert;

                Point point = cube_to_matrix(new Point(xm, zm), 2);
                zm += 1;
                //System.out.println("p " + point.x + " " + point.y);
                String s = (planetMatrix.get(id)[(int) point.x][(int) point.y]);


//                hexagon.setPoint(new Point(point.x, point.y));


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

    public Point cube_to_matrix (Point point, int r) {
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

    public ArrayList<String[][]> getPlanetMatrix() {
        return planetMatrix;
    }

    public Point[][] getMatrix_hex() {
        return matrix_hex;
    }

    public ArrayList<Hex> getHexs() {
        return hexs;
    }

    public Build[][][] getBuildMatrix() {
        return buildMatrix;
    }
}
