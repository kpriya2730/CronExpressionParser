package main.model.field;

import java.util.Set;

public class FieldConstraints {
    private final Integer startRange;
    private final Integer endRange;
    private final Set<SpecialCharacter> specialCharacters;

    public FieldConstraints(Integer startRange, Integer endRange, Set<SpecialCharacter> specialCharacters) {
        this.startRange = startRange;
        this.endRange = endRange;
        this.specialCharacters = specialCharacters;
    }

    public Integer getStartRange() {
        return startRange;
    }

    public Integer getEndRange() {
        return endRange;
    }

    public Set<SpecialCharacter> getSpecialCharacters() {
        return specialCharacters;
    }
}
