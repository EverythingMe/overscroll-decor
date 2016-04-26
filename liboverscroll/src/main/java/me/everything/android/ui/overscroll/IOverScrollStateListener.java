package me.everything.android.ui.overscroll;

/**
 * A callback-listener enabling over-scroll effect clients to subscribe to effect state transitions.
 * <br/>Invoked whenever state is transitioned onto one of {@link IOverScrollState#STATE_IDLE},
 * {@link IOverScrollState#STATE_DRAG_START_SIDE}, {@link IOverScrollState#STATE_DRAG_START_SIDE}
 * or {@link IOverScrollState#STATE_BOUNCE_BACK}.
 *
 * @author amit
 *
 * @see IOverScrollUpdateListener
 */
public interface IOverScrollStateListener {

    /**
     * The invoked callback.
     *
     * @param effect The associated over-scroll effect manager.
     * @param newState One of: {@link IOverScrollState#STATE_IDLE}, {@link
     *          IOverScrollState#STATE_DRAG_START_SIDE}, {@link IOverScrollState#STATE_DRAG_END_SIDE}
     *          or {@link IOverScrollState#STATE_BOUNCE_BACK}.
     */
    void onOverScrollStateChange(IOverScrollEffect effect, int newState);
}
