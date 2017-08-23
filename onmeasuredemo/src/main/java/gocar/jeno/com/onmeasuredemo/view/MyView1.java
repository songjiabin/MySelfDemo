package gocar.jeno.com.onmeasuredemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * author : 宋佳
 * time   : 2017/08/09
 * desc   :
 * version: 1.0.0
 */

public class MyView1 extends View {


    public MyView1(Context context) {
        super(context);
    }

    public MyView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            Log.i("数据", "onMeasure: "+"宽高都是 ----WRAP_CONTENT----  ");
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(ViewGroup.LayoutParams.WRAP_CONTENT, height);
            Log.i("数据", "onMeasure: "+"宽是 ----WRAP_CONTENT----  ");
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            Log.i("数据", "onMeasure: "+"高是 ----WRAP_CONTENT----  ");
        }else {
            Log.i("数据", "onMeasure: "+"宽高都不是 ----WRAP_CONTENT----  ");
        }

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);

    }
}
