package main.model.field;

import main.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class CronField {
    private final FieldName fieldName;
    private final FieldConstraints fieldConstraints;
    private List<Integer> validValues;

    public CronField(FieldName fieldName, FieldConstraints fieldConstraints) {
        this.fieldName = fieldName;
        this.fieldConstraints = fieldConstraints;
        this.validValues = new ArrayList<>();
    }

    public void print(){
        String name = fieldName.name;
        String output = name + Constants.SPACE.repeat(Constants.COLUMN_LENGTH-name.length());
        System.out.print(output);
        validValues.stream().forEach(val -> System.out.print(val + Constants.SPACE));
        System.out.println();
    }

    public void setValidValues(List<Integer> validValues) {
        this.validValues = validValues;
    }
}
