package com.carrom.cleanstrike.actions;

import com.carrom.cleanstrike.Game;
import com.carrom.cleanstrike.GameConstants;
import com.carrom.cleanstrike.parameters.Player;

public class MultiStrike implements GameAction {
    @Override
    public boolean executeAction(Player currentPlayer, Game game) {
        if (game.getCarromBoard().getBlackCoins() >= GameConstants.MULTI_STRIKE_BLACK_COIN_REDUCTION) {
            currentPlayer.increasePoints(GameConstants.MULTI_STRIKE_POINT);
            currentPlayer.resetNoPockets();
            game.getCarromBoard().decreaseBlackCoins(GameConstants.MULTI_STRIKE_BLACK_COIN_REDUCTION);
            return true;
        } else {
            System.out.println("Not enough black coins to perform Multi-Strike. Try again. ");
            return false;
        }
    }
}
