package com.yizhuo.rmi.rpc;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class ServerDemo {

    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.publisher(new HelloServiceImpl(),8888);
        System.out.println("服务启动成功");
    }
}
