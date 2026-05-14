/**
 * Interface defining the contract for word generation logic within the game.
 * This abstraction allows the application to retrieve target words while
 * managing different difficulty levels, ensuring the core gameplay logic
 * remains independent of the specific word source.
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */
package com.example.miniproyecto1escriturarapidamaps.model.interfaces;

public interface IWords {
    /**
     * Retrieves a randomly selected word based on the specified difficulty mode.
     *
     * @param hardMode A boolean flag where {@code true} requests a high-difficulty word
     *                 (e.g., longer or more complex characters) and {@code false}
     *                 requests a standard-difficulty word.
     * @return A {@code String} representing the randomly generated target word.
     */
    String getRandomWord(boolean hardMode);
}
