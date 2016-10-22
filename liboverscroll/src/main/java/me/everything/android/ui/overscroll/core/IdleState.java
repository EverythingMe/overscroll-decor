package me.everything.android.ui.overscroll.core;

import android.view.MotionEvent;
import android.view.View;

import me.everything.android.ui.overscroll.IOverScrollState;
import me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter;

import static me.everything.android.ui.overscroll.IOverScrollState.*;

/**
 * Idle state: monitors move events, trying to figure out whether over-scrolling should be
 * initiated (i.e. when scrolled further when the view is at one of its displayable ends).
 * <br/>When such is the case, it hands over control to the over-scrolling state.
 */
class IdleState implements IDecoratorState {

    protected final OverScrollBounceEffectDecoratorBase mHost;
    protected final IOverScrollState mOverScrollingState;
    protected final IOverScrollDecoratorAdapter mViewAdapter;
    protected final MotionAttributes mMoveAttr;

    public IdleState(OverScrollBounceEffectDecoratorBase host, IOverScrollDecoratorAdapter viewAdapter, IOverScrollState overScrollingState, MotionAttributes moveAttributes) {
        mHost = host;
        mViewAdapter = viewAdapter;
        mOverScrollingState = overScrollingState;
        mMoveAttr = moveAttributes;
    }

    @Override
    public int getStateId() {
        return STATE_IDLE;
    }

    @Override
    public boolean handleMoveTouchEvent(MotionEvent event) {

        final View view = mViewAdapter.getView();
        if (!mMoveAttr.init(view, event)) {
            return false;
        }

        // Has over-scrolling officially started?
        if ((mViewAdapter.isInAbsoluteStart() && mMoveAttr.mDir) ||
            (mViewAdapter.isInAbsoluteEnd() && !mMoveAttr.mDir)) {

            // Save initial over-scroll attributes for future reference.
            mStartAttr.mPointerId = event.getPointerId(0);
            mStartAttr.mAbsOffset = mMoveAttr.mAbsOffset;
            mStartAttr.mDir = mMoveAttr.mDir;

            mHost.issueStateTransition(mOverScrollingState);
            return mOverScrollingState.handleMoveTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean handleUpOrCancelTouchEvent(MotionEvent event) {
        return false;
    }

    @Override
    public void handleEntryTransition(IDecoratorState fromState) {
        mHost.mStateListener.onOverScrollStateChange(mHost, fromState.getStateId(), this.getStateId());
    }
}
