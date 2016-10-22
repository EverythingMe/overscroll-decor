package me.everything.android.ui.overscroll.core;

import android.view.MotionEvent;
import me.everything.android.ui.overscroll.IOverScrollState;

/**
 * Interface of decorator-state delegation classes. Defines states as handles of two fundamental
 * touch events: actual movement, up/cancel.
 */
public interface IDecoratorState {

    /**
     * Handle a motion (touch) event.
     *
     * @param event The event from onTouch.
     * @return Return value for onTouch.
     */
    boolean handleMoveTouchEvent(MotionEvent event);

    /**
     * Handle up / touch-cancel events.
     *
     * @param event The event from onTouch.
     * @return Return value for onTouch.
     */
    boolean handleUpOrCancelTouchEvent(MotionEvent event);

    /**
     * Handle a transition onto this state, as it becomes 'current' state.
     * @param fromState
     */
    void handleEntryTransition(IDecoratorState fromState);

    /**
     * The client-perspective ID of the state associated with this (internal) one. ID's
     * are as specified in {@link IOverScrollState}.
     *
     * @return The ID, e.g. {@link IOverScrollState#STATE_IDLE}.
     */
    int getStateId();
}
