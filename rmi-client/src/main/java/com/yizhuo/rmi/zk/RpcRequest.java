package com.yizhuo.rmi.zk;

import java.io.Serializable;

/**
 * Created by yizhuo on 2018/6/5.
 */
public class RpcRequest implements Serializable{

    private static final long serialVersionUID = 374794935988089062L;
    private String className;
    private String methodName;
    private Object[] params;
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
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
