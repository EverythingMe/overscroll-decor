package me.everything.overscrolldemo.view;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator;
import me.everything.android.ui.overscroll.ListViewOverScrollDecorAdapter;
import me.everything.android.ui.overscroll.RecyclerViewOverScrollDecorAdapter;
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator;
import me.everything.overscrolldemo.R;
import me.everything.overscrolldemo.control.DemoItem;

/**
 * Created by amit on 11/4/15.
 */
public class ListViewDemoFragment extends Fragment {

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

        View fragmentView = inflater.inflate(R.layout.listview_overscroll_demo, null, false);
//        initHorizontalListView(items, (ListView) fragmentView.findViewById(R.id.horizontal_list_view));
        initVerticalListView(items, (ListView) fragmentView.findViewById(R.id.vertical_list_view));
        return fragmentView;
    }

    private void initHorizontalListView(List<DemoItem> content, ListView listView) {
        LayoutInflater appInflater = LayoutInflater.from(getActivity().getApplicationContext());
        ListAdapter adapter = new DemoListAdapterHorizontal(appInflater, content);
        listView.setAdapter(adapter);

        new HorizontalOverScrollBounceEffectDecorator(new ListViewOverScrollDecorAdapter(listView));
    }

    private void initVerticalListView(List<DemoItem> content, ListView listView) {
        LayoutInflater appInflater = LayoutInflater.from(getActivity().getApplicationContext());
        ListAdapter adapter = new DemoListAdapterVertical(appInflater, content);
        listView.setAdapter(adapter);

        new VerticalOverScrollBounceEffectDecorator(new ListViewOverScrollDecorAdapter(listView));
    }
}
