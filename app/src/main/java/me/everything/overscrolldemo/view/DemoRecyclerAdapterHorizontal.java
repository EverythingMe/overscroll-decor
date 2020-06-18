package me.everything.overscrolldemo.view;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import me.everything.overscrolldemo.R;

public class DemoRecyclerAdapterHorizontal extends DemoRecyclerAdapterBase {

    public DemoRecyclerAdapterHorizontal(LayoutInflater inflater) {
        super(inflater);
    }

    public DemoRecyclerAdapterHorizontal(List items, LayoutInflater inflater) {
        super(inflater, items);
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DemoViewHolder(R.layout.horizontal_list_item, parent, mInflater);
    }

}
