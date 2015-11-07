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

import java.util.Arrays;
import java.util.List;

import me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator;
import me.everything.android.ui.overscroll.RecyclerViewOverScrollDecorAdapter;
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator;
import me.everything.overscrolldemo.R;
import me.everything.overscrolldemo.control.DemoItem;

/**
 * Created by amit on 11/4/15.
 */
public class RecyclerDemoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Resources res = getResources();
        final List<DemoItem> items = Arrays.asList(
                new DemoItem(res.getColor(android.R.color.holo_blue_dark), "BLUE"),
                new DemoItem(res.getColor(android.R.color.holo_blue_light), "LIGHT BLUE"),
                new DemoItem(res.getColor(android.R.color.holo_green_dark), "GREEN"),
                new DemoItem(res.getColor(android.R.color.holo_green_light), "LIGHT GREEN"),
                new DemoItem(res.getColor(android.R.color.holo_red_dark), "RED"),
                new DemoItem(res.getColor(android.R.color.holo_red_light), "LIGHT RED"),
                new DemoItem(res.getColor(android.R.color.holo_purple), "PURPLE"),
                new DemoItem(res.getColor(android.R.color.holo_orange_dark), "ORANGE"),
                new DemoItem(res.getColor(android.R.color.holo_orange_light), "LIGHT ORANGE"),
                new DemoItem(res.getColor(android.R.color.white), "WHITE"),
                new DemoItem(res.getColor(android.R.color.darker_gray), "GRAY")
        );

        View fragmentView = inflater.inflate(R.layout.recycler_overscroll_demo, null, false);
        initHorizontalRecyclerView(items, (RecyclerView) fragmentView.findViewById(R.id.horizontal_recycler_view));
        initVerticalRecyclerView(items, (RecyclerView) fragmentView.findViewById(R.id.vertical_recycler_view));
        return fragmentView;
    }

    private void initHorizontalRecyclerView(List<DemoItem> content, RecyclerView recyclerView) {
        LayoutInflater appInflater = LayoutInflater.from(getActivity().getApplicationContext());
        RecyclerView.Adapter adapter = new RecyclerAdapterHorizontal(content, appInflater);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        new HorizontalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView));
    }

    private void initVerticalRecyclerView(List<DemoItem> content, RecyclerView recyclerView) {
        LayoutInflater appInflater = LayoutInflater.from(getActivity().getApplicationContext());
        RecyclerView.Adapter adapter = new RecyclerAdapterVertical(content, appInflater);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        new VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView));
    }

}
