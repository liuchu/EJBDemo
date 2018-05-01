package com.chuliu.ejb.rmi;

import java.rmi.Naming;

/**
 * Created by chuliu on 2018/4/29.
 */
public class MyClient {

    public static void main(String[] args) {

        try{
            Adder stub=(Adder) Naming.lookup("rmi://localhost:5555/sonoo");
            System.out.println("Call remote AdderRemote add(a,b) method, result of add(34,4) is");
            System.out.println(stub.add(34,4));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
