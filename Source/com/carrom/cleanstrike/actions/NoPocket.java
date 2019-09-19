package com.carrom.cleanstrike.actions;

import com.carrom.cleanstrike.Game;
import com.carrom.cleanstrike.parameters.Player;

public class NoPocket implements GameAction {
    @Override
    public boolean executeAction(Player currentPlayer, Game game) {
        currentPlayer.incrementNoPocket();
        return true;
    }
}
