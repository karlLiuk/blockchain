package com.lk.alliance.domain.model;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description 联盟成员表.
 * @Date 2020/7/7
 * @Author lk
 */
@Entity
@Table(name = "tb_alliance")
public class Alliance {

    /**
     * 主键.
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 联盟节点id.
     **/
    private String allianceId;

    /**
     * 联盟节点名称.
     **/
    private String allianceName;

    /**
     * 联盟节点IP白名单.
     **/
    private String allianceIp;

    /**
     * 联盟节点信息创建时间.
     **/
    private Date createTime;

    /**
     * 联盟节点信息更新时间.
     **/
    private Date updateTime;

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
     * 获取allianceId.
     *
     * @return allianceId
     **/
    public String getAllianceId() {
        return allianceId;
    }

    /**
     * 设置allianceId.
     *
     * @Param the allianceId to set
     **/
    public void setAllianceId(final String allianceId) {
        this.allianceId = allianceId;
    }

    /**
     * 获取allianceName.
     *
     * @return allianceName
     **/
    public String getAllianceName() {
        return allianceName;
    }

    /**
     * 设置allianceName.
     *
     * @Param the allianceName to set
     **/
    public void setAllianceName(final String allianceName) {
        this.allianceName = allianceName;
    }

    /**
     * 获取allianceIp.
     *
     * @return allianceIp
     **/
    public String getAllianceIp() {
        return allianceIp;
    }

    /**
     * 设置allianceIp.
     *
     * @Param the allianceIp to set
     **/
    public void setAllianceIp(final String allianceIp) {
        this.allianceIp = allianceIp;
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
     * 获取updateTime.
     *
     * @return updateTime
     **/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置updateTime.
     *
     * @Param the updateTime to set
     **/
    public void setUpdateTime(final Date updateTime) {
        this.updateTime = updateTime;
    }
}
