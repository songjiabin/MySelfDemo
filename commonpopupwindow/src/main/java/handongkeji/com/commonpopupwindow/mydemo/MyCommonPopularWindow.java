package handongkeji.com.commonpopupwindow.mydemo;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

/**
 * author : 宋佳
 * time   : 2017/07/04
 * desc   : 我的  PopularWindow
 * version: 1.0.0
 */

public class MyCommonPopularWindow extends PopupWindow {

    private PopularController popularController;

    public MyCommonPopularWindow(Context context) {
        popularController = new PopularController(context, this);
    }


    @Override
    public int getWidth() {
        return popularController.contentView.getMeasuredWidth();
    }

    @Override
    public int getHeight() {
        return popularController.contentView.getMeasuredHeight();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        //小时的时候
        popularController.setBackGroundLevel(1.0f);
    }

    public interface ViewInterface {
        void getChildView(View view, int layoutResId);
    }


    public static class Builder {

        private PopularController.PopupParams params;
        private Context context;
        private ViewInterface listener;

        public Builder(Context context) {
            params = new PopularController.PopupParams(context);
        }

        public Builder setView(int layoutResId) {
            params.mView = null;
            params.layoutResId = layoutResId;
            return this;
        }

        public Builder setView(View view) {
            params.layoutResId = 0;
            params.mView = view;
            return this;
        }

        public Builder setWidthAndHeight(int width, int height) {
            params.mWidth = width;
            params.mHeight = height;
            return this;
        }

        public Builder setBackGroundLevel(float level) {
            params.isShowBg = true;
            params.bg_level = level;
            return this;
        }

        public Builder setOutsideTouchable(boolean toubalbe) {
            params.isTouchable = toubalbe;
            return this;
        }

        public Builder setAnimationStyle(int animationStyle) {
            params.isShowAnim = true;
            params.animationStyle = animationStyle;
            return this;
        }

        public Builder setViewOnclickListener(ViewInterface listener) {
            this.listener = listener;
            return this;
        }


        public MyCommonPopularWindow create() {
            MyCommonPopularWindow myCommonPopularWindow = new MyCommonPopularWindow(params.mContext);
            params.apply(myCommonPopularWindow.popularController);
            if (listener != null && params.layoutResId != 0) {
                listener.getChildView(myCommonPopularWindow.popularController.contentView, params.layoutResId);
            }
            CommonUtil.measureWidthAndHeight(myCommonPopularWindow.popularController.contentView);
            return myCommonPopularWindow;
        }
    }
}
