package com.yizhuo.rmi.zk;

import com.yizhuo.rmi.zk.anno.RpcAnnotation;

/**
 * Created by yizhuo on 2018/6/5.
 */
@RpcAnnotation(value = IHelloService.class)
public class HelloServiceClusterImpl implements IHelloService {
    @Override
    public String sayHello(String msg) {
        return "I'm 8081 service. hello," + msg;
    }
}
