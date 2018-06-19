package com.yizhuo.rmi.zk.loadbalance;

import java.util.List;

/**
 * Created by yizhuo on 2018/6/19.
 */
public abstract class AbstractLoadBalance implements ILoadbalance{

    public String selectHost(List<String> repo) {
        if (repo == null || repo.size() ==0){
            return null;
        }
        if (repo.size() == 1){
            return repo.get(0);
        }
        return doSelect(repo);
    }

    protected abstract String doSelect(List<String> repo);
}
