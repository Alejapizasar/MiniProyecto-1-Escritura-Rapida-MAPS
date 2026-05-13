package com.example.miniproyecto1escriturarapidamaps.service;

import com.example.miniproyecto1escriturarapidamaps.model.GameModel;

/**
 * Service class that manages the core logic, scoring, and state of the game.
 * It separates the business logic from the UI controller.
 */
public class GameService {
    private GameModel model = new GameModel();
    private int score = 0;
    private int totalWordsCorrect = 0;
    private int wordsClearedInCurrentLevel = 0;
    private int timeLeft;

    /**
     * Resets all game statistics and initializes the timer based on the model.
     */
    public void resetGame() {
        score = 0;
        totalWordsCorrect = 0;
        wordsClearedInCurrentLevel = 0;
        model.reset();
        timeLeft = model.getTime();
    }

    /**
     * Validates the player's input against the target word and updates the score.
     * @param input The text entered by the user.
     * @param target The correct word to be matched.
     * @return true if the words match (ignoring case), false otherwise.
     */
    public boolean processWord(String input, String target) {
        if (input.equalsIgnoreCase(target)) {
            score += 100;
            totalWordsCorrect++;
            wordsClearedInCurrentLevel++;
            timeLeft += 2; // Bonus time for correct answer
            return true;
        } else {
            score = Math.max(0, score - 50); // Prevents negative scores
            return false;
        }
    }

    /**
     * Checks if the player has cleared enough words to progress to the next level.
     * @return true if 5 or more words have been cleared in the current level.
     */
    public boolean shouldLevelUp() {
        return wordsClearedInCurrentLevel >= 5;
    }

    /**
     * Advances the game to the next level and resets the level-specific counter.
     */
    public void levelUp() {
        model.increaseLevel();
        wordsClearedInCurrentLevel = 0;
        timeLeft = model.getTime();
    }

    /**
     * @return The current total score.
     */
    public int getScore() {
        return score;
    }

    /**
     * @return The remaining time in seconds.
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * @return The current game level from the model.
     */
    public int getLevel() {
        return model.getLevel();
    }

    /**
     * @return The total number of correct words throughout the session.
     */
    public int getTotalWordsCorrect() {
        return totalWordsCorrect;
    }

    /**
     * @return The number of words correctly typed in the current level.
     */
    public int getWordsInLevel() {
        return wordsClearedInCurrentLevel;
    }

    /**
     * Updates the remaining time. Usually called by the game's Timeline.
     * @param timeLeft The new time value in seconds.
     */
    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
}