package com.lk.domain.block;

import java.util.List;

/**
 * @Classname BlockBody
 * @Description 区块体， 存放交易的数组
 * @Date 2020/6/18
 * @Author lk
 */
public class BlockBody {
    /**
     * 交易内容集合.
     **/
    private List<ContentInfo> contentInfos;

    /**
     * 获取contentInfos.
     *
     * @return contentInfos
     **/
    public List<ContentInfo> getContentInfos() {
        return contentInfos;
    }

    /**
     * 设置contentInfos.
     *
     * @Param the contentInfos to set
     **/
    public void setContentInfos(List<ContentInfo> contentInfos) {
        this.contentInfos = contentInfos;
    }
}
