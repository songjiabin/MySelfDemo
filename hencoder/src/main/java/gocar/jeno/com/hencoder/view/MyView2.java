package gocar.jeno.com.hencoder.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import gocar.jeno.com.hencoder.R;

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

        //画一些复杂的图形 一个小demo
//        drawPath(canvas);

        //用 path 简单的画一个圆
//        drawPath1(canvas);

        //用 path 画线 主要是 lineTo();   rLine();
//        drawPath2(canvas);

        // 用 path 画 贝塞尔曲线
//        drawpath3(canvas);

        // 用 path 实现 moveTo();
//        drawPaht4(canvas);

        // 实现 arcTo 的效果  意思是进行 画弧
//        drawPath5(canvas);

        //close（）的使用 意思是关闭从结束点 到起始点
//        drawPath6(canvas);


        //以上是 path 的第一类的用法  只要是描述 路径的

        //下面是 第二类的方法  辅助的设置或计算


        //设置 bitmap
        drawBitmap(canvas);

    }

    private void drawBitmap(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        canvas.drawBitmap(bitmap, 100, 100, paint);
    }

    private void drawPath6(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(200, 100);
        path.lineTo(150, 150);
        // path.close();//从当前位置 闭合这个图形 其实就是  path.lineTo(100,100）；
        canvas.drawPath(path, paint);
    }

    /**
     * @param canvas arcTo    addArc
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawPath5(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        /**
         * 这个方法和 Canvas.drawArc() 比起来，少了一个参数 useCenter，而多了一个参数 forceMoveTo 。

         少了 useCenter ，
         是因为 arcTo() 只用来画弧形而不画扇形，所以不再需要 useCenter 参数；
         而多出来的这个 forceMoveTo 参数的意思是，
         绘制是要「抬一下笔移动过去」，
         还是「直接拖着笔过去」，区别在于是否留下移动的痕迹。

         其实  addArc 意思是  arcTo(xx, xx, xx, xx, xx, xx, true); 的简化版  将forceMoveTo 写死为 true 就是 跳过去然后画
         */

        Path path = new Path();
        path.lineTo(100, 100);
        path.arcTo(100, 100, 300, 300, -90, 90, true); // 强制移动到弧形起点（无痕迹）
        canvas.drawPath(path, paint);

    }


    /**
     * 用 path 拼接的时候  moveTo 这个参数
     *
     * @param canvas
     */
    private void drawPaht4(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        Path path = new Path();
        path.lineTo(100, 100);
        path.moveTo(200, 100);  //此为移动到某个点上去  用 moveTo可以移动起始点  =此过程不会绘制图形，不过会移动点
        path.lineTo(200, 0);   // lineTo 都是想对于上个点来说的
        canvas.drawPath(path, paint);
    }


    /**
     * 画线 用拜贝塞尔
     *
     * @param canvas
     */
    private void drawpath3(Canvas canvas) {

    }

    /**
     * 画线   一天线
     *
     * @param canvas
     */
    private void drawPath2(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        Path path = new Path();
        path.lineTo(100, 100);
        path.rLineTo(100, 0); //r 是相对路径 意思是从上次的（100,100）为起点，相对来说画一条向右 100 的直线
        path.quadTo(100, 100, 500, 500);
        canvas.drawPath(path, paint);
    }

    private void drawPath1(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.addCircle(300, 300, 200, Path.Direction.CW);
        canvas.drawPath(path, paint);
    }


    /**
     * 这个是画复杂的图形
     * Path 可以描述直线、二次曲线、三次曲线、圆、椭圆、弧形、矩形、圆角矩形。把这些图形结合起来，就可以描述出很多复杂的图形。
     *
     * @param canvas
     */
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
