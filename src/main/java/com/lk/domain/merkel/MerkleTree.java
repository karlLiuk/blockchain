package com.lk.domain.merkel;

import com.lk.util.hash.SHAUtil;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Classname MerkleTree
 * @Description Merkle树
 * @Date 2020/5/27
 * @Author lk
 */
public class MerkleTree {
    // 树节点集合
    private List<TreeNode> list;

    // 根节点
    private TreeNode root;

    public MerkleTree (final List<String> contents) {
        createMerkleTree(contents);
    }

    /**
     * @Description 构造MerkleTree
     *
     * @Param [contents] 数据集合
     * @return void
     **/
    private void createMerkleTree(final List<String> contents) {
        if (contents == null || contents.size() == 0) {
            return;
        }

        // 初始化
        list = new ArrayList<>();

        // 根据数据创建叶子节点
        final List<TreeNode> leafList = createLeafList(contents);
        list.addAll(leafList);

        // 创建父节点
        final List<TreeNode> parents = createParentList(leafList);
        list.addAll(parents);
    }

    /**
     * @Description 根据子节点列表创建父节点列表
     *
     * @Param [leafList] 子节点列表
     * @return java.util.List<com.lk.domain.merkel.TreeNode> 父节点列表
     **/
    private List<TreeNode> createParentList(final List<TreeNode> leafList) {
        List<TreeNode> parents = new ArrayList<TreeNode>();

        if (CollectionUtils.isEmpty(leafList)) {
            return parents;
        }

        final int leafLength = leafList.size();
        for (int i = 0; i < leafLength; i += 2) {
            TreeNode parent = createParenNode(leafList.get(i), leafList.get(i + 1));
            parents.add(parent);
        }

        // 奇数个节点时
        if (leafLength % 2 != 0) {
            TreeNode parent = createParenNode(leafList.get(leafLength - 1), null);
            parents.add(parent);
        }
        return parents;
    }

    /**
     * @Description 创建父节点
     *
     * @Param [left, right] 左右子节点
     * @return com.lk.domain.merkel.TreeNode 父节点
     **/
    private TreeNode createParenNode(TreeNode left, TreeNode right) {
        TreeNode parent = new TreeNode();
        parent.setLeft(left);
        parent.setRight(right);

        // 如果右节点为空
        String hash  = left.getHash();
        if (right != null) {
            hash = SHAUtil.getSHA256BasedHutool(left.getHash() + right.getHash());
            parent.setName("(" + left.getName() + "和" + right.getName() + "的父节点)");
        } else {
            parent.setName("(继承节点{" + left.getName() + "}成为父节点)");
        }
        parent.setData(hash);
        parent.setHash(hash);

        return parent;
    }

    /**
     * @Description 根据数据创建叶子节点
     * 
     * @Param [contents] 数据集合
     * @return java.util.List<com.lk.domain.merkel.TreeNode> 叶子节点集合
     **/
    private List<TreeNode> createLeafList(final List<String> contents) {
        List<TreeNode> leafList = new ArrayList<TreeNode>();

        if (contents == null || contents.size() == 0) {
            return leafList;
        }

        contents.forEach(content -> {
            leafList.add(new TreeNode(content));
        });
        return leafList;
    }

    /**
     * @Description 遍历树
     *
     * @Param []
     * @return void
     **/
    public void traverseTreeNodes() {
        Collections.reverse(list);
        TreeNode root = list.get(0);
        traverseTreeNodes(root);
    }

    private void traverseTreeNodes(TreeNode node) {
        System.out.println(node.getName());
        if (node.getLeft() != null) {
            traverseTreeNodes(node.getLeft());
        }

        if (node.getRight() != null) {
            traverseTreeNodes(node.getRight());
        }
    }

    public List<TreeNode> getList() {
        if (list == null) {
            return list;
        }
        Collections.reverse(list);
        return list;
    }

    public void setList(List<TreeNode> list) {
        this.list = list;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
