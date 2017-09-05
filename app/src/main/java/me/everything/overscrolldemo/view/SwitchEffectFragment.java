package me.everything.overscrolldemo.view;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.everything.overscrolldemo.OverScrollDemoActivity;
import me.everything.overscrolldemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SwitchEffectFragment extends Fragment {

    TextView mTvEffect;

    public SwitchEffectFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_switch_effect, container, false);
        mTvEffect = (TextView) fragmentView.findViewById(R.id.tv_effect_type);
        if (OverScrollDemoActivity.mCurrentEffect == OverScrollDemoActivity.EFFECT_BOUNCE) {
            mTvEffect.setText("current effect is bounce");
        } else {
            mTvEffect.setText("current effect is scale");
        }
        return fragmentView;
    }

}
