package main.parser;

import main.model.field.CronField;
import main.model.field.SpecialCharacter;

import java.util.List;

public abstract class Evaluator {
    protected final Evaluator next;
    private final SpecialCharacter specialCharacter;
    public Evaluator(Evaluator next, SpecialCharacter specialCharacter){
        this.next = next;
        this.specialCharacter = specialCharacter;
    }
    public List<Integer> evaluate(CronField cronField){
        if(cronField.getExpression().contains(specialCharacter.toString())){
           return parse(cronField);
        }else {
            return next.evaluate(cronField);
        }
    }
    protected abstract List<Integer> parse(CronField cronField);

    protected boolean containsGlobalSpecialCharacters(String field) {
        return (field.contains(SpecialCharacter.ASTERISK.toString()) || field.contains(SpecialCharacter.COMMA.toString()) || field.contains(SpecialCharacter.HYPHEN.toString()) || field.contains(SpecialCharacter.SLASH.toString()));
    }

}
