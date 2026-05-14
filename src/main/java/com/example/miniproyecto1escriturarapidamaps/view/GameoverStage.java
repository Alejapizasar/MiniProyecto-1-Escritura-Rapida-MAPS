package com.example.miniproyecto1escriturarapidamaps.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Custom Stage class that represents the Game Over window.
 * This class extends {@link Stage} to encapsulate the window configuration,
 * including FXML loading, window title, icons, and resizing behavior.
 *
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */
public class GameoverStage extends Stage {

    /**
     * Application icon loaded from resources to be displayed in the window's title bar.
     */
    Image icon = new Image(getClass().getResourceAsStream("/com/images/icons/keyboard.png"));

    /**
     * Constructs a new GameoverStage.
     * Loads the FXML layout, sets the window title, applies the icon, and displays the scene.
     *
     * @throws IOException If the FXML file for the Game Over screen cannot be found or loaded.
     */
    public GameoverStage()  throws IOException {
        // Initialize the loader with the specific FXML resource path
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/miniproyecto1escriturarapidamaps/gameOver.fxml")
        );

        // Load the root node from the FXML file
        Parent root = loader.load();

        // Window appearance configurations
        this.setTitle("Fast Typing Arcade");
        this.getIcons().add(icon);
        this.setResizable(false);

        // Scene initialization and display
        Scene scene = new Scene(root);
        setScene(scene);
        show();
    }
}

