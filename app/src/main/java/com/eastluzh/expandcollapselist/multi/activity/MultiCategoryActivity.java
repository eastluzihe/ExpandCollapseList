package com.eastluzh.expandcollapselist.multi.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.eastluzh.expandcollapselist.R;
import com.eastluzh.expandcollapselist.multi.adapter.MultiCategoryAdapter;
import com.eastluzh.expandcollapselist.multi.entity.CategoryBaseItemEntity;

import java.util.ArrayList;
import java.util.List;

import static com.eastluzh.expandcollapselist.multi.entity.CategoryBaseItemEntity.PARENT_CLASSIFY;
import static com.eastluzh.expandcollapselist.multi.entity.CategoryBaseItemEntity.SON_CLASSIFY;

public class MultiCategoryActivity extends AppCompatActivity {
    private MultiCategoryAdapter mAdapter;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_category);
        mRecyclerView = findViewById(R.id.rv_view);
        mAdapter = new MultiCategoryAdapter(null);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        mRecyclerView.addItemDecoration(new MultiCategoryAdapter.CategoryDecoration());
        mRecyclerView.setAdapter(mAdapter);
        setAdapter();
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                CategoryBaseItemEntity mBean = (CategoryBaseItemEntity) adapter.getData().get(position);
                switch (adapter.getItemViewType(position)) {
                    case PARENT_CLASSIFY:
                        break;
                    case SON_CLASSIFY:
                        mAdapter.setSelectTag(mBean.getTag());
                        break;
                }
            }
        });
    }

    private void setAdapter() {
        List<CategoryBaseItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            CategoryBaseItemEntity parentBean = new CategoryBaseItemEntity();
            parentBean.setLayout(PARENT_CLASSIFY);
            parentBean.setId(i + 1);
            parentBean.setName("音乐一级" + i);
            parentBean.setExpanded(false);
            List<CategoryBaseItemEntity> moreSonList = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                CategoryBaseItemEntity sonBean = new CategoryBaseItemEntity();
                sonBean.setLayout(SON_CLASSIFY);
                sonBean.setId(20 + j);
                sonBean.setName("一级" + i + "音乐二级" + j);
                sonBean.setPos(j);
                sonBean.setTag(parentBean.getName() + "-" + sonBean.getName());
                moreSonList.add(sonBean);
            }
            parentBean.setSon(moreSonList);
            list.add(parentBean);
            mAdapter.setNewInstance(list);
        }
    }

}
