package com.carrom.cleanstrike.actions;

import com.carrom.cleanstrike.Game;
import com.carrom.cleanstrike.GameConstants;
import com.carrom.cleanstrike.parameters.Player;

public class RedStrike implements GameAction {
    @Override
    public boolean executeAction(Player currentPlayer, Game game) {
        if (game.getCarromBoard().isRedCoinOnBoard()) {
            currentPlayer.increasePoints(GameConstants.RED_STRIKE_POINT);
            game.getCarromBoard().setRedCoinOnBoard(false);
            currentPlayer.resetNoPockets();
            return true;
        } else {
            System.out.println("Red strike already over. Try again.");
            return false;
        }
    }
}
