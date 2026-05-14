
/**
 * Represents the core data model of the game, maintaining the state of
 * level progression and countdown configurations.
 *
 * This class encapsulates the game's difficulty logic, specifically how
 * time limits decrease as the player advances through levels.
 *
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */

package com.example.miniproyecto1escriturarapidamaps.model;


/**
 * Represents the game model and stores
 * the current level and available time.
 */
public class GameModel {

    /**
     * The current difficulty level reached by the player.
     * Starts at 1 and increases as the player successfully completes word sets.
     */
    private int level = 1;

    /**
     * The total time allocated (in seconds) for the current level.
     * This value decreases periodically to increase difficulty.
     */
    private int time = 20;

    /**
     * Retrieves the current game level.
     *
     * @return An integer representing the active level.
     */
    public int getLevel() {

        return level;
    }

    /**
     * Retrieves the current time limit configured for the level.
     *
     * @return The available time in seconds.
     */
    public int getTime() {
        return time;
    }

    /**
     * Advances the player to the next level and updates difficulty scaling.
     * Every 5 levels reached, the available time is reduced by 2 seconds
     * to provide a progressive challenge, maintaining a minimum floor of 2 seconds.
     */
    public void increaseLevel() {

        level++;
        // Difficulty scaling logic: reduce time every 5 levels
        if(level % 5 == 0 && time > 2){

            time -= 2;
        }
    }

    /**
     * Restores the game model to its initial state.
     * Resets level progression to 1 and the starting time to 20 seconds.
     */
    public void reset(){

        level = 1;
        time = 20;
    }
}