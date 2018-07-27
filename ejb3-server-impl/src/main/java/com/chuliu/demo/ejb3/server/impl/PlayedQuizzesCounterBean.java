package com.chuliu.demo.ejb3.server.impl;

import com.chuliu.demo.ejb3.server.api.ILocalPlayedQuizzesCounter;
import com.chuliu.demo.ejb3.server.api.IPlayedQuizzesCounter;
import com.chuliu.demo.ejb3.server.api.IRemotePlayedQuizzesCounter;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;

/**
 * Created by eiuhucl on 7/23/2018.
 */
@Singleton
@Remote(IRemotePlayedQuizzesCounter.class)
@Local(ILocalPlayedQuizzesCounter.class)
public class PlayedQuizzesCounterBean implements IPlayedQuizzesCounter {

    long playedQuizzesNumber = 0;

    @Override
    public void increment() {
        playedQuizzesNumber++;
    }

    @Override
    public long getNumber() {
        return playedQuizzesNumber;
    }
}
