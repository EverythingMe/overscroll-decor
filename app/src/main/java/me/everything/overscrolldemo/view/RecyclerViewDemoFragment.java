package me.everything.overscrolldemo.view;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import me.everything.android.ui.overscroll.IOverScrollDecor;
import me.everything.android.ui.overscroll.IOverScrollStateListener;
import me.everything.android.ui.overscroll.IOverScrollUpdateListener;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator;
import me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter;
import me.everything.overscrolldemo.R;
import me.everything.overscrolldemo.control.DemoContentHelper;

import static me.everything.android.ui.overscroll.IOverScrollState.*;

/**
 * @author amitd
 */
public class RecyclerViewDemoFragment extends Fragment {

    private TextView mHorizScrollMeasure;
    private TextView mVertScrollMeasure;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.recyclerview_overscroll_demo, null, false);

        mHorizScrollMeasure = (TextView) fragmentView.findViewById(R.id.horizontal_scroll_measure);
        mVertScrollMeasure = (TextView) fragmentView.findViewById(R.id.vertical_scroll_measure);

        initHorizontalRecyclerView((RecyclerView) fragmentView.findViewById(R.id.horizontal_recycler_view));
        initVerticalRecyclerView((RecyclerView) fragmentView.findViewById(R.id.vertical_recycler_view));
        return fragmentView;
    }

    private void initHorizontalRecyclerView(RecyclerView recyclerView) {
        LayoutInflater appInflater = LayoutInflater.from(getActivity().getApplicationContext());
        RecyclerView.Adapter adapter = new DemoRecyclerAdapterHorizontal(DemoContentHelper.getSpectrumItems(getResources()), appInflater);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        // Apply over-scroll in 'standard form' - i.e. using the helper.
        IOverScrollDecor decor = OverScrollDecoratorHelper.setUpOverScroll(recyclerView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);

        // Over-scroll listeners can be applied in standard form as well.
        decor.setOverScrollUpdateListener(new IOverScrollUpdateListener() {
            @Override
            public void onOverScrollUpdate(IOverScrollDecor decor, int state, float offset) {
                mHorizScrollMeasure.setText(String.valueOf((int) offset));
            }
        });
        decor.setOverScrollStateListener(new IOverScrollStateListener() {

            private final int mDragColorLeft = getResources().getColor(android.R.color.holo_purple);
            private final int mBounceBackColorLeft = getResources().getColor(android.R.color.holo_blue_light);
            private final int mDragColorRight = getResources().getColor(android.R.color.holo_red_light);
            private final int mBounceBackColorRight = getResources().getColor(android.R.color.holo_orange_dark);
            private final int mClearColor = mHorizScrollMeasure.getCurrentTextColor();

            @Override
            public void onOverScrollStateChange(IOverScrollDecor decor, int oldState, int newState) {
                if (newState == STATE_DRAG_START_SIDE) {
                    mHorizScrollMeasure.setTextColor(mDragColorLeft);
                } else if (newState == STATE_DRAG_END_SIDE) {
                    mHorizScrollMeasure.setTextColor(mDragColorRight);
                } else if (newState == STATE_BOUNCE_BACK) {
                    mHorizScrollMeasure.setTextColor((oldState == STATE_DRAG_START_SIDE) ? mBounceBackColorLeft : mBounceBackColorRight);
                } else {
                    mHorizScrollMeasure.setTextColor(mClearColor);
                }
            }
        });
    }

    private void initVerticalRecyclerView(RecyclerView recyclerView) {
        LayoutInflater appInflater = LayoutInflater.from(getActivity().getApplicationContext());
        final DemoRecyclerAdapterBase adapter = new DemoRecyclerAdapterVertical(new ArrayList<>(DemoContentHelper.getReverseSpectrumItems(getResources())), appInflater);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        // Set-up of recycler-view's native item swiping.
        ItemTouchHelper.Callback itemTouchHelperCallback = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, ItemTouchHelper.RIGHT);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Item swiping is supported!")
                        .setMessage("Recycler-view's native item swiping and the over-scrolling effect can co-exist! But, to get them to work WELL -- please apply the effect using the dedicated helper method!")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .create();
                dialog.show();
            }
        };

        // Apply over-scroll in 'advanced form' - i.e. create an instance manually.
        VerticalOverScrollBounceEffectDecorator decorator = new VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView, itemTouchHelperCallback));

        // Over-scroll listeners are applied here via the decorator explicitly.
        decorator.setOverScrollUpdateListener(new IOverScrollUpdateListener() {
            @Override
            public void onOverScrollUpdate(IOverScrollDecor decor, int state, float offset) {
                mVertScrollMeasure.setText(String.valueOf((int) offset));
            }
        });
        decorator.setOverScrollStateListener(new IOverScrollStateListener() {
            private final int mDragColorTop = getResources().getColor(android.R.color.holo_red_light);
            private final int mBounceBackColorTop = getResources().getColor(android.R.color.holo_orange_dark);
            private final int mDragColorBottom = getResources().getColor(android.R.color.holo_purple);
            private final int mBounceBackColorBottom = getResources().getColor(android.R.color.holo_blue_light);
            private final int mClearColor = mHorizScrollMeasure.getCurrentTextColor();

            @Override
            public void onOverScrollStateChange(IOverScrollDecor decor, int oldState, int newState) {
                if (newState == STATE_DRAG_START_SIDE) {
                    mVertScrollMeasure.setTextColor(mDragColorTop);
                } else if (newState == STATE_DRAG_END_SIDE) {
                    mVertScrollMeasure.setTextColor(mDragColorBottom);
                } else if (newState == STATE_BOUNCE_BACK) {
                    mVertScrollMeasure.setTextColor(oldState == STATE_DRAG_START_SIDE ? mBounceBackColorTop : mBounceBackColorBottom);
                } else {
                    mVertScrollMeasure.setTextColor(mClearColor);
                }
            }
        });
    }

}
