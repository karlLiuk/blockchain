package com.lk.blockstorage.leveldb.dao;

import com.google.common.base.Strings;
import org.iq80.leveldb.DB;
import org.iq80.leveldb.DBFactory;
import org.iq80.leveldb.DBIterator;
import org.iq80.leveldb.Options;
import org.iq80.leveldb.impl.Iq80DBFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Map;

/**
 * @Description LeveldbDao工具类
 * @Date 2020/7/1
 * @Author lk
 */
public class LeveldbDao {

    /**
     * LOG.
     **/
    private static final Logger LOG = LoggerFactory.getLogger(LeveldbDao.class);

    /**
     * LevelDB的DB对象.
     **/
    private DB db;

    /**
     * 加载时初始化.
     * @return void
     **/
    @PostConstruct
    public void init() {
        try {
            final DBFactory factory = new Iq80DBFactory();
            final Options options = new Options();
            options.createIfMissing(true);
            // 配置LevelDB存储目录
            final String path = ".//leveldb";
            db = factory.open(new File(path), options);
            db.put("hello world".getBytes(), "tal tech".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        db.put(Iq80DBFactory.bytes(key), Iq80DBFactory.bytes(value));
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

        final byte[] valueBytes = db.get(Iq80DBFactory.bytes(key));

        return Iq80DBFactory.asString(valueBytes);
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

        db.delete(Iq80DBFactory.bytes(key));
    }

    /**
     * 遍历所有入库数据.
     **/
    public void traverseAllDatas() {
        // 迭代器
        final DBIterator iterator = db.iterator();
        while (iterator.hasNext()) {
            final Map.Entry<byte[], byte[]> next =
                    iterator.next();
            final String key = Iq80DBFactory.asString(next.getKey());
            final String value = Iq80DBFactory.asString(next.getValue());
            LOG.info("遍历所有数据，levelDb key=" + key + ";value=" + value);
        }
    }
}
