package me.everything.android.ui.overscroll.adapters;

import android.graphics.Canvas;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MotionEvent;
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

    protected final RecyclerView mRecyclerView;
    protected final LinearLayoutManager mLayoutManager;

    protected boolean mIsItemTouchInEffect = false;

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager == false) {
            throw new IllegalArgumentException("Recycler views with non-linear layout managers are not supported by this adapter. Consider implementing a new adapter, instead");
        }

        mRecyclerView = recyclerView;
        mLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
    }

    public RecyclerViewOverScrollDecorAdapter(RecyclerView recyclerView, ItemTouchHelper.Callback itemTouchHelperCallback) {
        this(recyclerView);

        new ItemTouchHelper(new ItemTouchHelperCallbackWrapper(itemTouchHelperCallback) {
            @Override
            public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
                mIsItemTouchInEffect = actionState != 0;
                super.onSelectedChanged(viewHolder, actionState);
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    public View getView() {
        return mRecyclerView;
    }

    @Override
    public boolean isInAbsoluteStart() {
        return !mIsItemTouchInEffect && mLayoutManager.findFirstCompletelyVisibleItemPosition() == 0;
    }

    @Override
    public boolean isInAbsoluteEnd() {
        return !mIsItemTouchInEffect && mLayoutManager.findLastCompletelyVisibleItemPosition() == (mRecyclerView.getAdapter().getItemCount() - 1);
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
