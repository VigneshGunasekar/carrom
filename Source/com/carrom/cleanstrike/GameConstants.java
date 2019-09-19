package com.carrom.cleanstrike;

public class GameConstants {

    //Game action constants
    public static final int STRIKE_ACTION = 1;
    public static final int MULTI_STRIKE_ACTION = 2;
    public static final int RED_STRIKE_ACTION = 3;
    public static final int STRIKER_STRIKE_ACTION = 4;
    public static final int DEFUNCT_STRIKE_ACTION = 5;
    public static final int NO_POCKET_ACTION = 6;

    //Game winning criteria
    public static final int POINTS_TO_WIN = 5;
    public static final int POINT_DIFFERENCE_TO_WIN = 3;

    //Game Configurations
    public static final String CONFIG_FILE_PATH = "F:\\cleanstrike_configurations.properties";
    public static final String MINUS_POINTS_ALLOWED_KEY = "minus_points_allowed";
    public static final String NUMBER_OF_PLAYERS_KEY = "number_of_players";
    public static final String NUMBER_OF_BLACK_COINS_KEY = "number_of_black_coins";

    //Game points criteria
    public static final int STRIKE_POINT = 1;
    public static final int STRIKE_BLACK_COIN_REDUCTION = 1;

    public static final int MULTI_STRIKE_POINT = 2;
    public static final int MULTI_STRIKE_BLACK_COIN_REDUCTION = 2;

    public static final int RED_STRIKE_POINT = 3;

    public static final int DEFUNCT_STRIKE_POINT_REDUCTION = 1;
    public static final int DEFUNCT_STRIKE_BLACK_COIN_REDUCTION = 1;
    public static final int STRIKER_STRIKE_POINT_REDUCTION = 1;

    public static final int NO_POCKET_LIMIT = 3;
    public static final int THREE_NO_POCKET_PENALTY = 1;
    public static final int FOUL_LIMIT = 3;
    public static final int THREE_FOUL_PENALTY = 1;
}
