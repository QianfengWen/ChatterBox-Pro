package main.java.chattingSystem.entities;

public class Password {
    private String password;


    private Criteria criteria;

    public Password(String password, Criteria criteria){
        this.password = password;
        this.criteria = criteria;
    }

    public String getPassword() {
        return password;
    }

}

