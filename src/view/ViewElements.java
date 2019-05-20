package view;

import controller.Game;
import controller.PaneHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.*;

import java.util.ArrayList;

public class ViewElements {

    //добавить в карту строку с 0-ми уровнями, запихнуть все в матрицу, а наши значения (в боте) в отдельный массив. Обработчики присвоить в Game или в новом классе.

    public void addBuild(int playerInd, int type, int hexInd, int x, int y, Game game){//расса, тип, номер гекса, коорднаты шестиугольника

        Hexagon hexagon = game.getMap().findHexagon(new Point(x, y));
        Point point = game.getMap().cube_to_matrix(hexagon.getPoint(), 2);

        Build build = new Build();
        switch (type){
            case 1:
                build = new Academy(game.getPlayers().get(playerInd));
                break;
            case 2:
                build = new Laboratory(game.getPlayers().get(playerInd));
                break;
            case 3:
                build = new Institute(game.getPlayers().get(playerInd));
                break;
            case 4:
                build = new Outpost(game.getPlayers().get(playerInd));
                break;
            case 5:
                build = new Mine(game.getPlayers().get(playerInd));
                break;
            case 6:
                //спутники
                break;
        }

        build.add(game.getPlayer());

        game.getMap().getBuildMatrix()[(int) point.x][(int) point.y][hexInd] = build;
        System.out.println("buildM " + point.x + " " + point.y);
        build.addEventHandler(MouseEvent.MOUSE_CLICKED, new PaneHandler(Events.hexagonOnClick, game, hexagon));
        game.getScalePane().getItems().add(build);

        build.setLayoutX(hexagon.getCenter().x - build.getFitWidth() / 2);
        build.setLayoutY(hexagon.getCenter().y - build.getFitHeight() / 2);


    }

    public void addGoals(HBox box, int[] goals) {
        box.setSpacing(4);
        for (int i = 0; i < goals.length - 2; i++) {
            box.getChildren().add(new ImageView(new Image("./view/resourses/images/goal/goal" + goals[i] + ".png")));
        }

        for (int i = goals.length - 2; i < goals.length; i++) {
            box.getChildren().add(new ImageView(new Image("./view/resourses/images/goal/final" + goals[i] + ".png")));
        }
    }

