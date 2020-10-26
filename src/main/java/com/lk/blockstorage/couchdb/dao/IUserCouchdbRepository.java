package com.lk.blockstorage.couchdb.dao;

import com.lk.blockstorage.couchdb.domain.UserCouchdb;
import org.springframework.data.couchbase.core.query.Dimensional;
import org.springframework.data.couchbase.core.query.View;
import org.springframework.data.geo.Box;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Description CouchDB用户的数据层
 * @Date 2020/7/2
 * @Author lk
 */
public interface IUserCouchdbRepository extends CrudRepository<UserCouchdb, String> {

    /**
     * 根据姓和年龄查询用户.
     * @param lastName 姓
     * @param minAge 年龄下限
     * @param maxAge 年龄上限
     * @return 用户集合
     **/
    List<UserCouchdb> findByLastNameAndAgeBetweenAnd(String lastName, int minAge, int maxAge);

    /**
     * 根据姓查询用户.
     * @param lastName 姓
     * @return 用户集合
     **/
    @View(designDocument = "user", viewName = "byName")
    List<UserCouchdb> findByLastName(String lastName);

    /**
     * 根据位置查询用户.
     * @param cityBoundingBox
     * @return 用户集合
     **/
    @Dimensional(designDocument = "userGeo", spatialViewName = "byLocation")
    List<UserCouchdb> findByLocationWithin(Box cityBoundingBox);
}
