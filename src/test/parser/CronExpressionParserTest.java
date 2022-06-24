package test.parser;

import main.model.expression.CronExpression;
import main.parser.CronExpressionParser;
import org.testng.annotations.Test;


public class CronExpressionParserTest {

    private CronExpressionParser cronExpressionParser = new CronExpressionParser();

    @Test
    public void validTest(){
        CronExpression cronExpression = cronExpressionParser.parse("0 3 12 */1 2 /usr/bin/find");

        asse
    }

}
