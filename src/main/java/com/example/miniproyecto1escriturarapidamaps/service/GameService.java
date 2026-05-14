/**
 * Service class that orchestrates the core gameplay mechanics, scoring rules,
 * and state transitions.
 *
 * This class acts as a bridge between the data {@link GameModel} and the
 * UI Controllers, ensuring that business logic is decoupled from the
 * presentation layer.
 *@author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */

package com.example.miniproyecto1escriturarapidamaps.service;

import com.example.miniproyecto1escriturarapidamaps.model.GameModel;

/**
 * Service class that manages the core logic, scoring, and state of the game.
 * It separates the business logic from the UI controller.
 */
public class GameService {
    /**
     * Internal reference to the game data model.
     */
    private GameModel model = new GameModel();

    /**
     * The player's cumulative score during the current session.
     */
    private int score = 0;

    /**
     * Total count of words correctly typed across all levels.
     */
    private int totalWordsCorrect = 0;

    /**
     * Counter for words correctly typed specifically within the active level.
     * Used to determine progression requirements.
     */
    private int wordsClearedInCurrentLevel = 0;

    /**
     * The dynamic countdown value in seconds for the current level.
     */
    private int timeLeft;

    /**
     * Resets all session-based statistics and restores the {@link GameModel}
     * to its starting configuration.
     */
    public void resetGame() {
        score = 0;
        totalWordsCorrect = 0;
        wordsClearedInCurrentLevel = 0;
        model.reset();
        timeLeft = model.getTime();
    }

    /**
     * Evaluates a user's input against a target word.
     * Applies scoring rewards for success or penalties for failure, and
     * manages time bonuses.
     *
     * @param input  The string entered by the player.
     * @param target The expected correct string.
     * @return {@code true} if the input matches (case-insensitive);
     *         {@code false} otherwise.
     */
    public boolean processWord(String input, String target) {
        if (input.equalsIgnoreCase(target)) {
            score += 100;
            totalWordsCorrect++;
            wordsClearedInCurrentLevel++;
            timeLeft += 2; // Success bonus to keep the game alive
            return true;
        } else {
            // Apply penalty without letting the score drop below zero
            score = Math.max(0, score - 50);
            return false;
        }
    }

    /**
     * Determines if the player has met the requirements to advance.
     *
     * @return {@code true} if 5 or more words have been successfully
     *         cleared in the current level.
     */
    public boolean shouldLevelUp() {
        return wordsClearedInCurrentLevel >= 5;
    }

    /**
     * Transitions the game state to the next difficulty tier.
     * Increases the level in the model, resets the level-specific counter,
     * and refreshes the timer.
     */
    public void levelUp() {
        model.increaseLevel();
        wordsClearedInCurrentLevel = 0;
        timeLeft = model.getTime();
    }

    /**
     * Retrieves the current session score.
     * @return Total points accumulated.
     */
    public int getScore() {
        return score;
    }

    /**
     * Retrieves the current countdown value.
     * @return Seconds remaining before game over.
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * Retrieves the current difficulty level from the model.
     * @return The active level number.
     */
    public int getLevel() {
        return model.getLevel();
    }

    /**
     * Retrieves the total volume of correctly typed words.
     * @return Overall successful attempts.
     */
    public int getTotalWordsCorrect() {
        return totalWordsCorrect;
    }

    /**
     * Retrieves the progress within the current level.
     * @return Successful attempts in the current level.
     */
    public int getWordsInLevel() {
        return wordsClearedInCurrentLevel;
    }

    /**
     * Updates the remaining time based on external triggers (e.g., Timeline).
     * @param timeLeft The new time value in seconds.
     */
    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
}