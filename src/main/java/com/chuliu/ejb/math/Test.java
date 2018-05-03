package com.chuliu.ejb.math;


import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by eiuhucl on 5/2/2018.
 */
public class Test {

    private String name;

    private static Logger logger = Logger.getLogger("Test");

    public Test(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void methodA(){
        logger.info("method A");
        setName("A");
    }

    public void methodB(){
        logger.info("method B");
        setName("B");
    }

    public void methodC(){
        logger.info("method C");
        setName("C");
    }

    public int add(int a, int b){
        return a+b;
    }

    public static void main(String[] args) {

        /*int temp = 0;

        for (int i=0;i<100;i++) {
            temp = i;
        }*/

        boolean condition = true;

        logger.setLevel(Level.FINEST);

        logger.info("---Main begin---");
        Test test = new Test("initial name");

        test.methodA();
        test.methodB();
        test.methodC();

        test.add(1,2);

        logger.info("---Main end---");
    }
}
