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


public class MenuController {
    @FXML
    private Button exitBtn;

    @FXML
    private Button startBtn;

    /**
     * Initializes menu animations
     * and hover effects.
     */

    @FXML
    public void initialize() {

        startBtn.setOnMouseEntered(e -> {

            startBtn.setScaleX(1.1);
            startBtn.setScaleY(1.1);
        });

        startBtn.setOnMouseExited(e -> {

            startBtn.setScaleX(1.0);
            startBtn.setScaleY(1.0);
        });

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
     * Opens the gameplay screen.
     *
     * @param event button click event
     * @throws IOException if the stage cannot be loaded
     */
    @FXML
    void onHandleStart(ActionEvent event) throws IOException {
        new GameplayStage();
        Stage currentStage= (Stage) startBtn.getScene().getWindow();
        currentStage.close();
    }
    /**
     * Closes the application.
     * @param event button click event
     */
    @FXML
    void onHandleExit(ActionEvent event) {
        System.exit(0);
    }

}
