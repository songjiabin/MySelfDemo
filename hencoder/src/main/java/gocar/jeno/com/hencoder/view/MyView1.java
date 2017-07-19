package gocar.jeno.com.hencoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * author : 宋佳
 * time   : 2017/07/14
 * desc   :
 * version: 1.0.0
 */

public class MyView1 extends View {


    private Paint mCirclePaint;

    public MyView1(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    /**
     * 初始化 画笔
     */
    private void initPaint() {
//        mCirclePaint = new Paint();
//        mCirclePaint.setColor(Color.parseColor("#FF4081"));
//        mCirclePaint.setTextSize(sp2px(15));


        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.FILL);//使用此样式绘制的几何图形和文本将被填充，忽略所有与笔触相关的设置。
        mCirclePaint.setStrokeWidth(dp2px(10));//设置 画线的宽度
        mCirclePaint.setTextSize(sp2px(15));
        mCirclePaint.setColor(Color.parseColor("#FF4081"));
        mCirclePaint.setAntiAlias(true); //设置锯齿开光  就是边缘是否平滑

    }


    /**
     * 自定义绘制的上手非常容易：
     * 提前创建好 Paint 对象，
     * 重写 onDraw()，把绘制代码写在 onDraw() 里面，
     * 就是自定义绘制最基本的实现。大概就像这样：
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
    }


    private void drawCircle(Canvas canvas) {
        canvas.drawColor(Color.parseColor("#88880000")); //给画布设置一层 透明色   半透明红色    这类颜色填充方法一般用于在绘制之前设置底色，或者在绘制之后为界面设置半透明蒙版
        canvas.drawCircle(300, 300, 200, mCirclePaint);

        RectF f=new RectF(300,500,600,600);
        canvas.drawRect(f,mCirclePaint);
    }


    /**
     * dp 2 px
     *
     * @param dpVal
     */
    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    /**
     * sp 2 px
     *
     * @param spVal
     * @return
     */
    protected int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getResources().getDisplayMetrics());

    }
}
