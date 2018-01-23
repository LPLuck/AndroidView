package com.view.lp.androidview;

import android.content.Context;
import android.util.TypedValue;

/**
 * 创建者：L.P
 * 创建时间：on 2018/1/23
 * 类描述：
 */

public class ScreenUtil {

    public static int dp2px(int dp,Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }
}
