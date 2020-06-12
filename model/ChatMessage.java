package model;

public class ChatMessage {
    private String text;
    public ChatMessage(String message, String from){
        int len = message.length();
        if(len>0){
            text = "["+from+"]: \t";
            int i = 0;
            while(i<message.length()/ Constants.ChatConstants.MAX_MESS_LEN){
                text = text.concat(message.substring(i*Constants.ChatConstants.MAX_MESS_LEN, (i+1)*Constants.ChatConstants.MAX_MESS_LEN));
                text = text.concat("\n\t");
                len -= Constants.ChatConstants.MAX_MESS_LEN;
                ++i;
            }
            text = text.concat(message.substring(i*Constants.ChatConstants.MAX_MESS_LEN, i*Constants.ChatConstants.MAX_MESS_LEN+len));
            text = text.concat("\n");
        }
    }
    public ChatMessage(String message ){
        int len = message.length();
        if(len>0){
            text = message;
        }
    }
    public String getText() {
        return text;
    }
}
