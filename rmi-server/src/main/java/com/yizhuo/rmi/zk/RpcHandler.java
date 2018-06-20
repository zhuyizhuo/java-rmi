package com.yizhuo.rmi.zk;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class RpcHandler implements Runnable {

    private Socket socket;
    private Map<String,Object> handlerMap = new HashMap<String,Object>();

    public RpcHandler(Socket socket, Map<String,Object> handlerMap) {
        this.socket = socket;
        this.handlerMap = handlerMap;
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
            String version = request.getVersion();
            String serviceName = request.getClassName();
            if (version!= null && !version.equals("")){
                serviceName = serviceName + "-" + version;
            }
            Object service = handlerMap.get(serviceName);
            Method method = service.getClass().getMethod(request.getMethodName(), types);
            Object invoke = method.invoke(service, params);
            return invoke;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
