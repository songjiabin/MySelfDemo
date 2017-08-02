package gocar.jeno.com.hencoder.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
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


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画  点
//        drawPoint(canvas);

        //画多个 点儿
//        drawPoints(canvas);

        // 画 椭圆
//        drawOval(canvas);

        //画 线
//        drawLine(canvas);

        // 画线 （多个坐标的）
//        drawLines(canvas);


        //画 椭圆
//        drawRoundRect(canvas);

        //画 扇形
//        drawArc(canvas);

        drawPath(canvas);

    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawPath(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);

        // 使用 path 对图形进行描述（这段描述代码不必看懂
        Path path = new Path(); // 初始化 Path 对象
        path.addArc(200, 200, 400, 400, -225, 225);
        path.arcTo(400, 200, 600, 400, -180, 225, false);
        path.lineTo(400, 542);
        canvas.drawPath(path, paint); // 绘制出 path 描述的图形（心形），大功告成
    }

    /**
     * 画扇形
     *
     * @param canvas
     */
    private void drawArc(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        float x = (getWidth() - getHeight() / 2) / 2;
        float y = getHeight() / 4;

        RectF oval = new RectF(x, y,
                getWidth() - x, getHeight() - y);
        canvas.drawRect(oval, paint);

        Paint paint_over = new Paint();
        paint_over.setStyle(Paint.Style.FILL);
        paint_over.setColor(Color.BLUE);

        canvas.drawArc(oval, -90, 90, true, paint_over);//其中的参数 ： RectF oval 一个矩形  , float startAngle 开始的偏移量 正数代表顺时针  负数代表逆时针, float sweepAngle  画圈的大下, boolean useCenter 是否连接到圆心,@NonNull Paint paint
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawRoundRect(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        canvas.drawRoundRect(100, 100, 500, 300, 150, 50, paint); //参数的意思是： left top right bottom 的坐标   rx 是圆角的横向的左标  ry是圆角的纵向坐标
    }


    /**
     * 画线  两个坐标就进行连接
     *
     * @param canvas
     */
    private void drawLines(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        float[] points = {20, 20, 120, 20, 70, 20, 70, 120, 20, 120, 120, 120, 150, 20, 250, 20, 150, 20, 150, 120, 250, 20, 250, 120, 150, 120, 250, 120};
        canvas.drawLines(points, paint);
    }

    /**
     * 画线  主要是两个点儿之间的线
     *
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#00FA9A"));
        paint.setStrokeWidth(40);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawLine(200, 200, 400, 400, paint);
    }


    /**
     * 画椭圆
     *
     * @param canvas
     */
    private void drawOval(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#FF7F24"));
        paint.setStyle(Paint.Style.FILL); //设置描边的画法

//        canvas.drawOval(50, 50, 300, 200, paint);

        RectF rectF = new RectF();
        rectF.set(50, 50, 300, 200);
        canvas.drawOval(rectF, paint);
    }


    /**
     * 画多个 点儿  再进行点儿的连接
     *
     * @param canvas
     */
    private void drawPoints(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#FF7F24"));
        paint.setStrokeWidth(20);
        paint.setStrokeCap(Paint.Cap.ROUND);
        float[] points = {0, 0, 50, 50, 50, 100, 100, 50, 100, 100, 150, 50, 150, 100};   // 绘制四个点：(50, 50) (50, 100) (100, 50) (100, 100)

        canvas.drawPoints(points, 2 /* 跳过两个数，即前两个 0 */,
                8 /* 一共绘制 8 个数（4 个点）*/, paint);
    }


    /**
     * 画 点儿
     *
     * @param canvas
     */
    private void drawPoint(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#FF7F24"));
        paint.setStrokeWidth(200);//设置 点 的大小
        //paint.setStrokeCap(Paint.Cap.ROUND); //圆形的 点  //圆头


        //paint.setStrokeCap(Paint.Cap.BUTT); //平头

        paint.setStrokeCap(Paint.Cap.SQUARE); //方头

        canvas.drawPoint(250, 250, paint);
    }


}
