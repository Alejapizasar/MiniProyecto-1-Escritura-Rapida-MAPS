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


public class GamePlayController {

    @FXML private Label wordLabel;
    @FXML private Label timeLabel;
    @FXML private Label levelLabel;
    @FXML private Label msnLabel;
    @FXML private TextField textFieldBox;
    @FXML private Label scoreLabel;

    private GameModel model = new GameModel();
    private WordGenerator wordGenerator = new WordGenerator();
    private String currentWord;
    private Timeline timeline;

    private int timeLeft;
    private int score = 0;
    private int wordsClearedInCurrentLevel = 0;
    private int totalWordsCorrect = 0;
    private GameHandler gameHandler = new GameHandler();

    /**
     * Inner class responsible for
     * handling timer events.
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
     * Inner class responsible for
     * handling keyboard events.
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
     * Inner class that extends
     * GameEventAdapter to manage
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
    }

    /**
     * Generates and displays
     * the next word for the player.
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto1escriturarapidamaps/gameOver.fxml"));
            Parent root = loader.load();
            GameOverController controller = loader.getController();
            controller.setResults(model.getLevel(), score, totalWordsCorrect);
            Stage stage = (Stage) wordLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML void onHandleCheck() { validateWord(); }
}