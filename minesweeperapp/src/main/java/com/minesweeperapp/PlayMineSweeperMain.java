package com.minesweeperapp;

import com.minesweeperapp.runner.GameRunner;

/**
 * The class containing the main method to play the Minesweeper game
 * @author Mark Isla
 */
public class PlayMineSweeperMain {
    public static void main(String[] args) {
        GameRunner gr = new GameRunner();
        gr.run();

    }
}