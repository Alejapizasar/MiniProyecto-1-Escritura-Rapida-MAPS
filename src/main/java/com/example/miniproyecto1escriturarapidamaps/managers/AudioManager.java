package com.example.miniproyecto1escriturarapidamaps.manager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Manager class responsible for handling audio resources within the game.
 * It manages both short sound effects and looping background music.
 */
public class AudioManager {
    private MediaPlayer backgroundMusic;

    /**
     * Plays a short sound effect once.
     * Use this for immediate feedback like correct or wrong answers.
     * @param path The absolute path to the audio resource (e.g., "/sounds/correct.wav").
     */
    public void playEffect(String path) {
        try {
            var resource = getClass().getResource(path);
            if (resource != null) {
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
     * Plays background music in an infinite loop.
     * If music is already playing, it stops it before starting the new track.
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
     * Stops the background music if it is currently playing.
     */
    public void stopBackgroundMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }
}