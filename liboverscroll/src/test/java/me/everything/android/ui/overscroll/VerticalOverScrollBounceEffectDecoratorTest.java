package me.everything.android.ui.overscroll;

import android.view.MotionEvent;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter;

import static me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator.DEFAULT_DECELERATE_FACTOR;
import static me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator.DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyFloat;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author amitd
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class VerticalOverScrollBounceEffectDecoratorTest {

    View mView;
    IOverScrollDecoratorAdapter mViewAdapter;

    @Before
    public void setUp() throws Exception {
        mView = mock(View.class);
        mViewAdapter = mock(IOverScrollDecoratorAdapter.class);
        when(mViewAdapter.getView()).thenReturn(mView);
    }

    /*
     * Move-action event
     */

    @Test
    public void onTouchMoveAction_notInViewEnds_ignoreTouchEvent() throws Exception {

        // Arrange

        MotionEvent event = createShortDownwardsMoveEvent();

        when(mViewAdapter.isInAbsoluteStart()).thenReturn(false);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(false);

        VerticalOverScrollBounceEffectDecorator uut = getUUT();

        // Act

        boolean ret = uut.onTouch(mView, event);

        // Assert

        verify(mView, never()).setTranslationX(anyFloat());
        verify(mView, never()).setTranslationY(anyFloat());
        assertFalse(ret);
    }

    @Test
    public void onTouchMoveAction_dragDownInUpperEnd_overscrollDownwards() throws Exception {

        // Arrange

        MotionEvent event = createShortDownwardsMoveEvent();

        when(mViewAdapter.isInAbsoluteStart()).thenReturn(true);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(false);

        VerticalOverScrollBounceEffectDecorator uut = getUUT();

        // Act

        boolean ret = uut.onTouch(mView, event);

        // Assert

        float expectedTransY = (event.getY() - event.getHistoricalY(0)) / DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD;
        verify(mView).setTranslationY(expectedTransY);
        verify(mView, never()).setTranslationX(anyFloat());
        assertTrue(ret);
    }

    @Test
    public void onTouchMoveAction_dragUpInBottomEnd_overscrollUpwards() throws Exception {

        // Arrange

        MotionEvent event = createShortUpwardsMoveEvent();

        when(mViewAdapter.isInAbsoluteStart()).thenReturn(false);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(true);

        VerticalOverScrollBounceEffectDecorator uut = getUUT();

        // Act

        boolean ret = uut.onTouch(mView, event);

        // Assert

        float expectedTransY = (event.getY() - event.getHistoricalY(0)) / DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD;
        verify(mView).setTranslationY(expectedTransY);
        verify(mView, never()).setTranslationX(anyFloat());
        assertTrue(ret);
    }

    @Test
    public void onTouchMoveAction_dragUpInUpperEnd_ignoreTouchEvent() throws Exception {

        // Arrange

        MotionEvent event = createShortUpwardsMoveEvent();

        when(mViewAdapter.isInAbsoluteStart()).thenReturn(true);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(false);

        VerticalOverScrollBounceEffectDecorator uut = getUUT();

        // Act

        boolean ret = uut.onTouch(mView, event);

        // Assert

        verify(mView, never()).setTranslationX(anyFloat());
        verify(mView, never()).setTranslationY(anyFloat());
        assertFalse(ret);
    }

    @Test
    public void onTouchMoveAction_dragDownInBottomEnd_ignoreTouchEvent() throws Exception {

        // Arrange

        MotionEvent event = createShortDownwardsMoveEvent();

        when(mViewAdapter.isInAbsoluteStart()).thenReturn(false);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(true);

        VerticalOverScrollBounceEffectDecorator uut = getUUT();

        // Act

        boolean ret = uut.onTouch(mView, event);

        // Assert

        verify(mView, never()).setTranslationX(anyFloat());
        verify(mView, never()).setTranslationY(anyFloat());
        assertFalse(ret);
    }

    @Test
    public void onTouchMoveAction_2ndDownDragInUpperEnd_overscrollDownwardsFurther() throws Exception {

        // Arrange

        // Bring UUT to a downwards-overscroll state
        MotionEvent event1 = createShortDownwardsMoveEvent();

        when(mViewAdapter.isInAbsoluteStart()).thenReturn(true);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(false);

        VerticalOverScrollBounceEffectDecorator uut = getUUT();
        uut.onTouch(mView, event1);
        reset(mView);

        // Create 2nd downwards-drag event
        MotionEvent event2 = createLongDownwardsMoveEvent();

        // Act

        boolean ret = uut.onTouch(mView, event2);

        // Assert

        float expectedTransY = (event2.getY() - event2.getHistoricalY(0)) / DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD;
        verify(mView).setTranslationY(expectedTransY);
        verify(mView, never()).setTranslationX(anyFloat());
        assertTrue(ret);
    }

    @Test
    public void onTouchMoveAction_2ndUpDragInBottomEnd_overscrollUpwardsFurther() throws Exception {

        // Arrange

        // Bring UUT to an upwards-overscroll state
        MotionEvent event1 = createShortUpwardsMoveEvent();

        when(mViewAdapter.isInAbsoluteStart()).thenReturn(false);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(true);

        VerticalOverScrollBounceEffectDecorator uut = getUUT();
        uut.onTouch(mView, event1);
        reset(mView);

        // Create 2nd upward-drag event
        MotionEvent event2 = createLongUpwardsMoveEvent();

        // Act

        boolean ret = uut.onTouch(mView, event2);

        // Assert

        float expectedTransY = (event2.getY() - event2.getHistoricalY(0)) / DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD;
        verify(mView).setTranslationY(expectedTransY);
        verify(mView, never()).setTranslationX(anyFloat());
        assertTrue(ret);
    }

    /**
     * When over-scroll has already started (downwards in this case) and suddenly the user changes
     * their mind and scrolls a bit in the other direction:
     * <br/>We expect the <b>touch to still be intercepted</b> in that case, and the <b>overscroll to remain in effect</b>.
     */
    @Test
    public void onTouchMoveAction_dragUpWhenDownOverscolled_continueOverscrollingUpwards() throws Exception {

        // Arrange

        // In down & up drag tests we use equal ratios to avoid the effect's under-scroll handling
        final float touchDragRatioFwd = 3f;
        final float touchDragRatioBck = 3f;

        // Bring UUT to a downwrads-overscroll state
        when(mViewAdapter.isInAbsoluteStart()).thenReturn(true);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(false);

        VerticalOverScrollBounceEffectDecorator uut = getUUT(touchDragRatioFwd, touchDragRatioBck);
        MotionEvent eventMoveRight = createLongDownwardsMoveEvent();
        uut.onTouch(mView, eventMoveRight);
        reset(mView);
        float startTransY = (eventMoveRight.getY() - eventMoveRight.getHistoricalY(0)) / touchDragRatioFwd;
        when(mView.getTranslationY()).thenReturn(startTransY);

        // Create the up-drag event
        MotionEvent eventMoveUpwards = createShortUpwardsMoveEvent();

        // Act

        boolean ret = uut.onTouch(mView, eventMoveUpwards);

        // Assert

        float expectedTransY = startTransY +
                (eventMoveUpwards.getY() - eventMoveUpwards.getHistoricalY(0)) / touchDragRatioBck;
        verify(mView).setTranslationY(expectedTransY);
        verify(mView, never()).setTranslationX(anyFloat());
        assertTrue(ret);
    }

    /**
     * When over-scroll has already started (upwards in this case) and suddenly the user changes
     * their mind and scrolls a bit in the other direction:
     * <br/>We expect the <b>touch to still be intercepted</b> in that case, and the <b>overscroll to remain in effect</b>.
     */
    @Test
    public void onTouchMoveAction_dragDownWhenUpOverscolled_continueOverscrollingDownwards() throws Exception {

        // Arrange

        // In up & down drag tests we use equal ratios to avoid the effect's under-scroll handling
        final float touchDragRatioFwd = 3f;
        final float touchDragRatioBck = 3f;

        // Bring UUT to an upwards-overscroll state
        when(mViewAdapter.isInAbsoluteStart()).thenReturn(false);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(true);

        VerticalOverScrollBounceEffectDecorator uut = getUUT(touchDragRatioFwd, touchDragRatioBck);
        MotionEvent eventMoveUp = createLongUpwardsMoveEvent();
        uut.onTouch(mView, eventMoveUp);
        reset(mView);

        float startTransY = (eventMoveUp.getY() - eventMoveUp.getHistoricalY(0)) / touchDragRatioFwd;
        when(mView.getTranslationY()).thenReturn(startTransY);

        // Create the down-drag event
        MotionEvent eventMoveDown = createShortDownwardsMoveEvent();

        // Act

        boolean ret = uut.onTouch(mView, eventMoveDown);

        // Assert

        float expectedTransY = startTransY + (eventMoveDown.getY() - eventMoveDown.getHistoricalY(0)) / touchDragRatioBck;
        verify(mView).setTranslationY(expectedTransY);
        verify(mView, never()).setTranslationX(anyFloat());
        assertTrue(ret);
    }

    /*
     * Up action event
     */

    @Test
    public void onTouchUpAction_eventWhenNotOverscrolled_ignoreTouchEvent() throws Exception {

        // Arrange

        MotionEvent event = createDefaultUpActionEvent();

        when(mViewAdapter.isInAbsoluteStart()).thenReturn(true);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(true);

        VerticalOverScrollBounceEffectDecorator uut = getUUT();

        // Act

        boolean ret = uut.onTouch(mView, event);

        // Assert

        verify(mView, never()).setTranslationX(anyFloat());
        verify(mView, never()).setTranslationY(anyFloat());
        assertFalse(ret);
    }

    protected MotionEvent createShortDownwardsMoveEvent() {
        MotionEvent event = mock(MotionEvent.class);
        when(event.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(event.getX()).thenReturn(200f);
        when(event.getY()).thenReturn(100f);
        when(event.getX(0)).thenReturn(200f);
        when(event.getY(0)).thenReturn(100f);
        when(event.getHistorySize()).thenReturn(1);
        when(event.getHistoricalX(eq(0))).thenReturn(180f);
        when(event.getHistoricalY(eq(0))).thenReturn(90f);
        when(event.getHistoricalX(eq(0), eq(0))).thenReturn(180f);
        when(event.getHistoricalY(eq(0), eq(0))).thenReturn(90f);
        return event;
    }

    protected MotionEvent createLongDownwardsMoveEvent() {
        MotionEvent event = mock(MotionEvent.class);
        when(event.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(event.getX()).thenReturn(250f);
        when(event.getY()).thenReturn(150f);
        when(event.getX(0)).thenReturn(250f);
        when(event.getY(0)).thenReturn(150f);
        when(event.getHistorySize()).thenReturn(1);
        when(event.getHistoricalX(eq(0))).thenReturn(200f);
        when(event.getHistoricalY(eq(0))).thenReturn(100f);
        when(event.getHistoricalX(eq(0), eq(0))).thenReturn(200f);
        when(event.getHistoricalY(eq(0), eq(0))).thenReturn(100f);
        return event;
    }

    protected MotionEvent createShortUpwardsMoveEvent() {
        MotionEvent event = mock(MotionEvent.class);
        when(event.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(event.getX()).thenReturn(200f);
        when(event.getY()).thenReturn(100f);
        when(event.getX(0)).thenReturn(200f);
        when(event.getY(0)).thenReturn(100f);
        when(event.getHistorySize()).thenReturn(1);
        when(event.getHistoricalX(eq(0))).thenReturn(220f);
        when(event.getHistoricalY(eq(0))).thenReturn(120f);
        when(event.getHistoricalX(eq(0), eq(0))).thenReturn(220f);
        when(event.getHistoricalY(eq(0), eq(0))).thenReturn(120f);
        return event;
    }

    protected MotionEvent createLongUpwardsMoveEvent() {
        MotionEvent event = mock(MotionEvent.class);
        when(event.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(event.getX()).thenReturn(150f);
        when(event.getY()).thenReturn(50f);
        when(event.getX(0)).thenReturn(150f);
        when(event.getY(0)).thenReturn(50f);
        when(event.getHistorySize()).thenReturn(1);
        when(event.getHistoricalX(eq(0))).thenReturn(200f);
        when(event.getHistoricalY(eq(0))).thenReturn(100f);
        when(event.getHistoricalX(eq(0), eq(0))).thenReturn(200f);
        when(event.getHistoricalY(eq(0), eq(0))).thenReturn(100f);
        return event;
    }

    protected MotionEvent createDefaultUpActionEvent() {
        MotionEvent event = mock(MotionEvent.class);
        when(event.getAction()).thenReturn(MotionEvent.ACTION_UP);
        return event;
    }

    protected VerticalOverScrollBounceEffectDecorator getUUT() {
        return new VerticalOverScrollBounceEffectDecorator(mViewAdapter);
    }

    protected VerticalOverScrollBounceEffectDecorator getUUT(float touchDragRatioFwd, float touchDragRatioBck) {
        return new VerticalOverScrollBounceEffectDecorator(mViewAdapter, touchDragRatioFwd, touchDragRatioBck, DEFAULT_DECELERATE_FACTOR);
    }
}