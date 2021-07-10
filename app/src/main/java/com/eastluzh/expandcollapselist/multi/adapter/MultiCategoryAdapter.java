
package com.eastluzh.expandcollapselist.multi.adapter;

import android.graphics.Rect;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.eastluzh.expandcollapselist.R;
import com.eastluzh.expandcollapselist.multi.entity.CategoryBaseItemEntity;
import com.qmuiteam.qmui.layout.QMUILinearLayout;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.eastluzh.expandcollapselist.multi.entity.CategoryBaseItemEntity.PARENT_CLASSIFY;
import static com.eastluzh.expandcollapselist.multi.entity.CategoryBaseItemEntity.SON_CLASSIFY;


public class MultiCategoryAdapter extends BaseMultiItemQuickAdapter<CategoryBaseItemEntity, BaseViewHolder> {
    /**
     * 默认选中的item的Tag
     */
    private String mCheckTag = "";

    /**
     * 设置当前选择Tag
     *
     * @param selectTag
     */
    public void setSelectTag(String selectTag) {
        this.mCheckTag = selectTag;
        notifyDataSetChanged();
    }

    public MultiCategoryAdapter(@Nullable List<CategoryBaseItemEntity> data) {
        super(data);
        addItemType(PARENT_CLASSIFY, R.layout.item_category_parent);
        addItemType(SON_CLASSIFY, R.layout.item_category_child);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder holder, CategoryBaseItemEntity item) {
        switch (holder.getItemViewType()) {
            case PARENT_CLASSIFY:
                ImageView iv_arrow = holder.getView(R.id.iv_arrow);
                setArrowSpin(iv_arrow, item, true);
                iv_arrow.setOnClickListener(v -> {
                    setExpanded(item, holder.getLayoutPosition());
                });
                holder.setText(R.id.tv_name, item.getName())
                        .setGone(R.id.line, item.isExpanded());
                break;
            case SON_CLASSIFY:
                QMUILinearLayout ll_item = holder.getView(R.id.ll_item);
                TextView tv_name = holder.getView(R.id.tv_name);
                holder.setText(R.id.tv_name, item.getName());
                boolean check;
                try {
                    check = mCheckTag.equals(item.getTag());
                } catch (Exception e) {
                    check = false;
                }
                ll_item.setBackground(check ? ContextCompat.getDrawable(getContext(), R.drawable.shape_border_ff3364_r22) : ContextCompat.getDrawable(getContext(), R.drawable.shape_border_ffffff_r22));
                tv_name.setTextColor(check ? ContextCompat.getColor(getContext(), R.color.color_ff3364) : ContextCompat.getColor(getContext(), R.color.color_333333));
                break;
            default:
                break;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        GridLayoutManager manager = (GridLayoutManager) recyclerView.getLayoutManager();
        assert manager != null;
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (getData().size() == 0 || getData().get(position).getLayout() == PARENT_CLASSIFY) {
                    return 3;
                }
                return 1;
            }
        });
    }

    private void setExpanded(CategoryBaseItemEntity item, int pos) {
        if (item.isExpanded()) {
            getData().removeAll(item.getSon());
        } else {
            getData().addAll(pos + 1, item.getSon());
        }
        item.setExpanded(!item.isExpanded());
        notifyDataSetChanged();
    }

    private void setArrowSpin(ImageView iv_arrow, CategoryBaseItemEntity item, boolean isAnimate) {
        if (item.isExpanded()) {
            if (isAnimate) {
                ViewCompat.animate(iv_arrow).setDuration(200)
                        .setInterpolator(new DecelerateInterpolator())
                        .rotation(-180f)
                        .start();
            } else {
                iv_arrow.setRotation(-180f);
            }
        } else {
            if (isAnimate) {
                ViewCompat.animate(iv_arrow).setDuration(200)
                        .setInterpolator(new DecelerateInterpolator())
                        .rotation(0f)
                        .start();
            } else {
                iv_arrow.setRotation(0f);
            }
        }
    }

    public static class CategoryDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            MultiCategoryAdapter adapter = (MultiCategoryAdapter) parent.getAdapter();
            if (adapter.getData().size() == 0) return;
            List<CategoryBaseItemEntity> data = adapter.getData();
            int centerMargin10 = SizeUtils.dp2px(10);
            int centerMargin4 = SizeUtils.dp2px(4);
            int position = parent.getChildAdapterPosition(view);
            int type = data.get(position).getLayout();

            if (SON_CLASSIFY == type) {
                if (data.get(position).getPos() % 3 == 0) {
                    outRect.set(centerMargin10, 0, 0, 0);
                } else if (data.get(position).getPos() % 3 == 2) {
                    outRect.set(0, 0, centerMargin10, 0);
                } else {
                    outRect.set(centerMargin4, 0, centerMargin4, 0);
                }

            }
        }
    }

}
