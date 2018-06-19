package com.yizhuo.rmi.zk.loadbalance;

import java.util.List;
import java.util.Random;

/**
 * Created by yizhuo on 2018/6/19.
 */
public class RandomLoadbalanceImpl extends AbstractLoadBalance {

    @Override
    protected String doSelect(List<String> repo) {
        int size = repo.size();
        Random random = new Random();
        return repo.get(random.nextInt(size));
    }
}
