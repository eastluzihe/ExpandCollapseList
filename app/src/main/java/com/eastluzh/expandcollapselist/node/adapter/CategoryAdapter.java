package com.eastluzh.expandcollapselist.node.adapter;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.eastluzh.expandcollapselist.node.entity.CategoryBean;
import com.eastluzh.expandcollapselist.node.entity.ChildCategoryBean;
import com.eastluzh.expandcollapselist.node.provider.CategoryProvider;
import com.eastluzh.expandcollapselist.node.provider.ChildCategoryProvider;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CategoryAdapter extends BaseNodeAdapter {

    public CategoryAdapter() {
        super();
        addFullSpanNodeProvider(new CategoryProvider());
        addNodeProvider(new ChildCategoryProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> data, int position) {
        BaseNode node = data.get(position);
        if (node instanceof CategoryBean) {
            return 0;
        } else if (node instanceof ChildCategoryBean) {
            return 1;
        }
        return -1;
    }
}
