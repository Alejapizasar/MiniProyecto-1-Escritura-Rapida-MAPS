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

import com.example.miniproyecto1escriturarapidamaps.managers.AudioManager;
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
 * Orchestrates the UI updates and user interactions by delegating business logic
 * to Service, Manager, and Utility classes.
 */
public class GamePlayController {

    @FXML private Label wordLabel, timeLabel, levelLabel, msnLabel, scoreLabel;
    @FXML private TextField textFieldBox;
    @FXML private BorderPane mainBorderPane;

    // Core logic and resource orchestrators
    private final GameService gameService = new GameService();
    private final AudioManager audioManager = new AudioManager();
    private final WordGenerator wordGenerator = new WordGenerator();

    private String currentWord;
    private Timeline timeline;

    /**
     * Initializes the controller, sets up keyboard event listeners,
     * and triggers initial UI entrance animations.
     */
    @FXML
    public void initialize() {
        // Visual and event configurations
        textFieldBox.setOnKeyPressed(new KeyboardHandler());
        AnimationManager.applyFadeScaleEntrance(mainBorderPane);

        startGame();
    }

    /**
     * Resets the game service state and prepares the UI for a new play session.
     * Starts background music and initializes the first word and timer.
     */
    public void startGame() {
        gameService.resetGame();
        updateUI();

        // Start background ambient music
        audioManager.playBackgroundMusic("/sounds/game.wav");

        nextWord();
        startTimer();
    }

    /**
     * Fetches a new word from the generator based on the current level difficulty
     * and updates the display label.
     */
    private void nextWord() {
        boolean useHardWords = gameService.getLevel() > 3;
        currentWord = wordGenerator.getRandomWord(useHardWords);
        wordLabel.setText(currentWord);
        textFieldBox.clear();
    }
    /**
     * Compares the user input with the target word.
     * Triggers success or error feedback (sounds, animations, messages)
     * and checks for level progression.
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
    /**
     * Advances the player to the next level, refreshes the UI,
     * and restarts the countdown timer.
     */
    private void levelUp() {
        gameService.levelUp();
        updateUI();
        nextWord();
        startTimer();
    }
    /**
     * Synchronizes the UI labels with the current data stored in the GameService.
     */
    private void updateUI() {
        scoreLabel.setText("Score: " + gameService.getScore());
        levelLabel.setText("Level: " + gameService.getLevel());
        timeLabel.setText(gameService.getTimeLeft() + " sec");
    }
    /**
     * Configures and starts the JavaFX Timeline responsible for the game's countdown.
     */
    private void startTimer() {
        if (timeline != null) timeline.stop();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new TimerHandler()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Terminates the current game session, stops all audio/timers,
     * and transitions the scene to the Game Over view.
     */
    public void goToGameOver() {
        try {
            if (timeline != null) timeline.stop();
            audioManager.stopBackgroundMusic();
            audioManager.playEffect("/sounds/gameover.wav");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto1escriturarapidamaps/gameOver.fxml"));
            Parent root = loader.load();

            GameOverController controller = loader.getController();
            controller.setResults(gameService.getLevel(), gameService.getScore(), gameService.getTotalWordsCorrect());

            Stage stage = (Stage) wordLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // --- Inner Handlers ---

    /**
     * Inner class to handle the tick logic of the game timer.
     * Updates remaining time and provides visual/auditory warnings.
     */
    private class TimerHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            int time = gameService.getTimeLeft() - 1;
            gameService.setTimeLeft(time);
            timeLabel.setText(time + " sec");

            // Critical time warnings (5 seconds or less)
            if (time <= 5 && time > 0) {
                timeLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                audioManager.playEffect("/sounds/warning.wav");
                AnimationManager.playCorrectAnimation(timeLabel);
            }
            else if (time <= 10) {
                timeLabel.setStyle("-fx-text-fill: yellow;");
                AnimationManager.playCorrectAnimation(timeLabel);
            }
            else {
                timeLabel.setStyle("-fx-text-fill: #2ee6e6;");
            }

            if (time <= 0) {
                timeline.stop();
                goToGameOver();
            }
        }
    }
    /**
     * Inner class to handle keyboard input within the TextField.
     * Specifically listens for the ENTER key to trigger word validation.
     */
    private class KeyboardHandler implements EventHandler<KeyEvent> {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.ENTER) {
                validateWord();
            }
        }
    }
    /**
     * Event handler for the manual check button.
     */
    @FXML void onHandleCheck() { validateWord(); }
    /**
     * Event handler to restart the game session manually.
     */
    @FXML void handleRestartGame() { startGame(); }
}