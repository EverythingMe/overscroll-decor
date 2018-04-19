package me.everything.overscrolldemo.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import me.everything.overscrolldemo.R;

public class SwipeRefreshDemoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final SwipeRefreshLayout view = (SwipeRefreshLayout) inflater.inflate(R.layout.fragment_swiperefresh_overscroll_demo, container, false);
        NestedScrollView nestedScrollView = view.findViewById(R.id.scrollView);
        OverScrollDecoratorHelper.setUpOverScroll(nestedScrollView, true);
        return view;
    }
}