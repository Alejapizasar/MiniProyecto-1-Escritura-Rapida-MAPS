/**
 * Generates random words for the game.
 * Supports easy and hard difficulty levels.
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */
package com.example.miniproyecto1escriturarapidamaps.model;
import com.example.miniproyecto1escriturarapidamaps.model.interfaces.IWords;
import java.util.Random;

/**
 * Concrete implementation of the {@link IWords} interface that provides
 * randomly selected words for the game.
 *
 * This class maintains two categorized lists of words following a retro-technology
 * and synth-wave theme, providing different challenges based on word complexity.
 */

public class WordGenerator implements IWords {

    /**
     * A collection of shorter, high-frequency retro-themed words
     * intended for lower difficulty levels.
     */
    private final String[] easyWords = {
            "Atari", "Pixel", "Retro", "Synth", "Disco", "Video", "Laser", "Neon",
            "Sonic", "Level", "Cyber", "Score", "Vintage", "Pacman", "Arcade",
            "Console", "Digital",  "Joystick", "Walman", "Bitmaps", "Gameboy",
            "Monitor", "Floppy", "Glitch", "Gamepad",
    };

    /**
     * A collection of longer, technical retro-computing terms
     * intended for advanced difficulty levels.
     */
    private final String[] hardWords = {
            "Commodore", "Synthesizer", "Vaporwave", "Megahertz", "Bitstream",
            "Cyberpunk", "Motherboard", "Pixelated", "Mainframe", "Datassette",
            "Arcadecabinet","Floppydisk", "Retroengine", "Soundblaster",
            "Joystickport", "Cartridgeslot", "Bootloader", "Scanlines",
            "Vectorgraphics", "Neonmatrix"
        };

    /**
     * Random number generator used to select indices from the word arrays.
     */
    private final Random random = new Random();

    /**
     * Selects and returns a random word from the internal libraries based
     * on the requested difficulty.
     *
     * @param hardMode If {@code true}, selects from the technical/long word list;
     *                 otherwise, selects from the standard list.
     * @return A randomly chosen {@code String} from the appropriate array.
     */
@Override
    public String getRandomWord(boolean hardMode) {
        // Determine which array to pull from based on the difficulty flag
        String[] selectedList = hardMode ? hardWords : easyWords;

    // Return a random element from the chosen list
        return selectedList[random.nextInt(selectedList.length)];
    }
}
