package com.carrom.cleanstrike;

import com.carrom.cleanstrike.parameters.Player;

public class GamePointsCalculator {
    private boolean playerMinusPointAllowed;

    public GamePointsCalculator(boolean minusPointAllowed) {
        playerMinusPointAllowed = minusPointAllowed;

    }

    public void reducePlayerPoints(Player currentPlayer, int minusPoint) {
        if (currentPlayer.getPoints() < minusPoint && !playerMinusPointAllowed) {
            currentPlayer.resetPoints();
        } else {
            currentPlayer.decreasePoints(minusPoint);
        }
    }

    public Player updateConditionalPoints(Player currentPlayer) {
        if (currentPlayer.getNoPockets() == GameConstants.NO_POCKET_LIMIT) {
            reducePlayerPoints(currentPlayer, GameConstants.THREE_NO_POCKET_PENALTY);
            currentPlayer.resetNoPockets();
        }
        if (currentPlayer.getFouls() == GameConstants.FOUL_LIMIT) {
            reducePlayerPoints(currentPlayer, GameConstants.THREE_FOUL_PENALTY);
            currentPlayer.resetFouls();
        }
        return currentPlayer;
    }

}
