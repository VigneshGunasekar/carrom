package com.carrom.cleanstrike;

import com.carrom.cleanstrike.parameters.CarromBoard;
import com.carrom.cleanstrike.parameters.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class GameStatusChecker {

    private static final int GAME_IN_PROGRESS = 0;
    private static final int GAME_WON = 1;
    private static final int GAME_DRAWN = 2;

    public boolean hasGameEnded(Player players[], CarromBoard board) {
        int gameStatus = GAME_IN_PROGRESS;

        ArrayList<Player> sortedPlayerList = sortPlayersOnPoints(players);
        Player winner = sortedPlayerList.get(sortedPlayerList.size() - 1);
        Player runner = sortedPlayerList.get(sortedPlayerList.size() - 2);
        int winnerPoints = winner.getPoints();
        int runnerPoints = runner.getPoints();

        if (isNoCoinOnBoard(board)) {
            gameStatus = doesPlayerWinOnCoinExhaust(winnerPoints, runnerPoints) ? GAME_WON : GAME_DRAWN;
        } else if (doesPlayerWin(winnerPoints, runnerPoints)) {
            gameStatus = GAME_WON;
        }


        if (gameStatus == GAME_WON) {
            System.out.print(winner.getName() + " won the game. ");
            System.out.print("Final Score: " + players[0].getPoints());
            for (int i = 1; i < players.length; i++) {
                System.out.println("-" + sortedPlayerList.get(i).getPoints());
            }
            return true;
        } else if (gameStatus == GAME_DRAWN) {
            System.out.println("Game ended in a Draw. ");
            return true;
        }

        return false;

    }

    private ArrayList<Player> sortPlayersOnPoints(Player players[]) {
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
        return playerList;
    }

    public boolean isNoCoinOnBoard(CarromBoard board) {
        boolean isNoCoinOnBoard = false;
        if (board.getBlackCoins() == 0 && !board.isRedCoinOnBoard()) {
            isNoCoinOnBoard = true;
        }
        return isNoCoinOnBoard;
    }

    public boolean doesPlayerWinOnCoinExhaust(int winnerPoints, int runnerPoints) {
        boolean playerLeads = false;
        if ((winnerPoints != runnerPoints) && (winnerPoints >= GameConstants.POINTS_TO_WIN) || (winnerPoints - runnerPoints >= GameConstants.POINT_DIFFERENCE_TO_WIN)) {
            playerLeads = true;
        }
        return playerLeads;
    }

    public boolean doesPlayerWin(int winnerPoints, int runnerPoints) {
        boolean playerWins = false;
        if ((winnerPoints >= GameConstants.POINTS_TO_WIN) && (winnerPoints - runnerPoints >= GameConstants.POINT_DIFFERENCE_TO_WIN)) {
            playerWins = true;
        }
        return playerWins;
    }

}
