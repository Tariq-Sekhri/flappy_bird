package ca.sekhrit.flappy_bird;

import javafx.animation.AnimationTimer;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
/**
 * The Player class represents the player in the game.
 * It handles player movements and animations.
 */
@Data
public class Player {
    private String name;
    private Double x;
    private Double y;
    private Double vx;
    private Double vy;
    private ImageView imageView;
    private long lastUpdate = 0;
    private double gravity=1;
    private Score score;
    int imageNumber=0;

    /**
     * This constructor initializes the player and sets up the player's image.
     */
    public Player(){

        Image image  = new Image(getClass().getResource("/ca/sekhrit/flappy_bird/bird.png").toString());
        imageView = new ImageView(image);
        imageView.setFitHeight(65);
        imageView.setFitWidth(65);
        imageView.setPreserveRatio(true);
        imageView.setViewport(new Rectangle2D(0, 0, 147/* width from v1*/ , 140/* hight down*/));
        setX(50.0);
        setVy(10.0);
        setY(50.0);
        setVx(1.0);

        AnchorPane.setTopAnchor(imageView, getX());
        AnchorPane.setLeftAnchor(imageView, getY());

    }
    /**
     * This method updates the player's position based on the elapsed time.
     *
     * @param elapsedTime The elapsed time since the last frame.
     */
    public void move(double elapsedTime) {
        if(getY()<=0){
            setVy(50.0);
        }
        setX(getX() + getVx() * elapsedTime);
        setVy(getVy() +11.0);
        setY(getY() + getVy() * elapsedTime);
        AnchorPane.setTopAnchor(imageView, getY());
        AnchorPane.setLeftAnchor(imageView, getX());
    }
    /**
     * This method makes the player jump.
     */
    public void jump(){

        setVy(-700.0);
        if(getY()<=-50){
            setVy(40.0);
        }
    }
    /**
     * This method updates the player's animation frame.
     */
    public void nextFrame(){
        switch (imageNumber){
            case 0:
                imageView.setViewport(new Rectangle2D(0, 0, 147/* width from v1*/ , 140/* hight down*/));

                break;
            case 1:
                imageView.setViewport(new Rectangle2D(160, 0, 147/* width from v1*/ , 140/* hight down*/));
                break;
            case 2:
                imageView.setViewport(new Rectangle2D(330, 0, 147/* width from v1*/ , 140/* hight down*/));
                break;
            case 3:
                imageView.setViewport(new Rectangle2D(500, 0, 147/* width from v1*/, 140/* hight down*/));
                break;

            default:
                break;

        }
        imageNumber++;
        if(imageNumber>3){
            imageNumber=0;
        }

    }

}
