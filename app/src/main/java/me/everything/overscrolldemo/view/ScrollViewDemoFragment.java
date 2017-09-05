package me.everything.overscrolldemo.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import me.everything.overscrolldemo.OverScrollDemoActivity;
import me.everything.overscrolldemo.R;

/**
 * @author amitd
 */
public class ScrollViewDemoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.scrollview_overscroll_demo, null, false);
        initHorizontalScrollView((HorizontalScrollView) fragmentView.findViewById(R.id.horizontal_scroll_view));
        initVerticalScrollView((ScrollView) fragmentView.findViewById(R.id.vertical_scroll_view));
        return fragmentView;
    }

    private void initHorizontalScrollView(HorizontalScrollView scrollView) {
        if (OverScrollDemoActivity.mCurrentEffect == OverScrollDemoActivity.EFFECT_BOUNCE) {
            OverScrollDecoratorHelper.setUpOverScroll(scrollView);
        } else {
            OverScrollDecoratorHelper.setUpScaleOverScroll(scrollView, 2);
        }
    }

    private void initVerticalScrollView(ScrollView scrollView) {
        if (OverScrollDemoActivity.mCurrentEffect == OverScrollDemoActivity.EFFECT_BOUNCE) {
            OverScrollDecoratorHelper.setUpOverScroll(scrollView);
        } else {
            OverScrollDecoratorHelper.setUpScaleOverScroll(scrollView, 2);
        }
    }

}
