package main.model.field;

import main.parser.Evaluator;
import main.parser.EvaluatorChain;
import main.parser.QuestionMarkEvaluator;
import main.utils.Constants;
import java.util.List;

public abstract class CronField {
    private final int position;
    private final FieldName fieldName;
    private final FieldConstraints fieldConstraints;
    private String expression;

    private List<Integer> validValues;

    protected CronField(int position, FieldName fieldName, FieldConstraints fieldConstraints) {
        this.position = position;
        this.fieldName = fieldName;
        this.fieldConstraints = fieldConstraints;
    }

    public int getPosition() {
        return position;
    }

    public FieldName getFieldName() {
        return fieldName;
    }

    public FieldConstraints getFieldConstraints() {
        return fieldConstraints;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression){
        this.expression = expression;
    }


    public void setValidValues(List<Integer> validValues) {
        this.validValues = validValues;
    }

    public List<Integer> getValidValues() {
        return validValues;
    }

    public void parse() {
        EvaluatorChain evaluator = new EvaluatorChain();
        setValidValues(evaluator.evaluate(this));
    }

    public void print() {
        String name = fieldName.name;
        String output = name + Constants.SPACE.repeat(Constants.COLUMN_LENGTH-name.length());
        System.out.print(output);
        validValues.forEach(val -> System.out.print(val + Constants.SPACE));
        System.out.println();
    }
}
