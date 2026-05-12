package com.example.miniproyecto1escriturarapidamaps.events;
/**
 * Interface used to define
 * custom gameplay events.
 */
public interface GameEventListener {
    void onCorrectWord();

    void onWrongWord();

    void onTimeFinished();
}
