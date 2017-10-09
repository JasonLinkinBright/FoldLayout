package com.jason.foldlayout.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.jason.foldlayout.R;

/**
 * ClassName:LevelBehavior
 * Description: 身份级别Behavior
 * Created by Jason on 17/6/20.
 */

public class LevelBehavior extends BaseBehavior {
    /**
     * 初始化
     */
    public LevelBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);
        int offsetTopAndBottom = mNavigationBarHeight - child.getMeasuredHeight() - mDefaultPadding;
        mAvatarCiv = (ImageView) parent.findViewById(R.id.ivAvatar);
        int mAvatarCivMeasuredWidth = mAvatarCiv.getMeasuredWidth();
        int offsetLeftAndRight = mAvatarCivMeasuredWidth + mDefaultPadding * 5;
        ViewCompat.offsetTopAndBottom(child, offsetTopAndBottom);
        ViewCompat.offsetLeftAndRight(child, offsetLeftAndRight);
        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        float fraction = Math.abs(ViewCompat.getTranslationY(dependency)) / maxMoveDistance;
        ViewCompat.setTranslationY(child, -fraction * maxMoveDistance);

        if (fraction < 0.7f) {
            ViewCompat.setAlpha(child, 1 - fraction / 0.7f);
        } else {
            ViewCompat.setAlpha(child, 0);
        }
        return true;
    }
}

