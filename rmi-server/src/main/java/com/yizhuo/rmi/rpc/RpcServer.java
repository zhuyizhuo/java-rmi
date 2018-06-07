package com.yizhuo.rmi.rpc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class RpcServer {

    private static final Executor executor = Executors.newCachedThreadPool();

    public void publisher(final Object service,int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true){
                Socket accept = serverSocket.accept();
                executor.execute(new RpcHandler(accept,service));
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
