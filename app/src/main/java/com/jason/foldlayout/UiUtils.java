package com.jason.foldlayout;

import android.content.Context;

/**
 * ClassName:UiUtils
 * Description:
 * Created by Jason on 17/6/20.
 */

public class UiUtils {

    /**
     * sp值换算成px
     *
     * @param context 上下文
     * @param spValue sp的值
     * @return px的值
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
