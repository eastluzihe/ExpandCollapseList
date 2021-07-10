package com.eastluzh.expandcollapselist.node.entity;


import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class ChildCategoryBean extends BaseNode {

    private int id;
    private String name;

    public ChildCategoryBean(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChildCategoryBean that = (ChildCategoryBean) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
