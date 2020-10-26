package com.lk.domain.merkel;

import com.lk.util.hash.SHAUtil;

/**
 * @Classname TreeNode
 * @Description 树节点
 * @Date 2020/5/27
 * @Author lk
 */
public class TreeNode {
    // 左子节点
    private TreeNode left;
    // 右子节点
    private TreeNode right;
    // 子节点的数据
    private String data;
    // 子节点数据对应的hash
    private String hash;
    // 节点的名称
    private String name;

    public TreeNode () {}

    public TreeNode (String data) {
        this.data = data;
        this.hash = SHAUtil.getSHA256BasedHutool(data);
        this.name = "[节点：" + data + "]";
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
