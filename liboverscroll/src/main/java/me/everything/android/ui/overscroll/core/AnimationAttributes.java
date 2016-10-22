package me.everything.android.ui.overscroll.core;

import android.util.Property;
import android.view.View;

abstract class AnimationAttributes {
    public Property<View, Float> mProperty;
    public float mAbsOffset;
    public float mMaxOffset;

    protected abstract void init(View view);
}
