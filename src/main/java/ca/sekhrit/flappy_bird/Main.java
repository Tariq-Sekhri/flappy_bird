package ca.sekhrit.flappy_bird;

import ca.sekhrit.flappy_bird.controllers.GameEnd;
import ca.sekhrit.flappy_bird.controllers.Game;
import ca.sekhrit.flappy_bird.controllers.WelcomeScreen;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * This is the main class that manages the scenes
 *
 * @author Tariq Sekhri
 */
public class Main extends Application {

    private Stage primaryStage;


    Game game;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showWelcomeScreen();
    }
    /**
     * this shows the welcome screen
     */
    public void showWelcomeScreen() {
        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.setMain(this);
        Scene scene = new Scene(welcomeScreen.getRoot(), 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome");
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - scene.getWidth()) / 2);
        primaryStage.setY((screenBounds.getHeight() - scene.getHeight()) / 2);

        primaryStage.setResizable(false);
        primaryStage.show();
    }
    /**
     * this shows the new game screen
     */
    public void showNewGame(String name) {
        game = new Game(name);
        game.setMain(this);
        Scene scene = new Scene(game.getRoot(), 1920, 1000);
        game.setScene(scene);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - scene.getWidth()) / 2);
        primaryStage.setY((screenBounds.getHeight() - scene.getHeight()) / 2);

        primaryStage.show();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game");
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    /**
     * this shows the game over screen
     */
    public void showGameOver(Score score)  {
        game=null;
        GameEnd gameEnd = new GameEnd(score);
        gameEnd.setMain(this);
        Scene scene = new Scene(gameEnd.getRoot(), 1920, 1000);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - scene.getWidth()) / 2);
        primaryStage.setY((screenBounds.getHeight() - scene.getHeight()) / 2);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Over");
        primaryStage.setResizable(false);
        primaryStage.show();
    }


}
