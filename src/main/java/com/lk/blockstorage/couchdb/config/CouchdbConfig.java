package com.lk.blockstorage.couchdb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Arrays;
import java.util.List;

/**
 * @Description CouchDB的配置类.
 * @Date 2020/7/2
 * @Author lk
 */
@Configuration
@EnableCouchbaseRepositories
public class CouchdbConfig extends AbstractCouchbaseConfiguration {

    /**
     * 启动时建立配置的集群的连接，客户端将发现所有节点并保持集群映射最新.
     * @return 集群
     **/
    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList("host1", "host2");
    }

    /**
     * 配置主数据桶的名称.
     * @return 名称
     **/
    @Override
    protected String getBucketName() {
        return "default";
    }

    /**
     * 配置主数据桶的密码.
     * @return 密码
     **/
    @Override
    protected String getBucketPassword() {
        return "";
    }
}
