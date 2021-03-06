package com.yizhuo.rmi.zk;

import com.yizhuo.rmi.zk.registry.IRegistry;
import com.yizhuo.rmi.zk.registry.RegistryImpl;

import java.io.IOException;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class ServerDemo {

    public static void main(String[] args) throws IOException {
        IRegistry registry = new RegistryImpl();
        RpcServer rpcServer = new RpcServer(registry,"127.0.0.1:8080");
        IHelloService helloService = new HelloServiceImpl();
        IHelloService helloService2 = new HelloServiceVersionImpl();
        rpcServer.bind(helloService,helloService2);
        rpcServer.publisher();
        System.out.println("服务启动成功");

        System.in.read();
    }
}
