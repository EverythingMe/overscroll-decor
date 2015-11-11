package me.everything.overscrolldemo.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import me.everything.android.ui.overscroll.HorizontalOverScrollBounceEffectDecorator;
import me.everything.android.ui.overscroll.adapters.StaticOverScrollDecorAdapter;
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator;
import me.everything.overscrolldemo.R;

/**
 * @author amitd
 */
public class MiscViewDemoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.misc_overscroll_demo, null, false);

        View textView = fragmentView.findViewById(R.id.demo_text);
        new HorizontalOverScrollBounceEffectDecorator(new StaticOverScrollDecorAdapter(textView));

        View imageView = fragmentView.findViewById(R.id.demo_image);
        new VerticalOverScrollBounceEffectDecorator(new StaticOverScrollDecorAdapter(imageView));

        Chronometer chrono = (Chronometer) fragmentView.findViewById(R.id.demo_chronometer);
        new HorizontalOverScrollBounceEffectDecorator(new StaticOverScrollDecorAdapter(chrono));
        chrono.start();

        return fragmentView;
    }

}
