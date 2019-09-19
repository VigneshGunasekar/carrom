package com.carrom.cleanstrike.actions;

import com.carrom.cleanstrike.Game;
import com.carrom.cleanstrike.GameConstants;
import com.carrom.cleanstrike.parameters.Player;

public class Strike implements GameAction {
    @Override
    public boolean executeAction(Player currentPlayer, Game game) {
        if (game.board.getBlackCoins() >= GameConstants.STRIKE_BLACK_COIN_REDUCTION) {
            currentPlayer.increasePoints(GameConstants.STRIKE_POINT);
            currentPlayer.resetNoPockets();
            game.board.decreaseBlackCoins(GameConstants.STRIKE_BLACK_COIN_REDUCTION);
            return true;
        } else {
            System.out.println("Not enough black coins to perform Strike. Try again. ");
            return false;
        }
    }
}
