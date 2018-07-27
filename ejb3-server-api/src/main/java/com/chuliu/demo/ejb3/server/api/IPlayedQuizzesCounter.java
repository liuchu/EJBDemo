package com.chuliu.demo.ejb3.server.api;

/**
 * Created by eiuhucl on 7/23/2018.
 */
public interface IPlayedQuizzesCounter {

    void increment();
    long getNumber();
}
