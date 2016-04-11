package me.everything.android.ui.overscroll;

import android.view.View;

/**
 * A callback-listener enabling over-scroll effect clients to subscribe to effect state transitions.
 * <br/>Invoked whenever state is transitioned onto one of {@link IOverScrollState#STATE_IDLE},
 * {@link IOverScrollState#STATE_DRAG} or {@link IOverScrollState#STATE_BOUNCE_BACK}.
 *
 * @author amit
 *
 * @see IOverScrollUpdateListener
 */
public interface IOverScrollStateListener {

    /**
     * The invoked callback.
     *
     * @param view The associated view.
     * @param newState One of: {@link IOverScrollState#STATE_IDLE}, {@link IOverScrollState#STATE_DRAG}
     *                 or {@link IOverScrollState#STATE_BOUNCE_BACK}.
     */
    void onOverScrollStateChange(View view, int newState);
}
