package com.eastluzh.expandcollapselist.node.entity;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CategoryBean extends BaseExpandNode {

    private List<BaseNode> childNode;
    private int id;
    private String name;

    public CategoryBean(List<BaseNode> childNode, int id, String name) {
        this.childNode = childNode;
        this.id = id;
        this.name = name;
    }

    public CategoryBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setChildNode(List<BaseNode> childNode) {
        this.childNode = childNode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@link BaseNode}
     * 重写此方法，获取子节点。如果没有子节点，返回 null 或者 空数组
     *
     * @return child nodes
     */
    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }

}
