package com.chuliu.demo.ejb3.server.api;

/**
 * Created by eiuhucl on 7/23/2018.
 */
public interface IQuiz {

    void begin(String userName);
    String generateQuestionAndAnswer();
    boolean verifyAnswerAndReward(int result);
    void end();
    int getScore();
    String getPlayerName();
}
