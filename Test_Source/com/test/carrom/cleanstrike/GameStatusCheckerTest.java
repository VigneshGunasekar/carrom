package com.test.carrom.cleanstrike;

import com.carrom.cleanstrike.GameStatusChecker;
import com.carrom.cleanstrike.parameters.CarromBoard;
import com.carrom.cleanstrike.parameters.Player;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class GameStatusCheckerTest {
    @Test
    public void gameStatus_HasGameEnded_returnTrue() {
        boolean expected = true;

        Player[] players = {new Player("Player 1"), new Player("Player 2")};
        players[0].increasePoints(7);
        players[1].increasePoints(2);
        CarromBoard board = new CarromBoard(9);

        boolean actual = new GameStatusChecker().hasGameEnded(players, board);
        assertEquals(actual, expected);

        players[0].increasePoints(5);
        players[1].increasePoints(4);
        board = new CarromBoard(0);
        board.setRedCoinOnBoard(false);

        actual = new GameStatusChecker().hasGameEnded(players, board);
        assertEquals(actual, expected);

        players[0].increasePoints(2);
        players[1].increasePoints(4);
        board = new CarromBoard(0);
        board.setRedCoinOnBoard(false);

        actual = new GameStatusChecker().hasGameEnded(players, board);
        assertEquals(actual, expected);
    }

    @Test
    public void gameStatus_HasGameEnded_returnFalse() {
        boolean expected = true;

        Player[] players = {new Player("Player 1"), new Player("Player 2")};
        players[0].increasePoints(7);
        players[1].increasePoints(5);
        CarromBoard board = new CarromBoard(9);

        boolean actual = new GameStatusChecker().hasGameEnded(players, board);
        assertNotEquals(actual, expected);
    }

    @Test
    public void gameStatus_IsNoCoinOnBoard_returnTrue() {
        boolean expected = true;
        CarromBoard board = new CarromBoard(0);
        board.setRedCoinOnBoard(false);

        boolean actual = new GameStatusChecker().isNoCoinOnBoard(board);
        assertEquals(actual, expected);
    }

    @Test
    public void gameStatus_IsNoCoinOnBoard_returnFalse() {
        boolean expected = true;
        CarromBoard board = new CarromBoard(0);

        boolean actual = new GameStatusChecker().isNoCoinOnBoard(board);
        assertNotEquals(actual, expected);
    }

    @Test
    public void gameStatus_DoesPlayerWinOnCoinExhaust_returnTrue() {
        boolean expected = true;
        int winnerPoints = 7;
        int runnerPoints = 5;
        boolean actual = new GameStatusChecker().doesPlayerWinOnCoinExhaust(winnerPoints, runnerPoints);

        assertEquals(actual, expected);
    }

    @Test
    public void gameStatus_DoesPlayerWinOnCoinExhaust_returnFalse() {
        boolean expected = true;
        int winnerPoints = 4;
        int runnerPoints = 2;
        boolean actual = new GameStatusChecker().doesPlayerWinOnCoinExhaust(winnerPoints, runnerPoints);

        assertNotEquals(actual, expected);
    }

    @Test
    public void gameStatus_DoesPlayerWin_returnTrue() {
        boolean expected = true;
        int winnerPoints = 7;
        int runnerPoints = 4;
        boolean actual = new GameStatusChecker().doesPlayerWin(winnerPoints, runnerPoints);

        assertEquals(actual, expected);
    }

    @Test
    public void gameStatus_DoesPlayerWin_returnFalse() {
        boolean expected = true;
        int winnerPoints = 7;
        int runnerPoints = 6;
        boolean actual = new GameStatusChecker().doesPlayerWin(winnerPoints, runnerPoints);

        assertNotEquals(actual, expected);
    }
}
