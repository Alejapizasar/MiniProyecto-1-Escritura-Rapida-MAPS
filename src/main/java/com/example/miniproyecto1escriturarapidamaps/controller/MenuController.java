/**
 * Controls the main menu screen
 * and handles navigation events.
 *
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */

package com.example.miniproyecto1escriturarapidamaps.controller;

import com.example.miniproyecto1escriturarapidamaps.view.GameplayStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the Main Menu screen.
 * This class handles the initial user interactions, including starting the game,
 * exiting the application, and managing visual hover effects for the menu buttons.
 */
public class MenuController {
    @FXML
    private Button exitBtn;

    @FXML
    private Button startBtn;

    /**
     * Initializes the menu controller.
     * Sets up interactive scaling animations (hover effects) for the menu buttons
     * to improve the user interface experience.
     */
    @FXML
    public void initialize() {

        // Apply hover effects to the Start Button
        startBtn.setOnMouseEntered(e -> {
            startBtn.setScaleX(1.1);
            startBtn.setScaleY(1.1);
        });

        startBtn.setOnMouseExited(e -> {
            startBtn.setScaleX(1.0);
            startBtn.setScaleY(1.0);
        });

        // Apply hover effects to the Exit Button
        exitBtn.setOnMouseEntered(e -> {
            exitBtn.setScaleX(1.1);
            exitBtn.setScaleY(1.1);
        });

        exitBtn.setOnMouseExited(e -> {
            exitBtn.setScaleX(1.0);
            exitBtn.setScaleY(1.0);
        });
    }
    /**
     * Handles the action of the start button by initializing the gameplay stage.
     * Closes the current menu window and opens the main game screen.
     *
     * @param event The ActionEvent triggered by clicking the start button.
     * @throws IOException If the FXML for the GameplayStage cannot be loaded.
     */
    @FXML
    void onHandleStart(ActionEvent event) throws IOException {
        // Initialize the new gameplay window
        new GameplayStage();

        // Retrieve and close the current menu stage
        Stage currentStage= (Stage) startBtn.getScene().getWindow();
        currentStage.close();
    }
    /**
     * Terminates the application execution when the exit button is clicked.
     *
     * @param event The ActionEvent triggered by clicking the exit button.
     */
    @FXML
    void onHandleExit(ActionEvent event) {
        System.exit(0);
    }

}
