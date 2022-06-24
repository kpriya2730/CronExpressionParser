package main.parser;

import main.converter.AnnotationConverter;
import main.converter.Converter;
import main.model.expression.CronExpression;
import main.model.field.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CronExpressionParser {
    private final List<CronField> cronFields;
    private final Converter annotationConverter;

    public CronExpressionParser(){
        this.cronFields = new ArrayList<>(List.of(new MinuteField(), new HourField(), new DayOfMonthField(), new MonthField(), new DayOfWeekField()));
        annotationConverter = new AnnotationConverter();
    }
    public CronExpression parse(String cronExpression){
        String expression = cronExpression;

        if(expression.startsWith("@")){
            expression = annotationConverter.convert(cronExpression);
        }

        final String[] fields = expression.split(" ");
        validateFields(fields);
        for(int i=0;i<5;i++){
            CronField cronField = cronFields.get(i);
            cronField.setExpression(fields[i]);
            cronField.parse();

        }
        StringBuilder sb = new StringBuilder();
        for(int i=5;i<fields.length;i++){
            sb.append(fields[i]).append(" ");
        }

        return new CronExpression(cronFields, sb.toString().trim(), expression);
    }

    private void validateFields(String[] fields){
        final int expressionLength = fields.length;
        if(expressionLength < 6){
            throw new IllegalArgumentException(String.format("Cron expression contains %s parts, expected at least 6 parts", expressionLength));
        }

        String invalidField = Arrays.stream(fields).filter(f -> f.endsWith("-")).findAny().orElse(null);
        if (invalidField != null){
            throw new IllegalArgumentException(String.format("Invalid field value, trailing hyphens not permitted '%s'", invalidField));
        }

        invalidField = Arrays.stream(fields).filter(f -> f.endsWith(",")).findAny().orElse(null);
        if (invalidField != null){
            throw new IllegalArgumentException(String.format("Invalid field value, trailing commas not permitted '%s'", invalidField));
        }

        if(fields[2].equals(SpecialCharacter.QUESTION_MARK.toString()) && fields[4].equals(SpecialCharacter.QUESTION_MARK.toString())){
            throw new IllegalArgumentException(String.format("Cron expression invalid. Atmost one of %s and %s should be ?, but not both.", FieldName.DAY_OF_MONTH, FieldName.DAY_OF_WEEK));
        }
    }
}
