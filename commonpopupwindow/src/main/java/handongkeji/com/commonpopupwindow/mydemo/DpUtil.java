package handongkeji.com.commonpopupwindow.mydemo;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * author : 宋佳
 * time   : 2017/07/04
 * desc   :
 * version: 1.0.0
 */

public class DpUtil {
    /**
     * dp转换成px
     *
     * @param context Context
     * @param dp      dp
     * @return px值
     */
    public static float dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    /**
     * sp转换成px
     *
     * @param context Context
     * @param sp      sp
     * @return px值
     */
    public static float sp2px(Context context, float sp) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    /**
     * 获得屏幕宽度
     *
     * @param context Context
     * @return 屏幕宽度（像素）
     */
    public static int getScreenSizeWidth(Context context) {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metric);
        return metric.widthPixels;
    }


}
