package main.converter;

public interface Converter {
    String convert(String expression);
    void validate(String expression);
}
