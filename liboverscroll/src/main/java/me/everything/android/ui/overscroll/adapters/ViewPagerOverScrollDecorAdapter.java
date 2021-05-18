package me.everything.android.ui.overscroll.adapters;

import android.view.View;

import androidx.viewpager.widget.ViewPager;
import me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator;

/**
 * An adapter to enable over-scrolling over object of {@link ViewPager}
 *
 * @see HorizontalOverScrollBounceEffectDecorator
 */
public class ViewPagerOverScrollDecorAdapter implements IOverScrollDecoratorAdapter, ViewPager.OnPageChangeListener {

    private static final float APPROXIMATELY_EQUAL_DELTA = 0.01f;

    protected final ViewPager mViewPager;

    protected float mLastPagerScrollOffset;

    public ViewPagerOverScrollDecorAdapter(ViewPager viewPager) {
        this.mViewPager = viewPager;

        mViewPager.addOnPageChangeListener(this);

        mLastPagerScrollOffset = 0f;
    }

    @Override
    public View getView() {
        return mViewPager;
    }

    @Override
    public boolean isInAbsoluteStart() {
        return mViewPager.getCurrentItem() == 0 && isApproximatelyEquals(mLastPagerScrollOffset, 0f);
    }

    @Override
    public boolean isInAbsoluteEnd() {
        return mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1 &&
                (isApproximatelyEquals(mLastPagerScrollOffset, 0f) || isApproximatelyEquals(mLastPagerScrollOffset, 1f));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mLastPagerScrollOffset = positionOffset;
    }

    @Override
    public void onPageSelected(int position) {
        
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private static boolean isApproximatelyEquals(float first, float second) {
        return Math.abs(second - first) < APPROXIMATELY_EQUAL_DELTA;
    }
}
