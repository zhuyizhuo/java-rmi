package com.yizhuo.rmi.rpc;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class ClientDemo {

    public static void main(String[] args) {
        IHelloService helloService = RpcProxy.getInstance(IHelloService.class,"localhost",8888);
        System.out.println(helloService.sayHello("yizhuo"));
    }
}
