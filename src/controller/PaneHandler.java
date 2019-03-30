package controller;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import model.*;

import java.util.ArrayList;

public class PaneHandler implements EventHandler<MouseEvent> {


    Events event;
    GameRules gameRules = new GameRules();
    Game game;
    private Hexagon hexagon;
    private Button button;
    ScienceImg scienceImg;
    private ArrayList<Mine>[] builds;

    public PaneHandler (Events event, Game game) {
        this.event = event;
        this.game = game;
    }

    public PaneHandler(Events event, Game game, ScienceImg scienceImg) {
        this.event = event;
        this.game = game;
        this.scienceImg = scienceImg;
    }

    public PaneHandler(Events event, Game game, Button button) {
        this.event = event;
        this.game = game;
        this.button = button;
        this.builds = builds;
    }

    public PaneHandler(Events event, Game game, Hexagon hexagon) {
        this.event = event;
        this.game = game;
        this.hexagon = hexagon;
        this.button = button;
    }

    @Override
    public void handle(MouseEvent event) {
        switch (this.event) {
            case scienceMapClick:
                switch (String.valueOf(event.getEventType())) {
                    case "MOUSE_CLICKED":
                        gameRules.levelUpScience(game, scienceImg);
                        break;
                }
                break;
            case buildsBtnClick:
                switch (String.valueOf(event.getEventType())) {
                    case "MOUSE_CLICKED":
                        gameRules.selectBuild(game, button);
                        break;
                }
                break;
            case hexagonOnClick:
                switch (String.valueOf(event.getEventType())) {
                    case "MOUSE_CLICKED":
                        gameRules.addBuild(game, hexagon);
                        break;
                }
                break;
            case endTurnClick:
                gameRules.endTurnClick(game);
                break;
        }
    }
}
