package controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Game {

    @FXML
    AnchorPane main;

    @FXML
    void initialize() {
        createHex(50, 100, 100);
        createHex(50, 150, 450);
    }

    void createHex(int size, double x, double y){
        double h = size * 2;
        double vert = h * 3/4;
        double w = Math.sqrt(3)/2 * h;
        double horiz = w;
        int[] mas = {3, 4, 5, 4, 3};
        double x0 = x, y0 = y;
        for (int i = 0; i < 5; i ++) {
            for (int j = 0; j < mas[i]; j++) {
                ImageView hex = new ImageView("./view/resourses/images/hexagon.png");
                hex.setFitHeight(h);
                hex.setFitWidth(w);
                hex.setLayoutX(x);
                hex.setLayoutY(y);
                x = x + horiz;
                main.getChildren().add(hex);
            }
            if (i != 4) {
                x = x0 - horiz * (mas[i + 1] - mas[0]) / 2;
                y = y + vert;
            }
        }
    }

}
