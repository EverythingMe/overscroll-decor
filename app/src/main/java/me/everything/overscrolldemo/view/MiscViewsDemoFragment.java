package me.everything.overscrolldemo.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import me.everything.overscrolldemo.OverScrollDemoActivity;
import me.everything.overscrolldemo.R;

/**
 * @author amitd
 */
public class MiscViewsDemoFragment extends Fragment {

    private static final String CHRONO_TIME_SAVE_ID = "chronoTime";

    private Chronometer mChrono;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.misc_overscroll_demo, null, false);

        View textView = fragmentView.findViewById(R.id.demo_text);
        if (OverScrollDemoActivity.mCurrentEffect == OverScrollDemoActivity.EFFECT_BOUNCE) {
            OverScrollDecoratorHelper.setUpStaticOverScroll(textView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
        } else {
            OverScrollDecoratorHelper.setUpScaleStaticOverScroll(textView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL, 2);
        }

        View imageView = fragmentView.findViewById(R.id.demo_image);
        if (OverScrollDemoActivity.mCurrentEffect == OverScrollDemoActivity.EFFECT_BOUNCE) {
            OverScrollDecoratorHelper.setUpStaticOverScroll(imageView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
        } else {
            OverScrollDecoratorHelper.setUpScaleStaticOverScroll(imageView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL, 2);
        }

        mChrono = (Chronometer) fragmentView.findViewById(R.id.demo_chronometer);
        if (savedInstanceState != null) {
            mChrono.setBase(savedInstanceState.getLong(CHRONO_TIME_SAVE_ID));
        }
        if (OverScrollDemoActivity.mCurrentEffect == OverScrollDemoActivity.EFFECT_BOUNCE) {
            OverScrollDecoratorHelper.setUpStaticOverScroll(mChrono, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
        } else {
            OverScrollDecoratorHelper.setUpScaleStaticOverScroll(mChrono, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL, 2);
        }
        mChrono.start();

        return fragmentView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putLong(CHRONO_TIME_SAVE_ID, mChrono.getBase());

        super.onSaveInstanceState(outState);
    }
}
