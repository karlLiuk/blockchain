package com.lk.util;

import java.util.Collection;

/**
 * @Classname CollectionUtil
 * @Description 容器工具类
 * @Date 2020/5/28
 * @Author lk
 */
public final class CollectionUtil {

    public static boolean isEmpty(Collection<?> collection) {
        if (collection == null || collection.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
}
