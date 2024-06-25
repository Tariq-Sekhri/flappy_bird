package ca.sekhrit.flappy_bird.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import ca.sekhrit.flappy_bird.Main;

/**
 * The WelcomeScreen class represents the welcome screen of the game.
 * It allows the user to enter their name and start a new game.
 */
public class WelcomeScreen {
    private Main main;
    private final VBox root;
    private String userName;

    public WelcomeScreen() {
        root = new VBox();
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        Button saveButton = new Button("Start Game");
        saveButton.setOnAction(e -> {
            userName = nameField.getText();
            if (main != null) {
                main.showNewGame(userName);
            }
        });
        nameField.setOnAction(e -> {
            userName = nameField.getText();
            if (main != null) {
                main.showNewGame(userName);
            }
        });

        root.getChildren().addAll(nameField, saveButton);
    }

    /**
     * Sets the main application instance.
     *
     * @param main The main application instance.
     */
    public void setMain(Main main) {
        this.main = main;
    }

    /**
     * Returns the root node of the welcome screen.
     *
     * @return The root node of the welcome screen.
     */
    public Parent getRoot() {
        return root;
    }

    /**
     * Returns the user's name.
     *
     * @return The user's name.
     */
    public String getUserName() {
        return userName;
    }
}
