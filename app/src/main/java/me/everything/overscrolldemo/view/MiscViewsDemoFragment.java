package me.everything.overscrolldemo.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import me.everything.overscrolldemo.R;

/**
 * @author amitd
 */
public class MiscViewsDemoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.misc_overscroll_demo, null, false);

        View textView = fragmentView.findViewById(R.id.demo_text);
        OverScrollDecoratorHelper.setUpStaticOverScroll(textView, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);

        View imageView = fragmentView.findViewById(R.id.demo_image);
        OverScrollDecoratorHelper.setUpStaticOverScroll(imageView, OverScrollDecoratorHelper.ORIENTATION_VERTICAL);

        Chronometer chrono = (Chronometer) fragmentView.findViewById(R.id.demo_chronometer);
        OverScrollDecoratorHelper.setUpStaticOverScroll(chrono, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
        chrono.start();

        return fragmentView;
    }

}
