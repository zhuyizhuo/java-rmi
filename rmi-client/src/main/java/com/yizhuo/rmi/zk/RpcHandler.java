package com.yizhuo.rmi.zk;

import com.yizhuo.rmi.zk.discover.IServerDiscovery;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class RpcHandler implements InvocationHandler {

    private IServerDiscovery serverDiscovery;

    public RpcHandler(IServerDiscovery serverDiscovery) {
        this.serverDiscovery = serverDiscovery;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest();
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParams(args);

        String discover = serverDiscovery.discover(request.getClassName());
        String[] split = discover.split(":");
        RpcTrans rpcTrans = new RpcTrans(split[0],Integer.parseInt(split[1]));
        return  rpcTrans.trans(request);
    }
}
