package com.github.baseclass;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.State;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author zhy
 */
public class BaseDividerGridItem extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};
    private Drawable mDivider;
    private int orientation= LinearLayout.VERTICAL;
    private boolean reverseLayout;
    private int mDividerHeight;
    private int mDividerWidth;
    private int mOrientation;

    /*public BaseDividerGridItem(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }*/

    public BaseDividerGridItem(Context context) {
        this(context, -1);
    }
    public BaseDividerGridItem(Context context, int dividerHeight) {
        this(context, dividerHeight, -1);
    }
    public BaseDividerGridItem(Context context, int dividerHeight, int drawableId) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        if(drawableId<0){
            mDivider = a.getDrawable(0);
        }else{
            mDivider= ContextCompat.getDrawable(context, drawableId);
        }
        if(dividerHeight<0){
            mDividerHeight=mDivider.getIntrinsicHeight();
            mDividerWidth=mDivider.getIntrinsicWidth();
        }else{
            mDividerHeight=dividerHeight;
            mDividerWidth=dividerHeight;
        }
        a.recycle();
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, State state) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        reverseLayout=layoutManager.getReverseLayout();
        orientation=layoutManager.getOrientation();
        if(orientation==LinearLayout.VERTICAL){
            drawHorizontalLine(c, parent);
            drawVerticalLine(c, parent);
        }else{
            drawHorizontalLine2(c, parent);
            drawVerticalLine2(c, parent);
        }
    }
    public void drawHorizontalLine2(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        int spanCount = getSpanCount(parent);
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            if(isFirstRawForHorizontal(parent, i, spanCount, childCount)&&isFirstColForHorizontal(parent, i, spanCount, childCount)) {
                drawBottomHorizontalLine(c, child, params);
                drawTopHorizontalLine(c, child, params);
            } else if (isFirstRawForHorizontal(parent, i, spanCount, childCount)) {
                // 如果是第一行，绘制上右下
                drawBottomHorizontalLine(c, child, params);
                drawTopHorizontalLine(c, child, params);
            }else if (isFirstColForHorizontal(parent, i, spanCount, childCount)) {
                // 如果是第一列，绘制左右下
                drawBottomHorizontalLine(c, child, params);
            }else{
                drawBottomHorizontalLine(c, child, params);
            }
        }
    }
    public void drawVerticalLine2(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        int spanCount = getSpanCount(parent);
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            if(isFirstRawForHorizontal(parent, i, spanCount, childCount)&&isFirstColForHorizontal(parent, i, spanCount, childCount)) {
                drawRightVerticalLine(c, child, params);
                drawLeftVerticalLine(c, child, params);
            } else if (isFirstRawForHorizontal(parent, i, spanCount, childCount)) {
                // 如果是第一行，绘制上右下
                if(reverseLayout){
                    drawLeftVerticalLine(c, child, params);
                }else{
                    drawRightVerticalLine(c, child, params);
                }

            }else if (isFirstColForHorizontal(parent, i, spanCount, childCount)) {
                // 如果是第一列，绘制左右下
                drawRightVerticalLine(c, child, params);
                drawLeftVerticalLine(c, child, params);
            }else{
                if(reverseLayout){
                    drawLeftVerticalLine(c, child, params);
                }else{
                    drawRightVerticalLine(c, child, params);
                }
            }
        }
    }
    private int getSpanCount(RecyclerView parent) {
        // 列数
        int spanCount = -1;
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
        }
        return spanCount;
    }

    public void drawHorizontalLine(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        int spanCount = getSpanCount(parent);
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            if(isFirstRawForHorizontal(parent, i, spanCount, childCount)&&isFirstColForHorizontal(parent, i, spanCount, childCount)) {
                drawBottomHorizontalLine(c, child, params);
                drawTopHorizontalLine(c, child, params);
            } else if (isFirstRawForHorizontal(parent, i, spanCount, childCount)) {
                // 如果是第一行，绘制上右下
                drawBottomHorizontalLine(c, child, params);
                drawTopHorizontalLine(c, child, params);
            }else if (isFirstColForHorizontal(parent, i, spanCount, childCount)) {
                // 如果是第一列，绘制左右下
                if(reverseLayout){
                    drawTopHorizontalLine(c, child, params);
                }else{
                    drawBottomHorizontalLine(c, child, params);
                }
            }else{
                if(reverseLayout){
                    drawTopHorizontalLine(c, child, params);
                }else{
                    drawBottomHorizontalLine(c, child, params);
                }
            }
        }
    }
    private void drawTopHorizontalLine(Canvas c, View child, RecyclerView.LayoutParams params) {
        int left2 = child.getLeft() - params.leftMargin-mDividerWidth;
        int right2 = child.getRight() + params.rightMargin+ mDividerWidth;
        int top2 = child.getTop() - params.topMargin;
        if(orientation==LinearLayout.VERTICAL){
            top2-=mDividerWidth;
        }
        final int bottom2 = top2 + mDividerHeight;
        mDivider.setBounds(left2, top2, right2, bottom2);
        mDivider.draw(c);
    }

    private void drawBottomHorizontalLine(Canvas c, View child, RecyclerView.LayoutParams params) {
        final int left = child.getLeft() - params.leftMargin-mDividerWidth;
        final int right = child.getRight() + params.rightMargin+ mDividerWidth;
        final int top = child.getBottom() + params.bottomMargin;
        final int bottom = top + mDividerHeight;

        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);

    }
    private void drawLeftVerticalLine(Canvas c, View child, RecyclerView.LayoutParams params) {
        final int top2 = child.getTop() - params.topMargin;
        final int bottom2 = child.getBottom() + params.bottomMargin;
        final int left2 = child.getLeft() - params.leftMargin-mDividerWidth;
        final int right2 = left2 + mDividerWidth;
        mDivider.setBounds(left2, top2, right2, bottom2);
        mDivider.draw(c);
    }

    private void drawRightVerticalLine(Canvas c, View child, RecyclerView.LayoutParams params) {
        final int top = child.getTop() - params.topMargin;
        final int bottom = child.getBottom() + params.bottomMargin;
        final int left = child.getRight() + params.rightMargin;
        final int right = left + mDividerWidth;
        mDivider.setBounds(left, top, right, bottom);
        mDivider.draw(c);

    }
    public void drawVerticalLine(Canvas c, RecyclerView parent) {
        final int childCount = parent.getChildCount();
        int spanCount = getSpanCount(parent);
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            if(isFirstRawForHorizontal(parent, i, spanCount, childCount)&&isFirstColForHorizontal(parent, i, spanCount, childCount)) {
                drawRightVerticalLine(c, child, params);
                drawLeftVerticalLine(c, child, params);
            } else if (isFirstRawForHorizontal(parent, i, spanCount, childCount)) {
                    drawRightVerticalLine(c, child, params);

            }else if (isFirstColForHorizontal(parent, i, spanCount, childCount)) {
                // 如果是第一列，绘制左右下
                drawRightVerticalLine(c, child, params);
                drawLeftVerticalLine(c, child, params);
            }else{
                    drawRightVerticalLine(c, child, params);
            }
        }
    }
    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        int spanCount = getSpanCount(parent);
        int childCount = parent.getAdapter().getItemCount();
        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        if(layoutManager.getOrientation()==LinearLayout.VERTICAL){
            if(isFirstRawForHorizontal(parent,itemPosition,spanCount,childCount)){
                outRect.set(mDividerWidth,  mDividerWidth, mDividerWidth,mDividerWidth);
            }else{
                if(layoutManager.getReverseLayout()){
                    //垂直反向
                    outRect.set(mDividerWidth,  mDividerWidth, mDividerWidth,0);
                }else{
                    //垂直顺向
                    outRect.set(mDividerWidth,  0, mDividerWidth,mDividerWidth);
                }
            }
        }else{
            outRect.set(mDividerWidth, 0, mDividerWidth, mDividerHeight);
        }
    }



    /**
     * 是否是第一行
     */
    private boolean isFirstRawForHorizontal(RecyclerView parent, int pos, int spanCount, int childCount) {
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if(((LinearLayoutManager)layoutManager).getOrientation()== LinearLayout.VERTICAL){
                if((pos+1)<=spanCount){
                    return true;
                }
            }else{
                if(pos%spanCount==0){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 是否是第一列
     * @param parent
     * @param pos
     * @param spanCount
     * @param childCount
     * @return
     */
    private boolean isFirstColForHorizontal(RecyclerView parent, int pos, int spanCount, int childCount) {
        LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            if(((LinearLayoutManager)layoutManager).getOrientation()==LinearLayout.VERTICAL){
                if(pos%spanCount==0){
                    return true;
                }
            }else{
                if((pos+1)<=spanCount){
                    return true;
                }
            }
        }
        return false;
    }
}
