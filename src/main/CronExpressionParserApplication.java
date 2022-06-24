package main;

import main.model.expression.CronExpression;
import main.parser.CronExpressionParser;

import java.util.*;

public class CronExpressionParserApplication {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String expression  = sc.nextLine();

        CronExpressionParserApplication application = new CronExpressionParserApplication();
        CronExpressionParser cronExpressionParser= new CronExpressionParser();
        CronExpression cronExpression = cronExpressionParser.parse(expression);
        cronExpression.print();
//        application.test();

    }

    private void test(){
        CronExpressionParser cronExpressionParser= new CronExpressionParser();
        List<String> jobs = cronJobs();
        jobs.forEach(job -> {
            CronExpression cronExpression = cronExpressionParser.parse(job);
            cronExpression.print();
        });
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
