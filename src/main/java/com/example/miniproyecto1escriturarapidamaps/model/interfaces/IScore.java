/**
 * Interface defining the essential contract for score and progression tracking.
 * Any class implementing this interface must provide logic to retrieve
 * the current difficulty tier or level of the game session.
 *
 * This ensures a standardized way for other game components to query
 * the player's progress.
 */
package com.example.miniproyecto1escriturarapidamaps.model.interfaces;

public interface IScore {
    /**
     * Retrieves the current level reached by the player.
     * The level typically dictates game difficulty, such as word length
     * or timer speed.
     *
     * @return An integer representing the current game level.
     */
    int getLevel();
}