package com.carrom.cleanstrike;

import com.carrom.cleanstrike.actions.DefunctCoin;
import com.carrom.cleanstrike.actions.GameAction;
import com.carrom.cleanstrike.actions.MultiStrike;
import com.carrom.cleanstrike.actions.NoPocket;
import com.carrom.cleanstrike.actions.RedStrike;
import com.carrom.cleanstrike.actions.Strike;
import com.carrom.cleanstrike.actions.StrikerStrike;
import com.carrom.cleanstrike.parameters.CarromBoard;
import com.carrom.cleanstrike.parameters.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {
    private CarromBoard board;
    private Player players[];

    private int noOfPlayers;
    private boolean playerMinusPointAllowed;
    private GamePointsCalculator gamePointsCalculator;

    private static final Map<Integer, GameAction> actionMap;

    static {
        Map<Integer, GameAction> actions = new HashMap<>();
        actions.put(GameConstants.STRIKE_ACTION, new Strike());
        actions.put(GameConstants.MULTI_STRIKE_ACTION, new MultiStrike());
        actions.put(GameConstants.RED_STRIKE_ACTION, new RedStrike());
        actions.put(GameConstants.STRIKER_STRIKE_ACTION, new StrikerStrike());
        actions.put(GameConstants.DEFUNCT_STRIKE_ACTION, new DefunctCoin());
        actions.put(GameConstants.NO_POCKET_ACTION, new NoPocket());
        actionMap = Collections.unmodifiableMap(actions);
    }

    public CarromBoard getCarromBoard() {
        return board;
    }

    public void setCarromBoard(CarromBoard carromBoard) {
        board = carromBoard;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getNoOfPlayers() {
        return noOfPlayers;
    }

    public void setNoOfPlayers(int noOfPlayers) {
        this.noOfPlayers = noOfPlayers;
    }

    public boolean isPlayerMinusPointAllowed() {
        return playerMinusPointAllowed;
    }

    public void setPlayerMinusPointAllowed(boolean playerMinusPointAllowed) {
        this.playerMinusPointAllowed = playerMinusPointAllowed;
    }

    public GamePointsCalculator getGamePointsCalculator() {
        return gamePointsCalculator;
    }

    public void setGamePointsCalculator(GamePointsCalculator gamePointsCalculator) {
        this.gamePointsCalculator = gamePointsCalculator;
    }

    public void startGame() throws Exception {

        GameInitializer initializer = new GameInitializer();
        initializer.initializeGame(this);

        int playerIndex = 0;
        Player currentPlayer = players[playerIndex];

        Scanner scanner = new Scanner(System.in);

        boolean gameEnded = false;
        boolean switchTurn;

        GameStatusChecker gameStatusChecker = new GameStatusChecker();
        while (!gameEnded) {

            System.out.print(currentPlayer.getName());
            System.out.println(": Choose an outcome from the list below\n" +
                    "1. Strike\n" +
                    "2. Multistrike\n" +
                    "3. Red strike\n" +
                    "4. Striker strike\n" +
                    "5. Defunct coin\n" +
                    "6. None\n");

            int turnOutput = scanner.nextInt();

            if (turnOutput < 1 || turnOutput > 6) {
                System.out.println("Enter a valid option...");
                continue;
            }

            GameAction currentTurnGameAction = actionMap.get(turnOutput);
            switchTurn = currentTurnGameAction.executeAction(currentPlayer, this);

            gamePointsCalculator.updateConditionalPoints(currentPlayer);

            gameEnded = gameStatusChecker.hasGameEnded(players, board);

            if (switchTurn) {
                currentPlayer = switchPlayer(currentPlayer, playerIndex);
                playerIndex++;
            }
        }

    }

    private Player switchPlayer(Player currentPlayer, int playerIndex) {
        playerIndex = (playerIndex + 1) % noOfPlayers;
        currentPlayer = players[playerIndex];
        return currentPlayer;
    }

}
