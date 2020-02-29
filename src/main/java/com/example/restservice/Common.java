package com.example.restservice;

public class Common {

    //base directory
    public static String BASE_ENV_PATH = System.getProperty("user.dir");

    //directory of current files
    public static String ENV_PATH = BASE_ENV_PATH + "/src/main/java/com/example/restservice/";
    public static String FILE_DICTIONARY = "dictionary.txt";
    public static String FILE_DEFAULT_BOARD = "test_board.txt";

    //default value
    public static String DEFAULT_BOARD = "*, *, *, *, *, *, *, *, *, *, *, *, *, *, *, *";

    //default error
    public static String DEFAULT_INVALID_ERROR_MESSAGE = "{\"message\":\"Invalid request\"}";
}
