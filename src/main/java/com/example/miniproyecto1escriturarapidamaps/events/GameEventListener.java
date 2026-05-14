/**
    * Interface definition for a callback to be invoked when gameplay events occur.
    * Classes that need to react to game milestones, such as scoring or timer expiration,
    * should implement this interface.
    *
    * This follows the Observer Design Pattern to decouple game logic from the UI.
    * @author Maria Alejandra Pizarro Sarria
    * @version 1.0
 */

package com.example.miniproyecto1escriturarapidamaps.events;
/**
 * Interface used to define
 * custom gameplay events.
 */
public interface GameEventListener {
    /**
     * Called when the user successfully matches the target word.
     * Implementation should handle score increments and success feedback.
     */
    void onCorrectWord();

    /**
     * Called when the user input does not match the target word.
     * Implementation should handle error feedback and penalty logic if applicable.
     */
    void onWrongWord();

    /**
     * Called when the countdown timer reaches zero.
     * Implementation should handle the transition to the end-of-game state.
     */
    void onTimeFinished();
}
