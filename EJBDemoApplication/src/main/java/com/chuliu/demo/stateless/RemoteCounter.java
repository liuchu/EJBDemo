package com.chuliu.demo.stateless;

/**
 * Created by eiuhucl on 5/3/2018.
 */
public interface RemoteCounter {

    void increment();

    void decrement();

    int getCount();
}
