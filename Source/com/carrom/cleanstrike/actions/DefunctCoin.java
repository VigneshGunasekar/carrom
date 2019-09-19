package com.carrom.cleanstrike.actions;

import com.carrom.cleanstrike.Game;
import com.carrom.cleanstrike.GameConstants;
import com.carrom.cleanstrike.GamePointsCalculator;
import com.carrom.cleanstrike.parameters.Player;

public class DefunctCoin implements GameAction {
    @Override
    public boolean executeAction(Player currentPlayer, Game game) {
        if (game.board.getBlackCoins() >= GameConstants.DEFUNCT_STRIKE_BLACK_COIN_REDUCTION) {
            new GamePointsCalculator().reducePlayerPoints(currentPlayer, GameConstants.DEFUNCT_STRIKE_POINT_REDUCTION, game.playerMinusPointAllowed);
            game.board.decreaseBlackCoins(GameConstants.DEFUNCT_STRIKE_BLACK_COIN_REDUCTION);
            currentPlayer.incrementFoul();
            currentPlayer.incrementNoPocket();
            return true;
        } else {
            System.out.println("Not enough black coins to DefunctCoin Strike. Try again. ");
            return false;
        }
    }
}
