package handongkeji.com.commonpopupwindow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import handongkeji.com.commonpopupwindow.mydemo.MyCommonPopularWindow;

/**
 * author : 宋佳
 * time   : 2017/07/04
 * desc   :
 * version: 1.0.0
 */

public class MyDemoActivity extends AppCompatActivity {

    private MyCommonPopularWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydemo);
    }

    public void showUpPop(View view) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        popupWindow = new MyCommonPopularWindow.Builder(this)
                .setView(R.layout.layout_popular_2)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .create();
//        popupWindow.showAsDropDown(view, 0, -(popupWindow.getHeight() + view.getMeasuredHeight()));

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1]-popupWindow.getHeight());
    }
}
