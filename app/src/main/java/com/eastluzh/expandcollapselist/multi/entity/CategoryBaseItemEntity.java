
package com.eastluzh.expandcollapselist.multi.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class CategoryBaseItemEntity implements MultiItemEntity {
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private int pos;

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public int getItemType() {
        return layout;
    }

    public final static int PARENT_CLASSIFY = 0;
    public final static int SON_CLASSIFY = 1;

    private int layout;

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    private int id;
    private String name;
    private boolean isExpanded;
    private List<CategoryBaseItemEntity> son;

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

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public List<CategoryBaseItemEntity> getSon() {
        return son;
    }

    public void setSon(List<CategoryBaseItemEntity> son) {
        this.son = son;
    }

}
