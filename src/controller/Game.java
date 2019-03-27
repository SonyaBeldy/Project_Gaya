package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.*;

import java.io.FileNotFoundException;


public class Game {

    private ScalePane scalePane;
    private int[] roundScoring = new int[6];
    private int[] finalScoring = new int[2];
    private int currentRound = 0;
    Map map;

    @FXML
    AnchorPane main;

    @FXML
    void initialize() throws FileNotFoundException {
        scalePane = new ScalePane();
        main.getChildren().add(scalePane);
        map = new Map(main, scalePane);
        main.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("click!");

            }
        });
    }




}
