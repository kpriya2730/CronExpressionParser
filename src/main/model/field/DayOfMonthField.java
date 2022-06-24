package main.model.field;

import java.util.Arrays;
import java.util.HashSet;

public class DayOfMonthField extends CronField{
    public DayOfMonthField() {
        super(2,
                FieldName.DAY_OF_MONTH,
                new FieldConstraints(1, 31, new HashSet<>(Arrays.asList(SpecialCharacter.ASTERISK, SpecialCharacter.COMMA, SpecialCharacter.HYPHEN, SpecialCharacter.SLASH, SpecialCharacter.QUESTION_MARK))));
    }
}
