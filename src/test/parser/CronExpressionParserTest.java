package test.parser;

import main.model.expression.CronExpression;
import main.parser.CronExpressionParser;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.testng.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CronExpressionParserTest {

    private final CronExpressionParser cronExpressionParser = new CronExpressionParser();



    @Test
    public void validTest(){
        CronExpression cronExpression = cronExpressionParser.parse("@yearly /usr/bin/find");
        int[] minutes = cronExpression.getCronFields().get(0).getValidValues().stream().mapToInt(integer -> integer).toArray();
        int[] hours = cronExpression.getCronFields().get(1).getValidValues().stream().mapToInt(integer -> integer).toArray();
        int[] dayOfMonth = cronExpression.getCronFields().get(2).getValidValues().stream().mapToInt(integer -> integer).toArray();
        int[] month = cronExpression.getCronFields().get(3).getValidValues().stream().mapToInt(integer -> integer).toArray();
        int[] dayOfWeek = cronExpression.getCronFields().get(4).getValidValues().stream().mapToInt(integer -> integer).toArray();

        int[] expectedMinutes = new int[] {0};
        int[] expectedHours = new int[] {0};
        int[] expectedDayOfMonth = new int[] {1};
        int[] expectedMonth = new int[] {1};
        int[] expectedDayOfWeek = new int[] {0, 1, 2 , 3, 4, 5, 6};

        assertArrayEquals(expectedMinutes, minutes);
        assertArrayEquals(expectedHours, hours);
        assertArrayEquals(expectedDayOfMonth, dayOfMonth);
        assertArrayEquals(expectedMonth, month);
        assertArrayEquals(expectedDayOfWeek, dayOfWeek);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Test
    public void monthlyAnnotationTest(){
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage("Invalid annotation @monthly");
        CronExpression cronExpression = cronExpressionParser.parse("@monthly /usr/bin/find");
    }

}
