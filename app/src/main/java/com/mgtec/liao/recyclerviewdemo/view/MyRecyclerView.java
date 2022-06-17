package com.mgtec.liao.recyclerviewdemo.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MyRecyclerView extends RecyclerView {

    private static String TAG = MyRecyclerView.class.getName();

    public MyRecyclerView(@NonNull Context context) {
        super(context);
        setAnimation(null);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //实现item滑动居中
    private boolean mSelectItemCenterd = true;
    private int mSelectedItemOffsetStart = 0;
    private int mSelectedItemOffsetEnd = 0;

    final boolean canScrollHorizontal = getLayoutManager().canScrollHorizontally();
    final boolean canScrollVertical = getLayoutManager().canScrollVertically();

    @Override
    public void requestChildFocus(View child, View focused) {
        if (mSelectItemCenterd && child != null ){
            mSelectedItemOffsetStart = !isVerticalScrollBarEnabled() ? (getWidth()-child.getWidth()) : (getHeight() - child.getHeight());
            mSelectedItemOffsetStart /= 2;
            mSelectedItemOffsetEnd = mSelectedItemOffsetStart;
        }
        super.requestChildFocus(child, focused);
    }



    @Override
    public boolean requestChildRectangleOnScreen(View child, Rect rect, boolean immediate) {
        //返回当前视图距离父视图的左边padding
        final int parentLeft = getPaddingLeft();
        //返回当前视图距离父视图的右边padding
        final int parentRight = getWidth() - getPaddingRight();
        //返回当前视图距离父视图上边的padding
        final int parentTop = getPaddingTop();
        //返回当前视图距离父视图下边的padding
        final int parentBottom = getHeight() - getPaddingBottom();


        final int childLeft = child.getLeft() + rect.left;
        final int childTop = child.getTop() + rect.top;

        final int childRight = childLeft + rect.width();
        final int childBottom = childTop + rect.height();

        final int offScreenLeft = Math.min(0, childLeft - parentLeft - mSelectedItemOffsetStart);
        final int offScreenRight = Math.max(0, childRight - parentRight + mSelectedItemOffsetEnd);

        final int offScreenTop = Math.min(0, childTop - parentTop - mSelectedItemOffsetStart);
        final int offScreenBottom = Math.max(0, childBottom - parentBottom + mSelectedItemOffsetEnd);



        // Favor the "start" layout direction over the end when bringing one side or the other
        // of a large rect into view. If we decide to bring in end because start is already
        // visible, limit the scroll such that start won't go out of bounds.
        final int dx;
        if (canScrollHorizontal) {
            if (ViewCompat.getLayoutDirection(this) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                dx = offScreenRight != 0 ? offScreenRight
                        : Math.max(offScreenLeft, childRight - parentRight);
            } else {
                dx = offScreenLeft != 0 ? offScreenLeft
                        : Math.min(childLeft - parentLeft, offScreenRight);
            }
        } else {
            dx = 0;
        }
        // Favor bringing the top into view over the bottom. If top is already visible and
        // we should scroll to make bottom visible, make sure top does not go out of bounds.
        final int dy;
        if (canScrollVertical) {
            dy = offScreenTop != 0 ? offScreenTop : Math.min(childTop - parentTop, offScreenBottom);
        } else {
            dy = 0;
        }

        if (dx != 0 || dy != 0) {
            scrollBy(dx, dy);
            if (immediate) {
                scrollBy(dx, dy);
            } else {
                smoothScrollBy(dx, dy);
            }
            // 重绘是为了选中item置顶，具体请参考getChildDrawingOrder方法
            postInvalidate();
            return true;
        }
        return false;
    }

}
