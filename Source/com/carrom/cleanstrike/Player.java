package com.carrom.cleanstrike;

public class Player {

    private String name;
    private int points;
    private int fouls;
    private int noPockets;

    public Player(String name) {
        this.name = name;
        this.points = this.fouls = this.noPockets = 0;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void increasePoints(int pointsToIncrease) {
        this.points += pointsToIncrease;
    }

    public void decreasePoints(int pointsToDecrease) {
        this.points -= pointsToDecrease;
    }

    public void resetPoints() {
        this.points = 0;
    }

    public int getNoPockets() {
        return noPockets;
    }

    public void incrementNoPocket() {
        this.noPockets++;
    }

    public void resetNoPockets() {
        this.noPockets = 0;
    }

    public int getFouls() {
        return fouls;
    }

    public void incrementFoul() {
        this.fouls++;
    }

    public void resetFouls() {
        this.fouls = 0;
    }

}
