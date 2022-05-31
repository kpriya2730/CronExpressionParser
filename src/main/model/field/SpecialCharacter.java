package main.model.field;

public enum SpecialCharacter {
    ASTERISK('*'),
    COMMA(','),
    HYPHEN('-'),
    SLASH('/'),
    QUESTION_MARK('?');

    public final Character symbol;

    private SpecialCharacter(Character symbol){
        this.symbol = symbol;
    }


    public String toString(){
        return Character.toString(symbol);
    }

}
