package com.yizhuo.rmi.zk;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class RpcTrans {
    private String host;
    private int port;

    public RpcTrans(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object trans(RpcRequest request){
        try{
            Socket socket = new Socket(host,port);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return objectInputStream.readObject();
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
