package com.yizhuo.rmi.zk.registry;

import com.yizhuo.rmi.zk.constance.ZkConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created by yizhuo on 2018/6/19.
 */
public class RegistryImpl implements IRegistry {

    private CuratorFramework curatorFramework;

    {
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(ZkConfig.CONNECT_ADDR).
                sessionTimeoutMs(5000).
                retryPolicy(new ExponentialBackoffRetry(1000,10)).build();
        curatorFramework.start();
    }

    @Override
    public void registry(String address, String serviceName) {
        String servicePath = ZkConfig.REGISTRY_PATH + "/" + serviceName;
        try {
            if(curatorFramework.checkExists().forPath(servicePath) == null){
                curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(servicePath);
            }

            String addressPath = servicePath + "/" + address;
            String s = curatorFramework.create().withMode(CreateMode.EPHEMERAL).forPath(addressPath);
            System.out.println("服务注册成功->" + s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
