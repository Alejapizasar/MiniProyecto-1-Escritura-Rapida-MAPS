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

import com.example.miniproyecto1escriturarapidamaps.events.GameEventAdapter;
import com.example.miniproyecto1escriturarapidamaps.model.GameModel;
import com.example.miniproyecto1escriturarapidamaps.model.WordGenerator;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class GamePlayController {

    @FXML private Label wordLabel;
    @FXML private Label timeLabel;
    @FXML private Label levelLabel;
    @FXML private Label msnLabel;
    @FXML private TextField textFieldBox;
    @FXML private Label scoreLabel;

    private MediaPlayer correctSound;
    private MediaPlayer wrongSound;
    private MediaPlayer gameOverSound;

    private GameModel model = new GameModel();
    private WordGenerator wordGenerator = new WordGenerator();
    private String currentWord;
    private Timeline timeline;

    private MediaPlayer backgroundMusicPlayer;

    private int timeLeft;
    private int score = 0;
    private int wordsClearedInCurrentLevel = 0;
    private int totalWordsCorrect = 0;
    private GameHandler gameHandler = new GameHandler();

    /**
     * Inner class responsible for handling timer events.
     */
    private class TimerHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {

            timeLeft--;
            timeLabel.setText(timeLeft + " seg");
            timeLabel.setStyle("");
            if(timeLeft <= 10 && timeLeft > 5){
                timeLabel.setStyle("-fx-text-fill: yellow;");
            }
            else if(timeLeft <= 5){
                timeLabel.setStyle("-fx-text-fill: red;");
            }
            else{
                timeLabel.setStyle("-fx-text-fill: #2ee6e6;");
            }

            if (timeLeft <= 0) {

                timeline.stop();
                msnLabel.setText("¡TimeOut!");
                gameHandler.onTimeFinished();
            }
        }
    }
    /**
     * Inner class responsible for handling keyboard events.
     */
    private class KeyboardHandler implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {

            if(event.getCode() == KeyCode.ENTER){

                validateWord();
            }
        }
    }
    /**
     * Inner class that extends GameEventAdapter to manage
     * custom gameplay events.
     */
    private class GameHandler extends GameEventAdapter {

        @Override
        public void onCorrectWord() {

            msnLabel.setText("Correct!");
        }

        @Override
        public void onWrongWord() {

            msnLabel.setText("Wrong!");
        }

        @Override
        public void onTimeFinished() {
            playSound(gameOverSound);
            goToGameOver();
        }
    }

    /**
     * Initializes the gameplay screen
     * and configures keyboard events.
     */

    @FXML
    public void initialize() {
        textFieldBox.setOnKeyPressed(new KeyboardHandler());
        startGame();
        // Pre-load sounds to avoid lag
        correctSound = createMediaPlayer("/sounds/correct.wav");
        wrongSound = createMediaPlayer("/sounds/wrong.wav");
        gameOverSound = createMediaPlayer("/sounds/gameover.wav");

        textFieldBox.setOnKeyPressed(new KeyboardHandler());
        startGame();
    }

    /**
     * Helper method to create a MediaPlayer and pre-load the resource.
     */
    private MediaPlayer createMediaPlayer(String path) {
        try {
            var resource = getClass().getResource(path);
            if (resource != null) {
                return new MediaPlayer(new Media(resource.toExternalForm()));
            }
            System.err.println("Resource not found during pre-load: " + path);
        } catch (Exception e) {
            System.err.println("Error pre-loading: " + path);
        }
        return null;
    }


    /**
     * Restarts the game from the beginning.
     */
    @FXML
    void handleRestartGame() {
        if (timeline != null) timeline.stop();
        model.reset();
        msnLabel.setText("");
        startGame();
    }
    /**
     * Starts a new game and resets
     * score, timer, and level data.
     */
    public void startGame() {
        score = 0;
        totalWordsCorrect = 0;
        wordsClearedInCurrentLevel = 0;

        scoreLabel.setText("Score: 0");
        levelLabel.setText("Level: " + model.getLevel());
        timeLeft = model.getTime();
        timeLabel.setText(timeLeft + " seg");

        nextWord();
        startTimer();
        playBackgroundMusic("/sounds/game.wav");
    }

    /**
     * Generates and displays the next word for the player.
     */
    public void nextWord() {
        boolean useHardWords = model.getLevel() > 3;
        currentWord = wordGenerator.getRandomWord(useHardWords);
        wordLabel.setText(currentWord);
        textFieldBox.clear();
    }
    /**
     * Starts the timer for the current level.
     */
    public void startTimer() {

        if (timeline != null) timeline.stop();

        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), new TimerHandler())
        );

        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.play();
    }
    /**
     * Validates the word entered
     * by the player and updates
     * the score and game state.
     */
    public void validateWord() {
        String input = textFieldBox.getText();
        if (input.equalsIgnoreCase(currentWord)) {
            score += 100;
            totalWordsCorrect++;
            wordsClearedInCurrentLevel++;
            scoreLabel.setText("Score: " + score);
            gameHandler.onCorrectWord();
            msnLabel.setText("¡Correct! (" + wordsClearedInCurrentLevel + "/5)");
            msnLabel.setStyle("-fx-text-fill: #00ff00;");
            playSound(correctSound);

            ScaleTransition scale = new ScaleTransition(Duration.millis(200), wordLabel);

            scale.setToX(1.2);
            scale.setToY(1.2);

            scale.setAutoReverse(true);
            scale.setCycleCount(2);

            scale.play();

            timeLeft += 2;
            timeLabel.setText(timeLeft + " seg");
            if (wordsClearedInCurrentLevel >= 5) {
                levelUp();
            } else {
                nextWord();
            }
        } else {
            score = Math.max(0, score - 50);
            scoreLabel.setText("Score: " + score);
            gameHandler.onWrongWord();
            msnLabel.setText("¡Wrong! Try Again!");
            msnLabel.setStyle("-fx-text-fill: #ff0000;");
            playSound(wrongSound);
            TranslateTransition shake = new TranslateTransition(Duration.millis(60), textFieldBox);

            shake.setByX(10);
            shake.setCycleCount(6);
            shake.setAutoReverse(true);

            shake.play();
            textFieldBox.clear();
        }
    }
    /**
     * Increases the current level
     * and restarts the timer.
     */
    private void levelUp() {
        model.increaseLevel();
        wordsClearedInCurrentLevel = 0;
        levelLabel.setText("Level: " + model.getLevel());
        timeLeft = model.getTime();
        nextWord();
        startTimer();
    }
    /**
     * Loads the Game Over screen
     * and sends the final results.
     */
    public void goToGameOver() {
        try {
            if (timeline != null) timeline.stop();
            if (backgroundMusicPlayer != null) {
                backgroundMusicPlayer.stop();
            }
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto1escriturarapidamaps/gameOver.fxml"));
            Parent root = loader.load();
            GameOverController controller = loader.getController();
            controller.setResults(model.getLevel(), score, totalWordsCorrect);
            playSound(gameOverSound);

            Stage stage = (Stage) wordLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Plays a sound effect using a pre-loaded MediaPlayer.
     * Stops the player before playing to ensure the sound starts from the beginning.
     * @param player the MediaPlayer object to play
     */
    private void playSound(MediaPlayer player) {
        if (player != null) {
            player.stop(); // This rewinds the sound if it's already playing
            player.play();
        }
    }

    /**
     * Plays background music in an infinite loop.
     * Stops any previous background music before starting the new one.
     * @param soundFile absolute path of the music file in the resources folder
     */
    private void playBackgroundMusic(String soundFile) {
        try {
            if (backgroundMusicPlayer != null) {
                backgroundMusicPlayer.stop();
            }

            var resource = getClass().getResource(soundFile);
            if (resource != null) {
                Media media = new Media(resource.toExternalForm());
                backgroundMusicPlayer = new MediaPlayer(media);
                backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                backgroundMusicPlayer.play();
            } else {
                System.err.println("Background music resource not found: " + soundFile);
            }
        } catch (Exception e) {
            System.err.println("Error playing background music: " + e.getMessage());
        }
    }
    @FXML void onHandleCheck() { validateWord(); }
}