package model;

import controller.Game;
import controller.PaneHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class Tech {

    static void addActionOnBtn(Game game, ImageView imageView, int ind){
            switch (ind) {
                case 6:
                    imageView.addEventFilter(MouseEvent.MOUSE_CLICKED, new PaneHandler(Events.techBtnClick, game, ind, imageView));
                    imageView.addEventFilter(MouseEvent.MOUSE_ENTERED, event -> {imageView.setOpacity(0.6);});
                    imageView.addEventFilter(MouseEvent.MOUSE_EXITED, event -> {imageView.setOpacity(1);});
                    break;
            }

        }

    public static void afterBuild(Game game, Hexagon hex, Build build){
        System.out.println("TECH " + game.getPlayer().getTech()[3]);
        Factory factory = new Factory();
        System.out.println("hex " + hex.getPlanet() + " " + factory.getPlanet("gr"));
        if(game.getPlayer().getTech()[3] == 1){
            if((build.getClass().equals(Mine.class))&&(hex.getPlanet().equals(factory.getPlanet("gr")))){
                int score = game.getPlayer().getScore() + 3;
                game.getPlayer().setScore(score);
                game.getViewElements().updateScore(game);
            }
        }
    }

    public static void getResFromTech(Game game, int n) {
        switch (n) {
            case 1:
                game.getPlayer().getResources()[1]++;
                game.getPlayer().getResources()[3]++;
                game.getViewElements().updateRes(game);
                break;
            case 2:
                game.getPlayer().getIncome()[1]++;
                game.getPlayer().getIncome()[5]++;
                game.getViewElements().updateIncome(game);
                break;
            case 3:
                game.getPlayer().getIncome()[2]++;
                game.getPlayer().getIncome()[0]++;
                game.getViewElements().updateIncome(game);
                break;
            case 4:

                break;
            case 5:
                game.getPlayer().getIncome()[2]+=4;
                break;
            case 6:
                //кнопка на перегонку
                break;
            case 7:
                game.getPlayer().setScore(game.getPlayer().getScore() + 7);
                game.getViewElements().updateScore(game);
                break;
            case 8:

                break;
            case 9:

                break;
        }
    }

}
