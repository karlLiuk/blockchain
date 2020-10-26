package com.lk.blockstorage.rocksdb.dao;

import com.google.common.base.Strings;
import org.rocksdb.RocksDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @Description RocksdbDao工具类
 * @Date 2020/7/2
 * @Author lk
 */
public class RocksdbDao {

    /**
     * LOG.
     **/
    private static final Logger LOG = LoggerFactory.getLogger(RocksdbDao.class);

    /*
     * rocksDB.
     **/
    @Resource
    private RocksDB rocksDB;

    private static final String CHARSET = "utf-8";

    /**
     * 保存或更新数据.
     * @param key 键
     * @param value 值
     * @return void
     **/
    public void put(final String key, final String value) {
        if (Strings.isNullOrEmpty(key) || Strings.isNullOrEmpty(value)) {
            return;
        }
        try {
            rocksDB.put(key.getBytes(CHARSET), value.getBytes(CHARSET));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取的值.
     * @param key 键
     * @return java.lang.String 对应的值
     **/
    public String get(final String key) {
        if (Strings.isNullOrEmpty(key)) {
            return null;
        }
        try {
            final byte[] bytes = rocksDB.get(key.getBytes(CHARSET));
            if (bytes != null) {
                return new String(bytes);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除数据.
     * @param key 键
     * @return void
     **/
    public void delete(final String key) {
        if (Strings.isNullOrEmpty(key)) {
            return;
        }
        try {
            rocksDB.delete(rocksDB.get(key.getBytes(CHARSET)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
