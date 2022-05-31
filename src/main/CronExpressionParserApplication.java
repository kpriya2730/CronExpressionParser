package main;

import main.utils.Constants;
import main.model.expression.CronExpression;
import main.model.field.FieldConstraints;
import main.model.field.FieldName;
import main.model.field.SpecialCharacter;
import main.parser.CronExpressionParser;
import main.parser.CronFieldParser;

import java.util.*;

public class CronExpressionParserApplication {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String expression  = sc.nextLine();

        CronExpressionParserApplication application = new CronExpressionParserApplication();
        CronExpressionParser cronExpressionParser= application.initialize();
        CronExpression cronExpression = cronExpressionParser.parse(expression);
        cronExpression.print();
//        application.test();

    }

    private void test(){
        CronExpressionParser cronExpressionParser= initialize();
        List<String> jobs = cronJobs();
        jobs.forEach(job -> {
            CronExpression cronExpression = cronExpressionParser.parse(job);
            cronExpression.print();
        });
    }
    private CronExpressionParser initialize(){
        List<CronFieldParser> cronFieldParsers = new ArrayList<>(Constants.NUMBER_OF_FIELDS);
        cronFieldParsers.add(0, new CronFieldParser(FieldName.MINUTE, new FieldConstraints(0, 59, new HashSet<>(Arrays.asList(SpecialCharacter.ASTERISK, SpecialCharacter.COMMA, SpecialCharacter.HYPHEN, SpecialCharacter.SLASH)))));
        cronFieldParsers.add(1, new CronFieldParser(FieldName.HOUR, new FieldConstraints(0, 23, new HashSet<>(Arrays.asList(SpecialCharacter.ASTERISK, SpecialCharacter.COMMA, SpecialCharacter.HYPHEN, SpecialCharacter.SLASH)))));
        cronFieldParsers.add(2, new CronFieldParser(FieldName.DAY_OF_MONTH, new FieldConstraints(1, 31, new HashSet<>(Arrays.asList(SpecialCharacter.ASTERISK, SpecialCharacter.COMMA, SpecialCharacter.HYPHEN, SpecialCharacter.SLASH, SpecialCharacter.QUESTION_MARK)))));
        cronFieldParsers.add(3, new CronFieldParser(FieldName.MONTH, new FieldConstraints(1, 12, new HashSet<>(Arrays.asList(SpecialCharacter.ASTERISK, SpecialCharacter.COMMA, SpecialCharacter.HYPHEN, SpecialCharacter.SLASH)))));
        cronFieldParsers.add(4, new CronFieldParser(FieldName.DAY_OF_WEEK, new FieldConstraints(0, 6, new HashSet<>(Arrays.asList(SpecialCharacter.ASTERISK, SpecialCharacter.COMMA, SpecialCharacter.HYPHEN, SpecialCharacter.SLASH, SpecialCharacter.QUESTION_MARK)))));
        return new CronExpressionParser(cronFieldParsers);
    }

    private List<String> cronJobs(){
        List<String> jobs = new ArrayList<>();


//        jobs.add("3* * * * * /usr/bin/find");
//        jobs.add("0 3 ? */1 ? /usr/bin/find");
//        jobs.add("0 3 ? */1 *? /usr/bin/find");
//        jobs.add("0 3 2 0/2 4 /usr/bin/find");
//        jobs.add("1 2 0 1 4 /usr/bin/find");
//        jobs.add("0 ? 12 */1 2 /usr/bin/find");

        jobs.add("0 3 12 */1 2 /usr/bin/find");
        jobs.add("* * * * * /usr/bin/find");
        jobs.add("*/15 0 1,15 * 1-5 /usr/bin/find");
        jobs.add("0 12 * * ? sudo kill -9");
        jobs.add("0 12 * * ? / -type d -name notes. txt");
        jobs.add("15 10 * * ? diff file1.ext file2.ext");
        jobs.add("* 14 * * ? chown linuxuser2 file.ext");
        jobs.add("0/5 14 * * ? ping google.com");
        jobs.add("0/5 14,18 * * ? uname");
        jobs.add("*/15 0 1,15 * 1-5 /usr/bin/find");
        return jobs;
    }
}
