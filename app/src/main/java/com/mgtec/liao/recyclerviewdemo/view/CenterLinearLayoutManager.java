package com.mgtec.liao.recyclerviewdemo.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CenterLinearLayoutManager extends LinearLayoutManager {
    public CenterLinearLayoutManager(Context context) {
        super(context);
    }

    public CenterLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CenterLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private View mSelectedView;
    private boolean isFirst = true;
    int visibleSize = 0;


    @Override
    public boolean requestChildRectangleOnScreen(final RecyclerView parent, View child, Rect rect, boolean immediate, boolean focusedChildVisible) {
        final int childLeft = child.getLeft() + rect.left;
        final int childTop = child.getTop() + rect.top;
        final int childRight = childLeft + rect.width();
        final int childBottom = childTop + rect.height();
        int centerB;
        int centerT;
        mSelectedView = child;
        if (isFirst) {
            visibleSize = findLastVisibleItemPosition();
            isFirst = false;
        }
        int itemCount = getItemCount();
        int curPosition = getPosition(child);
        int centerP = findFirstVisibleItemPosition() + visibleSize / 2;
        switch (getOrientation()) {
            case HORIZONTAL:
                centerB = child.getWidth() * (visibleSize / 2);
                centerT = child.getWidth() * (visibleSize / 2 + 1);

                if (curPosition > centerP && findLastVisibleItemPosition() < itemCount - 1 && childLeft > centerB) {
                    parent.smoothScrollBy(childLeft - centerB, 0);
                    return true;
                } else if (curPosition < centerP && findFirstVisibleItemPosition() > 0 && childRight < centerT) {
                    parent.smoothScrollBy(childRight - centerT, 0);
                    return true;
                }
                break;
            case VERTICAL:
                centerB = child.getHeight() * (visibleSize / 2 + 1);
                centerT = child.getHeight() * (visibleSize / 2);
                if (curPosition > centerP && findLastVisibleItemPosition() < itemCount - 1 && childBottom > centerB) {
                    parent.smoothScrollBy(0, childBottom - centerB);
                    return true;
                } else if (curPosition < centerP && findFirstVisibleItemPosition() > 0 && childTop < centerT) {
                    parent.smoothScrollBy(0, childTop - centerT);
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }

    /**
     * 焦点搜索失败处理.
     */
    @Override
    public View onFocusSearchFailed(View focused, int focusDirection, RecyclerView.Recycler recycler,
                                    RecyclerView.State state) {
        return focused;
    }

    public View getSelectedView() {
        return mSelectedView;
    }

    @Override
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }
}
