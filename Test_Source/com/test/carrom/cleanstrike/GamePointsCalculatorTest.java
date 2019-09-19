package com.test.carrom.cleanstrike;

import com.carrom.cleanstrike.GamePointsCalculator;
import com.carrom.cleanstrike.parameters.Player;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GamePointsCalculatorTest {
    @Test
    public void pointsCalulator_ReducePlayerPoints() throws Exception {
        Player player = new Player("Player 1");
        int playerPoints = 2;
        player.increasePoints(playerPoints);
        int expectedPoints = player.getPoints() - 1;
        new GamePointsCalculator().reducePlayerPoints(player, 1, true);

        assertEquals(player.getPoints(), expectedPoints);
    }

    @Test
    public void pointsCalulator_UpdateConditionalPoints() throws Exception {
        GamePointsCalculator calculator = new GamePointsCalculator();

        Player player = new Player("Player 1");
        int playerPoints = 4;
        player.increasePoints(playerPoints);

        int expectedPoints = player.getPoints() - 1;
        int expectedNoPockets = 0;
        player.incrementNoPocket();
        player.incrementNoPocket();
        player.incrementNoPocket();
        calculator.updateConditionalPoints(player, true);
        assertEquals(player.getPoints(), expectedPoints);
        assertEquals(player.getNoPockets(), expectedNoPockets);


        expectedPoints = player.getPoints() - 1;
        int expectedFouls = 0;
        player.incrementFoul();
        player.incrementFoul();
        player.incrementFoul();

        calculator.updateConditionalPoints(player, true);

        assertEquals(player.getPoints(), expectedPoints);
        assertEquals(player.getFouls(), expectedFouls);

    }
}
