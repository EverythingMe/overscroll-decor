package me.everything.android.ui.overscroll.adapters;

import android.graphics.Canvas;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.List;

import me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator;
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator;

/**
 * @author amitd
 *
 * @see HorizontalOverScrollBounceEffectDecorator
 * @see VerticalOverScrollBounceEffectDecorator
 */
public class RecyclerViewOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {

    /**
     * A delegation of the adapter implementation of this view that should provide the processing
     * of {@link #isInAbsoluteStart()} and {@link #isInAbsoluteEnd()}. Essentially needed simply
     * because the implementation depends on the layout manager implementation being used.
     */
    protected interface Impl {
        boolean isInAbsoluteStart();
        boolean isInAbsoluteEnd();
    }

    protected final RecyclerView mRecyclerView;
    protected final Impl mImpl;

    protected boolean mIsItemTouchInEffect = false;

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView) {

        mRecyclerView = recyclerView;

        final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            mImpl = new ImplLinearLayout(recyclerView, (LinearLayoutManager) layoutManager);
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            mImpl = new ImplStaggeredGridLayout(recyclerView, (StaggeredGridLayoutManager) layoutManager);
        } else {
            throw new IllegalArgumentException("Recycler views with custom layout managers are not supported by this adapter out of the box." +
                    "Try implementing and providing an explicit 'impl' parameter to the other c'tors, or otherwise create a custom adapter subclass of your own.");
        }
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView, Impl impl) {
        mRecyclerView = recyclerView;
        mImpl = impl;
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView, ItemTouchHelper.Callback itemTouchHelperCallback) {
        this(recyclerView);
        setUpTouchHelperCallback(itemTouchHelperCallback);
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView, Impl impl, ItemTouchHelper.Callback itemTouchHelperCallback) {
        this(recyclerView, impl);
        setUpTouchHelperCallback(itemTouchHelperCallback);
    }

    protected void setUpTouchHelperCallback(final ItemTouchHelper.Callback itemTouchHelperCallback) {
        new ItemTouchHelper(new ItemTouchHelperCallbackWrapper(itemTouchHelperCallback) {
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                mIsItemTouchInEffect = actionState != 0;
                super.onSelectedChanged(viewHolder, actionState);
            }
        }).attachToRecyclerView(mRecyclerView);
    }

    @Override
    public View getView() {
        return mRecyclerView;
    }

    @Override
    public boolean isInAbsoluteStart() {
        return !mIsItemTouchInEffect && mImpl.isInAbsoluteStart();
    }

    @Override
    public boolean isInAbsoluteEnd() {
        return !mIsItemTouchInEffect && mImpl.isInAbsoluteEnd();
    }

    public static class ImplLinearLayout implements Impl {

        private final RecyclerView mRecyclerView;
        private final LinearLayoutManager mLayoutManager;

        public ImplLinearLayout(RecyclerView recyclerView, LinearLayoutManager layoutManager) {
            mRecyclerView = recyclerView;
            mLayoutManager = layoutManager;
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

    protected static class ImplStaggeredGridLayout implements Impl {

        private final RecyclerView mRecyclerView;
        private final StaggeredGridLayoutManager mLayoutManager;
        final int[] mVisiblePositionsBuffer;

        public ImplStaggeredGridLayout(RecyclerView recyclerView, StaggeredGridLayoutManager layoutManager) {
            mRecyclerView = recyclerView;
            mLayoutManager = layoutManager;

            final int spanCount = layoutManager.getSpanCount();
            mVisiblePositionsBuffer = new int[spanCount];
        }

        @Override
        public boolean isInAbsoluteStart() {
            mLayoutManager.findFirstCompletelyVisibleItemPositions(mVisiblePositionsBuffer);
            return mVisiblePositionsBuffer[0] == 0;
        }

        @Override
        public boolean isInAbsoluteEnd() {
            mLayoutManager.findLastCompletelyVisibleItemPositions(mVisiblePositionsBuffer);

            final int lastItemPos = mRecyclerView.getAdapter().getItemCount() - 1;
            for (int item : mVisiblePositionsBuffer) {
                if (item == lastItemPos) {
                    return true;
                }
            }
            return false;
        }
    }

    private static class ItemTouchHelperCallbackWrapper extends ItemTouchHelper.Callback {

        final ItemTouchHelper.Callback mCallback;

        private ItemTouchHelperCallbackWrapper(ItemTouchHelper.Callback callback) {
            mCallback = callback;
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return mCallback.getMovementFlags(recyclerView, viewHolder);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return mCallback.onMove(recyclerView, viewHolder, target);
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            mCallback.onSwiped(viewHolder, direction);
        }

        @Override
        public int convertToAbsoluteDirection(int flags, int layoutDirection) {
            return mCallback.convertToAbsoluteDirection(flags, layoutDirection);
        }

        @Override
        public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder current, RecyclerView.ViewHolder target) {
            return mCallback.canDropOver(recyclerView, current, target);
        }

        @Override
        public boolean isLongPressDragEnabled() {
            return mCallback.isLongPressDragEnabled();
        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return mCallback.isItemViewSwipeEnabled();
        }

        @Override
        public int getBoundingBoxMargin() {
            return mCallback.getBoundingBoxMargin();
        }

        @Override
        public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
            return mCallback.getSwipeThreshold(viewHolder);
        }

        @Override
        public float getMoveThreshold(RecyclerView.ViewHolder viewHolder) {
            return mCallback.getMoveThreshold(viewHolder);
        }

        @Override
        public RecyclerView.ViewHolder chooseDropTarget(RecyclerView.ViewHolder selected, List<RecyclerView.ViewHolder> dropTargets, int curX, int curY) {
            return mCallback.chooseDropTarget(selected, dropTargets, curX, curY);
        }

        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            mCallback.onSelectedChanged(viewHolder, actionState);
        }

        @Override
        public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int fromPos, RecyclerView.ViewHolder target, int toPos, int x, int y) {
            mCallback.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
        }

        @Override
        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            mCallback.clearView(recyclerView, viewHolder);
        }

        @Override
        public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            mCallback.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        @Override
        public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            mCallback.onChildDrawOver(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }

        @Override
        public long getAnimationDuration(RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
            return mCallback.getAnimationDuration(recyclerView, animationType, animateDx, animateDy);
        }

        @Override
        public int interpolateOutOfBoundsScroll(RecyclerView recyclerView, int viewSize, int viewSizeOutOfBounds, int totalSize, long msSinceStartScroll) {
            return mCallback.interpolateOutOfBoundsScroll(recyclerView, viewSize, viewSizeOutOfBounds, totalSize, msSinceStartScroll);
        }
    }
}
