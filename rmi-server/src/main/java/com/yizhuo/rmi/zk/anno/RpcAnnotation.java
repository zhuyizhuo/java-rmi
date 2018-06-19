package com.yizhuo.rmi.zk.anno;

import java.lang.annotation.*;

/**
 * Created by yizhuo on 2018/6/19.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcAnnotation {

    Class<?> value();

}
