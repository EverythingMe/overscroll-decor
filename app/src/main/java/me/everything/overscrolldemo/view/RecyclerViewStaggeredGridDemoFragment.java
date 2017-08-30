package me.everything.overscrolldemo.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import me.everything.overscrolldemo.OverScrollDemoActivity;
import me.everything.overscrolldemo.R;
import me.everything.overscrolldemo.control.DemoContentHelper;

/**
 * @author amitd
 */
public class RecyclerViewStaggeredGridDemoFragment extends Fragment {

    private static final int GRID_SPAN_COUNT = 2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.recyclerview_stgrid_overscroll_demo, null, false);
        initVerticalRecyclerView((RecyclerView) fragmentView.findViewById(R.id.vertical_recycler_view));
        return fragmentView;
    }

    private void initVerticalRecyclerView(RecyclerView recyclerView) {
        LayoutInflater appInflater = LayoutInflater.from(getActivity().getApplicationContext());
        final DemoRecyclerAdapterBase adapter = new DemoRecyclerAdapterVertical(new ArrayList<>(DemoContentHelper.getReverseSpectrumItems(getResources())), appInflater);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(GRID_SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL));
        if (OverScrollDemoActivity.mCurrentEffect == OverScrollDemoActivity.EFFECT_BOUNCE) {
            OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        } else {
            OverScrollDecoratorHelper.setUpScaleOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL, 2);
        }
    }

}
