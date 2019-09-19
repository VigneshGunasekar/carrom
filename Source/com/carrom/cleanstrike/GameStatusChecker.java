package com.carrom.cleanstrike;

import com.carrom.cleanstrike.parameters.CarromBoard;
import com.carrom.cleanstrike.parameters.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class GameStatusChecker {

    public boolean checkGameCompletion(Player players[], CarromBoard board) {
        boolean gameEnded = false;
        boolean gameDrawn = false;

        ArrayList<Player> playerList = new ArrayList<Player>(Arrays.asList(players));
        Collections.sort(playerList, new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2) {
                if (player1.getPoints() == player2.getPoints()) {
                    return 0;
                } else if (player1.getPoints() > player2.getPoints()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        Player winner = playerList.get(playerList.size() - 1);
        int winnerPoints = winner.getPoints();
        Player runner = playerList.get(playerList.size() - 2);
        int runnerPoints = runner.getPoints();

        if (winnerPoints >= GameConstants.POINTS_TO_WIN && winnerPoints - runnerPoints >= GameConstants.POINT_DIFFERENCE_TO_WIN) {
            gameEnded = true;
        }

        if (board.getBlackCoins() == 0 && !board.isRedCoinOnBoard()) {
            gameEnded = true;
            if ((winnerPoints != runnerPoints) && (winnerPoints >= GameConstants.POINTS_TO_WIN) || winnerPoints - runnerPoints >= GameConstants.POINT_DIFFERENCE_TO_WIN) {
                gameDrawn = false;
            } else {
                gameDrawn = true;
            }
        }


        if (gameEnded) {
            if (!gameDrawn) {
                System.out.print(winner.getName() + " won the game. ");
                System.out.print("Final Score: " + players[0].getPoints());
                for (int i = 1; i < players.length; i++) {
                    System.out.println("-" + playerList.get(i).getPoints());
                }
            } else {
                System.out.println("Game ended in a Draw. ");
            }

        }
        return gameEnded;

    }
}
