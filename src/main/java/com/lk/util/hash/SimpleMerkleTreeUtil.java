package com.lk.util.hash;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname SimpleMerkleTree
 * @Description 简化的Merkle树根节点哈希值计算
 * @Date 2020/5/26
 * @Author lk
 */
public class SimpleMerkleTreeUtil {

    /**
     * @Description 计算根节点哈希值
     *
     * @Param [hashList] 数据集合
     * @return java.lang.String 根节点hash
     **/
    public static String getTreeRootNodeHash(List<String> hashList) {
        if (hashList == null || hashList.size() == 0) {
            return null;
        }

        while (hashList.size() != 1) {
            hashList = getMerKleNodeList(hashList);
        }

        // 最后只剩下根节点
        return hashList.get(0);
    }

    /**
     * @Description 根据Merkle树思想计算根节点哈希值
     *
     * @Param [contentList] 数据集合
     * @return List<String> 父节点hash集合
     **/
    private static List<String> getMerKleNodeList(final List<String> contentList) {
        List<String> merKleNodeList = new ArrayList<String>();

        if (contentList == null || contentList.size() == 0) {
            return merKleNodeList;
        }

        int index = 0;
        int length = contentList.size();
        while (index < length) {
            // 获取左子节点数据
            String leftNode = contentList.get(index++);
            String rightNode = "";
            if (index < length) {
                rightNode = contentList.get(index++);
            }

            // 计算左右子节点的父节点的哈希值
            String sha2HexValue = SHAUtil.getSHA256BasedHutool(leftNode + rightNode);
            merKleNodeList.add(sha2HexValue);
        }
        return merKleNodeList;
    }
}
