package com.test.carrom.cleanstrike;

import com.carrom.cleanstrike.Board;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BoardTest {


    @Test
    public void board_RedCoinStatus_True() {
        boolean expected = true;
        boolean actual = new Board(9).isRedCoinOnBoard();

        assertEquals(actual, expected);
    }

    @Test
    public void board_RedCoinStatus_False() {
        boolean expected = false;
        Board board = new Board(9);
        board.setRedCoinOnBoard(false);
        boolean actual = board.isRedCoinOnBoard();


        assertEquals(actual, expected);
    }

    @Test
    public void board_GetBlackCoins_returnsBlackCoinCount() {
        int expected = 9;
        Board board = new Board(9);
        int actual = board.getBlackCoins();

        assertEquals(actual, expected);
    }

    @Test
    public void board_decreaseBackCoins_returnsValue() {
        int expected = 8;
        Board board = new Board(9);
        board.decreaseBlackCoins(1);
        int actual = board.getBlackCoins();

        assertEquals(actual, expected);
    }
}
