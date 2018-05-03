package com.chuliu.ejb.math;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by eiuhucl on 5/2/2018.
 */
public class CalculatorTest {

    @Test
    public void divideNoZero() {
        new Calculator().divide(1,2);
    }

    @Test(expected = ArithmeticException.class)
    public void divideWithZeroExpectAnException() {
        new Calculator().divide(1,0);
    }

    @Test
    public void divideWithZero() {
        new Calculator().divide(1,0);
    }

    @Test
    public void failThistest(){
        fail("Make the test fail directly");
    }

    @Test(timeout = 1000)
    public void timeoutMethod(){

        try {
            Thread.sleep(2*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Ignore("Deprecated test case")
    @Test
    public void oldTest(){
        System.out.println("This test case would be skipped");
    }




}