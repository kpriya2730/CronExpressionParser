package main.model.expression;

import main.model.field.CronField;
import main.utils.Constants;

import java.util.List;

public class CronExpression {
    private final List<CronField> cronFields;
    private final String command;
    private final String expression;

    public CronExpression(List<CronField> cronFields, String command, String expression){
        this.cronFields = cronFields;
        this.command = command;
        this.expression = expression;
    }

    public List<CronField> getCronFields() {
        return cronFields;
    }

    public String getCommand() {
        return command;
    }

    public String getExpression() {
        return expression;
    }

    public void print(){
        cronFields.forEach(CronField::print);
        printCommand();
    }

    private void printCommand(){
        String description = "command";
        String output = description + Constants.SPACE.repeat(Constants.COLUMN_LENGTH - description.length()) + command;
        System.out.println(output);
    }
}
