/**
 * Controls the Game Over screen
 * and displays final results.
 *
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
     * Initializes hover effects
     * for the Game Over menu.
     */
    @FXML
    public void initialize() {

        addHoverEffect(restartBtn);

        addHoverEffect(menuBtn);

        addHoverEffect(exitBtn);
    }

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
     * Displays the final results
     * of the completed game.
     * @param level final level reached
     * @param finalScore final score obtained
     * @param totalWords total correct words
     */
    public void setResults(int level, int finalScore, int totalWords) {
        levelLabel.setText(String.valueOf(level));
        wordsLabel.setText(String.valueOf(totalWords));
        scoreResultLabel.setText(String.valueOf(finalScore));
    }

    /**
     * Restarts the game.
     * @param event button click event
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
     * Returns to the main menu.
     * @param event button click event
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
     * Closes the application completely.
     */
    @FXML
    void handleExit() {
        System.exit(0);
    }
}