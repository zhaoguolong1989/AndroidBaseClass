package com.github.baseclass.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/7/5.
 */

public class LoadMoreView extends LinearLayout {
    private AbsListView mAbsListView;
    private FooterView mDefaultView;
    private View mFooterView;

    public LoadMoreView(Context context) {
        super(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mAbsListView=(ListView)getChildAt(0);
        init();
    }

    private void init() {


    }




    public static class FooterView extends LinearLayout {
        public FooterView(Context context) {
            super(context);
        }
        public FooterView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }
        public FooterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }
    }
}
