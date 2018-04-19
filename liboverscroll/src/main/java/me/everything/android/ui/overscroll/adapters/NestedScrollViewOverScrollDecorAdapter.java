package me.everything.android.ui.overscroll.adapters;

import android.support.v4.widget.NestedScrollView;
import android.view.View;

public class NestedScrollViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {

    protected final NestedScrollView mView;
    protected final boolean mEnableSwipeToRefresh;

    public NestedScrollViewOverScrollDecorAdapter(NestedScrollView view, boolean enableSwipeToRefresh) {
        mView = view;
        this.mEnableSwipeToRefresh = enableSwipeToRefresh;
    }

    @Override
    public View getView() {
        return mView;
    }

    @Override
    public boolean isInAbsoluteStart() {
        return !mView.canScrollVertically(-1);
    }

    @Override
    public boolean isInAbsoluteEnd() {
        return !mView.canScrollVertically(1);
    }

    public boolean swipeToRefreshEnabled() {
        return mEnableSwipeToRefresh;
    }
}
