package main.parser;

import main.model.field.CronField;
import main.model.field.FieldConstraints;
import main.model.field.SpecialCharacter;

import java.util.ArrayList;
import java.util.List;

public class ValueEvaluator extends Evaluator{
    public ValueEvaluator() {
        super(null, null);
    }

    @Override
    public List<Integer> evaluate(CronField cronField){
        return parse(cronField);
    }

    @Override
    protected List<Integer> parse(CronField cronField) {
        String field = cronField.getExpression();
        FieldConstraints fieldConstraints = cronField.getFieldConstraints();

        int value = Integer.parseInt(field);
        if(value < fieldConstraints.getStartRange() || value > fieldConstraints.getEndRange()){
            throw new IllegalArgumentException(String.format("Invalid field value '%s', valid values from %s to %s",field, fieldConstraints.getStartRange(), fieldConstraints.getEndRange()));
        }
        return new ArrayList<>(List.of(Integer.parseInt(field)));
    }
}
