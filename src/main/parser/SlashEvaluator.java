package main.parser;

import main.model.field.CronField;
import main.model.field.FieldConstraints;
import main.model.field.SpecialCharacter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SlashEvaluator extends Evaluator{


    public SlashEvaluator(Evaluator next) {
        super(next, SpecialCharacter.SLASH);
    }

    @Override
    protected List<Integer> parse(CronField cronField) {
        String field = cronField.getExpression();
        FieldConstraints fieldConstraints = cronField.getFieldConstraints();

        String[] arguments = field.split(SpecialCharacter.SLASH.toString());
        if(arguments.length != 2){
            throw new IllegalArgumentException(String.format("Invalid field value '%s', expected 2 arguments, passed %s",field, arguments.length));
        }
        int startRange = (arguments[0].equals(SpecialCharacter.ASTERISK.toString()))? fieldConstraints.getStartRange() : Integer.parseInt(arguments[0]);
        if(startRange < fieldConstraints.getStartRange() || startRange > fieldConstraints.getEndRange()){
            throw new IllegalArgumentException(String.format("Invalid field value '%s', valid start range from %s to %s",field, fieldConstraints.getStartRange(), fieldConstraints.getEndRange()));
        }
        int step = Integer.parseInt(arguments[1]);
        return IntStream.iterate(startRange, val -> val+step).limit(fieldConstraints.getEndRange()+1).filter(val -> val <= fieldConstraints.getEndRange()).boxed().collect(Collectors.toList());
    }
}
