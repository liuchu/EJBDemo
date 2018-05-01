package com.chuliu.ejb.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by chuliu on 2018/4/29.
 */
public interface Adder extends Remote {

    public int add(int x,int y)throws RemoteException;

}
