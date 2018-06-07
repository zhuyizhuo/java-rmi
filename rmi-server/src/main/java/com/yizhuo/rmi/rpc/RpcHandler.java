package com.yizhuo.rmi.rpc;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class RpcHandler implements Runnable {

    private Socket socket;
    private Object service;

    public RpcHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            RpcRequest request = (RpcRequest)objectInputStream.readObject();
            Object invoke = invoke(request);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(invoke);

            inputStream.close();
            objectInputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Object invoke(RpcRequest request) {
        try {
            Object[] params = request.getParams();
            Class<?>[] types = new Class[params.length];
            for (int i = 0; i < params.length ;i++){
                types[i] = params[i].getClass();
            }
            Method method = service.getClass().getMethod(request.getMethodName(), types);
            Object invoke = method.invoke(service, params);
            return invoke;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
