package com.yizhuo.rmi.zk.discover;

import com.yizhuo.rmi.zk.constance.ZkConfig;
import com.yizhuo.rmi.zk.loadbalance.RandomLoadbalanceImpl;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * Created by yizhuo on 2018/6/19.
 */
public class ServerDiscoveryImpl implements IServerDiscovery {

    private CuratorFramework curatorFramework;
    private List<String> list ;

    {
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(ZkConfig.CONNECT_ADDR).
                sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,10)).build();
        curatorFramework.start();
    }

    public String discover(String serviceName) {
        String path = ZkConfig.REGISTRY_PATH + "/" + serviceName;
        try {
            list = curatorFramework.getChildren().forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }

        registeWatcher(path);

        RandomLoadbalanceImpl randomLoadbalance = new RandomLoadbalanceImpl();
        return randomLoadbalance.selectHost(list);
    }

    private void registeWatcher(final String path) {
        PathChildrenCache childrenCache = new PathChildrenCache(curatorFramework,path,true);

        PathChildrenCacheListener cacheListener = new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                list = curatorFramework.getChildren().forPath(path);
            }
        };
        childrenCache.getListenable().addListener(cacheListener);

        try {
            childrenCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
