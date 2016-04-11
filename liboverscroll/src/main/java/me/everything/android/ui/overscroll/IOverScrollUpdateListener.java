package me.everything.android.ui.overscroll;

import android.view.View;

/**
 * A callback-listener enabling over-scroll effect clients to subscribe to <b>real-time</b> updates
 * of over-scrolling magnitude, described as the offset from pre-scroll position.
 *
 * @author amit
 *
 * @see IOverScrollStateListener
 */
public interface IOverScrollUpdateListener {

    /**
     * The invoked callback.
     *
     * @param view The associated view.
     * @param state One of: {@link IOverScrollState#STATE_IDLE}, {@link IOverScrollState#STATE_DRAG}
     *              or {@link IOverScrollState#STATE_BOUNCE_BACK}.
     * @param offset The currently visible offset created due to over-scroll.
     */
    void onOverScrollUpdate(View view, int state, float offset);
}
