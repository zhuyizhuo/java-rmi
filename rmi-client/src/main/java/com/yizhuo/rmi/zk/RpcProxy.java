package com.yizhuo.rmi.zk;

import com.yizhuo.rmi.zk.discover.IServerDiscovery;

import java.lang.reflect.Proxy;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class RpcProxy {

    private IServerDiscovery serverDiscovery;

    public RpcProxy(IServerDiscovery serverDiscovery) {
        this.serverDiscovery = serverDiscovery;
    }

    public <T> T getInstance(Class<T> clazz){
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new RpcHandler(serverDiscovery));
    }
}