    public void createScienceMap(VBox mapBox, HBox upScience, int[] highTech, HBox highTechBox) {
        AnchorPane pane3 = new AnchorPane();
        mapBox.getChildren().add(pane3);
        pane3.getChildren().add(new ImageView(new Image("./view/resourses/images/smap.png")));
        pane3.getChildren().add(highTechBox);

        AnchorPane.setLeftAnchor(highTechBox, (double) 51);
        AnchorPane.setTopAnchor(highTechBox, (double) 89);
        highTechBox.setSpacing(62);
        for (int i = 0; i < highTech.length; i++) {
            highTechBox.getChildren().add(new ImageView(new Image("./view/resourses/images/tech/tp" + highTech[i] + ".png")));
        }

        mapBox.setPadding(new Insets(10));
        mapBox.setSpacing(10);
        upScience.setSpacing(66);
        AnchorPane pane = new AnchorPane();
        mapBox.getChildren().add(pane);
        AnchorPane.setLeftAnchor(upScience, (double) 33);
        pane.getChildren().add(upScience);

        for (int i = 1; i < 7; i++) {
            ImageView imageView = new ImageView(new Image("./view/resourses/images/" + i + ".png"));
            upScience.getChildren().add(imageView);
            DropShadow shadow = new DropShadow(BlurType.THREE_PASS_BOX, getShadowColor(i), 18, 0.9, 0, 0);
            shadow.setWidth(6);
            shadow.setHeight(6);
            imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> imageView.setEffect(shadow));
            imageView.addEventHandler(MouseEvent.MOUSE_EXITED, event ->  imageView.setEffect(null));

        }

    }

    public void addTechOnScienceMap(VBox mapBox, int[] tech, HBox techBox1, HBox techBox2) {
        AnchorPane pane2 = new AnchorPane();
        mapBox.getChildren().add(pane2);
        ImageView image = new ImageView(new Image("./view/resourses/images/s.png"));
        pane2.getChildren().add(image);

        pane2.getChildren().add(techBox1);
        techBox1.setSpacing(47);
        AnchorPane.setLeftAnchor(techBox1, (double) 22);
        AnchorPane.setTopAnchor(techBox1, (double) 5);

        for (int i = 0; i < tech.length - 3; i++) {
            ImageView tech1 = new ImageView(new Image("./view/resourses/images/tech/t" + tech[i] + ".png"));
            techBox1.getChildren().add(tech1);
            tech1.setDisable(true);
            DropShadow shadow = new DropShadow(BlurType.THREE_PASS_BOX, getShadowColor(i + 1), 18, 0.9, 0, 0);
            shadow.setWidth(6);
            shadow.setHeight(6);
            techBox1.getChildren().get(i).addEventFilter(MouseEvent.MOUSE_ENTERED, event -> { tech1.setEffect(shadow); });
            techBox1.getChildren().get(i).addEventFilter(MouseEvent.MOUSE_EXITED, event -> { tech1.setEffect(null); });
        }

        pane2.getChildren().add(techBox2);
        techBox2.setSpacing(191);
        AnchorPane.setLeftAnchor(techBox2, (double) 95);
        AnchorPane.setTopAnchor(techBox2, (double) 107);
        int j = 0;
        for (int i = tech.length - 3; i < tech.length; i++) {
            ImageView tech2 = new ImageView(new Image("./view/resourses/images/tech/t" + tech[i] + ".png"));
            techBox2.getChildren().add(tech2);
            tech2.setDisable(true);
            DropShadow shadow = new DropShadow(BlurType.THREE_PASS_BOX, Color.WHITE, 18, 0.9, 0, 0);
            shadow.setWidth(6);
            shadow.setHeight(6);
            techBox2.getChildren().get(j).addEventFilter(MouseEvent.MOUSE_ENTERED, event -> { tech2.setEffect(shadow); });
            techBox2.getChildren().get(j).addEventFilter(MouseEvent.MOUSE_EXITED, event -> { tech2.setEffect(null); });
            j++;
        }
        AnchorPane.setLeftAnchor(image, (double) 17);
    }

    public void createBuildButton(Game game, VBox vBox, Label[] labels) {
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        for (int i = 0; i < game.getBuildsBtn().length; i++) {
            Button button = new Button("");
            int n = i + 1;
            ImageView imageView = new ImageView(new Image("./view/resourses/images/buildings/" + game.getPlayer().getRace() + "/" + n + ".png"));
            button.setGraphic(imageView);
            button.setStyle("-fx-background-color: transparent");
            button.setPrefWidth(imageView.getFitWidth());
            button.setPrefHeight(imageView.getFitHeight());
            button.setId(String.valueOf(i));
            game.getBuildsBtn()[i] = button;
            button.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> button.setStyle("-fx-opacity: 0.8; -fx-background-color: transparent"));
            button.addEventHandler(MouseEvent.MOUSE_EXITED, event -> button.setStyle("-fx-opacity: 1; -fx-background-color: transparent"));
            vBox.getChildren().add(button);

            Label label = new Label("0/1\n ");
            label.setStyle("-fx-font-family: 'Akrobat Bold'; -fx-text-fill: #7a7a7a; -fx-font-size: 16");
            labels[i] = label;
            vBox.getChildren().add(label);
        }
    }

    public void updateLeftoversBuilds(Game game) {
        Label[] labels = game.getLeftoversBuilds();
        labels[0].setText(game.getPlayer().getBuildsCount()[0] + "/" + 2);
        labels[1].setText(game.getPlayer().getBuildsCount()[1] + "/" + 3);
        labels[2].setText(game.getPlayer().getBuildsCount()[2] + "/" + 1);
        labels[3].setText(game.getPlayer().getBuildsCount()[3] + "/" + 4);
        labels[4].setText(game.getPlayer().getBuildsCount()[4] + "/" + 8);
    }

    public ArrayList<Label[]> createResBox(Label[] resL, Label[] incomeL, int[] res, int[] income, HBox hBox) {
        ArrayList<Label[]> list = new ArrayList<>();
        hBox.setSpacing(4);
        hBox.setAlignment(Pos.CENTER);
        for (int i = 0; i < resL.length; i++) {
            int n = i + 1;
            ImageView imageView = new ImageView(new Image("./view/resourses/images/res/" + n + ".png"));
            imageView.setFitWidth(29);
            imageView.setFitHeight(36);
            Label label = new Label("   " + String.valueOf(res[i]), imageView);
            label.setStyle("-fx-font-size: 16pt; -fx-font-family: 'Akrobat Bold'; -fx-text-fill: #919191");
            hBox.getChildren().add(label);
            resL[i] = label;
            label = new Label("+ " + income[i] + "    ");
            label.setStyle("-fx-font-size: 14pt; -fx-font-family: 'Akrobat Bold'; -fx-text-fill: #4d4d4d");
            hBox.getChildren().add(label);
            incomeL[i] = label;

        }
        list.add(resL);
        list.add(incomeL);
        return list;
    }

    public void createEnergyText(Game game, HBox energyBox, Label[] energyLabels){
        energyBox.setSpacing(40);
        energyBox.setPrefWidth(200);
        for (int i = 0; i < energyLabels.length; i++) {
            System.out.println("text " + game.getPlayer().getEnergy()[i]);
            Label text = new Label(String.valueOf(game.getPlayer().getEnergy()[i]));
            text.setStyle("-fx-font-size: 18pt; -fx-font-family: 'Akrobat Bold'; -fx-text-fill: #b9b7b7");
            energyBox.getChildren().add(text);
            energyLabels[i] = text;
        }
    }

    public void updateRes (Game game) {
        for (int i = 0; i < game.getResLabels().length; i++) {
            game.getResLabels()[i].setText("   " + String.valueOf(game.getPlayer().getResources()[i]));
        }
    }

    public void updateIncome(Game game) {
        for (int i = 0; i < game.getIncomeLabels().length; i++) {
            game.getIncomeLabels()[i].setText(String.valueOf("+ " + game.getPlayer().getIncome()[i]) + "    ");
        }
    }

    public void updateEnergy(Game game) {
        for (int i = 0; i < game.getEnergyBox().getChildren().size(); i++) {
            game.getEnergyLabels()[i].setText(String.valueOf(game.getPlayer().getEnergy()[i]));
        }
    }

    public void updateScore(Game game){
        game.getScoreText().setText(String.valueOf(game.getPlayer().getScore()));
    }



    public ScienceImg[][] createScienceMap(HBox hBox, ScienceImg[][] scienceMap) {
        hBox.setPrefHeight(369);
        hBox.setSpacing(6);
        int k = 1;
        for (int i = 1; i < 7; i++) {
            VBox vBox = new VBox(6);
            hBox.getChildren().add(vBox);
            for (int j = 5; j >= 0; j--) {
                ScienceImg scienceImg = createImageView(143, 59, "./view/resourses/images/sience/" + i + "." + j + ".png", i, i, j);
                vBox.getChildren().add(scienceImg);
                scienceMap[i - 1][Math.abs(j - 5)] = scienceImg;
                if (j == 3) {
                    StackPane stackPane = new StackPane();
                    stackPane.setPrefWidth(143);
                    stackPane.setPrefHeight(54);
                    ImageView imageView = new ImageView("./view/resourses/images/sience/e" + k + ".png");
                    imageView.setFitWidth(46);
                    imageView.setFitWidth(54);
                    stackPane.getChildren().add(imageView);
                    vBox.getChildren().add(stackPane);
                    k++;
                }
            }
        }
        return scienceMap;
    }



    public ScienceImg[] createBotScienceMap(HBox hBox, int[] science, ScienceImg[] scienceImgs) {
        hBox.setSpacing(6);
        for (int i = 1; i < 7; i++) {
            ScienceImg scienceImg = createImageView(143, 59, "./view/resourses/images/sience/" + i + "." + science[i - 1] + ".png", i, i, 0);
            hBox.getChildren().add(scienceImg);
            scienceImgs[i - 1] = scienceImg;
        }
        return scienceImgs;
    }

    private ScienceImg createImageView(int w, int h, String url, int ind, int type, int level) {
        ScienceImg science = new ScienceImg(type, level);
        science.setImage(new Image(url));
        science.setFitWidth(w);
        science.setFitHeight(h);
        DropShadow shadow = new DropShadow(BlurType.THREE_PASS_BOX, getShadowColor(ind), 18, 0.9, 0, 0);
        shadow.setWidth(6);
        shadow.setHeight(6);
        if (ind != 0) {
            science.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> science.setEffect(shadow));
            science.addEventHandler(MouseEvent.MOUSE_EXITED, event -> science.setEffect(null));
            //science.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> getUrl(science));
        }
        return science;
    }

    private void getUrl(ScienceImg imageView) {
        System.out.println(imageView.getType() + " . " + imageView.getLevel());
    }

    private Color getShadowColor(int ind) {
        Color color = Color.valueOf("#ffffff");
        switch (ind) {
            case 1:
                color = Color.valueOf("#c59550");
                break;
            case 2:
                color = Color.valueOf("#5f92c9");
                break;
            case 3:
                color = Color.valueOf("#5abc5a");
                break;
            case 4:
                color = Color.valueOf("#c462ae");
                break;
            case 5:
                color = Color.valueOf("#b5b256");
                break;
            case 6:
                color = Color.valueOf("#61c5c5");
                break;

        }
        return color;
    }

}
