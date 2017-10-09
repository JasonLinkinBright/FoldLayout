package com.jason.foldlayout.behavior;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.jason.foldlayout.R;

/**
 * ClassName:AvatarBehavior
 * Description:
 * 用于调整‘头像’位置的Behavior，
 * 依赖于R.id.rlUserInfo，
 * 根据它的状态改变来改变自身的状态
 * Created by Jason on 17/6/20.
 */

public class AvatarBehavior extends BaseBehavior {
    /**
     * 头像默认距离左侧的大小
     */
    private int defaultMargin;

    /**
     * 头像默认的高度，也就是最开始高度
     */
    private int mAvatarDefaultSize;
    /**
     * 头像缩放之后的大小
     */
    private int mAvatarEndSize;

    /**
     * 初始化
     */
    public AvatarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources resources = context.getResources();
        // 从dimens.xml文件中获取相关Size
        defaultMargin = resources.getDimensionPixelSize(R.dimen.dimen_avatar_left_margin);
        mAvatarDefaultSize = resources.getDimensionPixelSize(R.dimen.dimen_avatar_default_size);
        mAvatarEndSize = resources.getDimensionPixelSize(R.dimen.dimen_avatar_end_size);
    }

    /**
     * 确定头像的位置
     *
     * @param parent
     * @param child
     * @param layoutDirection
     * @return
     */
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);
        // 头像的位置为NavigationBar高度 - 头像的高度 / 2
        // 获取NavigationBar的高度
        View navigationBar = parent.findViewById(R.id.backdrop);
        int navigationBarHeight = navigationBar.getMeasuredHeight();
        // 获取头像一般的高度
        int avatarHeight = child.getMeasuredHeight();
        // 计算头像的位置
        int offset = navigationBarHeight - avatarHeight / 2;
        // 移动头像的计算的位置
        ViewCompat.offsetTopAndBottom(child, offset);
        ViewCompat.offsetLeftAndRight(child, defaultMargin);
        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        // 计算头像缩放的比例
        float fraction = Math.abs(ViewCompat.getTranslationY(dependency)) / maxMoveDistance;
        ViewCompat.setTranslationY(child, ViewCompat.getTranslationY(dependency));

        fraction = 1 - (1 - mAvatarEndSize / mAvatarDefaultSize) * fraction;
        fraction = Math.max(fraction, 0.7f);
        fraction = Math.min(fraction, 1.0f);
        ViewCompat.setPivotY(child, child.getMeasuredHeight());
        ViewCompat.setScaleX(child, fraction);
        ViewCompat.setScaleY(child, fraction);
        return true;
    }
}

