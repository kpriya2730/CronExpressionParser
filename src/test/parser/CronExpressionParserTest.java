package test.parser;

import main.model.expression.CronExpression;
import main.parser.CronExpressionParser;
import org.junit.Test;
import org.testng.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CronExpressionParserTest {

    private CronExpressionParser cronExpressionParser = new CronExpressionParser();

    @Test
    public void validTest(){
        CronExpression cronExpression = cronExpressionParser.parse("0 3 12 */1 2 /usr/bin/find");
    }

}
