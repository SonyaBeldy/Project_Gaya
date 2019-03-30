package model;

import controller.Game;
import controller.PaneHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import view.Map;



public class GameRules {

    public void endTurnClick(Game game) {
        game.getPlayer().updateResEndTurn();
        game.getViewElements().updateRes(game);
    }

    public void addBuild (Game game, Hexagon hex) {
        if(game.isBuildSelected()) {
            Map map = game.getMap();
            Point point = game.getMap().cube_to_matrix(hex.getPoint(), 2);
            Build build = game.getSelectedBuid();
            double cx = hex.getFitWidth()/2 + hex.getLayoutX();
            double cy = hex.getFitHeight()/2 + hex.getLayoutY();

            Build build2 = map.getBuildMatrix()[(int) point.x][(int) point.y][hex.getHexId()];


            if (build2 == null) {
                if (build.getClass().equals(Mine.class)) {

                    if(!build.isBuildIsOver(game.getPlayer())) {
                        build.add(game.getPlayer());
                        map.getBuildMatrix()[(int) point.x][(int) point.y][hex.getHexId()] = build;
                        build.addEventHandler(MouseEvent.MOUSE_CLICKED, new PaneHandler(Events.hexagonOnClick, game, hex));
                        game.getScalePane().getItems().add(build);
                        build.setLayoutX(cx - build.getFitWidth() / 2);
                        build.setLayoutY(cy - build.getFitHeight() / 2);
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
                    }

                } else {
                    System.out.println("Вы не можете построить это здание");// ЗДАНИЕ ПОСТАВИТЬ НЕЛЬЗЯ
                }
            }


        }
        game.getViewElements().updateRes(game);
        game.getViewElements().updateIncome(game);
        game.getViewElements().updateLeftoversBuilds(game);
        game.setBuildSelected(false);
    }

    private Build createBuild(Build build) {

        return build;
    }

    public void selectBuild (Game game, Button button) {
        Build build = new Build();
        System.out.println(button.getId());
        switch (Integer.parseInt(button.getId())) {
            case 0:
                build  = new Academy();
                break;
            case 1:
                build = new Laboratory();
                break;
            case 2:
                build = new Institute();
                break;
            case 3:
                build = new Outpost();
                break;
            case 4:
                build = new Mine();
                break;

        }
        game.setBuildSelected(true);
        game.setSelectedBuid(build);


    }

    public void levelUpScience (Game game, ScienceImg scienceImg) {
        int type = scienceImg.getType();
        int level = scienceImg.getLevel();
        game.getScienceImgs()[type - 1].setImage(scienceImg.getImage());
        //game.getScienceImgs()[type] = scienceImg;
        game.getPlayer().getScience()[type - 1] = level;
        System.out.println("type " + type + " level " + level);

    }

//Image image = new Image(getClass().getResourceAsStream("play3.jpg"));

}
