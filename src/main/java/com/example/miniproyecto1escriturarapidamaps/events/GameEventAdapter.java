/**
 * * An abstract adapter class for receiving game events.
 *  * The methods in this class are empty; this class exists as a convenience for
 *  * creating listener objects.
 *  *
 *  * By extending this class, developers can override only the specific event
 *  * methods they are interested in, rather than implementing every method in
 *  * the {@link GameEventListener} interface.
 *
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */

package com.example.miniproyecto1escriturarapidamaps.events;
/**
 * Adapter class that provides
 * empty implementations of
 * GameEventListener methods.
 */
public class GameEventAdapter implements GameEventListener {

    /**
     * Invoked when the user successfully types a correct word.
     * Default implementation is empty.
     */
    @Override
    public void onCorrectWord() {
        // Override to handle correct word logic
    }
    /**
     * Invoked when the user submits an incorrect word.
     * Default implementation is empty.
     */
    @Override
    public void onWrongWord() {
        // Override to handle error logic
    }
    /**
     * Invoked when the countdown timer reaches zero.
     * Default implementation is empty.
     */
    @Override
    public void onTimeFinished() {
        // Override to handle game-over transitions
    }
}