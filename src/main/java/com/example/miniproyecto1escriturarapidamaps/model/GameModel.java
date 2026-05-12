
/**
 * Represents the game state,
 * including level progression
 * and timer configuration.
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */package com.example.miniproyecto1escriturarapidamaps.model;
import com.example.miniproyecto1escriturarapidamaps.model.interfaces.IScore;
import com.example.miniproyecto1escriturarapidamaps.model.interfaces.ITime;

/**
 * Represents the game state and logic.
 */
public class GameModel implements IScore, ITime {

    // Current player level
    private int level;

    // Base game time
    private int baseTime = 20;

    /**
     * Constructor.
     * Initializes game values.
     */
    public GameModel() {
        level = 1;
    }

    /**
     * Returns current level.
     * @return current level
     */
    @Override
    public int getLevel() {
        return level;
    }

    /**
     * Returns current game time.
     * Every 5 levels time decreases by 2 seconds.
     * Minimum time is 2 seconds.
     * @return current available time
     */
    @Override
    public int getTime() {

        int reduction = (level / 5) * 2;

        int currentTime = baseTime - reduction;

        return Math.max(currentTime, 2);
    }

    /**
     * Increases player level.
     */
    public void increaseLevel() {
        level++;
    }

    /**
     * Resets game values.
     */
    public void reset() {
        level = 1;
    }
}