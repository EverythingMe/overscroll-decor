package me.everything.android.ui.overscroll.core;

import android.view.MotionEvent;
import android.view.View;

import me.everything.android.ui.overscroll.IOverScrollState;
import me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter;

import static me.everything.android.ui.overscroll.IOverScrollState.STATE_DRAG_END_SIDE;
import static me.everything.android.ui.overscroll.IOverScrollState.STATE_DRAG_START_SIDE;

/**
 * Handles the actual over-scrolling: thus translating the view according to configuration
 * and user interactions, dynamically.
 *
 * <br/><br/>The state is exited - thus completing over-scroll handling, in one of two cases:
 * <br/>When user lets go of the view, it transitions control to the bounce-back state.
 * <br/>When user moves the view back onto a potential 'under-scroll' state, it abruptly
 * transitions control to the idle-state, so as to return touch-events management to the
 * normal over-scroll-less environment (thus preventing under-scrolling and potentially regaining
 * regular scrolling).
 */
class OverScrollingState implements IDecoratorState {

    protected final OverScrollBounceEffectDecoratorBase mHost;
    protected final IOverScrollDecoratorAdapter mViewAdapter;
    protected final IDecoratorState mBounceBackState;
    protected final float mTouchDragRatioFwd;
    protected final float mTouchDragRatioBck;

    final MotionAttributes mMoveAttr;
    int mCurrDragState;

    public OverScrollingState(OverScrollBounceEffectDecoratorBase host, IOverScrollDecoratorAdapter viewAdapter, IDecoratorState bounceBackState, MotionAttributes moveAttr, float touchDragRatioFwd, float touchDragRatioBck) {
        mHost = host;
        mViewAdapter = viewAdapter;
        mBounceBackState = bounceBackState;
        mMoveAttr = moveAttr;
        mTouchDragRatioFwd = touchDragRatioFwd;
        mTouchDragRatioBck = touchDragRatioBck;
    }

    @Override
    public int getStateId() {
        // This is really a single class that implements 2 states, so our ID depends on what
        // it was during the last invocation.
        return mCurrDragState;
    }

    @Override
    public boolean handleMoveTouchEvent(MotionEvent event) {

        // Switching 'pointers' (e.g. fingers) on-the-fly isn't supported -- abort over-scroll
        // smoothly using the default bounce-back animation in this case.
        if (mStartAttr.mPointerId != event.getPointerId(0)) {
            mHost.issueStateTransition(mBounceBackState);
            return true;
        }

        final View view = mViewAdapter.getView();
        if (!mMoveAttr.init(view, event)) {
            // Keep intercepting the touch event as long as we're still over-scrolling...
            return true;
        }

        float deltaOffset = mMoveAttr.mDeltaOffset / (mMoveAttr.mDir == mStartAttr.mDir ? mTouchDragRatioFwd : mTouchDragRatioBck);
        float newOffset = mMoveAttr.mAbsOffset + deltaOffset;

        // If moved in counter direction onto a potential under-scroll state -- don't. Instead, abort
        // over-scrolling abruptly, thus returning control to which-ever touch handlers there
        // are waiting (e.g. regular scroller handlers).
        if ( (mStartAttr.mDir && !mMoveAttr.mDir && (newOffset <= mStartAttr.mAbsOffset)) ||
             (!mStartAttr.mDir && mMoveAttr.mDir && (newOffset >= mStartAttr.mAbsOffset)) ) {
            translateViewAndEvent(view, mStartAttr.mAbsOffset, event);
            mUpdateListener.onOverScrollUpdate(OverScrollBounceEffectDecoratorBase.this, mCurrDragState, 0);

            issueStateTransition(mIdleState);
            return true;
        }

        if (view.getParent() != null) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
        }

        long dt = event.getEventTime() - event.getHistoricalEventTime(0);
        if (dt > 0) { // Sometimes (though rarely) dt==0 cause originally timing is in nanos, but is presented in millis.
            mVelocity = deltaOffset / dt;
        }

        translateView(view, newOffset);
        mHost.mUpdateListener.onOverScrollUpdate(mHost, mCurrDragState, newOffset);

        return true;
    }

    @Override
    public boolean handleUpOrCancelTouchEvent(MotionEvent event) {
        issueStateTransition(mBounceBackState);
        return true;
    }

    @Override
    public void handleEntryTransition(IDecoratorState fromState) {
        mCurrDragState = (mStartAttr.mDir ? STATE_DRAG_START_SIDE : STATE_DRAG_END_SIDE);
        mHost.mStateListener.onOverScrollStateChange(mHost, fromState.getStateId(), this.getStateId());
    }
}
