/**
 * Controls the gameplay logic, timer system,
 * user interactions, score handling,
 * and level progression.
 *
 * This controller applies event-oriented
 * programming concepts using JavaFX,
 * EventHandler implementations,
 * inner classes, and adapters.
 *
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */
package com.example.miniproyecto1escriturarapidamaps.controller;

import com.example.miniproyecto1escriturarapidamaps.manager.AudioManager;
import com.example.miniproyecto1escriturarapidamaps.service.GameService;
import com.example.miniproyecto1escriturarapidamaps.util.AnimationManager;
import com.example.miniproyecto1escriturarapidamaps.model.WordGenerator;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;

/**
 * Main controller for the gameplay screen.
 * Orchestrates the UI updates by delegating logic to Service, Manager, and Utility classes.
 */
public class GamePlayController {

    @FXML private Label wordLabel, timeLabel, levelLabel, msnLabel, scoreLabel;
    @FXML private TextField textFieldBox;
    @FXML private BorderPane mainBorderPane;

    // Orchestrators
    private final GameService gameService = new GameService();
    private final AudioManager audioManager = new AudioManager();
    private final WordGenerator wordGenerator = new WordGenerator();

    private String currentWord;
    private Timeline timeline;

    /**
     * Initializes the controller, sets up events, and starts the game.
     */
    @FXML
    public void initialize() {
        textFieldBox.setOnKeyPressed(new KeyboardHandler());
        AnimationManager.applyFadeScaleEntrance(mainBorderPane);

        startGame();
    }

    /**
     * Resets service state and UI for a new game session.
     */
    public void startGame() {
        gameService.resetGame();
        updateUI();

        audioManager.playBackgroundMusic("/sounds/game.wav");
        nextWord();
        startTimer();
    }

    /**
     * Generates a new word based on current level difficulty.
     */
    private void nextWord() {
        boolean useHardWords = gameService.getLevel() > 3;
        currentWord = wordGenerator.getRandomWord(useHardWords);
        wordLabel.setText(currentWord);
        textFieldBox.clear();
    }

    /**
     * Validates user input and triggers corresponding animations/sounds.
     */
    private void validateWord() {
        String input = textFieldBox.getText();
        boolean isCorrect = gameService.processWord(input, currentWord);

        if (isCorrect) {
            audioManager.playEffect("/sounds/correct.wav");
            AnimationManager.playCorrectAnimation(wordLabel);
            msnLabel.setText("Correct! (" + gameService.getWordsInLevel() + "/5)");
            msnLabel.setStyle("-fx-text-fill: #00ff00;");

            if (gameService.shouldLevelUp()) {
                levelUp();
            } else {
                nextWord();
            }
        } else {
            audioManager.playEffect("/sounds/wrong.wav");
            AnimationManager.playErrorShake(textFieldBox);
            msnLabel.setText("Wrong! Try Again!");
            msnLabel.setStyle("-fx-text-fill: #ff0000;");
            textFieldBox.clear();
        }
        updateUI();
    }

    private void levelUp() {
        gameService.levelUp();
        updateUI();
        nextWord();
        startTimer();
    }

    private void updateUI() {
        scoreLabel.setText("Score: " + gameService.getScore());
        levelLabel.setText("Level: " + gameService.getLevel());
        timeLabel.setText(gameService.getTimeLeft() + " seg");
    }

    /**
     * Sets up and starts the countdown timeline.
     */
    private void startTimer() {
        if (timeline != null) timeline.stop();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new TimerHandler()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void goToGameOver() {
        try {
            if (timeline != null) timeline.stop();
            audioManager.stopBackgroundMusic();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto1escriturarapidamaps/gameOver.fxml"));
            Parent root = loader.load();

            GameOverController controller = loader.getController();
            controller.setResults(gameService.getLevel(), gameService.getScore(), gameService.getTotalWordsCorrect());

            audioManager.playEffect("/sounds/gameover.wav");

            Stage stage = (Stage) wordLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- Inner Handlers ---

    private class TimerHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            int time = gameService.getTimeLeft() - 1;
            gameService.setTimeLeft(time);
            timeLabel.setText(time + " seg");

            // Visual feedback for low time
            if (time <= 5) timeLabel.setStyle("-fx-text-fill: red;");
            else if (time <= 10) timeLabel.setStyle("-fx-text-fill: yellow;");
            else timeLabel.setStyle("-fx-text-fill: #2ee6e6;");

            if (time <= 0) {
                timeline.stop();
                goToGameOver();
            }
        }
    }

    private class KeyboardHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.ENTER) {
                validateWord();
            }
        }
    }

    @FXML void onHandleCheck() { validateWord(); }
    @FXML void handleRestartGame() { startGame(); }
}