package main.converter;

import java.util.HashMap;
import java.util.Map;

public class AnnotationConverter implements Converter{

    private final Map<String, String> annotationMap;

    public AnnotationConverter(){
        annotationMap = new HashMap<>();
        annotationMap.put("@yearly", "0 0 1 1 * ");
    }

    @Override
    public String convert(String expression) {
        validate(expression);
        String[] fields = expression.split(" ");
        int commandIdx = expression.indexOf(" ")+1;
        return annotationMap.get(fields[0]) + expression.substring(commandIdx);
    }

    @Override
    public void validate(String expression) {
        String[] fields = expression.split(" ");
        if(!annotationMap.containsKey(fields[0])){
            throw new IllegalArgumentException(String.format("Invalid annotation %s", fields[0]));
        }
    }


}
