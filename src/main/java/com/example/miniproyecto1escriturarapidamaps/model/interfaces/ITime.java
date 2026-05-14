/**
 * Interface defining the contract for time-tracking components within the game.
 * This abstraction allows different parts of the application to access
 * temporal data, such as countdown values or duration metrics, without
 * being coupled to a specific implementation.
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */
package com.example.miniproyecto1escriturarapidamaps.model.interfaces;

public interface ITime {
    /**
     * Retrieves the current time value associated with the game state.
     * Depending on the implementation, this typically represents the
     * remaining seconds in a countdown or the elapsed time in a session.
     *
     * @return An integer representing the time value in seconds.
     */
    int getTime();
}