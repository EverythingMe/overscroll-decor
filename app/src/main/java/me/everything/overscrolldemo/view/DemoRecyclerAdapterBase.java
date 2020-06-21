package me.everything.overscrolldemo.view;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import me.everything.overscrolldemo.control.DemoItem;

public abstract class DemoRecyclerAdapterBase extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int COLOR_VIEW_TYPE = 0;

    public static class DemoViewHolder extends RecyclerView.ViewHolder {

        public DemoViewHolder(int resId, ViewGroup parent, LayoutInflater inflater) {
            super(inflater.inflate(resId, parent, false));

            getTextView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getTextView().getContext(), "Tapped on: "+getTextView().getText(), Toast.LENGTH_SHORT).show();
                }
            });
            getTextView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(getTextView().getContext(), "Long-tapped on: "+getTextView().getText(), Toast.LENGTH_SHORT).show();
                    return false;
                }
            });
        }

        public void init(DemoItem item) {
            TextView textView = getTextView();
            textView.setText(item.getColorName());

            int color = item.getColor();
            textView.setBackgroundColor(color);
        }

        private TextView getTextView() {
            return (TextView) itemView;
        }
    }

    protected final LayoutInflater mInflater;
    protected List<DemoItem> mItems;

    protected DemoRecyclerAdapterBase(LayoutInflater inflater) {
        mInflater = inflater;
    }

    public DemoRecyclerAdapterBase(LayoutInflater inflater, List<DemoItem> items) {
        mInflater = inflater;
        mItems = items;
    }

    public void setItems(List items) {
        mItems = items;
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
