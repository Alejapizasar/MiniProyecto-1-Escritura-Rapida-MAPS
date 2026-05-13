
/**
 * Represents the game state,
 * including level progression
 * and timer configuration.
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */
package com.example.miniproyecto1escriturarapidamaps.model;
import com.example.miniproyecto1escriturarapidamaps.model.interfaces.IScore;
import com.example.miniproyecto1escriturarapidamaps.model.interfaces.ITime;

/**
 * Represents the game model and stores
 * the current level and available time.
 */
public class GameModel {

    /**
     * Current level of the player.
     */
    private int level = 1;

    /**
     * Available time for the current level.
     */
    private int time = 20;

    /**
     * Returns the current game level.
     *
     * @return current level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the current available time.
     *
     * @return current time in seconds
     */
    public int getTime() {
        return time;
    }

    /**
     * Increases the game level.
     * Every 5 levels, the available
     * time decreases by 2 seconds
     * until reaching a minimum of 2 seconds.
     */
    public void increaseLevel() {

        level++;

        if(level % 5 == 0 && time > 2){

            time -= 2;
        }
    }

    /**
     * Resets the game values
     * to their default state.
     */
    public void reset(){

        level = 1;
        time = 20;
    }
}