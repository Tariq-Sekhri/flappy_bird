package ca.sekhrit.flappy_bird.controllers;

import ca.sekhrit.flappy_bird.Score;
import ca.sekhrit.flappy_bird.DatabaseAccess;
import javafx.animation.PauseTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import ca.sekhrit.flappy_bird.Main;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * The GameEnd class represents the end screen of the game.
 * It displays the final score and provides an option to play again.
 */
public class        GameEnd {
    private Scene scene;
    private Main main;
    private AnchorPane root;

    /**
     * This constructor initializes the end screen with the final score.
     * It sets up the score display and the play again button.
     *
     * @param score The final score of the game.
     */
    public GameEnd(Score score) {

        AnchorPane pane = new AnchorPane();
        Label newScore = new Label(score.toString());

        newScore.setFont(new Font(40));
        AnchorPane.setTopAnchor(newScore, 500.0);
        AnchorPane.setLeftAnchor(newScore, 850.0);
        pane.getChildren().add(newScore);
        Button playAgain = new Button("Play Again");
        playAgain.setDisable(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> playAgain.setDisable(false));
        pause.play();
        playAgain.setOnAction(actionEvent -> {
            main.showWelcomeScreen();
        });
        AnchorPane.setTopAnchor(playAgain, 600.0);
        AnchorPane.setLeftAnchor(playAgain, 850.0);
        pane.getChildren().add(playAgain);

        try {
            DatabaseAccess t = new DatabaseAccess();
            t.addScore(score);
            t.loadScoreList();

            Label allScore = new Label(  t.toString());
            allScore.setFont(new Font(40));

            AnchorPane.setTopAnchor(allScore, 0.0);
            AnchorPane.setLeftAnchor(allScore, 0.0);
            pane.getChildren().add(allScore);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        root = pane;

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
     * This method returns the root node of the end screen.
     *
     * @return The root node of the end screen.
     */
    public Parent getRoot() {
        return root;
    }
}
