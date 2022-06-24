package main.parser;

import main.model.field.CronField;
import main.model.field.FieldConstraints;
import main.model.field.SpecialCharacter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommaEvaluator extends Evaluator{
    public CommaEvaluator(Evaluator next) {
        super(next, SpecialCharacter.COMMA);
    }

    @Override
    protected List<Integer> parse(CronField cronField) {
        String field = cronField.getExpression();
        FieldConstraints fieldConstraints = cronField.getFieldConstraints();

        List<Integer> values = Arrays.stream(field.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        if(values.get(0) < fieldConstraints.getStartRange() || values.get(values.size()-1) > fieldConstraints.getEndRange()){
            throw new IllegalArgumentException(String.format("Invalid field value '%s', valid values from %s to %s",field, fieldConstraints.getStartRange(), fieldConstraints.getEndRange()));
        }
        return values;
    }
}
