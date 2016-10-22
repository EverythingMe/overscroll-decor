package me.everything.android.ui.overscroll.core;

import android.view.View;

class AnimationAttributesHorizontal extends AnimationAttributes {

    public AnimationAttributesHorizontal() {
        mProperty = View.TRANSLATION_X;
    }

    @Override
    protected void init(View view) {
        mAbsOffset = view.getTranslationX();
        mMaxOffset = view.getWidth();
    }
}
