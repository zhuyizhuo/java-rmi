package com.yizhuo.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class HelloServiceImpl extends UnicastRemoteObject implements  IHelloService{

    protected HelloServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String sayHello(String msg) throws RemoteException {
        return "hello," + msg;
    }
}
