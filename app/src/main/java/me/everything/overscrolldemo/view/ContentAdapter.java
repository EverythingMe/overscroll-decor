package me.everything.overscrolldemo.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.everything.overscrolldemo.R;
import me.everything.overscrolldemo.control.DemoItem;

/**
 * Created by amit on 11/4/15.
 */
public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int COLOR_VIEW_TYPE = 0;

    public static class DemoViewHolder extends RecyclerView.ViewHolder {
        public DemoViewHolder(ViewGroup parent, LayoutInflater inflater) {
            super(inflater.inflate(R.layout.list_item, parent, false));
        }

        public void init(DemoItem item) {
            TextView textView = getTextView();
            textView.setText(item.getColorName());

            int color = item.getColor();
            textView.setBackgroundColor(color);

            int textColor = ~(color & 0xffffff) & 0xffffffff;
            textView.setTextColor(textColor);
        }

        private TextView getTextView() {
            return (TextView) itemView;
        }
    }

    private final LayoutInflater mInflater;

    private List<DemoItem> mItems;

    public ContentAdapter(LayoutInflater inflater) {
        mInflater = inflater;
    }

    public ContentAdapter(List items, LayoutInflater inflater) {
        mItems = items;
        mInflater = inflater;
    }

    public void setItems(List items) {
        mItems = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DemoViewHolder(parent, mInflater);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DemoItem item = mItems.get(position);

        DemoViewHolder demoHolder = (DemoViewHolder) holder;
        demoHolder.init(item);
    }

    @Override
    public int getItemViewType(int position) {
        return COLOR_VIEW_TYPE;
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
