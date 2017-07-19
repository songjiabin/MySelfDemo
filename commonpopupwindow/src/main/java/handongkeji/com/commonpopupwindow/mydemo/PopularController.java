package handongkeji.com.commonpopupwindow.mydemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * author : 宋佳
 * time   : 2017/07/04
 * desc   :  这个是 popular的控制类
 * version: 1.0.0
 */

public class PopularController {


    private PopupWindow popuWindow;
    private Context context;
    public View contentView;
    private Window mWindow;


    public PopularController(Context context, PopupWindow popupWindow) {
        this.context = context;
        this.popuWindow = popupWindow;
    }

    public void createView(int layoutId) {
        contentView = null;
        this.contentView = LinearLayout.inflate(context, layoutId, null);
        initContentView();
    }

    public void createView(View view) {
        contentView = null;
        this.contentView = view;
        initContentView();
    }

    /**
     * 初始化 popularWindow中的内容 view
     */
    public void initContentView() {
        this.popuWindow.setContentView(contentView);
    }

    /**
     * 设置宽度和高度
     *
     * @param width
     * @param height
     */
    public void setWidthAndHeight(int width, int height) {
        if (width == 0 || height == 0) {
            this.popuWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            this.popuWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            this.popuWindow.setWidth(width);
            this.popuWindow.setHeight(height);
        }
    }

    /**
     * 设置背景透明度
     *
     * @param level (0~~1)
     */
    public void setBackGroundLevel(float level) {
        mWindow = ((Activity) context).getWindow();
        WindowManager.LayoutParams params = mWindow.getAttributes();
        params.alpha = level;
        mWindow.setAttributes(params);
    }

    /**
     * 设置popular的动画状态
     *
     * @param animationStyle
     */
    public void setAnimationStyle(int animationStyle) {
        this.popuWindow.setAnimationStyle(animationStyle);
    }


    /**
     * 设置 popular外部是否可以点击
     *
     * @param touchable
     */
    public void setOutSideTouchable(boolean touchable) {
        this.popuWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置透明度
        this.popuWindow.setOutsideTouchable(touchable);
        this.popuWindow.setFocusable(touchable);
    }


    public static class PopupParams {
        public int layoutResId;//布局id
        public Context mContext;
        public int mWidth, mHeight;//弹窗的宽和高
        public boolean isShowBg, isShowAnim;
        public float bg_level;//屏幕背景灰色程度
        public int animationStyle;//动画Id
        public View mView;
        public boolean isTouchable = true;

        public PopupParams(Context context) {
            this.mContext = context;
        }

        public void apply(PopularController controller) {
            if (mView != null) {
                controller.createView(mView);
            } else if (layoutResId != 0) {
                controller.createView(layoutResId);
            } else {
                throw new IllegalArgumentException("PopupView's contentView is null");
            }
            controller.setWidthAndHeight(mWidth, mHeight);
            controller.setOutSideTouchable(isTouchable);
            if (isShowBg) {
                controller.setBackGroundLevel(bg_level);
            }
            if (isShowAnim) {
                controller.setAnimationStyle(animationStyle);
            }
        }

    }


}
