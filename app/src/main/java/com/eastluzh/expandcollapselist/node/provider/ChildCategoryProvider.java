package com.eastluzh.expandcollapselist.node.provider;

import android.view.View;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.eastluzh.expandcollapselist.R;
import com.eastluzh.expandcollapselist.node.entity.ChildCategoryBean;
import com.qmuiteam.qmui.layout.QMUILinearLayout;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChildCategoryProvider extends BaseNodeProvider {

    private ChildCategoryBean mSelectBean;

    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_category_child;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @Nullable BaseNode data) {
        if (data == null) {
            return;
        }
        ChildCategoryBean entity = (ChildCategoryBean) data;
        QMUILinearLayout ll_item = helper.getView(R.id.ll_item);
        TextView tv_name = helper.getView(R.id.tv_name);
        tv_name.setText(entity.getName());
        if (mSelectBean != null && mSelectBean.equals(entity)) {
            ll_item.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shape_border_ff3364_r22));
            tv_name.setTextColor(ContextCompat.getColor(getContext(), R.color.color_ff3364));
        } else {
            ll_item.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.shape_border_ffffff_r22));
            tv_name.setTextColor(ContextCompat.getColor(getContext(), R.color.color_333333));
        }
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        super.onClick(helper, view, data, position);
        ChildCategoryBean entity = (ChildCategoryBean) data;
        mSelectBean = entity;
        getAdapter().notifyDataSetChanged();
    }

}
