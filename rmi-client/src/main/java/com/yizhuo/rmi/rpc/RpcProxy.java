package com.yizhuo.rmi.rpc;

import java.lang.reflect.Proxy;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class RpcProxy {

    @SuppressWarnings("unchecked")
	public static <T> T getInstance(Class<T> clazz,String host,int port){
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new RpcHandler(host,port));
    }
}
