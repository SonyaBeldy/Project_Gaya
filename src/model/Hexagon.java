package model;


import javafx.scene.image.ImageView;

public class Hexagon extends ImageView {
    private int hexId;
    private ImageView image;
    private Point point; // matrix
    private String planet;
    private boolean checked = false;
    //ужна отдельная матрица с планетами и строениями на них
    public Hexagon(String url, Point point, int hexId) {
        super(url);
        this.point = point;
        this.hexId = hexId;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Point getCenter(){
        double x = getFitWidth()/2 + getLayoutX();
        double y = getFitHeight()/2 + getLayoutY();
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

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getHexId() {
        return hexId;
    }

    public void setHexId(int hexId) {
        this.hexId = hexId;
    }
}
