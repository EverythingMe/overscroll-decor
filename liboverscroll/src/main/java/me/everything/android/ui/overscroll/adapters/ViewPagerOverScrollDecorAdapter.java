package me.everything.android.ui.overscroll.adapters;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator;
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator;

/**
 * Created by Bruce too
 * On 2016/6/16
 * At 14:51
 * An adapter to enable over-scrolling over object of {@link ViewPager}
 *
 * @see HorizontalOverScrollBounceEffectDecorator
 * @see VerticalOverScrollBounceEffectDecorator
 */
public class ViewPagerOverScrollDecorAdapter implements IOverScrollDecoratorAdapter {

    protected final ViewPager mViewPager;

    public ViewPagerOverScrollDecorAdapter(ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    @Override
    public View getView() {
        return mViewPager;
    }

    @Override
    public boolean isInAbsoluteStart() {
        PagerAdapter adapter = mViewPager.getAdapter();
        if (null != adapter) {
            if (mViewPager.getCurrentItem() == 0) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean isInAbsoluteEnd() {
        PagerAdapter adapter = mViewPager.getAdapter();
        if (null != adapter && adapter.getCount() > 0) {
            if (mViewPager.getCurrentItem() == adapter.getCount() - 1) {
                return true;
            }
            return false;
        }
        return false;
    }
}
