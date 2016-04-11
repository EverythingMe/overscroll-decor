package me.everything.android.ui.overscroll;

/**
 * @author amit
 */
public interface IOverScrollState {

    /** No over-scroll is in-effect. */
    int STATE_IDLE = 0;

    /** User is actively touch-dragging the view into over-scroll mode. */
    int STATE_DRAG = 1;

    /** User has released their touch, thus throwing the view back into place via bounce-back animation. */
    int STATE_BOUNCE_BACK = 2;
}
