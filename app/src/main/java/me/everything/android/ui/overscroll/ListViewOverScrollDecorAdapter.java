package me.everything.android.ui.overscroll;

import android.view.View;
import android.widget.AbsListView;

/**
 * Created by amit on 11/4/15.
 */
public class ListViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {

    private final AbsListView mView;

    public ListViewOverScrollDecorAdapter(AbsListView view) {
        mView = view;
    }

    @Override
    public View getView() {
        return mView;
    }

    @Override
    public boolean isInAbsoluteStart() {
        return mView.getChildCount() > 0 && !canScrollListUp();
    }

    @Override
    public boolean isInAbsoluteEnd() {
        return mView.getChildCount() > 0 && !canScrollListDown();
    }

    public boolean canScrollListUp() {
        final int firstTop = mView.getChildAt(0).getTop();
        final int firstPosition = mView.getFirstVisiblePosition();
        return firstPosition > 0 || firstTop < mView.getListPaddingTop();
    }

    public boolean canScrollListDown() {
        final int childCount = mView.getChildCount();
        final int itemsCount = mView.getCount();
        final int firstPosition = mView.getFirstVisiblePosition();
        final int lastPosition = firstPosition + childCount;
        final int lastBottom = mView.getChildAt(childCount - 1).getBottom();
        return lastPosition < itemsCount || lastBottom > mView.getHeight() - mView.getListPaddingBottom();
    }
}
