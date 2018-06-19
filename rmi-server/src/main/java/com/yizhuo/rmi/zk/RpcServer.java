package com.yizhuo.rmi.zk;

import com.yizhuo.rmi.zk.anno.RpcAnnotation;
import com.yizhuo.rmi.zk.registry.IRegistry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class RpcServer {

    private static final Executor executor = Executors.newCachedThreadPool();

    private IRegistry registry;
    private String serviceAddress;
    private Map<String,Object> handlerMap = new HashMap<String,Object>();

    public RpcServer(IRegistry registry, String serviceAddress) {
        this.registry = registry;
        this.serviceAddress = serviceAddress;
    }

    public void bind(Object... services) {
        for (Object service : services) {
            RpcAnnotation annotation = service.getClass().getAnnotation(RpcAnnotation.class);
            String name = annotation.value().getName();
            handlerMap.put(name, service);
        }
    }

    public void publisher() {
        ServerSocket serverSocket = null;
        try {
            String[] address = serviceAddress.split(":");
            serverSocket = new ServerSocket(Integer.parseInt(address[1]));

            for (String name : handlerMap.keySet()) {
                registry.registry(serviceAddress, name);
                System.out.println("注册服务成功," + serviceAddress + "->" + name);
            }

            while (true){
                Socket accept = serverSocket.accept();
                executor.execute(new RpcHandler(accept,handlerMap));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
