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
    public CarromBoard board;
    public Player players[];

    public int noOfPlayers;
    public boolean playerMinusPointAllowed;

    private final Map<Integer, GameAction> actionMap;

    public Game() {
        Map actions = new HashMap<>();
        actions.put(1, new Strike());
        actions.put(2, new MultiStrike());
        actions.put(3, new RedStrike());
        actions.put(4, new StrikerStrike());
        actions.put(5, new DefunctCoin());
        actions.put(6, new NoPocket());
        actionMap = Collections.unmodifiableMap(actions);
    }

    public void startGame() throws Exception {

        GameInitializer initializer = new GameInitializer();
        initializer.initializeGame(this);

        int playerIndex = 0;
        Player currentPlayer = players[playerIndex];

        Scanner scanner = new Scanner(System.in);

        boolean gameEnded = false;
        boolean switchTurn;

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

            switchTurn = actionMap.get(turnOutput).executeAction(currentPlayer, this);

            new GamePointsCalculator().updateConditionalPoints(currentPlayer, playerMinusPointAllowed);

            gameEnded = new GameStatusChecker().hasGameEnded(players, board);

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
