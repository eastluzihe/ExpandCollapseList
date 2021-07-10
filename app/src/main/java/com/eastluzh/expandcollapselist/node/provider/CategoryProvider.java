package com.eastluzh.expandcollapselist.node.provider;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import androidx.core.view.ViewCompat;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.eastluzh.expandcollapselist.R;
import com.eastluzh.expandcollapselist.node.entity.CategoryBean;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CategoryProvider extends BaseNodeProvider {
    public static final int EXPAND_COLLAPSE_PAYLOAD = 110;

    @Override
    public int getItemViewType() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_category_parent;
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @Nullable BaseNode data) {
        CategoryBean entity = (CategoryBean) data;
        helper.setText(R.id.tv_name, entity.getName());
        setArrowSpin(helper, data, false);
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
//        getAdapter().expandOrCollapse(position);
        // 这里使用payload进行增量刷新（避免整个item刷新导致的闪烁，不自然）
        getAdapter().expandOrCollapse(position, true, true, EXPAND_COLLAPSE_PAYLOAD);
    }

    @Override
    public void convert(@NotNull BaseViewHolder helper, @NotNull BaseNode data, @NotNull List<?> payloads) {
        for (Object payload : payloads) {
            if (payload instanceof Integer && (int) payload == EXPAND_COLLAPSE_PAYLOAD) {
                // 增量刷新，使用动画变化箭头
                setArrowSpin(helper, data, true);
            }
        }
    }

    private void setArrowSpin(BaseViewHolder helper, BaseNode data, boolean isAnimate) {
        CategoryBean entity = (CategoryBean) data;
        ImageView ivArrow = helper.getView(R.id.iv_arrow);
        View line = helper.getView(R.id.line);
        if (entity.isExpanded()) {
            line.setVisibility(View.GONE);
            if (isAnimate) {
                ViewCompat.animate(ivArrow).setDuration(200)
                        .setInterpolator(new DecelerateInterpolator())
                        .rotation(-180f)
                        .start();
            } else {
                ivArrow.setRotation(-180f);
            }
        } else {
            line.setVisibility(View.VISIBLE);
            if (isAnimate) {
                ViewCompat.animate(ivArrow).setDuration(200)
                        .setInterpolator(new DecelerateInterpolator())
                        .rotation(0f)
                        .start();
            } else {
                ivArrow.setRotation(0f);
            }
        }
    }

}
