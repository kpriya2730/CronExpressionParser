package main.parser;

import main.model.field.CronField;
import main.model.field.FieldConstraints;
import main.model.field.SpecialCharacter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class QuestionMarkEvaluator extends Evaluator {
    public QuestionMarkEvaluator(Evaluator next) {
        super(next, SpecialCharacter.QUESTION_MARK);
    }

    @Override
    public List<Integer> evaluate(CronField cronField) {
        String field = cronField.getExpression();
        FieldConstraints fieldConstraints = cronField.getFieldConstraints();

        if(field.contains(SpecialCharacter.QUESTION_MARK.toString()) && !containsGlobalSpecialCharacters(field)){
            if(fieldConstraints.getSpecialCharacters().contains(SpecialCharacter.QUESTION_MARK)){
                return parse(cronField);
            }else {
                throw new IllegalArgumentException(String.format("Invalid field value '%s', special character '?' not allowed in field %s", field, cronField.getFieldName()));
            }
        }else{
            return  next.evaluate(cronField);
        }
    }

    @Override
    protected List<Integer> parse(CronField cronField){
        String field = cronField.getExpression();
        FieldConstraints fieldConstraints = cronField.getFieldConstraints();

        if(field.length() > 1){
            throw new IllegalArgumentException(String.format("Invalid field value '%s'", field));
        }
        return IntStream.rangeClosed(fieldConstraints.getStartRange(), fieldConstraints.getEndRange())
                .boxed().collect(Collectors.toList());
    }
}
