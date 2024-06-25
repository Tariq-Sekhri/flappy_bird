package ca.sekhrit.flappy_bird;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.Data;
/**
 * The Pipe class represents a pipe in the game.
 * It handles pipe movements and collisions.
 */
@Data
public class Pipe {

    private Double x;
    private Double y;
    private Double vx;
    private ImageView imageView;
    private int topOrBottom;
    /**
     * This method returns the x-coordinate of the pipe.
     *
     * @return The x-coordinate of the pipe.
     */
    public Double getX() {
        return x;
    }
    /**
     * This constructor initializes the pipe with its position and orientation.
     *
     * @param x The x-coordinate of the pipe.
     * @param y The y-coordinate of the pipe.
     * @param topOrBottom The orientation of the pipe (0 for bottom, 1 for top).
     */
    public Pipe(Double x, Double y, int topOrBottom){
        this.topOrBottom = topOrBottom;
        Image image = new Image(getClass().getResource("/ca/sekhrit/flappy_bird/pipe.png").toString());
        imageView = new ImageView();
        imageView.setImage(image);
        imageView.setViewport(new Rectangle2D(0, 0, 215, 800));
        setX(x);
        setY(y);
        setVx(-200.0);
        AnchorPane.setTopAnchor(imageView, getY());
        AnchorPane.setLeftAnchor(imageView, getX());
    }
    /**
     * This method moves the pipe based on the elapsed time.
     * It returns true if the pipe has moved off the screen, false otherwise.
     *
     * @param elapsedTime The elapsed time since the last frame.
     * @return true if the pipe has moved off the screen, false otherwise.
     */
    public boolean move(double elapsedTime) {
        setX(getX() + getVx() * elapsedTime);
        AnchorPane.setLeftAnchor(imageView, getX());

        if(getX()<-100 ){
            return true;
        }
        return false;
    }


}
