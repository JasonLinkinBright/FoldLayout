package com.jason.foldlayout.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;


import com.jason.foldlayout.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:UserInfoBehavior
 * Description:
 * 用于调整‘用户相关信息’位置的Behavior，主要依赖于NestedScrollingView
 * Created by Jason on 17/6/20.
 */

public class UserInfoBehavior extends BaseBehavior implements NeedExpandListener {
    /**
     * 代表上滑还是下滑，true为上滑，false为下滑
     */
    private boolean mUp;
    /**
     * 用户信息背景，此处为child本身
     */
    private View mUserInfoRl;
    /**
     * 我的页面Fragment对象
     */
    private MineContentAdapter mineContentAdapter;
    /**
     * 当前是否为手指控制滑动
     */
    private boolean mControlChange;
    /**
     * 动画
     */
    private ValueAnimator mValueAnimator;
    private List<View> mHardwareViews;
    RecyclerView contentRv;

    public UserInfoBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mValueAnimator = ValueAnimator.ofFloat(0, 1);
        mHardwareViews = new ArrayList<>();
    }


    /**
     * 计算出用户信息应该出现的位置
     *
     * @param parent
     * @param child
     * @param layoutDirection
     * @return
     */
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);
        mUserInfoRl = child;
        contentRv = (RecyclerView) parent.findViewById(R.id.rv_content);
        // 计算出用户信息的位置
        // 背景图的高度
        // 获取导航背景View
        ImageView imageView = (ImageView) parent.findViewById(R.id.backdrop);
        // 获取导航背景View的高度
        int navigationBarHeight = imageView.getMeasuredHeight();
        // 将用户信息View的位置移动到NavigationBar的下面
        ViewCompat.offsetTopAndBottom(child, navigationBarHeight);
        if (mHardwareViews.size() == 0) {
            mHardwareViews.add(parent.findViewById(R.id.ivAvatar));
            mHardwareViews.add(parent.findViewById(R.id.tvUserName));
            mHardwareViews.add(parent.findViewById(R.id.tvPost));
            mHardwareViews.add(parent.findViewById(R.id.tvAttention));
            mHardwareViews.add(parent.findViewById(R.id.tvAttentionCount));
            mHardwareViews.add(parent.findViewById(R.id.tvPraise));
            mHardwareViews.add(parent.findViewById(R.id.tvPraiseCount));
            mHardwareViews.add(parent.findViewById(R.id.tvFans));
            mHardwareViews.add(parent.findViewById(R.id.tvFansCount));
            mHardwareViews.add(parent.findViewById(R.id.rlUserInfo));
            mHardwareViews.add(parent.findViewById(R.id.divider2));
            mHardwareViews.add(parent.findViewById(R.id.divider1));
            mHardwareViews.add(parent.findViewById(R.id.tv_introduction));

            //开启硬件离屏缓存
            mValueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    for (View v : mHardwareViews) {
                        v.setLayerType(View.LAYER_TYPE_NONE, null);
                    }
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                    for (View v : mHardwareViews) {
                        v.setLayerType(View.LAYER_TYPE_HARDWARE, null);
                    }
                }
            });
        }
        return true;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        mControlChange = true; // 开始嵌套滚动，此时滚动为手指控制
        if (mineContentAdapter == null) {
            mineContentAdapter = (MineContentAdapter) contentRv.getAdapter();
        }
        // 设置NeedExpendListener
        if (mineContentAdapter != null
                && mineContentAdapter instanceof SupportNeedExpandListener
                && mineContentAdapter.getNeedExpendListener() == null) {
            mineContentAdapter.setNeedExpendListener(this);
        }
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        mUp = dy > 0; // dy > 0为上滑
        float childTranslationY = ViewCompat.getTranslationY(child);
        // 如果NestScrollView需要滑动，就执行NestedScrollView的滑动
        if (isChildRequestScroll(childTranslationY)) {
            consumed[1] = 0;
            return;
        }
        // 如果不需要，让coordinatorLayout全部消耗
        consumed[1] = dy;//全部消耗
        // 降低速度
        int distance = -dy / 2;
        if (childTranslationY + distance < -maxMoveDistance) {
            distance = -maxMoveDistance;
        } else if (childTranslationY + distance > 0) {
            distance = 0;
        } else {
            distance = (int) (childTranslationY + distance);
        }
        ViewCompat.setTranslationY(child, distance);
    }

    /**
     * 判断NestedScrollView是否需要滚动
     */
    private boolean isChildRequestScroll(float translationY) {
        return translationY == -maxMoveDistance
                && mineContentAdapter instanceof IsChildRequestScrollListener
                && mineContentAdapter.requestScroll(mUp);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        mControlChange = false; // 此时不是手指控制滚动
        float childTranslationY = ViewCompat.getTranslationY(child);
        if (childTranslationY == -maxMoveDistance || childTranslationY == 0) {
            return;
        }
        // 当手指停止滚动时，将动画执行完毕
        scroll(child, childTranslationY);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
        return !isChildRequestScroll(ViewCompat.getTranslationY(child));
    }

    private void scroll(final View child, final float translationY) {
        final float shouldMoveDistance;
        if (mUp) {
            //没超过 1/8
            if (Math.abs(translationY) < maxMoveDistance / 8f) {
                shouldMoveDistance = Math.abs(translationY);
            } else {
                shouldMoveDistance = Math.abs(translationY) - maxMoveDistance;
            }
        } else {//向下滑动
            // 没超过 1/8
            if (Math.abs(translationY) > maxMoveDistance * 7f / 8f) {
                shouldMoveDistance = -(maxMoveDistance + translationY);
            } else {
                shouldMoveDistance = Math.abs(translationY);
            }
        }

        mValueAnimator.setDuration((long) (Math.abs(shouldMoveDistance) / maxMoveDistance * 300));
        mValueAnimator.removeAllUpdateListeners();
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float offset = translationY + animation.getAnimatedFraction() * shouldMoveDistance;
                ViewCompat.setTranslationY(child, offset);
            }
        });
        mValueAnimator.start();
    }


    @Override
    public void needExpand() {
        if (!mControlChange) {
            mValueAnimator.setDuration(300);
            mValueAnimator.removeAllUpdateListeners();
            mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float offset = (animation.getAnimatedFraction() - 1) * maxMoveDistance;
                    ViewCompat.setTranslationY(mUserInfoRl, offset);
                }
            });
            mValueAnimator.start();
        }
    }
}

