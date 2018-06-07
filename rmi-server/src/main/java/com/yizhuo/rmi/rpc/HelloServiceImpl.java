package com.yizhuo.rmi.rpc;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String msg) {
        return "hello," + msg;
    }
}
