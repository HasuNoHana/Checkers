package model;
/*
 * @author Rafal Uzarowicz
 * @see "https://github.com/RafalUzarowicz"
 */

public class User {
    public User(String userName){
        this.userName = userName;
    }

    // Username
    private String userName;

    public void setName(String name){
        if(name.length()< Constants.UserConstants.MAX_USERNAME_LENGTH){
            this.userName = name;
        }
    }
    public String getName(){
        return this.userName;
    }
}
