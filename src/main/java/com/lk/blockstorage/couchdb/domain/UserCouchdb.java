package com.lk.blockstorage.couchdb.domain;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

/**
 * @Description 用户实体类.
 * @Date 2020/7/2
 * @Author lk
 */
@Document
public class UserCouchdb {

    /**
     * id.
     **/
    @Id
    private String id;

    /**
     * 姓.
     **/
    @Field
    private String lastName;

    /**
     * 性别.
     * 0:男, 1:女
     **/
    @Field
    private int sex;

    /**
     * 年龄.
     **/
    @Field
    private int age;

    /**
     * 获取id.
     *
     * @return id
     **/
    public String getId() {
        return id;
    }

    /**
     * 设置id.
     *
     * @Param the id to set
     **/
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * 获取lastName.
     *
     * @return lastName
     **/
    public String getLastName() {
        return lastName;
    }

    /**
     * 设置lastName.
     *
     * @Param the lastName to set
     **/
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * 获取sex.
     *
     * @return sex
     **/
    public int getSex() {
        return sex;
    }

    /**
     * 设置sex.
     *
     * @Param the sex to set
     **/
    public void setSex(final int sex) {
        this.sex = sex;
    }

    /**
     * 获取age.
     *
     * @return age
     **/
    public int getAge() {
        return age;
    }

    /**
     * 设置age.
     *
     * @Param the age to set
     **/
    public void setAge(final int age) {
        this.age = age;
    }
}
