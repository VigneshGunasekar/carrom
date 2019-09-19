package com.carrom.cleanstrike.actions;

import com.carrom.cleanstrike.Game;
import com.carrom.cleanstrike.parameters.Player;

public interface GameAction {

    public boolean executeAction(Player currentPlayer, Game game);

}
