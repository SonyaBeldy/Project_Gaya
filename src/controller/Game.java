package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.*;
import view.Map;
import view.styles.ViewElements;

import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Game {

    private ScalePane scalePane;
    private int[] roundScoring = new int[6];
    private int[] finalScoring = new int[2];
    private int currentRound = 0;
    private ScienceImg[] scienceImgs = new ScienceImg[6];
    private ScienceImg[][] scienceMap = new ScienceImg[6][6];
    private Label[] resLabels = new Label[5];
    private Label[] incomeLabels = new Label[5];
    private Button[] buildsBtn = new Button[5];
    private ArrayList<Mine>[] builds = new ArrayList[5];
    private Map map;
    ViewElements viewElements = new ViewElements();
    Player player;
    private boolean isBuildSelected = false;
    private Build selectedBuid;
    private Label[] leftoversBuilds = new Label[5];


    @FXML
    AnchorPane main, bot, resourcesPane;
    @FXML
    HBox hBox, hBoxBot, resBox;
    @FXML
    VBox buildsBox;
    @FXML
    Button endTurn;

    boolean f = false;

    @FXML
    void initialize() throws FileNotFoundException {
        player = new Player(Race.Terrans);
        player.updateResEndTurn();
        scalePane = new ScalePane();
        main.getChildren().add(scalePane);
        map = new Map(main, scalePane);
        scienceMap = viewElements.createScienceMap(hBox, scienceMap);
        scienceImgs = viewElements.createBotScienceMap(hBoxBot, player.getScience(), scienceImgs);
        ArrayList<Label[]> list = new ArrayList<>();
        list = viewElements.createResBox(resLabels, incomeLabels, player.getResources(), player.getIncome(), resBox);
        resLabels = list.get(0);
        incomeLabels = list.get(1);
        hBox.setVisible(false);
        viewElements.createBuildButton(this, buildsBox, leftoversBuilds);
        viewElements.updateLeftoversBuilds(this);
        viewElements.updateRes(this);
        hBoxBot.addEventFilter(MouseEvent.MOUSE_ENTERED, event -> {
            if (!hBox.isVisible()) hBox.setVisible(true);
            f = true;
        });
        hBoxBot.addEventFilter(MouseEvent.MOUSE_EXITED, event -> {
            f = false;
        });
        hBox.addEventFilter(MouseEvent.MOUSE_EXITED, event -> {
            if ((hBox.isVisible()) && (!f)) {
                hBox.setVisible(false);
            }
        });

        setScienceOnClick();
        setBuildsBtnOnClick();
        setHexagonOnClick();
        endTurn.addEventHandler(MouseEvent.MOUSE_CLICKED, new PaneHandler(Events.endTurnClick, this));


    }




    private void setHexagonOnClick() {
        for (int i = 0; i < map.getHexs().size(); i++) {
            for (int j = 0; j < map.getHexs().get(i).getHexagons().size(); j++) {
                map.getHexs().get(i).getHexagons().get(j).addEventHandler(MouseEvent.MOUSE_CLICKED, new PaneHandler(Events.hexagonOnClick, this,  map.getHexs().get(i).getHexagons().get(j)));
            }
        }
    }

    private void setBuildsBtnOnClick() {
        for (int i = 0; i < buildsBtn.length; i++) {
            buildsBtn[i].addEventHandler(MouseEvent.MOUSE_CLICKED, new PaneHandler(Events.buildsBtnClick, this, buildsBtn[i]));
        }
    }

    private void setScienceOnClick() {
        for (int i = 0; i < scienceMap.length; i++) {
            for (int j = 0; j < scienceMap.length; j++) {
                scienceMap[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new PaneHandler(Events.scienceMapClick, this, scienceMap[i][j]));
            }
        }
    }

    public ScienceImg[] getScienceImgs() {
        return scienceImgs;
    }

    public void setScienceImgs(ScienceImg[] scienceImgs) {
        this.scienceImgs = scienceImgs;
    }

    public ScienceImg[][] getScienceMap() {
        return scienceMap;
    }

    public Player getPlayer() {
        return player;
    }

    public AnchorPane getMain() {
        return main;
    }

    public ScalePane getScalePane() {
        return scalePane;
    }

    public void setSelectedBuid(Build selectedBuid) {
        this.selectedBuid = selectedBuid;
    }

    public Build getSelectedBuid() {
        return selectedBuid;
    }

    public void setBuildSelected(boolean buildSelected) {
        isBuildSelected = buildSelected;
    }

    public boolean isBuildSelected() {
        return isBuildSelected;
    }

    public Map getMap() {
        return map;
    }

    public ViewElements getViewElements() {
        return viewElements;
    }

    public Label[] getIncomeLabels() {
        return incomeLabels;
    }

    public Label[] getLeftoversBuilds() {
        return leftoversBuilds;
    }

    public Button[] getBuildsBtn() {
        return buildsBtn;
    }

    public Label[] getResLabels() {
        return resLabels;
    }
}
