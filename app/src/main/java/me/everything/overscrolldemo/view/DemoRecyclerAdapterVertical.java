package me.everything.overscrolldemo.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import me.everything.overscrolldemo.R;

/**
 * @author amit
 */
public class DemoRecyclerAdapterVertical extends DemoRecyclerAdapterBase {

    public DemoRecyclerAdapterVertical(LayoutInflater inflater) {
        super(inflater);
    }

    public DemoRecyclerAdapterVertical(List items, LayoutInflater inflater) {
        super(inflater, items);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DemoViewHolder(R.layout.vertical_list_item, parent, mInflater);
    }

}
