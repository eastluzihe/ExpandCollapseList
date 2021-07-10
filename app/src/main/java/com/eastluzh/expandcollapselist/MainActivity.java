package com.eastluzh.expandcollapselist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.blankj.utilcode.util.ActivityUtils;
import com.eastluzh.expandcollapselist.multi.activity.MultiCategoryActivity;
import com.eastluzh.expandcollapselist.node.activity.CategoryActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //BaseNodeAdapter
        findViewById(R.id.btn_brvah).setOnClickListener(v -> {
            ActivityUtils.startActivity(CategoryActivity.class);
        });
        //BaseMultiItemQuickAdapter
        findViewById(R.id.btn_brvah1).setOnClickListener(v -> {
            ActivityUtils.startActivity(MultiCategoryActivity.class);
        });
    }
}