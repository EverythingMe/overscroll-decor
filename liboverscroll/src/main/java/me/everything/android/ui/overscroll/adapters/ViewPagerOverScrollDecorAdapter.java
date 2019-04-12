package me.everything.android.ui.overscroll.adapters;

import android.support.v4.view.ViewPager;
import android.view.View;

import me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator;

/**
 * Created by Bruce too
 * Enhance by amit
 * On 2016/6/16
 * At 14:51
 * An adapter to enable over-scrolling over object of {@link ViewPager}
 *
 * @see HorizontalOverScrollBounceEffectDecorator
 */
public class ViewPagerOverScrollDecorAdapter implements IOverScrollDecoratorAdapter, ViewPager.OnPageChangeListener {

    private static final float APPROXIMATELY_EQUAL_DELTA = 0.001f;

    protected final ViewPager mViewPager;

    protected int mLastPagerPosition = 0;
    protected float mLastPagerScrollOffset;

    public ViewPagerOverScrollDecorAdapter(ViewPager viewPager) {
        this.mViewPager = viewPager;

        mViewPager.addOnPageChangeListener(this);

        mLastPagerPosition = mViewPager.getCurrentItem();
        mLastPagerScrollOffset = 0f;
    }

    @Override
    public View getView() {
        return mViewPager;
    }

    @Override
    public boolean isInAbsoluteStart() {
        return mLastPagerPosition == 0 && isApproximatelyEquals(mLastPagerScrollOffset, 0f);
    }

    @Override
    public boolean isInAbsoluteEnd() {
        return mLastPagerPosition == mViewPager.getAdapter().getCount() - 1 &&
                (isApproximatelyEquals(mLastPagerScrollOffset, 0f) || isApproximatelyEquals(mLastPagerScrollOffset, 1f));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mLastPagerScrollOffset = positionOffset;
    }

    @Override
    public void onPageSelected(int position) {
        mLastPagerPosition = position;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private static boolean isApproximatelyEquals(float first, float second) {
        return Math.abs(second - first) < APPROXIMATELY_EQUAL_DELTA;
    }
}
