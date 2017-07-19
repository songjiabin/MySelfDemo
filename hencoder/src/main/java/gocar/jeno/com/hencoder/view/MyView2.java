package gocar.jeno.com.hencoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * author : 宋佳
 * time   : 2017/07/14
 * desc   :
 * version: 1.0.0
 */

public class MyView2 extends View {
    public MyView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i("标记", "onDraw: " + "MyView2");
    }


}
