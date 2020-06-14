package model;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */

import java.awt.*;

public class Constants{
    public static class UserConstants{
        public final static int MAX_USERNAME_LENGTH = 10;
        public final static int MAX_INFO_LENGTH = 30;
    }
    public static class LayoutsConstants{
        public final static int H_GAP = 20;
        public final static int V_GAP = 20;
    }
    public static class FramesConstants{
        public final static int DEFAULT_WIDTH = 800;
        public final static int DEFAULT_HEIGHT = 600;
        public final static int DEFAULT_X_POSITION = 500;
        public final static int DEFAULT_Y_POSITION = 200;
    }
    public static class ChatConstants{
        public final static int BUBBLE_GAP = 10;
        public final static int BUBBLE_GAP_EMOTES = 5;
        public final static int MAX_MESS_LEN = 25;
        public final static String[] EMOTES = {":)", ":(", ":/", ":<", ":>", ":O", ":P"};
        public final static int MAX_MESS_NUM = 15;
        public final static Color YOUR_BUBBLE_COLOR = new Color(0,132,255);
        public final static Color YOUR_TEXT_COLOR = new Color(255,123,0);
        public final static Color ENEMY_BUBBLE_COLOR = new Color(0,198,255);
        public final static Color ENEMY_TEXT_COLOR = new Color(255,57,0);
    }
    public static class ConnectionConstants{
        public final static int PORT_LENGTH = 10;
        public final static int IP_LENGTH = 15;
        public final static String USER_NAME = "user_name";
        public final static String CHAT_MESSAGE = "chat_mess";
        public final static String EMOTE_MESSAGE = "emot_mess";
        public final static String BOARD_MOVE = "game_move";
    }
    public static class GameConstants{
        public final static int BOARD_SIZE = 8;
    }
}