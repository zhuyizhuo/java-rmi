package com.yizhuo.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by yizhuo on 2018/6/5.
 */
public interface IHelloService extends Remote{

    String sayHello(String msg) throws RemoteException;
}
