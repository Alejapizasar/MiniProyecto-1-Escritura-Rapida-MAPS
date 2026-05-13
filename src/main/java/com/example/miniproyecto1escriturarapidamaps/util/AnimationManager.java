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
     * Applies a combined fade-in and scale-up transition to a node.
     * Often used for screen entrances to create a smooth, professional feel.
     * @param node The UI component to be animated.
     */
    public static void applyFadeScaleEntrance(Node node) {
        if (node == null) return;

        // Fade transition from transparent to fully opaque
        FadeTransition fade = new FadeTransition(Duration.millis(700), node);
        fade.setFromValue(0.0);
        fade.setToValue(1.0);

        // Scale transition to create a slight "zoom-in" effect
        ScaleTransition scale = new ScaleTransition(Duration.millis(700), node);
        scale.setFromX(0.95);
        scale.setFromY(0.95);
        scale.setToX(1.0);
        scale.setToY(1.0);

        // Combine both animations to run in parallel
        ParallelTransition combined = new ParallelTransition(fade, scale);
        combined.setInterpolator(Interpolator.EASE_OUT); // Ensures a smooth finish
        combined.play();
    }

    /**
     * Plays a quick "pulse" animation.
     * Ideal for providing positive feedback when a user performs a correct action.
     * @param node The UI component to be animated (e.g., a score label or a word).
     */
    public static void playCorrectAnimation(Node node) {
        if (node == null) return;

        ScaleTransition scale = new ScaleTransition(Duration.millis(200), node);
        scale.setToX(1.2);
        scale.setToY(1.2);
        scale.setAutoReverse(true);
        scale.setCycleCount(2);
        scale.play();
    }

    /**
     * Plays a horizontal "shake" animation.
     * Useful for indicating errors or invalid inputs to the user.
     * @param node The UI component to be animated (e.g., a text field).
     */
    public static void playErrorShake(Node node) {
        if (node == null) return;

        TranslateTransition shake = new TranslateTransition(Duration.millis(60), node);
        shake.setByX(10);
        shake.setCycleCount(6);
        shake.setAutoReverse(true);
        shake.play();
    }
}