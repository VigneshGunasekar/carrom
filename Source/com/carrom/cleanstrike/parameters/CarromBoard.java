package com.carrom.cleanstrike.parameters;

public class CarromBoard {
    private boolean isRedCoinOnBoard;
    private int blackCoins;

    public CarromBoard(int noOfBlackCoins) {
        isRedCoinOnBoard = true;
        blackCoins = noOfBlackCoins;
    }

    public boolean isRedCoinOnBoard() {
        return isRedCoinOnBoard;
    }

    public void setRedCoinOnBoard(boolean redCoinStatus) {
        this.isRedCoinOnBoard = redCoinStatus;
    }

    public int getBlackCoins() {
        return blackCoins;
    }

    public void decreaseBlackCoins(int coinsToRemove) {
        this.blackCoins -= coinsToRemove;
    }
}
