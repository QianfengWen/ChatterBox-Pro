package main.java.chattingSystem.entities.Password;

public class Criteria {
    private boolean hasCharacter;
    private boolean hasNumeric;
    private int minLength;

    public Criteria(boolean hascharacter, boolean hasSpecial){
        this.hasCharacter = hascharacter;
        this.hasNumeric = hasSpecial;
        this.minLength = 8;
    }

    public boolean hasCharacter() {
        return hasCharacter;
    }
    public boolean hasNumeric() {
        return hasNumeric;
    }
    public int getMinLength() {
        return minLength;
    }
}

