package me.everything.overscrolldemo.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import me.everything.overscrolldemo.R;
import me.everything.overscrolldemo.control.DemoItem;

/**
 * Created by amit on 11/8/15.
 */
public class DemoListAdapterHorizontal extends DemoListAdapterBase {

    protected DemoListAdapterHorizontal(LayoutInflater inflater) {
        super(inflater);
    }

    public DemoListAdapterHorizontal(LayoutInflater inflater, List<DemoItem> items) {
        super(inflater, items);
    }

    @Override
    protected DemoViewHolder createViewHolder(ViewGroup parent) {
        return new DemoViewHolder(R.layout.horizontal_list_item, parent, mInflater);
    }
}
