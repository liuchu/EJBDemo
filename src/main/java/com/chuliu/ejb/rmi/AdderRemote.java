package com.chuliu.ejb.rmi;

import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by chuliu on 2018/4/29.
 */
public class AdderRemote extends UnicastRemoteObject implements Adder{
    /**
     * Creates and exports a new UnicastRemoteObject object using an
     * anonymous port.
     * <p>
     * <p>The object is exported with a server socket
     * created using the {@link RMISocketFactory} class.
     *
     * @throws RemoteException if failed to export object
     * @since JDK1.1
     */
    protected AdderRemote() throws RemoteException {

    }

    public int add(int x, int y) throws RemoteException {
        return x+y;
    }
}
