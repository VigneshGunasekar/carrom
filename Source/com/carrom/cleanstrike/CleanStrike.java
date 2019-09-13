package com.carrom.cleanstrike;

public class CleanStrike {

    public static void main(String args[]) {
        try {
            Game game = new Game();
            game.startGame();
        } catch (Exception e) {
            System.err.println("Exception while playing Clean Strike." + e);
        }
    }
}
