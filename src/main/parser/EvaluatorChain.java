package main.parser;

import main.model.field.CronField;
import main.model.field.SpecialCharacter;

import java.util.List;

public class EvaluatorChain {
    private Evaluator chain;
    public EvaluatorChain(){
        chain = new QuestionMarkEvaluator(new SlashEvaluator(new AsteriskEvaluator(new CommaEvaluator(new HyphenEvaluator(new ValueEvaluator())))));
    }

    public List<Integer> evaluate(CronField field){
        return chain.evaluate(field);
    }
}
