package com.jason.foldlayout.behavior;


import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.jason.foldlayout.R;

/**
 * ClassName:BaseBehavior
 * Description:
 * Created by Jason on 17/6/20.
 */

public class BaseBehavior extends CoordinatorLayout.Behavior {
    /**
     * NavigationBar的高度
     */
    protected int mNavigationBarHeight;

    /**
     * TitleBar的高度
     */
    protected int mTitleBarHeight;

    /**
     * 状态栏的高度
     */
    protected int mStatusBarHeight;

    /**
     * 最大移动距离
     */
    protected int maxMoveDistance;

    /**
     * 屏幕的宽度
     */
    protected int mScreenWidth;

    /**
     * 圆形头像
     */
    protected ImageView mAvatarCiv;

    /**
     * 默认内边距
     */
    protected int mDefaultPadding;

    /**
     * 最大字号
     */
    protected int maxTextSize;

    /**
     * 最小字号
     */
    protected int minTextSize;

    /**
     * 初始化
     */
    public BaseBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources resources = context.getResources();
        // 从dimens.xml文件中获取NavigationBar的高度值
        mNavigationBarHeight = resources.getDimensionPixelSize(R.dimen.dimen_navigation_bar_height);
        // 获取TitleBar的高度
        mTitleBarHeight = resources.getDimensionPixelSize(R.dimen.dimen_title_bar_height);
        // 获取状态栏的高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            mStatusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
        }
        // 本项目中，最大移动距离为NavigationBar的高度 - TitleBar的高度 - 状态栏的高度
        maxMoveDistance = mNavigationBarHeight - mTitleBarHeight;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            maxMoveDistance -= mStatusBarHeight;
        }
        // 获取屏幕的宽度
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        mScreenWidth = displayMetrics.widthPixels;
        // 获取默认内边距
        mDefaultPadding = resources.getDimensionPixelOffset(R.dimen.dimen_default_padding);
        // 获取最大字体大小
        maxTextSize = resources.getDimensionPixelSize(R.dimen.dimen_text_size_huge);
        // 获取最小字体大小
        minTextSize = resources.getDimensionPixelSize(R.dimen.textsize_small);
    }

    /**
     * 判断依赖关系
     */
    protected boolean isDependOn(View dependency) {
        return dependency != null && dependency.getId() == R.id.rlUserInfo;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return isDependOn(dependency);
    }
}