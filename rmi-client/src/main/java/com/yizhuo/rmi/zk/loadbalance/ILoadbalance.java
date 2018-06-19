package com.yizhuo.rmi.zk.loadbalance;

import java.util.List;

/**
 * Created by yizhuo on 2018/6/19.
 */
public interface ILoadbalance {

    String selectHost(List<String> repo);
}
