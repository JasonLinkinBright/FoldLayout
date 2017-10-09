package com.jason.foldlayout.behavior;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * ClassName:ViewOffsetHelper
 * Description:
 * Created by Jason on 17/6/20.
 */

public class ViewOffsetHelper {
    private final View mView;
    private int mLayoutTop;
    private int mLayoutLeft;
    private int mOffsetTop;
    private int mOffsetLeft;

    ViewOffsetHelper(View view) {
        this.mView = view;
    }

    public void onViewLayout() {
        this.mLayoutTop = this.mView.getTop();
        this.mLayoutLeft = this.mView.getLeft();
        this.updateOffsets();
    }

    private void updateOffsets() {
        ViewCompat.offsetTopAndBottom(this.mView, this.mOffsetTop - (this.mView.getTop() - this.mLayoutTop));
        ViewCompat.offsetLeftAndRight(this.mView, this.mOffsetLeft - (this.mView.getLeft() - this.mLayoutLeft));
    }

    public boolean setTopAndBottomOffset(int offset) {
        if(this.mOffsetTop != offset) {
            this.mOffsetTop = offset;
            this.updateOffsets();
            return true;
        } else {
            return false;
        }
    }

    public boolean setLeftAndRightOffset(int offset) {
        if(this.mOffsetLeft != offset) {
            this.mOffsetLeft = offset;
            this.updateOffsets();
            return true;
        } else {
            return false;
        }
    }

    public int getTopAndBottomOffset() {
        return this.mOffsetTop;
    }

    public int getLeftAndRightOffset() {
        return this.mOffsetLeft;
    }

    public int getLayoutTop() {
        return this.mLayoutTop;
    }

    public int getLayoutLeft() {
        return this.mLayoutLeft;
    }
}