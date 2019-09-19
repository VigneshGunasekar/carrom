package com.test.carrom.cleanstrike.parameters;


import com.carrom.cleanstrike.parameters.Player;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PlayerTest {
    @Test
    public void player_GetName_returnsName() {
        String expected = "Player 1";
        Player player = new Player("Player 1");
        String actual = player.getName();

        assertEquals(actual, expected);
    }

    @Test
    public void player_GetPoints_returnsPoints() {
        int expected = 0;
        Player player = new Player("Player 1");
        int actual = player.getPoints();

        assertEquals(actual, expected);
    }

    @Test
    void player_increasePoints_PointsIncreased() {
        int expected = 1;
        Player player = new Player("Player 1");
        player.increasePoints(1);
        int actual = player.getPoints();

        assertEquals(actual, expected);
    }

    @Test
    void player_decreasePoints_returnsValue() {
        int expected = 2;
        Player player = new Player("Player 1");
        player.increasePoints(3);
        player.decreasePoints(1);
        int actual = player.getPoints();

        assertEquals(actual, expected);
    }

    @Test
    public void player_ResetPoints_SetsPointsToZero() {
        int expected = 0;
        Player player = new Player("Player 1");
        player.increasePoints(1);
        player.resetPoints();
        int actual = player.getPoints();

        assertEquals(actual, expected);
    }

    @Test
    public void player_GetNoPockets_ReturnsNoPocketsCount() {
        int expected = 0;
        Player player = new Player("Player 1");
        int actual = player.getNoPockets();

        assertEquals(actual, expected);
    }

    @Test
    public void player_IncrementNoPocket_IncreaseByOne() {
        int expected = 1;
        Player player = new Player("Player 1");
        player.incrementNoPocket();
        int actual = player.getNoPockets();

        assertEquals(actual, expected);
    }

    @Test
    public void player_ResetNoPockets_setPointsToZero() {
        int expected = 0;
        Player player = new Player("Player 1");
        player.incrementNoPocket();
        player.resetNoPockets();
        int actual = player.getNoPockets();

        assertEquals(actual, expected);
    }

    @Test
    public void player_GetFouls_returnsFoulCount() {
        int expected = 0;
        Player player = new Player("Player 1");
        int actual = player.getFouls();

        assertEquals(actual, expected);
    }

    @Test
    public void player_IncrementFoul() {
        int expected = 1;
        Player player = new Player("Player 1");
        player.incrementFoul();
        int actual = player.getFouls();

        assertEquals(actual, expected);
    }

    @Test
    public void player_ResetFouls() {
        int expected = 0;
        Player player = new Player("Player 1");
        player.incrementFoul();
        player.resetFouls();
        int actual = player.getFouls();

        assertEquals(actual, expected);
    }
}
