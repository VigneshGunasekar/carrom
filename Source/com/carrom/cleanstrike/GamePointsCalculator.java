package com.carrom.cleanstrike;

import com.carrom.cleanstrike.parameters.Player;

public class GamePointsCalculator {

    public void reducePlayerPoints(Player currentPlayer, int minusPoint, boolean playerMinusPointAllowed) {
        if (currentPlayer.getPoints() < minusPoint && !playerMinusPointAllowed) {
            currentPlayer.resetPoints();
        } else {
            currentPlayer.decreasePoints(minusPoint);
        }
    }

    public Player updateConditionalPoints(Player currentPlayer, boolean playerMinusPointAllowed) {
        if (currentPlayer.getNoPockets() == GameConstants.NO_POCKET_LIMIT) {
            reducePlayerPoints(currentPlayer, GameConstants.THREE_NO_POCKET_PENALTY, playerMinusPointAllowed);
            currentPlayer.resetNoPockets();
        }
        if (currentPlayer.getFouls() == GameConstants.FOUL_LIMIT) {
            reducePlayerPoints(currentPlayer, GameConstants.THREE_FOUL_PENALTY, playerMinusPointAllowed);
            currentPlayer.resetFouls();
        }
        return currentPlayer;
    }

}
