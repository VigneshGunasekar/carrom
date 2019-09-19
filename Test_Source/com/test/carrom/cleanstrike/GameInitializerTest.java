package com.test.carrom.cleanstrike;

import com.carrom.cleanstrike.Game;
import com.carrom.cleanstrike.GameInitializer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GameInitializerTest {

    @Test
    public void gameInit_InitializeGameParameters_NoOfPlayersMoreThanOne() throws Exception {
        Game game = new Game();
        GameInitializer initializeGame = new GameInitializer();
        initializeGame.initializeGame(game);
        int expected = 1;
        int actual = game.noOfPlayers;

        Assert.assertNotEquals(actual, expected);
    }


}
