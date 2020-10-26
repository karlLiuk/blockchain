package com.lk.manage.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description 申请加入的成员信息.
 * @Date 2020/7/2
 * @Author lk
 */
@Entity
@Table(name = "tb_user_tojoin")
public class UserToJoin implements Serializable {

    /**
     * 主键.
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 机构名称.
     **/
    private String orgName;

    /**
     * 机构联系方式.
     **/
    private String orgPhone;

    /**
     * 机构联系人.
     **/
    private String orgRepresent;

    /**
     * 申请时间.
     **/
    private Date createTime;

    /**
     * 申请结果(0-未处理，1-已处理).
     **/
    private int result;

    /**
     * 获取id.
     *
     * @return id
     **/
    public Long getId() {
        return id;
    }

    /**
     * 设置id.
     *
     * @Param the id to set
     **/
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * 获取orgName.
     *
     * @return orgName
     **/
    public String getOrgName() {
        return orgName;
    }

    /**
     * 设置orgName.
     *
     * @Param the orgName to set
     **/
    public void setOrgName(final String orgName) {
        this.orgName = orgName;
    }

    /**
     * 获取orgPhone.
     *
     * @return orgPhone
     **/
    public String getOrgPhone() {
        return orgPhone;
    }

    /**
     * 设置orgPhone.
     *
     * @Param the orgPhone to set
     **/
    public void setOrgPhone(final String orgPhone) {
        this.orgPhone = orgPhone;
    }

    /**
     * 获取orgRepresent.
     *
     * @return orgRepresent
     **/
    public String getOrgRepresent() {
        return orgRepresent;
    }

    /**
     * 设置orgRepresent.
     *
     * @Param the orgRepresent to set
     **/
    public void setOrgRepresent(final String orgRepresent) {
        this.orgRepresent = orgRepresent;
    }

    /**
     * 获取createTime.
     *
     * @return createTime
     **/
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置createTime.
     *
     * @Param the createTime to set
     **/
    public void setCreateTime(final Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取result.
     *
     * @return result
     **/
    public int getResult() {
        return result;
    }

    /**
     * 设置result.
     *
     * @Param the result to set
     **/
    public void setResult(final int result) {
        this.result = result;
    }
}
