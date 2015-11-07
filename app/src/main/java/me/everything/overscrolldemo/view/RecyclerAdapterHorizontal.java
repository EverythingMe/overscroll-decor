package me.everything.overscrolldemo.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import me.everything.overscrolldemo.R;

/**
 * Created by amit on 11/4/15.
 */
public class RecyclerAdapterHorizontal extends AbstractRecyclerAdapter {

    public RecyclerAdapterHorizontal(LayoutInflater inflater) {
        super(inflater);
    }

    public RecyclerAdapterHorizontal(List items, LayoutInflater inflater) {
        super(inflater, items);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DemoViewHolder(R.layout.horizontal_list_item, parent, mInflater);
    }

}
