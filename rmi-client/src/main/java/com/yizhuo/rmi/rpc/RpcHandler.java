package com.yizhuo.rmi.rpc;

import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class RpcHandler implements InvocationHandler {

    private String host;
    private int port;

    public RpcHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest request = new RpcRequest();
        request.setMethodName(method.getName());
        request.setParams(args);
        RpcTrans rpcTrans = new RpcTrans(host,port);
        return  rpcTrans.trans(request);
    }
}
