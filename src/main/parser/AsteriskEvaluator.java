package main.parser;

import main.model.field.CronField;
import main.model.field.FieldConstraints;
import main.model.field.SpecialCharacter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AsteriskEvaluator extends Evaluator{

    public AsteriskEvaluator(Evaluator next) {
        super(next, SpecialCharacter.ASTERISK);
    }


    @Override
    protected List<Integer> parse(CronField cronField) {
        String field = cronField.getExpression();
        FieldConstraints fieldConstraints = cronField.getFieldConstraints();

        if(field.length() > 1){
            throw new IllegalArgumentException(String.format("Invalid field value '%s'", field));
        }
        return IntStream.rangeClosed(fieldConstraints.getStartRange(), fieldConstraints.getEndRange())
                .boxed().collect(Collectors.toList());
    }
}
