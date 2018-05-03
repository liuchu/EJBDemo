package com.chuliu.demo.stateless;

import javax.ejb.Remote;
import javax.ejb.Stateful;

/**
 * Created by eiuhucl on 5/3/2018.
 */
@Stateful
@Remote(RemoteCounter.class)
public class CounterBean implements RemoteCounter {

    private int i = 0;

    @Override
    public void increment() {
         i++;
    }

    @Override
    public void decrement() {
        i--;
    }

    @Override
    public int getCount() {
        return i;
    }
}
