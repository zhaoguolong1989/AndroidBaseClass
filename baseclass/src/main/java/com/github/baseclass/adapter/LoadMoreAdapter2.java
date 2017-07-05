/*
package com.github.baseclass.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

*/
/**
 * Created by Administrator on 2017/6/30.
 *//*


public abstract class LoadMoreAdapter2<T> extends RecyclerView.Adapter<LoadMoreViewHolder> {
    Handler handler;
    */
/*正常view item*//*

    private final int normal_view = 2000;
    */
/*显示加载更多*//*

    private final int load_more_view_type = 1000;
    */
/*暂无更多数据*//*

    private final int no_more_view_type = 1001;
    */
/*加载失败*//*

    private final int load_error_view_type = 1002;
    */
/*回调方法,触发加载更多*//*

    */
/***用于判断是否还有更多数据*//*

    private int pageSize;
    */
/***是否还有更多数据,没有更多数据显示"暂无更多"*//*

    private boolean hasMoreData = false;
    */
/*** 加载是否失败,用于点击重新加载*//*

    private boolean isLoadError;
    */
/*** 是否隐藏暂无内容的提示*//*

    private boolean isHiddenPromptView = false;
    private View loadView,errorView,noMoreView;
    private String loadViewText,errorViewText,noMoreViewText;

    protected List<T> mList;
    protected Context mContext;
    protected LayoutInflater mInflater;

    public LoadMoreAdapter2(Context mContext, int pageSize) {
        this.mContext = mContext;
        mInflater=LayoutInflater.from(mContext);
        this.pageSize=pageSize;
    }

    abstract public int getItemLayoutId(int viewType);

    abstract public void bindData(LoadMoreViewHolder holder, int position, T bean);

    @Override
    public LoadMoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LoadMoreViewHolder holder;
        if (viewType == normal_view) {//正常item view  viewType == normal_view
            holder= new LoadMoreViewHolder(mContext,
                    mInflater.inflate(getItemLayoutId(viewType), parent, false));
        } else {
            holder =new LoadMoreViewHolder(mContext,setDefaultView(viewType));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(LoadMoreViewHolder holder, int position) {
        if(position<=getItemCount()-2){
            bindData(holder, position, mList.get(position));
        }else{
            if(onLoadMoreListener!=null){
                switch (holder.getItemViewType()){
                    case load_more_view_type:
                        getHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                onLoadMoreListener.loadMore();
                            }
                        });
                        break;
                    case load_error_view_type:
                        holder.bottomView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                isLoadError=false;
                                hasMoreData=true;
                                notifyDataSetChanged();
                                getHandler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        onLoadMoreListener.loadMore();
                                    }
                                });
                            }
                        });
                        break;
                }
            }
        }
    }
    @Override
    public int getItemViewType(int position) {

        return normal_view;
    }
    private View setDefaultView(int viewType) {
        */
/*LoadMoreViewHolder.BottomView bottomView = new LoadMoreViewHolder.BottomView(mContext);
        bottomView.setBackgroundColor(mContext.getResources().getColor(android.R.color.white));
        bottomView.setGravity(Gravity.CENTER);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        bottomView.setLayoutParams(layoutParams);

        TextView textView = new TextView(mContext);
        switch (viewType) {
            case load_more_view_type://加载更多view
                if(loadView!=null){
                    bottomView.addView(loadView);
                }else{
                    layoutParams.height=dip2px(mContext,50);
                    textView.setText(TextUtils.isEmpty(loadViewText)?"正在加载更多...":loadViewText);
                    bottomView.addView(textView);
                }
                break;
            case no_more_view_type://暂无更多view
                if(noMoreView!=null){
                    bottomView.addView(noMoreView);
                }else{
                    layoutParams.height=dip2px(mContext,50);
                    textView.setText(TextUtils.isEmpty(noMoreViewText)?"暂无更多":noMoreViewText);
                    bottomView.addView(textView);
                }
                if(isHiddenPromptView){
                    layoutParams.height=0;
                }
                break;
            case load_error_view_type://加载失败view
                if(errorView!=null){
                    bottomView.addView(errorView);
                }else{
                    layoutParams.height=dip2px(mContext,50);
                    textView.setText(TextUtils.isEmpty(errorViewText)?"加载失败,点击重试":errorViewText);
                    bottomView.addView(textView);
                }
                break;
        }*//*

        return null;
    }
    @Override
    public int getItemCount() {
        if(onLoadMoreListener!=null){
            return mList==null?0:mList.size()+1;
        }else{
            return mList==null?0:mList.size();
        }
    }

      /////////////////////////////////////////////////////

    public Handler getHandler(){
        if(handler==null){
            handler=new Handler();
        }
        return handler;
    }
    */
/*是否隐藏底部暂无内容的view*//*

    public void setHiddenPromptView(boolean hiddenPromptView) {
        setHiddenPromptView(hiddenPromptView,false);
    }
    public void setHiddenPromptView(boolean hiddenPromptView,boolean isNotifyData){
        isHiddenPromptView = hiddenPromptView;
        if(isNotifyData){
            notifyDataSetChanged();
        }
    }
    */
/*是否加载失败*//*

    public void setLoadError(boolean loadError) {
        isLoadError = loadError;
    }
    */
/*是否还有更多数据*//*

    public void setHasMoreData(boolean hasMoreData) {
        this.hasMoreData = hasMoreData;
    }
    */
/*设置正在加载的view*//*

    public void setLoadView(View loadView) {
        this.loadView = loadView;
    }
    */
/*设置加载失败的view*//*

    public void setErrorView(View errorView) {
        this.errorView = errorView;
    }
    */
/*设置没有更多数据的view*//*

    public void setNoMoreView(View noMoreView) {
        this.noMoreView = noMoreView;
    }
    */
/*默认BottomView的情况下，设置正在加载的文字*//*

    public void setLoadViewText(String loadViewText) {
        this.loadViewText = loadViewText;
    }
    */
/*默认BottomView的情况下，设置加载失败的文字*//*

    public void setErrorViewText(String errorViewText) {
        this.errorViewText = errorViewText;
    }
    */
/*默认BottomView的情况下，设置没有更多数据的文字*//*

    public void setNoMoreViewText(String noMoreViewText) {
        this.noMoreViewText = noMoreViewText;
    }
    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
*/
