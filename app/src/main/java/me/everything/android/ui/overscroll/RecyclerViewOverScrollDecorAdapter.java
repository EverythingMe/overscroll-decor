package me.everything.android.ui.overscroll;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author amitd
 *
 * @see HorizontalOverScrollBounceEffectDecorator
 */
public class RecyclerViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {

    final RecyclerView mRecyclerView;
    final LinearLayoutManager mLayoutManager;

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager == false) {
            throw new IllegalArgumentException("Recycler views with non-linear layout managers are not supported by this adapter. Consider implementing a new adapter, instead");
        }

        mRecyclerView = recyclerView;
        mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
    }

    @Override
    public View getView() {
        return mRecyclerView;
    }

    @Override
    public boolean isInAbsoluteStart() {
        return mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0;
    }

    @Override
    public boolean isInAbsoluteEnd() {
        return mLayoutManager.findLastCompletelyVisibleItemPosition() == (mRecyclerView.getAdapter().getItemCount() - 1);
    }

}
