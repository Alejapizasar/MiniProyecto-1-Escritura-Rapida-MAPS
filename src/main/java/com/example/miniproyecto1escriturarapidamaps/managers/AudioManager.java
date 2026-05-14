/**
 * Resource manager dedicated to handling audio playback within the application.
 * This class provides a centralized interface to manage short-duration sound effects
 * and persistent, looping background music using the JavaFX Media API.
 *
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */
package com.example.miniproyecto1escriturarapidamaps.managers;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Manager class responsible for handling audio resources within the game.
 * It manages both short sound effects and looping background music.
 */
public class AudioManager {
    /**
     * Persistent MediaPlayer instance for background tracks to allow control
     * over playback state (start/stop) across different game scenes.
     */
    private MediaPlayer backgroundMusic;

    /**
     * Plays a short-duration sound effect once.
     * This method is optimized for immediate auditory feedback such as
     * button clicks, correct answers, or error notifications.
     *
     * @param path The absolute path to the audio resource (e.g., "/sounds/correct.wav").
     */
    public void playEffect(String path) {
        try {
            var resource = getClass().getResource(path);
            if (resource != null) {
                // Initialize a local MediaPlayer for a one-time playback
                MediaPlayer player = new MediaPlayer(new Media(resource.toExternalForm()));
                player.play();
            } else {
                System.err.println("Sound effect resource not found: " + path);
            }
        } catch (Exception e) {
            System.err.println("Error playing effect: " + path + " - " + e.getMessage());
        }
    }

    /**
     * Initiates background music playback in an infinite loop.
     * If a background track is already active, it is automatically terminated
     * to prevent audio overlapping and resource leaks.
     *
     * @param path The absolute path to the music resource (e.g., "/sounds/game_theme.mp3").
     */
    public void playBackgroundMusic(String path) {
        // Stop previous music if it exists to avoid overlapping
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }

        try {
            var resource = getClass().getResource(path);
            if (resource != null) {
                // Set the player to repeat the track indefinitely
                backgroundMusic = new MediaPlayer(new Media(resource.toExternalForm()));
                backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
                backgroundMusic.play();
            } else {
                System.err.println("Background music resource not found: " + path);
            }
        } catch (Exception e) {
            System.err.println("Error playing background music: " + path + " - " + e.getMessage());
        }
    }

    /**
     * Stops the current background music playback and releases the associated
     * media player state.
     */
    public void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }
}