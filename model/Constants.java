package model;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */

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
        public final static int MAX_MESS_LEN = 15;
        public final static String[] EMOTES = {":)", ":(", ":/", ":<", ":>", ":O", ":P"};
    }
    public static class ConnectionConstants{
        public final static int PORT_LENGTH = 10;
        public final static int IP_LENGTH = 15;
        public final static String MESSAGE_START = "mes_start";
        public final static String MESSAGE_END = "messe_end";
        public final static String AVATAR_IMAGE = "avat_imag";
        public final static String USER_NAME = "user_name";
        public final static String CHAT_MESSAGE = "chat_mess";
        public final static String EMOTE_MESSAGE = "emot_mess";
        public final static String BOARD_MOVE = "game_move";
        public final static String CONNECTION_END = "conn_exit";
    }
}