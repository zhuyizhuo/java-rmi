package com.yizhuo.rmi.zk;

import com.yizhuo.rmi.zk.anno.RpcAnnotation;

/**
 * Created by yizhuo on 2018/6/5.
 */
@RpcAnnotation(value = IHelloService.class,version = "2.0")
public class HelloServiceVersionImpl implements IHelloService {
    @Override
    public String sayHello(String msg) {
        return "I'm version 2.0,hello," + msg;
    }
}
