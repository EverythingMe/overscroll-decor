package me.everything.overscrolldemo.view;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.Arrays;
import java.util.List;

import me.everything.android.ui.overscroll.AbsListViewOverScrollDecorAdapter;
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator;
import me.everything.overscrolldemo.R;
import me.everything.overscrolldemo.control.DemoItem;

/**
 * Created by amit on 11/4/15.
 */
public class GridViewDemoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Resources res = getResources();
        final List<DemoItem> items = Arrays.asList(
                new DemoItem(res.getColor(android.R.color.holo_purple), "PURPLE"),
                new DemoItem(res.getColor(android.R.color.holo_blue_dark), "BLUE"),
                new DemoItem(res.getColor(android.R.color.holo_blue_light), "LIGHT BLUE"),
                new DemoItem(res.getColor(R.color.spectrum_cyan), "CYAN"),
                new DemoItem(res.getColor(android.R.color.holo_green_dark), "GREEN"),
                new DemoItem(res.getColor(android.R.color.holo_green_light), "LIGHT GREEN"),
                new DemoItem(res.getColor(R.color.spectrum_yellow), "YELLOW"),
                new DemoItem(res.getColor(android.R.color.holo_orange_light), "LIGHT ORANGE"),
                new DemoItem(res.getColor(android.R.color.holo_orange_dark), "ORANGE"),
                new DemoItem(res.getColor(android.R.color.holo_red_light), "LIGHT RED"),
                new DemoItem(res.getColor(android.R.color.holo_red_dark), "RED")
        );

        View fragmentView = inflater.inflate(R.layout.gridview_overscroll_demo, null, false);
        initVerticalGridView(items, (GridView) fragmentView.findViewById(R.id.grid_view));
        return fragmentView;
    }

    private void initVerticalGridView(List<DemoItem> content, GridView gridView) {
        LayoutInflater appInflater = LayoutInflater.from(getActivity().getApplicationContext());
        ListAdapter adapter = new DemoGridAdapter(appInflater, content);
        gridView.setAdapter(adapter);

        new VerticalOverScrollBounceEffectDecorator(new AbsListViewOverScrollDecorAdapter(gridView));
    }
}
