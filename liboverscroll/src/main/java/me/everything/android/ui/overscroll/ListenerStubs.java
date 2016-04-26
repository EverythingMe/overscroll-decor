package me.everything.android.ui.overscroll;

/**
 * @author amit
 */
public interface ListenerStubs {

    class OverScrollStateListenerStub implements IOverScrollStateListener {
        @Override
        public void onOverScrollStateChange(IOverScrollEffect effect, int newState) { }
    }

    class OverScrollUpdateListenerStub implements IOverScrollUpdateListener {
        @Override
        public void onOverScrollUpdate(IOverScrollEffect effect, int state, float offset) { }
    }
}
