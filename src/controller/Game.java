package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.text.Text;
import model.*;
import view.Map;
import view.ViewElements;

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
    private Label[] energyLabels = new Label[4];
    private Button[] buildsBtn = new Button[5];
    private Map map;
    private ViewElements viewElements = new ViewElements();
    Start start = new Start();
    Player player;
    private boolean isBuildSelected = false;
    private Build selectedBuid;
    private Label[] leftoversBuilds = new Label[5];
    private int goals[] = new int[8];
    private int highTech[] = new int[6];
    private int tech[] = new int[9];
    private HBox upScience = new HBox();
    private HBox techBox1 = new HBox();
    private HBox techBox2 = new HBox();
    private HBox highTechBox = new HBox();
    private VBox playersTecnologias = new VBox();
    private boolean getHighTech = false;
    private ArrayList<Player> players = new ArrayList<>();


    @FXML
    AnchorPane main, bot, resourcesPane;
    @FXML
    HBox hBox, hBoxBot, resBox, goalsBox, energyBox;
    @FXML
    VBox buildsBox, mapBox, otherBox;
    @FXML
    Button endTurn, mapBtn, otherBtn, ebtn;

    @FXML
    ImageView e;

    @FXML Text scoreText;


    boolean f = false;

    @FXML
    void initialize() throws FileNotFoundException {
        player = new Player(Race.Terrans);
        start.generateQueue("Terrans", players);
        player.updateResEndTurn();
        scalePane = new ScalePane();
        main.getChildren().add(scalePane);
        map = new Map(main, scalePane);
        e.toFront();
        energyBox.toFront();
        ebtn.toFront();

        AnchorPane backPane = new AnchorPane();
        main.getChildren().add(backPane);
        backPane.toFront();
        AnchorPane.setTopAnchor(backPane, 58d);
        AnchorPane.setLeftAnchor(backPane, 11d);
        ImageView backMap = new ImageView(new Image("./view/resourses/images/backMap.png"));
        backPane.getChildren().add(backMap);
        backPane.setVisible(false);
        backPane.getChildren().add(otherBox);

        start.generateTech(tech);
        start.generateHighTech(highTech);
        viewElements.createScienceMap(mapBox, upScience, highTech, highTechBox);
        viewElements.addTechOnScienceMap(mapBox, tech, techBox1, techBox2);
        scienceImgs = viewElements.createBotScienceMap(hBoxBot, player.getScience(), scienceImgs);

        ArrayList<Label[]> list = viewElements.createResBox(resLabels, incomeLabels, player.getResources(), player.getIncome(), resBox);

        resLabels = list.get(0);
        incomeLabels = list.get(1);

        hBox.setVisible(false);

        viewElements.createBuildButton(this, buildsBox, leftoversBuilds);
        viewElements.updateLeftoversBuilds(this);
        viewElements.updateRes(this);
        viewElements.createEnergyText(this, energyBox, energyLabels);
        viewElements.updateEnergy(this);

        setBuildsBtnOnClick();
        setHexagonOnClick();
        endTurn.addEventHandler(MouseEvent.MOUSE_CLICKED, new PaneHandler(Events.endTurnClick, this));

        start.generateGoals(goals);
        viewElements.addGoals(goalsBox, goals);

        mapBox.setVisible(false);
        mapBtn.toFront();
        mapBtn.setOnAction(event -> {
            if(!mapBox.isVisible()) {
                hideBox(backPane);
                mapBox.setVisible(true);
                moveBoxBtn(1);
                backPane.setVisible(true);

                } else {
                hideBox(backPane);
                moveBoxBtn(-1);
            }
        });

        otherBtn.setOnAction(event -> {
            if(!otherBox.isVisible()){
                hideBox(backPane);
                backPane.setVisible(true);
                otherBox.setVisible(true);
                moveBoxBtn(1);
            } else {
                hideBox(backPane);
                moveBoxBtn(-1);
            }


        });

        setScienceUpClick(upScience);
        setTechClick(techBox1, techBox2);

        otherBox.setSpacing(15);
        //e.getChildren().add(new ImageView(new Image("./view/resourses/images/e.png")));
        otherBox.getChildren().add(playersTecnologias);
        AnchorPane.setLeftAnchor(playersTecnologias, (double) 15);
        //AnchorPane.setTopAnchor(playersTecnologias, (double) 15);
        playersTecnologias.setSpacing(15);

        ebtn.addEventHandler(MouseEvent.MOUSE_CLICKED, new PaneHandler(Events.energTransfer, this));
        ebtn.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {ebtn.setOpacity(0.6);});
        ebtn.addEventHandler(MouseEvent.MOUSE_EXITED, event -> {ebtn.setOpacity(1);});

    }

    private void moveBoxBtn (int i) {
        switch (i) {
            case 1:
                AnchorPane.setLeftAnchor(mapBtn, (double) 904);
                mapBtn.getGraphic().setRotate(-90);
                AnchorPane.setLeftAnchor(otherBtn, (double) 904);
                otherBtn.getGraphic().setRotate(-90);
                break;
            case -1:
                AnchorPane.setLeftAnchor(mapBtn, (double) 0);
                mapBtn.getGraphic().setRotate(90);
                AnchorPane.setLeftAnchor(otherBtn, (double) 0);
                otherBtn.getGraphic().setRotate(90);
                break;
        }
    }

    public void hideBox(AnchorPane backMap) {
        backMap.setVisible(false);

        otherBox.setVisible(false);
        mapBox.setVisible(false);
    }

    public VBox getPlayersTecnologias() {
        return playersTecnologias;
    }

    public HBox getTechBox1() {
        return techBox1;
    }

    public HBox getTechBox2() {
        return techBox2;
    }

    private void setScienceUpClick(HBox upScience) {
        for (int i = 0; i < upScience.getChildren().size(); i++) {
            upScience.getChildren().get(i).addEventFilter(MouseEvent.MOUSE_CLICKED, new PaneHandler(Events.scienceUpClick, this, scienceImgs[i]));
        }
    }

    private void setTechClick(HBox tech, HBox tech2){
        for (int i = 0; i < tech.getChildren().size(); i++) {
            tech.getChildren().get(i).addEventFilter(MouseEvent.MOUSE_CLICKED, new PaneHandler(Events.techClick, this, scienceImgs[i]));
        }
        int j = 6;
        for (int i = 0; i < tech2.getChildren().size(); i++) {
            tech2.getChildren().get(i).addEventFilter(MouseEvent.MOUSE_CLICKED, new PaneHandler(Events.tech2Click, this, j));
            j++;
        }
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

    private void setScienceOnClick(HBox buttons) {

        for (int i = 0; i < buttons.getChildren().size(); i++) {
            buttons.getChildren().get(i).addEventFilter(MouseEvent.MOUSE_CLICKED, new PaneHandler(Events.scienceUpClick, this, i));
        }
        for (int i = 0; i < scienceMap.length; i++) {
            for (int j = 0; j < scienceMap.length; j++) {
                //scienceMap[i][j].addEventHandler(MouseEvent.MOUSE_CLICKED, new PaneHandler(Events.scienceMapClick, this, scienceMap[i][j]));
            }
        }
    }

    public HBox getEnergyBox() {
        return energyBox;
    }

    public Label[] getEnergyLabels() {
        return energyLabels;
    }

    public Text getScoreText() {
        return scoreText;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int[] getTech() {
        return tech;
    }

    public boolean isGetHighTech() {
        return getHighTech;
    }

    public void setGetHighTech(boolean getHighTech) {
        this.getHighTech = getHighTech;
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
