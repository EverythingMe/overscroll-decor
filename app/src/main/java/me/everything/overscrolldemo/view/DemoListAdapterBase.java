package me.everything.overscrolldemo.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.everything.overscrolldemo.control.DemoItem;

public abstract class DemoListAdapterBase extends BaseAdapter {

    private static final int COLOR_VIEW_TYPE = 0;

    public static class DemoViewHolder {

        TextView mTextView;

        public DemoViewHolder(int resId, ViewGroup parent, LayoutInflater inflater) {
            mTextView = (TextView) inflater.inflate(resId, parent, false);
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mTextView.getContext(), "Tapped on: "+mTextView.getText(), Toast.LENGTH_SHORT).show();
                }
            });
            mTextView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(mTextView.getContext(), "Long-tapped on: "+mTextView.getText(), Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }

        public void init(DemoItem item) {
            mTextView.setText(item.getColorName());

            int color = item.getColor();
            mTextView.setBackgroundColor(color);
        }

        public View getView() {
            return mTextView;
        }
    }

    protected final LayoutInflater mInflater;
    protected List<DemoItem> mItems;

    protected DemoListAdapterBase(LayoutInflater inflater) {
        mInflater = inflater;
    }

    public DemoListAdapterBase(LayoutInflater inflater, List<DemoItem> items) {
        mInflater = inflater;
        mItems = items;
    }

    public void setItems(List items) {
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        DemoViewHolder holder;
        if (convertView == null) {
            holder = createViewHolder(parent);
            holder.getView().setTag(holder);
        } else {
            holder = (DemoViewHolder) convertView.getTag();
        }

        holder.init((DemoItem) getItem(position));
        return holder.getView();
    }

    protected abstract DemoViewHolder createViewHolder(ViewGroup parent);

    @Override
    public int getItemViewType(int position) {
        return COLOR_VIEW_TYPE;
    }

}
