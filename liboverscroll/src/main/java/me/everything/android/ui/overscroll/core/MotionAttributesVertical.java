package me.everything.android.ui.overscroll.core;

import android.view.MotionEvent;
import android.view.View;

class MotionAttributesVertical extends MotionAttributes {

    public boolean init(View view, MotionEvent event) {

        // We must have history available to calc the dx. Normally it's there - if it isn't temporarily,
        // we declare the event 'invalid' and expect it in consequent events.
        if (event.getHistorySize() == 0) {
            return false;
        }

        // Allow for counter-orientation-direction operations (e.g. item swiping) to run fluently.
        final float dy = event.getY(0) - event.getHistoricalY(0, 0);
        final float dx = event.getX(0) - event.getHistoricalX(0, 0);
        if (Math.abs(dx) > Math.abs(dy)) {
            return false;
        }

        mAbsOffset = view.getTranslationY();
        mDeltaOffset = dy;
        mDir = mDeltaOffset > 0;

        return true;
    }
}
