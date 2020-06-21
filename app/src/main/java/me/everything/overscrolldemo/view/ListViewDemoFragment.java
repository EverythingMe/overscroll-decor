package me.everything.overscrolldemo.view;

import androidx.fragment.app.Fragment;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import me.everything.overscrolldemo.R;
import me.everything.overscrolldemo.control.DemoContentHelper;
import me.everything.overscrolldemo.control.DemoItem;

public class ListViewDemoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.listview_overscroll_demo, null, false);
        initVerticalListView(DemoContentHelper.getSpectrumItems(getResources()), (ListView) fragmentView.findViewById(R.id.list_view));
        return fragmentView;
    }

    private void initVerticalListView(List<DemoItem> content, ListView listView) {
        LayoutInflater appInflater = LayoutInflater.from(getActivity().getApplicationContext());
        ListAdapter adapter = new DemoListAdapter(appInflater, content);
        listView.setAdapter(adapter);

        OverScrollDecoratorHelper.setUpOverScroll(listView);
    }
}
