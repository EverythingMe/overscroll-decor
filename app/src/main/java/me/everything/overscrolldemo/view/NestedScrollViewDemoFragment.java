package me.everything.overscrolldemo.view;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import me.everything.overscrolldemo.R;

public class NestedScrollViewDemoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ScrollView rootView = (ScrollView) inflater.inflate(R.layout.nested_scrollview_demo, null, false);
        OverScrollDecoratorHelper.setUpOverScroll(rootView);
        return rootView;
    }
}
