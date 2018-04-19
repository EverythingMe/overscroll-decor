package me.everything.overscrolldemo.view;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import me.everything.overscrolldemo.R;
import me.everything.overscrolldemo.control.DemoContentHelper;
import me.everything.overscrolldemo.control.DemoItem;

/**
 * Created by Bruce too
 * On 2016/6/16
 * At 15:04
 */
public class ViewPagerDemoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.viewpager_overscroll_demo,null,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initHorizontalViewPager(DemoContentHelper.getSpectrumItems(getResources()), ((ViewPager) view.findViewById(R.id.view_pager)));
    }

    private void initHorizontalViewPager(List<DemoItem> items, ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(items);
        viewPager.setAdapter(adapter);
        OverScrollDecoratorHelper.setUpOverScroll(viewPager);
    }

    public static class ViewPagerAdapter extends PagerAdapter{

        private List<DemoItem> items;
        public ViewPagerAdapter(List<DemoItem> items){
            this.items = items;
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(container.getContext());
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setText(items.get(position).getColorName());
            textView.setBackgroundColor(items.get(position).getColor());
            textView.setGravity(Gravity.CENTER);
            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
