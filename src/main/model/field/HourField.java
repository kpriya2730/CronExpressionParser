package main.model.field;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class HourField extends CronField{
    public HourField() {
        super(1,
                FieldName.MINUTE,
                new FieldConstraints(0, 23, new HashSet<>(Arrays.asList(SpecialCharacter.ASTERISK, SpecialCharacter.COMMA, SpecialCharacter.HYPHEN, SpecialCharacter.SLASH))));
    }

}
