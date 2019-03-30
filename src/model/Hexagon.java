package model;


import javafx.scene.image.ImageView;

public class Hexagon extends ImageView {
    private int hexId;
    private ImageView image;
    private Point point; // matrix
    private String planet;
    //ужна отдельная матрица с планетами и строениями на них
    public Hexagon(String url, Point point, int hexId) {
        super(url);
        this.point = point;
        this.hexId = hexId;
    }

    public Point getCenter(){
        double x = image.getFitWidth()/2 + image.getLayoutX();
        double y = image.getFitHeight()/2 + image.getLayoutY();
        return new Point(x, y);
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public String getPlanet() {
        return planet;
    }

    public Point getPoint() {
        return point;
    }

    public int getHexId() {
        return hexId;
    }

    public void setHexId(int hexId) {
        this.hexId = hexId;
    }
}
