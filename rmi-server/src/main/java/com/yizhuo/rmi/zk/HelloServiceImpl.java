package com.yizhuo.rmi.zk;

import com.yizhuo.rmi.zk.anno.RpcAnnotation;

/**
 * Created by yizhuo on 2018/6/5.
 */
@RpcAnnotation(value = IHelloService.class)
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(String msg) {
        return "hello," + msg;
    }
}
