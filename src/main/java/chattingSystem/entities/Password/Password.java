package main.java.chattingSystem.entities.Password;

public class Password {
    private String password;


    public Password(String password){
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public boolean isValid(){
        if (password.length() < 8) return false;
        if (!password.matches(".*[a-zA-Z]+.*")) return false;
        if (!password.matches(".*[0-9]+.*")) return false;
        return true;
    }
}

