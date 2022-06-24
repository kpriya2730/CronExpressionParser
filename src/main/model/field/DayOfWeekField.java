package main.model.field;

import java.util.Arrays;
import java.util.HashSet;

public class DayOfWeekField  extends CronField{
    public DayOfWeekField() {
        super(4,
                FieldName.DAY_OF_WEEK,
                new FieldConstraints(0, 6, new HashSet<>(Arrays.asList(SpecialCharacter.ASTERISK, SpecialCharacter.COMMA, SpecialCharacter.HYPHEN, SpecialCharacter.SLASH, SpecialCharacter.QUESTION_MARK))));
    }
}