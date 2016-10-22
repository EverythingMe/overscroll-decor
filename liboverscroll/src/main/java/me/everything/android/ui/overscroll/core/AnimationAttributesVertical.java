package me.everything.android.ui.overscroll.core;

import android.view.View;

class AnimationAttributesVertical extends AnimationAttributes {

    public AnimationAttributesVertical() {
        mProperty = View.TRANSLATION_Y;
    }

    @Override
    protected void init(View view) {
        mAbsOffset = view.getTranslationY();
        mMaxOffset = view.getHeight();
    }
}
