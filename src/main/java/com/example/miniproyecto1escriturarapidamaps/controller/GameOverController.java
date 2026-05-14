/**
 * Controller class for the Game Over screen.
 * This class handles the display of final statistics and navigation
 * back to the game or the main menu.
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */
package com.example.miniproyecto1escriturarapidamaps.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Control;

public class GameOverController {

    @FXML private Label levelLabel;
    @FXML private Label wordsLabel;
    @FXML private Label scoreResultLabel;
    @FXML private Button restartBtn;
    @FXML private Button menuBtn;
    @FXML private Label exitBtn;

    /**
     * Initializes the controller by setting up visual effects.
     * This method is automatically called after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {

        addHoverEffect(restartBtn);

        addHoverEffect(menuBtn);

        addHoverEffect(exitBtn);
    }
    /**
     * Adds a scaling hover effect to a JavaFX control.
     * When the mouse enters the control area, it scales up; when it exits, it returns to normal size.
     *
     * @param control The JavaFX control (Button, Label, etc.) to apply the effect to.
     */
    private void addHoverEffect(Control control) {

        if(control == null) return;

        control.setOnMouseEntered(e -> {

            control.setScaleX(1.1);
            control.setScaleY(1.1);
        });

        control.setOnMouseExited(e -> {

            control.setScaleX(1.0);
            control.setScaleY(1.0);
        });
    }

    /**
     * Sets and displays the final performance metrics of the game session.
     *
     * @param level The final level reached by the player.
     * @param finalScore The total score accumulated during the session.
     * @param totalWords The total count of correctly typed words.
     */
    public void setResults(int level, int finalScore, int totalWords) {
        levelLabel.setText(String.valueOf(level));
        wordsLabel.setText(String.valueOf(totalWords));
        scoreResultLabel.setText(String.valueOf(finalScore));
    }

    /**
     * Transitions the scene back to the gameplay screen to start a new match.
     *
     * @param event The ActionEvent triggered by clicking the restart button.
     */
    @FXML
    void handleRestart(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/miniproyecto1escriturarapidamaps/gameplay.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Transitions the scene back to the main menu screen.
     *
     * @param event The ActionEvent triggered by clicking the menu button.
     */
    @FXML
    void handleMenu(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/miniproyecto1escriturarapidamaps/mainMenu.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Terminates the application execution.
     */
    @FXML
    void handleExit() {
        System.exit(0);
    }
}