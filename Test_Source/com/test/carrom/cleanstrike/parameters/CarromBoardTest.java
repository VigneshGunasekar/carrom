package com.test.carrom.cleanstrike.parameters;

import com.carrom.cleanstrike.parameters.CarromBoard;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CarromBoardTest {


    @Test
    public void carromBoard_RedCoinStatus_True() {
        boolean expected = true;
        boolean actual = new CarromBoard(9).isRedCoinOnBoard();

        assertEquals(actual, expected);
    }

    @Test
    public void carromBoard_RedCoinStatus_False() {
        boolean expected = false;
        CarromBoard CarromBoard = new CarromBoard(9);
        CarromBoard.setRedCoinOnBoard(false);
        boolean actual = CarromBoard.isRedCoinOnBoard();


        assertEquals(actual, expected);
    }

    @Test
    public void carromBoard_GetBlackCoins_returnsBlackCoinCount() {
        int expected = 9;
        CarromBoard CarromBoard = new CarromBoard(9);
        int actual = CarromBoard.getBlackCoins();

        assertEquals(actual, expected);
    }

    @Test
    public void carromBoard_decreaseBackCoins_returnsValue() {
        int expected = 8;
        CarromBoard CarromBoard = new CarromBoard(9);
        CarromBoard.decreaseBlackCoins(1);
        int actual = CarromBoard.getBlackCoins();

        assertEquals(actual, expected);
    }
}
