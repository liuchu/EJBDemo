package com.chuliu.demo.ejb3.server.api.entities;

/**
 * Created by eiuhucl on 7/23/2018.
 */
public class LevelQuestion {

    private String question;
    private long expectedAswer;

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public long getExpectedAswer() {
        return expectedAswer;
    }
    public void setExpectedAswer(long expectedAswer) {
        this.expectedAswer = expectedAswer;
    }
}
