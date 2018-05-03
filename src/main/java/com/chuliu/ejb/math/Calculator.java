package com.chuliu.ejb.math;

/**
 * Created by eiuhucl on 5/2/2018.
 */
public class Calculator {

    public double divide(double a, double b) throws ArithmeticException{

        if (b == 0) {
            throw new ArithmeticException("Divisor is 0 !");
        }

        return a/b;
    }

    public double add(double a, double b){
        return a+b;
    }
}
