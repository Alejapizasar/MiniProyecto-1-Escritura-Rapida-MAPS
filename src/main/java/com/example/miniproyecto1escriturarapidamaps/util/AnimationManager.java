/**
 * Utility class providing a centralized suite of reusable JavaFX animations for UI components.
 * This class utilizes static methods to execute visual effects, ensuring a consistent
 * look and feel across the application without duplicating animation logic.
 *
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */

package com.example.miniproyecto1escriturarapidamaps.util;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.util.Duration;

/**
 * Utility class providing reusable JavaFX animations for UI components.
 * This class uses static methods to apply visual effects such as transitions and shakes.
 */
public class AnimationManager {

    /**
     * Executes a synchronized fade-in and scale-up transition on a specific node.
     * This entrance effect is typically applied to root containers during scene
     * initialization to provide a professional, fluid transition for the user.
     *
     * @param node The JavaFX Node (e.g., BorderPane, Label) to be animated.
     */
    public static void applyFadeScaleEntrance(Node node) {
        if (node == null) return;

        // Configuration for the opacity transition
        FadeTransition fade = new FadeTransition(Duration.millis(700), node);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);

        // Configuration for the slight expansion effect
        ScaleTransition scale = new ScaleTransition(Duration.millis(700), node);
        scale.setFromX(0.95);
        scale.setFromY(0.95);
        scale.setToX(1.0);
        scale.setToY(1.0);

        // Synchronize transitions to play simultaneously
        ParallelTransition combined = new ParallelTransition(fade, scale);

        // Use EASE_OUT for a natural deceleration as the animation concludes
        combined.setInterpolator(Interpolator.EASE_OUT);
        combined.play();
    }

    /**
     * Triggers a rapid "pulse" animation by scaling the node up and back down.
     * This visual cue serves as positive reinforcement for correct user input
     * or successful game milestones.
     *
     * @param node The UI component that will pulse (e.g., wordLabel or scoreLabel).
     */
    public static void playCorrectAnimation(Node node) {
        if (node == null) return;

        ScaleTransition scale = new ScaleTransition(Duration.millis(200), node);
        scale.setToX(1.2);
        scale.setToY(1.2);
        scale.setAutoReverse(true); // Returns the node to its original size
        scale.setCycleCount(2); // Completes one full pulse cycle
        scale.play();
    }

    /**
     * Triggers a high-frequency horizontal displacement (shake) effect.
     * This animation provides immediate negative feedback, effectively signaling
     * typing errors or invalid game actions to the player.
     *
     * @param node The UI component to shake (e.g., a TextField).
     */
    public static void playErrorShake(Node node) {
        if (node == null) return;

        TranslateTransition shake = new TranslateTransition(Duration.millis(60), node);
        shake.setByX(10); // Defines the amplitude of the shake
        shake.setCycleCount(6); // Number of oscillations
        shake.setAutoReverse(true); // Moves back and forth
        shake.play();
    }
}