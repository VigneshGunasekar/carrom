package com.carrom.cleanstrike;

import com.carrom.cleanstrike.parameters.CarromBoard;
import com.carrom.cleanstrike.parameters.Player;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GameInitializer {

    private int noOfBlackCoins;

    public void initializeGame(Game game) throws Exception {
        initializeConfigurations(game);
        initializeParameters(game);
    }

    private void initializeConfigurations(Game game) throws Exception {

        InputStream confFile = null;
        try {
            confFile = new FileInputStream(GameConstants.CONFIG_FILE_PATH);
            Properties configurations = new Properties();

            configurations.load(confFile);

            noOfBlackCoins = Integer.parseInt(configurations.getProperty(GameConstants.NUMBER_OF_BLACK_COINS_KEY, "9"));
            game.setPlayerMinusPointAllowed(Boolean.getBoolean(configurations.getProperty(GameConstants.MINUS_POINTS_ALLOWED_KEY, "false")));
            game.setNoOfPlayers(Integer.parseInt(configurations.getProperty(GameConstants.NUMBER_OF_PLAYERS_KEY, "2")));

            if (game.getNoOfPlayers() < 2) {
                throw new Exception("Number of players can not be less than 2.");
            }

        } catch (Exception e) {
            System.err.println("Exception while initializing game configurations." + e);
            throw e;
        } finally {
            try {
                if (confFile != null) {
                    confFile.close();
                }
            } catch (IOException e) {
                System.err.println("Exception while closing streams." + e);
            }
        }

    }

    private void initializeParameters(Game game) {
        game.setCarromBoard(new CarromBoard(noOfBlackCoins));
        Player[] players = new Player[game.getNoOfPlayers()];
        for (int i = 0; i < game.getNoOfPlayers(); i++) {
            players[i] = new Player("Player " + (i + 1));
        }
        game.setPlayers(players);
        game.setGamePointsCalculator(new GamePointsCalculator(game.isPlayerMinusPointAllowed()));

    }
}
