package com.yizhuo.rmi.rpc;

import java.io.Serializable;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class RpcRequest implements Serializable{

    private static final long serialVersionUID = 374794935988089062L;
    private String methodName;
    private Object[] params;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

}
