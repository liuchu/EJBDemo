package com.chuliu.demo.stateless;

import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 * Created by eiuhucl on 5/3/2018.
 */
@Stateless
@Remote(RemoteCalculator.class)
public class CalculatorBean implements RemoteCalculator {

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }
}
