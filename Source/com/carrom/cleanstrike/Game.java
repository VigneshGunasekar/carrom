package com.carrom.cleanstrike;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Game {
    private Board board;
    private Player players[];

    public int noOfPlayers;
    public int noOfBlackCoins;
    private boolean playerMinusPointAllowed;

    public Game() throws Exception {

        initializeGameParameters();

        board = new Board(noOfBlackCoins);
        players = new Player[noOfPlayers];
        for (int i = 0; i < noOfPlayers; i++) {
            players[i] = new Player("Player " + (i + 1));
        }

    }

    public void initializeGameParameters() throws Exception {
        InputStream confFile = null;
        try {
            confFile = new FileInputStream(GameConstants.CONFIG_FILE_PATH);
            Properties configurations = new Properties();

            configurations.load(confFile);

            playerMinusPointAllowed = Boolean.getBoolean(configurations.getProperty(GameConstants.MINUS_POINTS_ALLOWED_KEY, "true"));
            noOfBlackCoins = Integer.parseInt(configurations.getProperty(GameConstants.NUMBER_OF_BLACK_COINS_KEY, "9"));
            noOfPlayers = Integer.parseInt(configurations.getProperty(GameConstants.NUMBER_OF_PLAYERS_KEY, "2"));

            if (noOfPlayers < 2) {
                throw new Exception("Number of players can not be less than 2.");
            }

        } catch (Exception e) {
            System.err.println("Exception while initializing game parameters." + e);
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

    public void startGame() {
        int playerIndex = 0;
        Player currentPlayer = players[playerIndex];

        Scanner scanner = new Scanner(System.in);

        boolean gameEnded = false;
        boolean gameDiscontinued = false;

        while (!(gameEnded || gameDiscontinued)) {
            boolean switchTurn = true;

            System.out.print(currentPlayer.getName());
            System.out.println(": Choose an outcome from the list below\n" +
                    "1. Strike\n" +
                    "2. Multistrike\n" +
                    "3. Red strike\n" +
                    "4. Striker strike\n" +
                    "5. Defunct coin\n" +
                    "6. None\n");

            int turnOutput = scanner.nextInt();
            switch (turnOutput) {
                case 1:
                    switchTurn = strike(currentPlayer);
                    break;
                case 2:
                    switchTurn = multiStrike(currentPlayer);
                    break;
                case 3:
                    switchTurn = redStrike(currentPlayer);
                    break;
                case 4:
                    strikerStrike(currentPlayer);
                    break;
                case 5:
                    defunctCoin(currentPlayer);
                    break;
                case 6:
                    noPocket(currentPlayer);
                    break;
                default:
                    System.out.println("Game ended");
                    gameDiscontinued = true;
                    break;
            }

            updateConditionalPoints(currentPlayer);

            gameEnded = checkGameCompletion();

            currentPlayer = switchPlayer(currentPlayer, switchTurn, playerIndex);
            playerIndex++;
        }

    }

    public boolean strike(Player currentPlayer) {
        if (board.getBlackCoins() >= GameConstants.STRIKE_BLACK_COIN_REDUCTION) {
            currentPlayer.increasePoints(GameConstants.STRIKE_POINT);
            currentPlayer.resetNoPockets();
            board.decreaseBlackCoins(GameConstants.STRIKE_BLACK_COIN_REDUCTION);
            return true;
        } else {
            System.out.println("Not enough black coins to perform Strike. Try again. ");
            return false;
        }
    }

    public boolean multiStrike(Player currentPlayer) {
        if (board.getBlackCoins() >= GameConstants.MULTI_STRIKE_BLACK_COIN_REDUCTION) {
            currentPlayer.increasePoints(GameConstants.MULTI_STRIKE_POINT);
            currentPlayer.resetNoPockets();
            board.decreaseBlackCoins(GameConstants.MULTI_STRIKE_BLACK_COIN_REDUCTION);
            return true;
        } else {
            System.out.println("Not enough black coins to perform Multi-Strike. Try again. ");
            return false;
        }
    }

    public boolean redStrike(Player currentPlayer) {
        if (board.isRedCoinOnBoard()) {
            currentPlayer.increasePoints(GameConstants.RED_STRIKE_POINT);
            board.setRedCoinOnBoard(false);
            currentPlayer.resetNoPockets();
            return true;
        } else {
            System.out.println("Red strike already over. Try again.");
            return false;
        }
    }

    public void strikerStrike(Player currentPlayer) {
        reducePlayerPoints(currentPlayer, GameConstants.STRIKER_STRIKE_POINT_REDUCTION);
        currentPlayer.incrementFoul();
        currentPlayer.incrementNoPocket();
    }

    public void defunctCoin(Player currentPlayer) {
        reducePlayerPoints(currentPlayer, GameConstants.DEFUNCT_STRIKE_POINT_REDUCTION);
        board.decreaseBlackCoins(GameConstants.DEFUNCT_STRIKE_BLACK_COIN_REDUCTION);
        currentPlayer.incrementFoul();
        currentPlayer.incrementNoPocket();
    }

    public void noPocket(Player currentPlayer) {
        currentPlayer.incrementNoPocket();
    }

    public void reducePlayerPoints(Player currentPlayer, int minusPoint) {
        if (currentPlayer.getPoints() < minusPoint && !playerMinusPointAllowed) {
            currentPlayer.resetPoints();
        } else {
            currentPlayer.decreasePoints(minusPoint);
        }
    }

    private Player switchPlayer(Player currentPlayer, boolean switchTurn, int playerIndex) {
        if (switchTurn) {
            playerIndex = (playerIndex + 1) % noOfPlayers;
            currentPlayer = players[playerIndex];
        }
        return currentPlayer;
    }


    public Player updateConditionalPoints(Player currentPlayer) {
        if (currentPlayer.getNoPockets() == GameConstants.NO_POCKET_LIMIT) {
            reducePlayerPoints(currentPlayer, 1);
            currentPlayer.resetNoPockets();
        }
        if (currentPlayer.getFouls() == GameConstants.FOUL_LIMIT) {
            reducePlayerPoints(currentPlayer, 1);
            currentPlayer.resetFouls();
        }
        return currentPlayer;
    }


    private boolean checkGameCompletion() {
        boolean gameEnded = false;
        boolean gameDrawn = false;

        ArrayList<Player> playerList = new ArrayList<Player>(Arrays.asList(players));
        Collections.sort(playerList, new Comparator<Player>() {
            @Override
            public int compare(Player player1, Player player2) {
                if (player1.getPoints() == player2.getPoints()) {
                    return 0;
                } else if (player1.getPoints() > player2.getPoints()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        Player winner = playerList.get(playerList.size() - 1);
        int winnerPoints = winner.getPoints();
        Player runner = playerList.get(playerList.size() - 2);
        int runnerPoints = runner.getPoints();

        if (winnerPoints >= GameConstants.POINTS_TO_WIN && winnerPoints - runnerPoints >= GameConstants.POINT_DIFFERENCE_TO_WIN) {
            gameEnded = true;
        }

        if (board.getBlackCoins() <= 0 && !board.isRedCoinOnBoard()) {
            gameEnded = true;
            if (winnerPoints >= GameConstants.POINTS_TO_WIN || winnerPoints - runnerPoints >= GameConstants.POINT_DIFFERENCE_TO_WIN) {
                gameDrawn = false;
            } else {
                gameDrawn = true;
            }
        }


        if (gameEnded) {
            if (!gameDrawn) {
                System.out.print(winner.getName() + " won the game. ");
                System.out.print("Final Score: " + players[0].getPoints());
                for (int i = 1; i < players.length; i++) {
                    System.out.println("-" + playerList.get(i).getPoints());
                }
            } else {
                System.out.println("Game ended in a Draw. ");
            }

        }
        return gameEnded;

    }


}
