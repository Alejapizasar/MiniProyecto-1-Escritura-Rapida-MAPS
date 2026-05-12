package com.example.miniproyecto1escriturarapidamaps.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class GameplayStage extends Stage {

    Image icon = new Image(getClass().getResourceAsStream("/com/images/icons/keyboard.png"));
    public GameplayStage()  throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/miniproyecto1escriturarapidamaps/gamePlay.fxml")
        );

        Parent root = loader.load();
        this.setTitle("Fast Typing Arcade");
        this.getIcons().add(icon);
        this.setResizable(false);


        Scene scene = new Scene(root);
        setScene(scene);
        show();
    }
}

