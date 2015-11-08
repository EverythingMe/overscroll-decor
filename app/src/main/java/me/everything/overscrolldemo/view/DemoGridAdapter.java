package me.everything.overscrolldemo.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import me.everything.overscrolldemo.R;
import me.everything.overscrolldemo.control.DemoItem;

/**
 * Created by amit on 11/8/15.
 */
public class DemoGridAdapter extends DemoListAdapterBase {

    protected DemoGridAdapter(LayoutInflater inflater) {
        super(inflater);
    }

    public DemoGridAdapter(LayoutInflater inflater, List<DemoItem> items) {
        super(inflater, items);
    }

    @Override
    protected DemoViewHolder createViewHolder(ViewGroup parent) {
        return new DemoViewHolder(R.layout.grid_item, parent, mInflater);
    }
}
