package com.example.miniproyecto1escriturarapidamaps.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Custom Stage class that manages the primary gameplay window.
 * This class extends {@link Stage} to centralize the initialization of the
 * game arena, handling FXML loading, resource mapping, and window properties.
 *
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */
public class GameplayStage extends Stage {

    /**
     * Application icon displayed in the system taskbar and window title bar.
     */
    Image icon = new Image(getClass().getResourceAsStream("/com/images/icons/keyboard.png"));

    /**
     * Constructs a new GameplayStage and initializes the user interface.
     * Loads the FXML definition for the game play screen and configures
     * the primary stage settings.
     *
     * @throws IOException If the gamePlay.fxml resource cannot be located or loaded.
     */
    public GameplayStage()  throws IOException {
        // Initialize the FXML loader with the gameplay layout path
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/miniproyecto1escriturarapidamaps/gamePlay.fxml")
        );

        // Load the UI hierarchy from the FXML file
        Parent root = loader.load();

        // Window metadata and behavior configuration
        this.setTitle("Fast Typing Arcade");
        this.getIcons().add(icon);
        this.setResizable(false);

        // Scene setup and execution
        Scene scene = new Scene(root);
        setScene(scene);
        show();
    }
}

