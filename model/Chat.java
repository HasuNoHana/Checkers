package model;

import java.util.ArrayList;

/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */
public class Chat {
    private ArrayList<String> messages;

    public Chat(){
        messages = new ArrayList<>();
    }

    public final ArrayList<String> getMessages() {
        return messages;
    }

    public void addMessage(String message) {
        this.messages.add(message);
    }
}
