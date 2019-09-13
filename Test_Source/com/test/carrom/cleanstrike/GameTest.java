package com.test.carrom.cleanstrike;

import com.carrom.cleanstrike.Game;
import com.carrom.cleanstrike.GameConstants;
import com.carrom.cleanstrike.Player;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GameTest {

    @Test
    public void game_InitializeGameParameters_NoOfPlayersMoreThanOne() throws Exception {
        Game game = new Game();
        int expected = 1;
        int actual = game.noOfPlayers;

        Assert.assertNotEquals(actual, expected);
    }

    @Test
    public void game_Strike_PlayerPointsIncrease() throws Exception {
        Game game = new Game();
        Player player = new Player("Player 1");
        int expectedPoints = GameConstants.STRIKE_POINT;
        game.strike(player);

        assertEquals(player.getPoints(), expectedPoints);
    }

    @Test
    public void game_MultiStrike_PlayerPointsIncrease() throws Exception {
        Game game = new Game();
        Player player = new Player("Player 1");
        int expectedPoints = GameConstants.MULTI_STRIKE_POINT;
        game.multiStrike(player);

        assertEquals(player.getPoints(), expectedPoints);
    }

    @Test
    public void game_RedStrike() throws Exception {
        Game game = new Game();
        Player player = new Player("Player 1");
        int expectedPoints = GameConstants.RED_STRIKE_POINT;
        game.redStrike(player);

        assertEquals(player.getPoints(), expectedPoints);
    }

    @Test
    public void game_StrikerStrike() throws Exception {
        Game game = new Game();
        Player player = new Player("Player 1");
        int playerPoints = 2;
        player.increasePoints(playerPoints);
        int expectedPoints = playerPoints - GameConstants.STRIKER_STRIKE_POINT_REDUCTION;
        game.strikerStrike(player);

        assertEquals(player.getPoints(), expectedPoints);
    }

    @Test
    public void game_DefunctCoin() throws Exception {
        Game game = new Game();
        Player player = new Player("Player 1");
        int playerPoints = 2;
        player.increasePoints(playerPoints);
        int expectedPoints = playerPoints - GameConstants.DEFUNCT_STRIKE_POINT_REDUCTION;
        game.defunctCoin(player);

        assertEquals(player.getPoints(), expectedPoints);
    }

    @Test
    public void game_NoPocket() throws Exception {
        Game game = new Game();
        Player player = new Player("Player 1");
        int expectedPoints = player.getPoints();
        game.noPocket(player);

        assertEquals(player.getPoints(), expectedPoints);
    }

    @Test
    public void game_ReducePlayerPoints() throws Exception {
        Game game = new Game();
        Player player = new Player("Player 1");
        int playerPoints = 2;
        player.increasePoints(playerPoints);
        int expectedPoints = player.getPoints() - 1;
        game.reducePlayerPoints(player, 1);

        assertEquals(player.getPoints(), expectedPoints);

    }

    @Test
    public void game_UpdateConditionalPoints() throws Exception {
        Game game = new Game();
        Player player = new Player("Player 1");
        int playerPoints = 4;
        player.increasePoints(playerPoints);

        int expectedPoints = player.getPoints() - 1;
        int expectedNoPockets = 0;
        player.incrementNoPocket();
        player.incrementNoPocket();
        player.incrementNoPocket();
        game.updateConditionalPoints(player);
        assertEquals(player.getPoints(), expectedPoints);
        assertEquals(player.getNoPockets(), expectedNoPockets);


        expectedPoints = player.getPoints() - 1;
        int expectedFouls = 0;
        player.incrementFoul();
        player.incrementFoul();
        player.incrementFoul();

        game.updateConditionalPoints(player);

        assertEquals(player.getPoints(), expectedPoints);
        assertEquals(player.getFouls(), expectedFouls);

    }
}
