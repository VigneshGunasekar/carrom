package com.carrom.cleanstrike;

import com.carrom.cleanstrike.parameters.Player;

public class GamePointsCalculator {

    public void reducePlayerPoints(Player currentPlayer, int minusPoint, Game game) {
        if (currentPlayer.getPoints() < minusPoint && !game.playerMinusPointAllowed) {
            currentPlayer.resetPoints();
        } else {
            currentPlayer.decreasePoints(minusPoint);
        }
    }

    public Player updateConditionalPoints(Player currentPlayer, Game game) {
        if (currentPlayer.getNoPockets() == GameConstants.NO_POCKET_LIMIT) {
            reducePlayerPoints(currentPlayer, GameConstants.THREE_NO_POCKET_PENALTY, game);
            currentPlayer.resetNoPockets();
        }
        if (currentPlayer.getFouls() == GameConstants.FOUL_LIMIT) {
            reducePlayerPoints(currentPlayer, GameConstants.THREE_FOUL_PENALTY, game);
            currentPlayer.resetFouls();
        }
        return currentPlayer;
    }

}
