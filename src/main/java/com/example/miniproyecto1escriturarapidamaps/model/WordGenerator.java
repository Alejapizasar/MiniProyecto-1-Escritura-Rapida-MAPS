/**
 * Generates random words for the game.
 * Supports easy and hard difficulty levels.
 * @author Maria Alejandra Pizarro Sarria
 * @version 1.0
 */
package com.example.miniproyecto1escriturarapidamaps.model;
import com.example.miniproyecto1escriturarapidamaps.model.interfaces.IWords;
import java.util.Random;

public class WordGenerator implements IWords {

    private final String[] easyWords = {
            "Atari", "Pixel", "Retro", "Synth", "Disco", "Video", "Laser", "Neon",
            "Sonic", "Level", "Cyber", "Score", "Vintage", "Pacman", "Arcade",
            "Console", "Digital",  "Joystick", "Walman", "Bitmaps", "Gameboy",
            "Monitor", "Floppy", "Glitch", "Gamepad",
    };

    private final String[] hardWords = {
            "Commodore", "Synthesizer", "Vaporwave", "Megahertz", "Bitstream",
            "Cyberpunk", "Motherboard", "Pixelated", "Mainframe", "Datassette",
            "Arcadecabinet","Floppydisk", "Retroengine", "Soundblaster",
            "Joystickport", "Cartridgeslot", "Bootloader", "Scanlines",
            "Vectorgraphics", "Neonmatrix"
        };

    private final Random random = new Random();

@Override
    public String getRandomWord(boolean hardMode) {
        String[] selectedList = hardMode ? hardWords : easyWords;
        return selectedList[random.nextInt(selectedList.length)];
    }
}
