package controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Outpost;

public class PaneHandler implements EventHandler<MouseEvent> {
    @Override
    public void handle(MouseEvent event) {
        switch (String.valueOf(event.getEventType())) {
            case "MOUSE_CLICKED":

                break;
        }
    }
}
