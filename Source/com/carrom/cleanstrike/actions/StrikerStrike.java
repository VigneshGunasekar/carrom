package com.carrom.cleanstrike.actions;

import com.carrom.cleanstrike.Game;
import com.carrom.cleanstrike.GameConstants;
import com.carrom.cleanstrike.GamePointsCalculator;
import com.carrom.cleanstrike.parameters.Player;

public class StrikerStrike implements GameAction {
    @Override
    public boolean executeAction(Player currentPlayer, Game game) {
        new GamePointsCalculator().reducePlayerPoints(currentPlayer, GameConstants.STRIKER_STRIKE_POINT_REDUCTION, game.playerMinusPointAllowed);
        currentPlayer.incrementFoul();
        currentPlayer.incrementNoPocket();
        return true;
    }
}
