package model;

import controller.Game;
import controller.PaneHandler;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.Map;

import java.util.ArrayList;
import java.util.Collections;

public class GameRules {
    int distance=999;
    boolean f = false;


    private boolean startWaySearch(Game game, Point point){

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 7; k++) {
                    if(game.getMap().getBuildMatrix()[i][j][k]!=null){
                        Build build = game.getMap().getBuildMatrix()[i][j][k];
                        double cx = build.getLayoutX() + build.getFitWidth()/2;
                        double cy = build.getLayoutY() + build.getFitHeight()/2;
                        Hexagon hexagon = game.getMap().findHexagon(new Point(cx, cy));
                        ArrayList<Integer> result= new ArrayList<>();

                        game.getMap().setCheckedFalse();
                        ArrayList<Hexagon> checked = new ArrayList<>();
                        checked.add(hexagon);
                        //waySearch(hexagon, game.getMap().findHexagon(point), 0, game, result, checked);
                        waySearch2(hexagon, game.getMap().findHexagon(point), 0, game);
                        System.out.println("distance " + distance);
                        distance = 999;
                        //int distanse = waySearch(hexagon, game.getMap().findHexagon(point), 0, game, new ArrayList<>(), result);
                        if(!result.isEmpty()) {
                            System.out.println("d " + Collections.min(result));
                        }

                    }
                }
            }

        }
        System.out.println(f);
        return f;
    }

    private void waySearch2(Hexagon current, Hexagon last, int distanceSoFar, Game game){

        if((current == null)||(distanceSoFar > game.getPlayer().getRange())){
            //System.out.println("Не получилосб");
            //f = false;
            return;
        }
        if((distanceSoFar == game.getPlayer().getRange())&&(current.getPlanet() == null)){
            //f = false;
            return;
        }
        if(current == last){
            distance = distanceSoFar;
            System.out.println("Получилось");
            f = true;
            return;
        }
            double w = current.getFitWidth() / 2;
            double h = current.getFitHeight() / 2;
            if ((distanceSoFar > 0)) {
                //DropShadow shadow = new DropShadow(BlurType.THREE_PASS_BOX, Color.valueOf("#9b9b9b"), 18, 0.9, 0, 0);
                //shadow.setWidth(6);
                //shadow.setHeight(6);
                //current.setEffect(shadow);
                current.setOpacity(1);
            }
            current.addEventFilter(MouseEvent.MOUSE_ENTERED, event -> current.setStyle("-fx-opacity: 1"));
            current.addEventFilter(MouseEvent.MOUSE_EXITED, event -> current.setStyle("-fx-opacity: 1"));

            Point center = current.getCenter();

            Hexagon hex = game.getMap().findHexagon(new Point(center.x, center.y - 2 * h));
            if((hex!=null)&&(!hex.isChecked())) {
                waySearch2(hex, last, distanceSoFar + 1, game);
            }
            hex = game.getMap().findHexagon(new Point(center.x + 2 * w, center.y - h));
            if((hex!=null)&&(!hex.isChecked())) {
                waySearch2(hex, last, distanceSoFar + 1, game);
            }
            hex = game.getMap().findHexagon(new Point(center.x + 2 * w, center.y + h));
            if((hex!=null)&&(!hex.isChecked())) {
                waySearch2(hex, last, distanceSoFar + 1, game);
            }
            hex = game.getMap().findHexagon(new Point(center.x, center.y + 2 * h));
            if((hex!=null)&&(!hex.isChecked())) {
                waySearch2(hex, last, distanceSoFar + 1, game);
            }
            hex = game.getMap().findHexagon(new Point(center.x - 2 * w, center.y + h));
            if((hex!=null)&&(!hex.isChecked())) {
                waySearch2(hex, last, distanceSoFar + 1, game);
            }
            hex = game.getMap().findHexagon(new Point(center.x - 2 * w, center.y - h));
            if((hex!=null)&&(!hex.isChecked())) {
                waySearch2(hex, last, distanceSoFar + 1, game);
            }

    }

    private void waySearch(Hexagon current, Hexagon last, int distance, Game game, ArrayList<Integer> res, ArrayList<Hexagon> checked){
        if(current == last){
           // System.out.println("d " + distance);
            res.add(distance);
            //distance = 0;
        } else {
            double w = current.getFitWidth() / 2;
            double h = current.getFitHeight() / 2;
            distance++;
            System.out.println("checked = " );
//            System.out.print(distance + " ");
//            System.out.println();
            current.setChecked(true);
//            checked.add(current);
            current.setOpacity(1);
            Point center = current.getCenter();

            Hexagon hex = game.getMap().findHexagon(new Point(center.x, center.y - 2 * h));
            if((hex != null) && (checked.indexOf(hex) == -1)) {
                checked.add(hex);
                waySearch(hex, last, distance, game, res, new ArrayList<>(checked));
            }

            hex = game.getMap().findHexagon(new Point(center.x + 2 * w, center.y - h));
            if((hex != null) && (checked.indexOf(hex) == -1)) {
                checked.add(hex);
                waySearch(hex, last, distance, game, res, new ArrayList<>(checked));
            }

            hex = game.getMap().findHexagon(new Point(center.x + 2 * w, center.y + h));
            if((hex != null) && (checked.indexOf(hex) == -1)) {
                checked.add(hex);
                waySearch(hex, last, distance, game, res, new ArrayList<>(checked));
            }

            hex = game.getMap().findHexagon(new Point(center.x, center.y + 2 * h));
            if((hex != null) && (checked.indexOf(hex) == -1)) {
                checked.add(hex);
                waySearch(hex, last, distance, game, res, new ArrayList<>(checked));
            }

            hex = game.getMap().findHexagon(new Point(center.x - 2 * w, center.y + h));
            if((hex != null) && (checked.indexOf(hex) == -1)) {
                checked.add(hex);
                waySearch(hex, last, distance, game, res, new ArrayList<>(checked));
            }

            hex = game.getMap().findHexagon(new Point(center.x - 2 * w, center.y - h));
            if((hex != null) && (checked.indexOf(hex) == -1)) {
                checked.add(hex);
                waySearch(hex, last, distance, game, res, new ArrayList<>(checked));
            }
        }

    }

    public void techBtnClick(Game game, int ind, ImageView imageView){
        switch (ind){
            case 6:
                game.getPlayer().energyTransfer(4);
                game.getViewElements().updateEnergy(game);
                imageView.setDisable(true);
                break;
        }
    }

    public void energyTransfer(Game game){
        if(game.getPlayer().getEnergy()[2] >= 2){
            game.getPlayer().getResources()[4]--;
            game.getPlayer().getEnergy()[2]-=2;
            game.getPlayer().getEnergy()[3]++;
            game.getViewElements().updateRes(game);
            game.getViewElements().updateEnergy(game);
        } else {
            System.out.println("Во 2-м отсеке недостаточно энергии");
        }
    }

    public void endTurnClick(Game game) {
        game.getPlayer().updateResEndTurn();
        game.getViewElements().updateRes(game);
        game.getViewElements().updateEnergy(game);
        for (int i = 0; i < game.getPlayersTecnologias().getChildren().size(); i++) {
            game.getPlayersTecnologias().getChildren().get(i).setDisable(false);
        }

    }

    public void getTech(Game game, ScienceImg scienceImg) {
        int n = game.getTech()[scienceImg.getType() - 1];
        ImageView tech = new ImageView("view/resourses/images/tech/t" + n + ".png");
        game.getPlayersTecnologias().getChildren().add(tech);
        game.getPlayer().getTech()[n-1] = 1;
        scienceImg.levelUp();
        for (int i = 0; i < game.getTechBox1().getChildren().size(); i++) {
            game.getTechBox1().getChildren().get(i).setDisable(true);
        }
        for (int i = 0; i < game.getTechBox2().getChildren().size(); i++) {
            game.getTechBox2().getChildren().get(i).setDisable(true);
        }
        Tech.addActionOnBtn(game, tech, n);
        Tech.getResFromTech(game, n);
    }

    public void getTech2(Game game, int ind){
        game.setGetHighTech(true);
        game.getPlayer().getTech()[ind] = 1;

        game.getPlayersTecnologias().getChildren().add(game.getTechBox2().getChildren().get(ind));

        for (int i = 0; i < game.getTechBox1().getChildren().size(); i++) {
            game.getTechBox1().getChildren().get(i).setOpacity(0.7);
            game.getTechBox1().getChildren().get(i).setDisable(true);
        }
        for (int i = 0; i < game.getTechBox2().getChildren().size(); i++) {
            game.getTechBox1().getChildren().get(i).setOpacity(0.7);
            game.getTechBox2().getChildren().get(i).setDisable(true);
        }
        System.out.println("Вы можете бесплатно прокачать любую ветку науки");
    }

    public void addBuild (Game game, Hexagon hex) {
        if(game.isBuildSelected()) {
            Map map = game.getMap();

            Point point = game.getMap().cube_to_matrix(hex.getPoint(), 2);

            Build build = game.getSelectedBuid();
            Build build2 = map.getBuildMatrix()[(int) point.x][(int) point.y][hex.getHexId()];

            boolean f = true;
            if (build2 == null) {
                if (build.getClass().equals(Mine.class)) {
                    if(game.getPlayer().getMineStartCount() == 0) {
                        f = startWaySearch(game, hex.getCenter());
                    }
                    Factory factory = new Factory();
                    int ind = factory.getPlanetId(hex, point, game);
                    if(!build.isBuildIsOver(game.getPlayer()) && f && (hex.getPlanet() != null) && (ind != 8)) {
                        switch (ind) {
                            case 7:
                                game.getPlayer().getResources()[3] --;
                                break;
                            default:
                                game.getPlayer().getResources()[1] = game.getPlayer().getResources()[2] - game.getPlayer().getTerraCount()[ind] * game.getPlayer().getTerraCost();
                                break;
                        }
                        game.getViewElements().addBuild(game.getPlayers().size()-1, build.getInd(), hex.getHexId(), (int) hex.getCenter().x, (int)hex.getCenter().y, game);
                        Tech.afterBuild(game, hex, build);
                    }
                } else {
                    System.out.println("Вы не можете построить это здание здесь");// ЗДАНИЕ ПОСТАВИТЬ НЕЛЬЗЯ
                }
            } else {
                if (build.getClass().getSuperclass() == build2.getClass()) {
                    if(!build.isBuildIsOver(game.getPlayer())) {

                        System.out.println("build id " + build.getInd());
                        game.getViewElements().addBuild(game.getPlayers().size()-1, build.getInd(), hex.getHexId(), (int) hex.getCenter().x, (int)hex.getCenter().y, game);
                        build2.destroy(game.getPlayer());
                        game.getScalePane().getItems().remove(build2);

                        if ((build.getClass().equals(Laboratory.class))||(build.getClass().equals(Academy.class))) {
                            System.out.println("Вы можете взять обычнуюю технологию");
                            for (int i = 0; i < game.getTechBox1().getChildren().size(); i++) {
                                int n = game.getTech()[i];
                                if(game.getPlayer().getTech()[n - 1] == 0) {
                                    game.getTechBox1().getChildren().get(i).setDisable(false);
                                }
                            }
                            int j = 6;
                            for (int i = 0; i < game.getTechBox2().getChildren().size(); i++) {
                                if(game.getPlayer().getTech()[j] == 0) {
                                    game.getTechBox2().getChildren().get(i).setDisable(false);
                                }
                                j++;
                            }
                        }
                    }

                } else {
                    System.out.println("Вы не можете построить это здание");// ЗДАНИЕ ПОСТАВИТЬ НЕЛЬЗЯ
                }
            }


        }
        game.getMap().setNormOpacity();
        game.getViewElements().updateRes(game);
        game.getViewElements().updateIncome(game);
        game.getViewElements().updateLeftoversBuilds(game);
        game.setBuildSelected(false);
    }


    public void addBuild2 (Game game, Hexagon hex) {
        if(game.isBuildSelected()) {
            Map map = game.getMap();

            System.out.println("hex.getPoint " + hex.getPoint().x + " " + hex.getPoint().y);
            Point point = game.getMap().cube_to_matrix(hex.getPoint(), 2);
            System.out.println("cube to matrix  " + point.x + " " + point.y);


            Build build = game.getSelectedBuid();
            double cx = hex.getFitWidth()/2 + hex.getLayoutX();
            double cy = hex.getFitHeight()/2 + hex.getLayoutY();

            Build build2 = map.getBuildMatrix()[(int) point.x][(int) point.y][hex.getHexId()];

            boolean f = true;
            if (build2 == null) {
                if (build.getClass().equals(Mine.class)) {
                    if(game.getPlayer().getMineStartCount() == 0) {
                        f = startWaySearch(game, hex.getCenter());
                    }
                    Factory factory = new Factory();
                    int ind = factory.getPlanetId(hex, point, game);
                    if(!build.isBuildIsOver(game.getPlayer()) && f && (hex.getPlanet()!=null) && (ind != 8)) {

                        switch (ind) {
                            case 7:
                                game.getPlayer().getResources()[1] = game.getPlayer().getResources()[2] - game.getPlayer().getTerraCount()[ind] * game.getPlayer().getTerraCost();
                                break;
                            default:
                                game.getPlayer().getResources()[3] --;
                                break;
                        }
                        //System.out.println("planet " + ind + " cost " + game.getPlayer().getTerraCount()[ind]);
                        //System.out.println("planet " + ind + " cost " + game.getPlayer().getTerraCount()[ind]);

                        build.add(game.getPlayer());
                        map.getBuildMatrix()[(int) point.x][(int) point.y][hex.getHexId()] = build;
                        build.addEventHandler(MouseEvent.MOUSE_CLICKED, new PaneHandler(Events.hexagonOnClick, game, hex));
                        game.getScalePane().getItems().add(build);
                        build.setLayoutX(cx - build.getFitWidth() / 2);
                        build.setLayoutY(cy - build.getFitHeight() / 2);

                        Tech.afterBuild(game, hex, build);
                    }
                } else {
                    System.out.println("Вы не можете построить это здание здесь");// ЗДАНИЕ ПОСТАВИТЬ НЕЛЬЗЯ
                }
            } else {
                if (build.getClass().getSuperclass() == build2.getClass()) {

                    if(!build.isBuildIsOver(game.getPlayer())) {
                        build.add(game.getPlayer());
                        game.getScalePane().getItems().add(build);
                        build.setLayoutX(cx - build.getFitWidth() / 2);
                        build.setLayoutY(cy - build.getFitHeight() / 2);
                        build.addEventHandler(MouseEvent.MOUSE_CLICKED, new PaneHandler(Events.hexagonOnClick, game, hex));
                        build2.destroy(game.getPlayer());
                        game.getScalePane().getItems().remove(build2);
                        map.getBuildMatrix()[(int) point.x][(int) point.y][hex.getHexId()] = build;

                        if ((build.getClass().equals(Laboratory.class))||(build.getClass().equals(Academy.class))) {
                            System.out.println("Вы можете взять обычнуюю технологию");
                            for (int i = 0; i < game.getTechBox1().getChildren().size(); i++) {
                                int n = game.getTech()[i];
                                if(game.getPlayer().getTech()[n - 1] == 0) {
                                    game.getTechBox1().getChildren().get(i).setDisable(false);
                                }
                            }
                            int j = 6;
                            for (int i = 0; i < game.getTechBox2().getChildren().size(); i++) {
                                if(game.getPlayer().getTech()[j] == 0) {
                                    game.getTechBox2().getChildren().get(i).setDisable(false);
                                }
                                j++;
                            }
                        }
                    }

                } else {
                    System.out.println("Вы не можете построить это здание");// ЗДАНИЕ ПОСТАВИТЬ НЕЛЬЗЯ
                }
            }


        }
        game.getMap().setNormOpacity();
        game.getViewElements().updateRes(game);
        game.getViewElements().updateIncome(game);
        game.getViewElements().updateLeftoversBuilds(game);
        game.setBuildSelected(false);
    }

    public void selectBuild (Game game, Button button) {
        Build build = new Build();
        System.out.println(button.getId());
        switch (Integer.parseInt(button.getId())) {
            case 0:
                build  = new Academy(game.getPlayer());
                break;
            case 1:
                build = new Laboratory(game.getPlayer());
                break;
            case 2:
                build = new Institute(game.getPlayer());
                break;
            case 3:
                build = new Outpost(game.getPlayer());
                break;
            case 4:
                build = new Mine(game.getPlayer());
                startWaySearch(game, new Point(build.getX(), build.getY()));
                break;

        }
        game.setBuildSelected(true);
        game.setSelectedBuid(build);
    }

    public void levelUpScience (Game game, ScienceImg scienceImg) {
        if (scienceImg.getLevel() < 5) {
            scienceImg.levelUp();
            if (!game.isGetHighTech()) {
                game.getPlayer().getResources()[2] -= 4;
            } else {
                game.setGetHighTech(false);
            }
            game.getPlayer().updateResourses(scienceImg);
            game.getViewElements().updateRes(game);
            game.getViewElements().updateIncome(game);
            game.getViewElements().updateEnergy(game);
        }

    }

}
