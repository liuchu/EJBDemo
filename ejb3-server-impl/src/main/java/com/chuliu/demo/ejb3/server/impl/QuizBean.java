package com.chuliu.demo.ejb3.server.impl;

import com.chuliu.demo.ejb3.server.api.ILocalQuiz;
import com.chuliu.demo.ejb3.server.api.ILocalQuizQuestionGenerator;
import com.chuliu.demo.ejb3.server.api.IQuiz;
import com.chuliu.demo.ejb3.server.api.IRemoteQuiz;
import com.chuliu.demo.ejb3.server.api.entities.LevelQuestion;

import javax.ejb.*;

/**
 * Created by eiuhucl on 7/23/2018.
 */

@Stateful
@Remote(IRemoteQuiz.class)
@Local(ILocalQuiz.class)
public class QuizBean implements IQuiz {

    @EJB
    ILocalQuizQuestionGenerator levelQuestionGenerator;

    private String playerName;
    private String askedQuestion;
    private Long expectedAnswer;
    private int score=0;
    private int level=1;

    @Override
    public void begin(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public String generateQuestionAndAnswer() {
        LevelQuestion levelQuestion = levelQuestionGenerator.generateQuestion(level);
        askedQuestion = levelQuestion.getQuestion();
        expectedAnswer = levelQuestion.getExpectedAswer();
        return askedQuestion;
    }

    @Override
    public boolean verifyAnswerAndReward(int result) {
        System.out.println("Expected answer: "+expectedAnswer);
        System.out.println("Actually answer: "+result);
        if(expectedAnswer == result){
            this.score++;
            this.level++;
            System.out.println("The score is now "+score+". The level is now "+level);
            return true;
        }
        return false;
    }

    @Remove
    @Override
    public void end() {
        System.out.println("QuizBean instance will be removed..");
    }

    // Some getters to be able to congratulate the player all with showing him name, score,...

    @Override
    public String getPlayerName() {
        return playerName;
    }

    public String getAskedQuestion() {
        return askedQuestion;
    }

    @Override
    public int getScore() {
        return score;
    }

}
