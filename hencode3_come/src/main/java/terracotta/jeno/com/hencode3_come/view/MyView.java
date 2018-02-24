package terracotta.jeno.com.hencode3_come.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * author : 宋佳
 * time   : 2018/02/24
 * desc   :
 * version: 1.0.0
 */

public class MyView extends View {


    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);

        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);//设置抗锯齿

        //正方形
        canvas.drawColor(Color.BLUE);
        canvas.drawRect(100, 100, 500, 500, paint);

        //圆
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(300, 300, 200, paint);

        //点
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(300, 300, paint);

        //批量画点
        float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};
        // 绘制四个点：(50, 50) (50, 100) (100, 50) (100, 100)
        canvas.drawPoints(points, 2 /* 跳过两个数，即前两个 0 */,
                8 /* 一共绘制 8 个数（4 个点）*/, paint);

        //椭圆
        RectF rectF = new RectF(50, 100, 550, 500);
        paint.setColor(Color.DKGRAY);
        paint.setStrokeWidth(5);
        canvas.drawOval(rectF, paint);

        // 画线
        paint.setColor(Color.BLACK);
        canvas.drawLine(100, 100, 500, 500, paint);
        canvas.drawLine(500, 100, 100, 500, paint);


        //画椭圆
        RectF rectF1 = new RectF(100, 100, 500, 500);
        paint.setColor(Color.CYAN);
        canvas.drawRoundRect(rectF1, 150, 50, paint);


        //弧形或者扇形

    }
}





