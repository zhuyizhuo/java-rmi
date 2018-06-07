package com.yizhuo.rmi;

import com.yizhuo.rmi.IHelloService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class ClientDemo {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        IHelloService helloService = (IHelloService)Naming.lookup("rmi://127.0.0.1/hello");
        System.out.println(helloService.sayHello("yizhuo"));
    }
}
