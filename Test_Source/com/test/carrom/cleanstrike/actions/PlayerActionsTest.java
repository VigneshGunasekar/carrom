package com.test.carrom.cleanstrike.actions;

import com.carrom.cleanstrike.Game;
import com.carrom.cleanstrike.GameConstants;
import com.carrom.cleanstrike.GameInitializer;
import com.carrom.cleanstrike.actions.DefunctCoin;
import com.carrom.cleanstrike.actions.MultiStrike;
import com.carrom.cleanstrike.actions.NoPocket;
import com.carrom.cleanstrike.actions.RedStrike;
import com.carrom.cleanstrike.actions.Strike;
import com.carrom.cleanstrike.actions.StrikerStrike;
import com.carrom.cleanstrike.parameters.Player;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PlayerActionsTest {
    @Test
    public void game_Strike_PlayerPointsIncrease() throws Exception {
        Game game = new Game();
        new GameInitializer().initializeGame(game);
        Player player = new Player("Player 1");
        int expectedPoints = GameConstants.STRIKE_POINT;
        new Strike().executeAction(player, game);

        assertEquals(player.getPoints(), expectedPoints);
    }

    @Test
    public void game_MultiStrike_PlayerPointsIncrease() throws Exception {
        Game game = new Game();
        new GameInitializer().initializeGame(game);
        Player player = new Player("Player 1");
        int expectedPoints = GameConstants.MULTI_STRIKE_POINT;
        new MultiStrike().executeAction(player, game);

        assertEquals(player.getPoints(), expectedPoints);
    }

    @Test
    public void game_RedStrike() throws Exception {
        Game game = new Game();
        new GameInitializer().initializeGame(game);
        Player player = new Player("Player 1");
        int expectedPoints = GameConstants.RED_STRIKE_POINT;
        new RedStrike().executeAction(player, game);

        assertEquals(player.getPoints(), expectedPoints);
    }

    @Test
    public void game_StrikerStrike() throws Exception {
        Game game = new Game();
        new GameInitializer().initializeGame(game);
        Player player = new Player("Player 1");
        int playerPoints = 2;
        player.increasePoints(playerPoints);
        int expectedPoints = playerPoints - GameConstants.STRIKER_STRIKE_POINT_REDUCTION;
        new StrikerStrike().executeAction(player, game);

        assertEquals(player.getPoints(), expectedPoints);
    }

    @Test
    public void game_DefunctCoin() throws Exception {
        Game game = new Game();
        new GameInitializer().initializeGame(game);
        Player player = new Player("Player 1");
        int playerPoints = 2;
        player.increasePoints(playerPoints);
        int expectedPoints = playerPoints - GameConstants.DEFUNCT_STRIKE_POINT_REDUCTION;
        new DefunctCoin().executeAction(player, game);

        assertEquals(player.getPoints(), expectedPoints);
    }

    @Test
    public void game_NoPocket() throws Exception {
        Game game = new Game();
        new GameInitializer().initializeGame(game);
        Player player = new Player("Player 1");
        int expectedPoints = player.getPoints();
        new NoPocket().executeAction(player, game);

        assertEquals(player.getPoints(), expectedPoints);
    }

}
