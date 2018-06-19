package com.yizhuo.rmi.zk;

import com.yizhuo.rmi.zk.discover.ServerDiscoveryImpl;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class ClientDemo {

    public static void main(String[] args) {
        ServerDiscoveryImpl serverDiscovery = new ServerDiscoveryImpl();
        RpcProxy rpcProxy = new RpcProxy(serverDiscovery);
        IHelloService helloService = rpcProxy.getInstance(IHelloService.class);
        System.out.println(helloService.sayHello("yizhuo"));
    }
}
