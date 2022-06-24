package main.parser;

import main.model.field.CronField;
import main.model.field.FieldConstraints;
import main.model.field.SpecialCharacter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HyphenEvaluator extends Evaluator{
    public HyphenEvaluator(Evaluator next) {
        super(next, SpecialCharacter.HYPHEN);
    }

    @Override
    protected List<Integer> parse(CronField cronField) {
        String field = cronField.getExpression();
        FieldConstraints fieldConstraints = cronField.getFieldConstraints();

        String[] inputRanges = field.split(SpecialCharacter.HYPHEN.toString());
        if(inputRanges.length != 2){
            throw new IllegalArgumentException(String.format("Invalid field value '%s', expected start and end range",field));
        }
        int startRange = Integer.parseInt(inputRanges[0]);
        int endRange = Integer.parseInt(inputRanges[1]);

        if(startRange < fieldConstraints.getStartRange() || endRange > fieldConstraints.getEndRange()){
            throw new IllegalArgumentException(String.format("Invalid field value '%s', valid values from %s to %s",field, fieldConstraints.getStartRange(), fieldConstraints.getEndRange()));
        }
        return IntStream.rangeClosed(startRange, endRange).boxed().collect(Collectors.toList());
    }
}
