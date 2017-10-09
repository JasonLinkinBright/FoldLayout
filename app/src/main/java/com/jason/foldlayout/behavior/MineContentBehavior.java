package com.jason.foldlayout.behavior;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

import com.jason.foldlayout.R;

import java.util.List;

/**
 * ClassName:MineContentBehavior
 * Description: 底部列表内容的Behavior
 * Created by Jason on 17/6/20.
 */

public class MineContentBehavior extends HeaderScrollingViewBehavior {

    /**
     * TitleBar的高度
     */
    private int mTitleBarHeight;

    /**
     * 状态栏的高度
     */
    private int mStatusBarHeight;

    /**
     * 最大移动距离
     */
    private int maxMoveDistance;

    /**
     * 默认内边距
     */
    private int mDefaultPadding;

    /**
     * 初始化
     */
    public MineContentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources resources = context.getResources();
        // 获取TitleBar的高度
        mTitleBarHeight = resources.getDimensionPixelOffset(R.dimen.dimen_title_bar_height);
        // 获取状态栏的高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            mStatusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        // 获取默认内边距
        mDefaultPadding = resources.getDimensionPixelOffset(R.dimen.dimen_default_padding);
    }

    @Override
    View findFirstDependency(List<View> views) {
        for (View dependency : views) {
            if (isDependOn(dependency)) {
                return dependency;
            }
        }
        return null;
    }

    @Override
    int getScrollRange(View v) {
        if (isDependOn(v)) { // 最后剩余的距离为显示用户信息的高度的一半+
            return -v.getMeasuredHeight() / 2 - mTitleBarHeight + mStatusBarHeight - mDefaultPadding;
        } else {
            return super.getScrollRange(v);
        }
    }

    /**
     * 判断依赖
     */
    private boolean isDependOn(View dependency) {
        return dependency != null && dependency.getId() == R.id.rlUserInfo;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return isDependOn(dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        ViewCompat.setTranslationY(child, ViewCompat.getTranslationY(dependency));
        return true;
    }
}

