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

import me.everything.android.ui.overscroll.RecyclerViewOverScrollDecorAdapter;
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator;
import me.everything.overscrolldemo.R;
import me.everything.overscrolldemo.control.DemoItem;

/**
 * Created by amit on 11/4/15.
 */
public class VertRecyclerDemoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.vert_recycler, null, false);

        initRecyclerView((RecyclerView) fragmentView.findViewById(R.id.recycler_view));

        return fragmentView;
    }

    private void initRecyclerView(RecyclerView recyclerView) {

        Resources res = getResources();
        
        List<DemoItem> items = Arrays.asList(
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
                new DemoItem(res.getColor(android.R.color.darker_gray), "GRAY"),
                new DemoItem(res.getColor(android.R.color.black), "BLACK")
        );
        LayoutInflater appInflater = LayoutInflater.from(getActivity().getApplicationContext());
        ContentAdapter adapter = new ContentAdapter(items, appInflater);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        new VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView));
    }

}

