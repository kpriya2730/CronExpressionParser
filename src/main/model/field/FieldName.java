package main.model.field;

public enum FieldName {
    MINUTE("minute"),
    HOUR("hour"),
    DAY_OF_MONTH("day of month"),
    MONTH("month"),
    DAY_OF_WEEK("day of week");

    public final String name;

    private FieldName(String name){
        this.name = name;
    }
}
