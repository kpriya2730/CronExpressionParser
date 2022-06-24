package main.model.field;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MinuteField extends CronField{
    public MinuteField() {
        super(0,
                FieldName.MINUTE,
                new FieldConstraints(0, 59, new HashSet<>(Arrays.asList(SpecialCharacter.ASTERISK, SpecialCharacter.COMMA, SpecialCharacter.HYPHEN, SpecialCharacter.SLASH))));
    }
}
