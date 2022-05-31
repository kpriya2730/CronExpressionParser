package main.parser;

import main.model.field.CronField;
import main.model.field.FieldConstraints;
import main.model.field.FieldName;
import main.model.field.SpecialCharacter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class CronFieldParser {
    private final FieldName name;
    private final FieldConstraints fieldConstraints;

    public CronFieldParser(FieldName name, FieldConstraints fieldConstraints) {
        this.name = name;
        this.fieldConstraints = fieldConstraints;
    }

    CronField parse(String field){
        CronField cronField = new CronField(name, fieldConstraints);


            if(field.contains(SpecialCharacter.QUESTION_MARK.toString()) && !containsGlobalSpecialCharacters(field)){
                if(fieldConstraints.getSpecialCharacters().contains(SpecialCharacter.QUESTION_MARK)){
                    cronField.setValidValues(parseQuestionMarkOrAsterisk(field));
                 }else {
                    throw new IllegalArgumentException(String.format("Invalid field value '%s', special character '?' not allowed in field %s", field, name));
                }
            } else{
            if(field.contains(SpecialCharacter.SLASH.toString())){
                cronField.setValidValues(parseSlash(field));
            }else if(field.contains(SpecialCharacter.ASTERISK.toString())){
                cronField.setValidValues(parseQuestionMarkOrAsterisk(field));
            }else if(field.contains(SpecialCharacter.COMMA.toString())){
                cronField.setValidValues(parseComma(field));
            }else if(field.contains(SpecialCharacter.HYPHEN.toString())){
                cronField.setValidValues(parseHyphen(field));
            }else {
                int value = Integer.parseInt(field);
                if(value < fieldConstraints.getStartRange() || value > fieldConstraints.getEndRange()){
                    throw new IllegalArgumentException(String.format("Invalid field value '%s', valid values from %s to %s",field, fieldConstraints.getStartRange(), fieldConstraints.getEndRange()));
                }
                cronField.setValidValues(new ArrayList<>(List.of(Integer.parseInt(field))));
            }
        }

        return cronField;
    }

    private List<Integer> parseQuestionMarkOrAsterisk(String field){
        if(field.length() > 1){
            throw new IllegalArgumentException(String.format("Invalid field value '%s'", field));
        }
        return IntStream.rangeClosed(fieldConstraints.getStartRange(), fieldConstraints.getEndRange())
                .boxed().collect(Collectors.toList());
    }

    private List<Integer> parseComma(String field){
        List<Integer> values = Arrays.stream(field.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        if(values.get(0) < fieldConstraints.getStartRange() || values.get(values.size()-1) > fieldConstraints.getEndRange()){
            throw new IllegalArgumentException(String.format("Invalid field value '%s', valid values from %s to %s",field, fieldConstraints.getStartRange(), fieldConstraints.getEndRange()));
        }
        return values;
    }

    private List<Integer> parseHyphen(String field){
        String[] inputRanges = field.split(SpecialCharacter.HYPHEN.toString());
        if(inputRanges.length != 2){
            throw new IllegalArgumentException(String.format("Invalid field value '%s', expected start and end range",field));
        }
        int startRange = Integer.parseInt(inputRanges[0]);
        int endRange = Integer.parseInt(inputRanges[1]);

        if(startRange < fieldConstraints.getStartRange() || endRange > fieldConstraints.getEndRange()){
            throw new IllegalArgumentException(String.format("Invalid field value '%s', valid values from %s to %s",field, fieldConstraints.getStartRange(), fieldConstraints.getEndRange()));
        }
        return IntStream.rangeClosed(startRange, endRange).boxed().collect(Collectors.toList());
    }

    private List<Integer> parseSlash(String field){
        String[] arguments = field.split(SpecialCharacter.SLASH.toString());
        if(arguments.length != 2){
            throw new IllegalArgumentException(String.format("Invalid field value '%s', expected 2 arguments, passed %s",field, arguments.length));
        }
        int startRange = (arguments[0].equals(SpecialCharacter.ASTERISK.toString()))? fieldConstraints.getStartRange() : Integer.parseInt(arguments[0]);
        if(startRange < fieldConstraints.getStartRange() || startRange > fieldConstraints.getEndRange()){
            throw new IllegalArgumentException(String.format("Invalid field value '%s', valid start range from %s to %s",field, fieldConstraints.getStartRange(), fieldConstraints.getEndRange()));
        }
        int step = Integer.parseInt(arguments[1]);
        return IntStream.iterate(startRange, val -> val+step).limit(fieldConstraints.getEndRange()+1).filter(val -> val <= fieldConstraints.getEndRange()).boxed().collect(Collectors.toList());
    }

    private boolean containsGlobalSpecialCharacters(String field) {
        return (field.contains(SpecialCharacter.ASTERISK.toString()) || field.contains(SpecialCharacter.COMMA.toString()) || field.contains(SpecialCharacter.HYPHEN.toString()) || field.contains(SpecialCharacter.SLASH.toString()));
    }
}
