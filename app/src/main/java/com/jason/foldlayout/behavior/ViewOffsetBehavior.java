package com.jason.foldlayout.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * ClassName:ViewOffsetBehavior
 * Description:
 * Created by Jason on 17/6/20.
 */

public class ViewOffsetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    private ViewOffsetHelper mViewOffsetHelper;
    private int mTempTopBottomOffset = 0;
    private int mTempLeftRightOffset = 0;

    public ViewOffsetBehavior() {
    }

    public ViewOffsetBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onLayoutChild(CoordinatorLayout parent, V child, int layoutDirection) {
        this.layoutChild(parent, child, layoutDirection);
        if(this.mViewOffsetHelper == null) {
            this.mViewOffsetHelper = new ViewOffsetHelper(child);
        }

        this.mViewOffsetHelper.onViewLayout();
        if(this.mTempTopBottomOffset != 0) {
            this.mViewOffsetHelper.setTopAndBottomOffset(this.mTempTopBottomOffset);
            this.mTempTopBottomOffset = 0;
        }

        if(this.mTempLeftRightOffset != 0) {
            this.mViewOffsetHelper.setLeftAndRightOffset(this.mTempLeftRightOffset);
            this.mTempLeftRightOffset = 0;
        }

        return true;
    }

    protected void layoutChild(CoordinatorLayout parent, V child, int layoutDirection) {
        parent.onLayoutChild(child, layoutDirection);
    }

    public boolean setTopAndBottomOffset(int offset) {
        if(this.mViewOffsetHelper != null) {
            return this.mViewOffsetHelper.setTopAndBottomOffset(offset);
        } else {
            this.mTempTopBottomOffset = offset;
            return false;
        }
    }

    public boolean setLeftAndRightOffset(int offset) {
        if(this.mViewOffsetHelper != null) {
            return this.mViewOffsetHelper.setLeftAndRightOffset(offset);
        } else {
            this.mTempLeftRightOffset = offset;
            return false;
        }
    }

    public int getTopAndBottomOffset() {
        return this.mViewOffsetHelper != null?this.mViewOffsetHelper.getTopAndBottomOffset():0;
    }

    public int getLeftAndRightOffset() {
        return this.mViewOffsetHelper != null?this.mViewOffsetHelper.getLeftAndRightOffset():0;
    }
}

