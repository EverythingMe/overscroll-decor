package me.everything.overscrolldemo.view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import me.everything.overscrolldemo.R;

public class DemoRecyclerAdapterVertical extends DemoRecyclerAdapterBase {

    public DemoRecyclerAdapterVertical(LayoutInflater inflater) {
        super(inflater);
    }

    public DemoRecyclerAdapterVertical(List items, LayoutInflater inflater) {
        super(inflater, items);
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DemoViewHolder(R.layout.vertical_list_item, parent, mInflater);
    }
}
