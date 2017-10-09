package com.jason.foldlayout.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import com.jason.foldlayout.R;
import com.jason.foldlayout.UiUtils;

/**
 * ClassName:NavigationBarBehavior
 * Description: 顶部背景图片
 * Created by Jason on 17/6/20.
 */

public class NavigationBarBehavior extends BaseBehavior {

    View mTvUserName, mTvPost, mTvAttention, mBgaAttentionCount, mTvPraise, mBgaPraiseCount, mTvFans, mBgaFansCount, mLlReward, mUserInfoRl, mTvIntroduction, mDivider1, mDivider2;
    int maxHeight;
    int minHeight;
    int defaultMargin;
    float density;

    public NavigationBarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        defaultMargin = context.getResources().getDimensionPixelSize(R.dimen.dimen_avatar_left_margin);
        // 获取NavigationBar的高度，NavigationBar的高度和用户信息背景的默认高度相同
        maxHeight = context.getResources().getDimensionPixelOffset(R.dimen.dimen_navigation_bar_height);
        // 用户信息背景的最小高度为原高度的一半
        minHeight = maxHeight / 2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        density = displayMetrics.density - 0.5f;
    }

    /**
     * 确定NavigationBar的位置
     */
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);
        mAvatarCiv = (ImageView) parent.findViewById(R.id.ivAvatar);
        mTvUserName = parent.findViewById(R.id.tvUserName);
        mTvPost = parent.findViewById(R.id.tvPost);
        mTvAttention = parent.findViewById(R.id.tvAttention);
        mBgaAttentionCount = parent.findViewById(R.id.tvAttentionCount);
        mTvPraise = parent.findViewById(R.id.tvPraise);
        mBgaPraiseCount = parent.findViewById(R.id.tvPraiseCount);
        mTvFans = parent.findViewById(R.id.tvFans);
        mBgaFansCount = parent.findViewById(R.id.tvFansCount);
        mUserInfoRl = parent.findViewById(R.id.rlUserInfo);
        mLlReward = parent.findViewById(R.id.ll_reward);
        mTvIntroduction = parent.findViewById(R.id.tv_introduction);
        mDivider1 = parent.findViewById(R.id.divider1);
        mDivider2 = parent.findViewById(R.id.divider2);
        ViewCompat.offsetTopAndBottom(child, 0);
        return true;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        // 让NavigationBar跟随UserInfoRl移动
        ViewCompat.setTranslationY(child, ViewCompat.getTranslationY(dependency));
        // 用户名
        float fraction = Math.abs(ViewCompat.getTranslationY(dependency)) / maxMoveDistance;
        ViewCompat.setTranslationX(mTvUserName, (-mAvatarCiv.getMeasuredHeight() + defaultMargin) * 0.2f * fraction);
        // 职称
        ViewCompat.setTranslationY(mTvPost, (-mTvUserName.getMeasuredHeight() / 2 - mDefaultPadding) * fraction);
        float userNameTranslationX = Math.abs(ViewCompat.getTranslationX(mTvUserName));
        int translationX = (int) (mTvUserName.getMeasuredWidth() / 2 * fraction);
        ViewCompat.setTranslationX(mTvPost, userNameTranslationX + translationX);
        // 奖励
        if (fraction < 0.7f) {
            ViewCompat.setAlpha(mLlReward, 1 - fraction / 0.7f);
            ViewCompat.setAlpha(mTvIntroduction, 1 - fraction / 0.7f);
            ViewCompat.setAlpha(mDivider1, 1 - fraction / 0.7f);
            ViewCompat.setAlpha(mDivider2, 1 - fraction / 0.7f);
        } else {
            ViewCompat.setAlpha(mLlReward, 0);
            ViewCompat.setAlpha(mTvIntroduction, 0);
            ViewCompat.setAlpha(mDivider1, 0);
            ViewCompat.setAlpha(mDivider2, 0);
        }
        // 赞、粉丝、关注
        ViewCompat.setTranslationX(mTvAttention, fraction);
        ViewCompat.setTranslationX(mTvPraise, fraction);
        ViewCompat.setTranslationX(mTvFans, fraction);

        int offset = UiUtils.sp2px(parent.getContext(), 10);
        ViewCompat.setTranslationX(mBgaAttentionCount, (mBgaAttentionCount.getMeasuredWidth() + offset) / density * fraction);
        ViewCompat.setTranslationX(mBgaPraiseCount, (mBgaPraiseCount.getMeasuredWidth() + offset) / density * fraction);
        ViewCompat.setTranslationX(mBgaFansCount, (mBgaFansCount.getMeasuredWidth() + offset) / density * fraction);

        int offsetSize = UiUtils.sp2px(parent.getContext(), 3.5f);
        ViewCompat.setTranslationY(mBgaAttentionCount, -offsetSize * fraction);
        ViewCompat.setTranslationY(mBgaPraiseCount, -offsetSize * fraction);
        ViewCompat.setTranslationY(mBgaFansCount, -offsetSize * fraction);

        fraction = 1 - (1 - minTextSize / maxTextSize) * fraction;
        fraction = Math.max(fraction, 0.5f);
        fraction = Math.min(fraction, 1.0f);
        ViewCompat.setPivotY(mBgaAttentionCount, child.getMeasuredHeight() / 2);
        ViewCompat.setScaleX(mBgaAttentionCount, fraction);
        ViewCompat.setScaleY(mBgaAttentionCount, fraction);
        ViewCompat.setPivotY(mBgaPraiseCount, child.getMeasuredHeight() / 2);
        ViewCompat.setScaleX(mBgaPraiseCount, fraction);
        ViewCompat.setScaleY(mBgaPraiseCount, fraction);
        ViewCompat.setPivotY(mBgaFansCount, child.getMeasuredHeight() / 2);
        ViewCompat.setScaleX(mBgaFansCount, fraction);
        ViewCompat.setScaleY(mBgaFansCount, fraction);
        // 动态改变用户信息背景的高度
        float currHeight = mUserInfoRl.getTop() + ViewCompat.getTranslationY(dependency);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mUserInfoRl.getLayoutParams();
        currHeight = Math.max(currHeight, minHeight);
        currHeight = Math.min(currHeight, maxHeight);
        params.height = (int) currHeight;
        mUserInfoRl.setLayoutParams(params);
        return true;
    }
}


