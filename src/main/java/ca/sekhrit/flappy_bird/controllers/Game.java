package ca.sekhrit.flappy_bird.controllers;

import ca.sekhrit.flappy_bird.Main;
import ca.sekhrit.flappy_bird.Pipe;
import ca.sekhrit.flappy_bird.Player;
import ca.sekhrit.flappy_bird.Score;
import javafx.animation.AnimationTimer;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import lombok.Data;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
/**
 * The Game class represents the game logic and controls the game flow.
 * It handles player movements, pipe movements, and collisions.
 */
@Data
public class  Game {

    private Scene scene;
    private Main main;
    private AnchorPane root;
    ArrayList<Pipe> pipeList;
    ArrayList<Pipe> pipeToAddList;
    long lastUpdate;
    long lastPipeAddTime;
    long lastPlayerNextFrame;
    AnimationTimer timer;
    Score score;


    /**
     * This constructor initializes the game with the player's name.
     * It sets up the game loop and the game elements.
     *
     * @param name The name of the player.
     */
    public Game(String name) {

        AnchorPane pane = new AnchorPane();
        score = new Score(name, 0);
        Label scoreLabel = new Label("Score: " + score.getScore());
        scoreLabel.setFont(new Font(40)); // Set font size to 40
        AnchorPane.setTopAnchor(scoreLabel, 10.0);
        AnchorPane.setLeftAnchor(scoreLabel, 10.0);

        Player p = new Player();
        pane.getChildren().add(p.getImageView());

        pipeList  = new ArrayList<Pipe>();
        pipeToAddList  = new ArrayList<Pipe>();



        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (lastUpdate > 0) {
                    double elapsedTime = (now - lastUpdate) / 1_000_000_000.0;


                    gameLoop(p,elapsedTime,pipeList,scoreLabel);

                }
                lastUpdate = now;
            }
        };
        timer.start();
        pane.getChildren().add(scoreLabel);
        root = pane;
        lastPipeAddTime = System.currentTimeMillis();



    }
    /**
     * This method is the main game loop.
     * It updates the player's position, checks for collisions, and updates the score.
     *
     * @param player The player object.
     * @param elapsedTime The elapsed time since the last frame.
     * @param pipeList The list of pipes.
     * @param scoreLabel The label to display the score.
     */
    public void gameLoop(Player player,Double elapsedTime,List<Pipe> pipeList,Label scoreLabel) {
        if (System.currentTimeMillis() - lastPlayerNextFrame >= 140) {
            player.nextFrame();
            lastPlayerNextFrame = System.currentTimeMillis();
        }

        player.move(elapsedTime);
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE) {
                player.jump();
            }
        });

        if (System.currentTimeMillis() - lastPipeAddTime >= 3000) {
            Random rand = new Random();
            Double lowerBound = -700.0;
            Double upperBound = 0.0;
            Double randomNumber = rand.nextDouble(upperBound - lowerBound + 1) + lowerBound;
            Double topY= randomNumber;

            Double spaceBetween= 1100.0;
            Double buttomY=topY+spaceBetween;
            pipeToAddList.add(new Pipe(1900.0,topY ,1));

            pipeToAddList.add(new Pipe(1900.0,buttomY ,0));

            lastPipeAddTime = System.currentTimeMillis();
        }
        if(player.getY()>950){
            timer.stop();
            main.showGameOver(score);
        }

        Iterator<Pipe> iterator = pipeToAddList.iterator();
        while (iterator.hasNext()) {
            Pipe pipe = iterator.next();
            root.getChildren().add(pipe.getImageView());
            pipeList.add(pipe);
            iterator.remove();
        }

        Iterator<Pipe> iterator1 = pipeList.iterator();
        while (iterator1.hasNext()) {
            Pipe pipe = iterator1.next();
            if (pipe.move(elapsedTime)) {
                root.getChildren().remove(pipe.getImageView());
                iterator1.remove();
                if(pipe.getTopOrBottom() == 1){

                    score.increaseScore();
                    scoreLabel.setText("Score: " + score.getScore());
                }
            }
        }
        Iterator<Pipe> iterator2 = pipeList.iterator();
        while (iterator2.hasNext()) {
            Pipe pipe = iterator2.next();
            if (checkCollision(player,pipe) ){
                timer.stop();
                main.showGameOver(score);
            }
        }
    }


    /**
     * This method checks if the player has collided with a pipe.
     * It returns true if a collision has occurred, false otherwise.
     *
     * @param player The player object.
     * @param pipe The pipe object.
     * @return true if a collision has occurred, false otherwise.
     */
    public Boolean checkCollision(Player player, Pipe pipe){

        if(pipe.getX() <  300){
            if(pipe.getTopOrBottom() == 1){
                Double pipeX = pipe.getX();
                Double pipeY = pipe.getY()+790;
                Double playerX = player.getImageView().getFitWidth()+60;
                Double playerY = player.getY();
                if(pipeX <= playerX &&pipeY >= playerY){
                    return true;
                }
            } else {
                Double pipeX = pipe.getX();
                Double pipeY = pipe.getY();
                Double playerX = player.getImageView().getFitWidth()+60;
                Double playerY = player.getY()+player.getImageView().getFitHeight()-10;
                if(pipeX <= playerX && pipeY <= playerY){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method sets the main application instance.
     *
     * @param main The main application instance.
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * This method returns the root node of the game screen.
     *
     * @return The root node of the game screen.
     */
    public Parent getRoot() {
        return root;
    }






}
