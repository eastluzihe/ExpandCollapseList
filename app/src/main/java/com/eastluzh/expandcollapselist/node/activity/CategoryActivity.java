package com.eastluzh.expandcollapselist.node.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.eastluzh.expandcollapselist.R;
import com.eastluzh.expandcollapselist.node.adapter.CategoryAdapter;
import com.eastluzh.expandcollapselist.node.entity.CategoryBean;
import com.eastluzh.expandcollapselist.node.entity.ChildCategoryBean;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    private CategoryAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        mRecyclerView = findViewById(R.id.rv_view);
        mAdapter = new CategoryAdapter();
        mAdapter.setList(getEntity());
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<BaseNode> getEntity() {
        List<BaseNode> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            //Item Node
            ChildCategoryBean itemEntity1 = new ChildCategoryBean(1 + 10 * i, "一级 " + i + " - 二级舞蹈 0");
            ChildCategoryBean itemEntity2 = new ChildCategoryBean(2 + 10 * i, "一级 " + i + " - 二级舞蹈 1");
            ChildCategoryBean itemEntity3 = new ChildCategoryBean(3 + 10 * i, "一级 " + i + " - 二级舞蹈 2");
            ChildCategoryBean itemEntity4 = new ChildCategoryBean(4 + 10 * i, "一级 " + i + " - 二级舞蹈 3");
            ChildCategoryBean itemEntity5 = new ChildCategoryBean(5 + 10 * i, "一级 " + i + " - 二级舞蹈 4");
            List<BaseNode> items = new ArrayList<>();
            items.add(itemEntity1);
            items.add(itemEntity2);
            items.add(itemEntity3);
            items.add(itemEntity4);
            items.add(itemEntity5);

            // Root Node
            CategoryBean entity = new CategoryBean(items, i, "一级舞蹈" + i);
            //默认不展开
            entity.setExpanded(false);
            list.add(entity);
        }
        return list;
    }

}
