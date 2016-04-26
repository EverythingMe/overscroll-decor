package me.everything.android.ui.overscroll;

/**
 * A callback-listener enabling over-scroll effect clients to subscribe to <b>real-time</b> updates
 * of over-scrolling intensity, provided as the view-translation offset from pre-scroll position.
 *
 * @author amit
 *
 * @see IOverScrollStateListener
 */
public interface IOverScrollUpdateListener {

    /**
     * The invoked callback.
     * @param effect The associated over-scroll effect manager.
     * @param state One of: {@link IOverScrollState#STATE_IDLE}, {@link IOverScrollState#STATE_DRAG_START_SIDE},
     *              {@link IOverScrollState#STATE_DRAG_START_SIDE} or {@link IOverScrollState#STATE_BOUNCE_BACK}.
     * @param offset The currently visible offset created due to over-scroll.
     */
    void onOverScrollUpdate(IOverScrollEffect effect, int state, float offset);
}
