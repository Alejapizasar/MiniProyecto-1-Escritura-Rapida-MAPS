package com.example.miniproyecto1escriturarapidamaps;


import com.example.miniproyecto1escriturarapidamaps.view.MenuStage;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Entry point class for the Fast Typing Arcade application.
 * This class initializes the JavaFX runtime and transitions the application
 * into its first visual state by invoking the main menu.
 *
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Standard JVM entry point.
     * Delegates the application startup to the JavaFX launch pipeline.
     *
     * @param args Command line arguments passed to the application.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The main entry point for all JavaFX applications.
     * This method is called after the system is ready for the application to
     * begin running, specifically used here to instantiate the {@link MenuStage}.
     *
     * @param primaryStage The primary stage for this application, onto which
     *                     the application scene can be set.
     * @throws IOException If the initial FXML for the menu cannot be loaded.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Initializes the custom MenuStage to display the starting interface
        new MenuStage();
    }
}
