package com.lk.pbft.enums;

/**
 * @Classname VoteEnum
 * @Description PBFT投票枚举类
 * @Date 2020/6/11
 * @Author lk
 */
public enum VoteEnum {
    /**
     * pre-prepare阶段.
     **/
    PREPREPARE("节点将自己生成Block", 100),

    /**
     * prepare阶段.
     **/
    PREPARE("节点收到请求生成Block的消息，进入准备状态，并对外广播该状态", 200),

    /**
     * commit阶段.
     **/
    COMMIT("每个节点收到超过2f+1个不同节点的commit消息后，" +
            "则认为该区块已经达成一致，" +
            "即进入Commit状态，并将其持久化到区块链数据库中", 400);

    /**
     * 投票情况描述.
     **/
    private String msg;

    /**
     * 投票情况状态码.
     **/
    private int code;

    /**
     * @Description 带参构造函数.
     *
     * @Param msg 投票情况描述
     * @Param code 投票情况状态码
     **/
    VoteEnum(final String msg, final int code) {
    }

    /**
     * 返回msg.
     *
     * @return msg
     **/
    public String getMsg() {
        return msg;
    }

    /**
     * 设置msg.
     *
     * @Param the msg to set
     **/
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 返回code.
     *
     * @return code
     **/
    public int getCode() {
        return code;
    }

    /**
     * 设置code.
     *
     * @Param the code to set
     **/
    public void setCode(int code) {
        this.code = code;
    }
}
