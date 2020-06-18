package me.everything.overscrolldemo.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import me.everything.overscrolldemo.R;
import me.everything.overscrolldemo.control.DemoItem;

public class DemoListAdapter extends DemoListAdapterBase {

    protected DemoListAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    public DemoListAdapter(LayoutInflater inflater, List<DemoItem> items) {
        super(inflater, items);
    }

    @Override
    protected DemoViewHolder createViewHolder(ViewGroup parent) {
        return new DemoViewHolder(R.layout.vertical_list_item, parent, mInflater);
    }
}
