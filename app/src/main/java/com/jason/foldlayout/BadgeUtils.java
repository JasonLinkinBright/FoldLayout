package com.jason.foldlayout;

import android.graphics.Color;
import android.text.TextUtils;

import cn.bingoogolapple.badgeview.BGABadgeViewHelper;
import cn.bingoogolapple.badgeview.BGABadgeable;

/**
 * ClassName:BadgeUtils
 * Description:
 * Created by Jason on 17/6/20.
 */

public class BadgeUtils {

    /**
     * 显示小圆点
     */
    public static void showBadgeView(BGABadgeable badgeable) {
        if (badgeable == null) {
            return;
        }
        badgeable.getBadgeViewHelper().setBadgeGravity(BGABadgeViewHelper.BadgeGravity.RightTop);
        badgeable.getBadgeViewHelper().setBadgeHorizontalMarginDp(0);
        badgeable.getBadgeViewHelper().setBadgeVerticalMarginDp(2);
        badgeable.getBadgeViewHelper().setBadgePaddingDp(3);
        badgeable.showCirclePointBadge();
    }

    public static void showCircleBadgeView(BGABadgeable badgeable) {
        if (badgeable == null) {
            return;
        }
        badgeable.getBadgeViewHelper().setBadgeHorizontalMarginDp(23);
        badgeable.showCirclePointBadge();
    }

    public static void showTabBarBadge(BGABadgeable bgaBadgeable, String badgeNumber) {
        if (bgaBadgeable == null) {
            return;
        }
        if (TextUtils.isEmpty(badgeNumber)) {
            return;
        }
        bgaBadgeable.getBadgeViewHelper().setBadgeGravity(BGABadgeViewHelper.BadgeGravity.RightTop);
        bgaBadgeable.getBadgeViewHelper().setBadgeHorizontalMarginDp(0);
        bgaBadgeable.getBadgeViewHelper().setBadgeVerticalMarginDp(5);
        bgaBadgeable.showTextBadge(badgeNumber);
    }

    /**
     * 显示带数字的提醒点
     *
     * @param badgeable
     * @param badgeNumber
     */
    public static void showBadgeView(BGABadgeable badgeable, String badgeNumber) {
        if (badgeable == null) {
            return;
        }
        if (TextUtils.isEmpty(badgeNumber)) {
            return;
        }
        badgeable.getBadgeViewHelper().setBadgeGravity(BGABadgeViewHelper.BadgeGravity.RightTop);
        badgeable.getBadgeViewHelper().setBadgeHorizontalMarginDp(0);
        badgeable.getBadgeViewHelper().setBadgeVerticalMarginDp(0);
        badgeable.showTextBadge(badgeNumber);
    }

    public static void showCenterBadgeView(BGABadgeable badgeable, String badgeNumber) {
        if (badgeable == null) {
            return;
        }
        if (TextUtils.isEmpty(badgeNumber)) {
            return;
        }
        badgeable.getBadgeViewHelper().setBadgeHorizontalMarginDp(23);
        badgeable.showTextBadge(badgeNumber);
    }

    public static void showOtherUserBadgeView(BGABadgeable badgeable, String badgeNumber) {
        if (badgeable == null) {
            return;
        }
        if (TextUtils.isEmpty(badgeNumber)) {
            return;
        }
        badgeable.getBadgeViewHelper().setBadgeBgColorInt(Color.TRANSPARENT);
        badgeable.getBadgeViewHelper().setBadgeHorizontalMarginDp(23);
        badgeable.showTextBadge(badgeNumber);
    }

    public static void hideBadge(BGABadgeable bgaBadgeable) {
        if(bgaBadgeable == null){
            return;
        }
        bgaBadgeable.showTextBadge("");
        bgaBadgeable.getBadgeViewHelper().setBadgeBgColorInt(Color.RED);
        bgaBadgeable.getBadgeViewHelper().setBadgeTextColorInt(Color.WHITE);
        bgaBadgeable.hiddenBadge();
    }
}
