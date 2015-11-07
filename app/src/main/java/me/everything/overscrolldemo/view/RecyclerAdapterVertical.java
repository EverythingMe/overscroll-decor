package me.everything.overscrolldemo.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import me.everything.overscrolldemo.R;

/**
 * Created by amit on 11/4/15.
 */
public class RecyclerAdapterVertical extends AbstractRecyclerAdapter {

    public RecyclerAdapterVertical(LayoutInflater inflater) {
        super(inflater);
    }

    public RecyclerAdapterVertical(List items, LayoutInflater inflater) {
        super(inflater, items);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DemoViewHolder(R.layout.vertical_list_item, parent, mInflater);
    }

}
