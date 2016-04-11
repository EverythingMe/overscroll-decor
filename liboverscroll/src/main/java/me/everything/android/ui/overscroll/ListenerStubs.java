package me.everything.android.ui.overscroll;

import android.view.View;

/**
 * @author amit
 */
public interface ListenerStubs {

    class OverScrollStateListenerStub implements IOverScrollStateListener {
        @Override
        public void onOverScrollStateChange(View view, int newState) { }
    }

    class OverScrollUpdateListenerStub implements IOverScrollUpdateListener {
        @Override
        public void onOverScrollUpdate(View view, int state, float offset) { }
    }
}
