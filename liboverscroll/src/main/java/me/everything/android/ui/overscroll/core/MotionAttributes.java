package me.everything.android.ui.overscroll.core;

import android.view.MotionEvent;
import android.view.View;

/**
 * Motion attributes: keeps data describing current motion event.
 * <br/>Orientation agnostic: subclasses provide either horizontal or vertical
 * initialization of the agnostic attributes.
 */
abstract class MotionAttributes {
    public float mAbsOffset;
    public float mDeltaOffset;
    public boolean mDir; // True = 'forward', false = 'backwards'.

    protected abstract boolean init(View view, MotionEvent event);
}
