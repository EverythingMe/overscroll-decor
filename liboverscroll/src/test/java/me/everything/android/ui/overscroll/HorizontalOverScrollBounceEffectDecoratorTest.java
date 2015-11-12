package me.everything.android.ui.overscroll;

import android.view.MotionEvent;
import android.view.View;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import me.everything.android.ui.overscroll.adapters.IOverScrollDecoratorAdapter;

import static me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator.DEFAULT_DECELERATE_FACTOR;
import static me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator.DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyFloat;
import static org.mockito.Mockito.atLeastOnce;
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
public class HorizontalOverScrollBounceEffectDecoratorTest {

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

        MotionEvent event = createShortRightMoveEvent();

        when(mViewAdapter.isInAbsoluteStart()).thenReturn(false);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(false);

        HorizontalOverScrollBounceEffectDecorator uut = getUUT();

        // Act

        boolean ret = uut.onTouch(mView, event);

        // Assert

        verify(mView, never()).setTranslationX(anyFloat());
        verify(mView, never()).setTranslationY(anyFloat());
        assertFalse(ret);
    }

    @Test
    public void onTouchMoveAction_dragRightInLeftEnd_overscrollRight() throws Exception {

        // Arrange

        MotionEvent event = createShortRightMoveEvent();

        when(mViewAdapter.isInAbsoluteStart()).thenReturn(true);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(false);

        HorizontalOverScrollBounceEffectDecorator uut = getUUT();

        // Act

        boolean ret = uut.onTouch(mView, event);

        // Assert

        float expectedTransX = (event.getX() - event.getHistoricalX(0)) / DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD;
        verify(mView).setTranslationX(expectedTransX);
        verify(mView, never()).setTranslationY(anyFloat());
        assertTrue(ret);
    }

    @Test
    public void onTouchMoveAction_dragLeftInRightEnd_overscrollLeft() throws Exception {

        // Arrange

        MotionEvent event = createShortLeftMoveEvent();

        when(mViewAdapter.isInAbsoluteStart()).thenReturn(false);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(true);

        HorizontalOverScrollBounceEffectDecorator uut = getUUT();

        // Act

        boolean ret = uut.onTouch(mView, event);

        // Assert

        float expectedTransX = (event.getX() - event.getHistoricalX(0)) / DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD;
        verify(mView).setTranslationX(expectedTransX);
        verify(mView, never()).setTranslationY(anyFloat());
        assertTrue(ret);
    }

    @Test
    public void onTouchMoveAction_dragLeftInLeftEnd_ignoreTouchEvent() throws Exception {

        // Arrange

        MotionEvent event = createShortLeftMoveEvent();

        when(mViewAdapter.isInAbsoluteStart()).thenReturn(true);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(false);

        HorizontalOverScrollBounceEffectDecorator uut = getUUT();

        // Act

        boolean ret = uut.onTouch(mView, event);

        // Assert

        verify(mView, never()).setTranslationX(anyFloat());
        verify(mView, never()).setTranslationY(anyFloat());
        assertFalse(ret);
    }

    @Test
    public void onTouchMoveAction_dragRightInRightEnd_ignoreTouchEvent() throws Exception {

        // Arrange

        MotionEvent event = createShortRightMoveEvent();

        when(mViewAdapter.isInAbsoluteStart()).thenReturn(false);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(true);

        HorizontalOverScrollBounceEffectDecorator uut = getUUT();

        // Act

        boolean ret = uut.onTouch(mView, event);

        // Assert

        verify(mView, never()).setTranslationX(anyFloat());
        verify(mView, never()).setTranslationY(anyFloat());
        assertFalse(ret);
    }

    @Test
    public void onTouchMoveAction_2ndRightDragInLeftEnd_overscrollRightFurther() throws Exception {

        // Arrange

        // Bring UUT to a right-overscroll state
        MotionEvent event1 = createShortRightMoveEvent();

        when(mViewAdapter.isInAbsoluteStart()).thenReturn(true);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(false);

        HorizontalOverScrollBounceEffectDecorator uut = getUUT();
        uut.onTouch(mView, event1);
        reset(mView);

        // Create 2nd right-drag event
        MotionEvent event2 = createLongRightMoveEvent();

        // Act

        boolean ret = uut.onTouch(mView, event2);

        // Assert

        float expectedTransX = (event2.getX() - event2.getHistoricalX(0)) / DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD;
        verify(mView).setTranslationX(expectedTransX);
        verify(mView, never()).setTranslationY(anyFloat());
        assertTrue(ret);
    }

    @Test
    public void onTouchMoveAction_2ndLeftDragInRightEnd_overscrollLeftFurther() throws Exception {

        // Arrange

        // Bring UUT to a left-overscroll state
        MotionEvent event1 = createShortLeftMoveEvent();

        when(mViewAdapter.isInAbsoluteStart()).thenReturn(false);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(true);

        HorizontalOverScrollBounceEffectDecorator uut = getUUT();
        uut.onTouch(mView, event1);
        reset(mView);

        // Create 2nd left-drag event
        MotionEvent event2 = createLongLeftMoveEvent();

        // Act

        boolean ret = uut.onTouch(mView, event2);

        // Assert

        float expectedTransX = (event2.getX() - event2.getHistoricalX(0)) / DEFAULT_TOUCH_DRAG_MOVE_RATIO_FWD;
        verify(mView).setTranslationX(expectedTransX);
        verify(mView, never()).setTranslationY(anyFloat());
        assertTrue(ret);
    }

    /**
     * When over-scroll has already started (to the right in this case) and suddenly the user changes
     * their mind and scrolls a bit in the other direction:
     * <br/>We expect the <b>touch to still be intercepted</b> in that case, and the <b>overscroll to remain in effect</b>.
     */
    @Test
    public void onTouchMoveAction_dragLeftWhenRightOverscolled_continueOverscrollingLeft() throws Exception {

        // Arrange

        // In left & right tests we use equal ratios to avoid the effect's under-scroll handling
        final float touchDragRatioFwd = 3f;
        final float touchDragRatioBck = 3f;

        // Bring UUT to a right-overscroll state
        when(mViewAdapter.isInAbsoluteStart()).thenReturn(true);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(false);

        HorizontalOverScrollBounceEffectDecorator uut = getUUT(touchDragRatioFwd, touchDragRatioBck);
        MotionEvent eventMoveRight = createLongRightMoveEvent();
        uut.onTouch(mView, eventMoveRight);
        reset(mView);
        float startTransX = (eventMoveRight.getX() - eventMoveRight.getHistoricalX(0)) / touchDragRatioFwd;
        when(mView.getTranslationX()).thenReturn(startTransX);

        // Create the left-drag event
        MotionEvent eventMoveLeft = createShortLeftMoveEvent();

        // Act

        boolean ret = uut.onTouch(mView, eventMoveLeft);

        // Assert

        float expectedTransX = startTransX +
                                (eventMoveLeft.getX() - eventMoveLeft.getHistoricalX(0)) / touchDragRatioBck;
        verify(mView).setTranslationX(expectedTransX);
        verify(mView, never()).setTranslationY(anyFloat());
        assertTrue(ret);
    }

    /**
     * When over-scroll has already started (to the left in this case) and suddenly the user changes
     * their mind and scrolls a bit in the other direction:
     * <br/>We expect the <b>touch to still be intercepted</b> in that case, and the <b>overscroll to remain in effect</b>.
     */
    @Test
    public void onTouchMoveAction_dragRightWhenLeftOverscolled_continueOverscrollingRight() throws Exception {

        // Arrange

        // In left & right tests we use equal ratios to avoid the effect's under-scroll handling
        final float touchDragRatioFwd = 3f;
        final float touchDragRatioBck = 3f;

        // Bring UUT to a left-overscroll state
        when(mViewAdapter.isInAbsoluteStart()).thenReturn(false);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(true);

        HorizontalOverScrollBounceEffectDecorator uut = getUUT(touchDragRatioFwd, touchDragRatioBck);
        MotionEvent eventMoveLeft = createLongLeftMoveEvent();
        uut.onTouch(mView, eventMoveLeft);
        reset(mView);

        float startTransX = (eventMoveLeft.getX() - eventMoveLeft.getHistoricalX(0)) / touchDragRatioFwd;
        when(mView.getTranslationX()).thenReturn(startTransX);

        // Create the right-drag event
        MotionEvent eventMoveRight = createShortRightMoveEvent();

        // Act

        boolean ret = uut.onTouch(mView, eventMoveRight);

        // Assert

        float expectedTransX = startTransX + (eventMoveRight.getX() - eventMoveRight.getHistoricalX(0)) / touchDragRatioBck;
        verify(mView).setTranslationX(expectedTransX);
        verify(mView, never()).setTranslationY(anyFloat());
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

        HorizontalOverScrollBounceEffectDecorator uut = getUUT();

        // Act

        boolean ret = uut.onTouch(mView, event);

        // Assert

        verify(mView, never()).setTranslationX(anyFloat());
        verify(mView, never()).setTranslationY(anyFloat());
        assertFalse(ret);
    }

    /**
     * TODO: Make this work using a decent animation shadows / newer Robolectric
     * @throws Exception
     */
    @Ignore
    @Test
    public void onTouchUpAction_eventWhenLeftOverscrolling_smoothScrollBackToRightEnd() throws Exception {

        // Arrange

        // Bring UUT to a left-overscroll state
        MotionEvent moveEvent = createShortLeftMoveEvent();

        when(mViewAdapter.isInAbsoluteStart()).thenReturn(false);
        when(mViewAdapter.isInAbsoluteEnd()).thenReturn(true);

        HorizontalOverScrollBounceEffectDecorator uut = getUUT();
        uut.onTouch(mView, moveEvent);
        reset(mView);

        // Make the view as though it's been moved by the move event
        float viewX = moveEvent.getX();
        when(mView.getTranslationX()).thenReturn(viewX);

        MotionEvent upEvent = createDefaultUpActionEvent();

        // Act

        boolean ret = uut.onTouch(mView, upEvent);

        // Assert

        assertTrue(ret);

        verify(mView, atLeastOnce()).setTranslationX(anyFloat());
    }

    protected MotionEvent createShortRightMoveEvent() {
        MotionEvent event = mock(MotionEvent.class);
        when(event.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(event.getX()).thenReturn(100f);
        when(event.getY()).thenReturn(200f);
        when(event.getX(0)).thenReturn(100f);
        when(event.getY(0)).thenReturn(200f);
        when(event.getHistorySize()).thenReturn(1);
        when(event.getHistoricalX(eq(0))).thenReturn(90f);
        when(event.getHistoricalY(eq(0))).thenReturn(180f);
        when(event.getHistoricalX(eq(0), eq(0))).thenReturn(90f);
        when(event.getHistoricalY(eq(0), eq(0))).thenReturn(180f);
        return event;
    }

    protected MotionEvent createLongRightMoveEvent() {
        MotionEvent event = mock(MotionEvent.class);
        when(event.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(event.getX()).thenReturn(150f);
        when(event.getY()).thenReturn(250f);
        when(event.getX(0)).thenReturn(150f);
        when(event.getY(0)).thenReturn(250f);
        when(event.getHistorySize()).thenReturn(1);
        when(event.getHistoricalX(eq(0))).thenReturn(100f);
        when(event.getHistoricalY(eq(0))).thenReturn(200f);
        when(event.getHistoricalX(eq(0), eq(0))).thenReturn(100f);
        when(event.getHistoricalY(eq(0), eq(0))).thenReturn(200f);
        return event;
    }

    protected MotionEvent createShortLeftMoveEvent() {
        MotionEvent event = mock(MotionEvent.class);
        when(event.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(event.getX()).thenReturn(100f);
        when(event.getY()).thenReturn(200f);
        when(event.getX(0)).thenReturn(100f);
        when(event.getY(0)).thenReturn(200f);
        when(event.getHistorySize()).thenReturn(1);
        when(event.getHistoricalX(eq(0))).thenReturn(120f);
        when(event.getHistoricalY(eq(0))).thenReturn(220f);
        when(event.getHistoricalX(eq(0), eq(0))).thenReturn(120f);
        when(event.getHistoricalY(eq(0), eq(0))).thenReturn(220f);
        return event;
    }

    protected MotionEvent createLongLeftMoveEvent() {
        MotionEvent event = mock(MotionEvent.class);
        when(event.getAction()).thenReturn(MotionEvent.ACTION_MOVE);
        when(event.getX()).thenReturn(50f);
        when(event.getY()).thenReturn(150f);
        when(event.getX(0)).thenReturn(50f);
        when(event.getY(0)).thenReturn(150f);
        when(event.getHistorySize()).thenReturn(1);
        when(event.getHistoricalX(eq(0))).thenReturn(100f);
        when(event.getHistoricalY(eq(0))).thenReturn(200f);
        when(event.getHistoricalX(eq(0), eq(0))).thenReturn(100f);
        when(event.getHistoricalY(eq(0), eq(0))).thenReturn(200f);
        return event;
    }

    protected MotionEvent createDefaultUpActionEvent() {
        MotionEvent event = mock(MotionEvent.class);
        when(event.getAction()).thenReturn(MotionEvent.ACTION_UP);
        return event;
    }

    protected HorizontalOverScrollBounceEffectDecorator getUUT() {
        return new HorizontalOverScrollBounceEffectDecorator(mViewAdapter);
    }

    protected HorizontalOverScrollBounceEffectDecorator getUUT(float touchDragRatioFwd, float touchDragRatioBck) {
        return new HorizontalOverScrollBounceEffectDecorator(mViewAdapter, touchDragRatioFwd, touchDragRatioBck, DEFAULT_DECELERATE_FACTOR);
    }
}