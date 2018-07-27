package com.chuliu.demo.ejb3.server.impl;

import com.chuliu.demo.ejb3.server.api.ILocalQuizQuestionGenerator;
import com.chuliu.demo.ejb3.server.api.entities.LevelQuestion;

import javax.ejb.Local;
import javax.ejb.Stateless;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by eiuhucl on 7/23/2018.
 */
@Stateless
@Local
public class QuizQuestionGeneratorBean implements ILocalQuizQuestionGenerator {

    @Override
    public LevelQuestion generateQuestion(int level) {
        System.out.println("The question level is "+level);
        long range = (long) Math.pow(10,level);
        long a = ThreadLocalRandom.current().nextLong(range /10, range + 1);
        long b = ThreadLocalRandom.current().nextLong(range/10, range + 1);
        LevelQuestion levelQuestion = new LevelQuestion();
        levelQuestion.setQuestion( a+" + "+b+" = ?");
        levelQuestion.setExpectedAswer(a + b);
        return levelQuestion;
    }
}
