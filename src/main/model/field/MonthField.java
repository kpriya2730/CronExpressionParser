package main.model.field;


import java.util.Arrays;
import java.util.HashSet;

public class MonthField extends CronField{
    public MonthField() {
        super(3,
                FieldName.MONTH,
                new FieldConstraints(1, 12, new HashSet<>(Arrays.asList(SpecialCharacter.ASTERISK, SpecialCharacter.COMMA, SpecialCharacter.HYPHEN, SpecialCharacter.SLASH))));
    }
}
