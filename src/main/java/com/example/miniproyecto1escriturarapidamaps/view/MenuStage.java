package com.example.miniproyecto1escriturarapidamaps.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Custom Stage class that serves as the entry point for the application's user interface.
 * This class extends {@link Stage} to initialize the main menu window, handling
 * resource loading and window branding for the "Fast Typing Arcade" game.
 *
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */
public class MenuStage extends Stage {

    /**
     * Application icon loaded from the internal resource path.
     * Used to provide visual branding in the window's title bar and OS taskbar.
     */
    Image icon = new Image(getClass().getResourceAsStream("/com/images/icons/keyboard.png"));

    /**
     * Constructs a new MenuStage and prepares the primary menu scene.
     * Orchestrates the loading of the main menu FXML and applies
     * specific window constraints like non-resizability.
     *
     * @throws IOException If the mainMenu.fxml resource cannot be retrieved or parsed.
     */
    public MenuStage()  throws IOException {
        // Configure the FXMLLoader to point to the main menu view
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/miniproyecto1escriturarapidamaps/mainMenu.fxml")
        );

        // Load the FXML graph into a Parent node
        Parent root = loader.load();

        // Define window metadata and appearance
        this.setTitle("Fast Typing Arcade");
        this.getIcons().add(icon);
        this.setResizable(false);

        // Initialize the scene and attach it to this stage
        Scene scene = new Scene(root);
        setScene(scene);
        show();
    }
}
