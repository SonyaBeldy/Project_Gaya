package view.styles;

import controller.Game;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.ScienceImg;

import java.util.ArrayList;


public class ViewElements {


    //добавить в карту строку с 0-ми уровнями, запихнуть все в матрицу, а наши значения (в боте) в отдельный массив. Обработчики присвоить в Game или в новом классе.

    public void createBuildButton(Game game, VBox vBox, Label[] labels) {
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        for (int i = 0; i < game.getBuildsBtn().length; i++) {
            Button button = new Button("");
            int n = i + 1;
            ImageView imageView = new ImageView(new Image("./view/resourses/images/buildings/" + n + ".png"));
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
