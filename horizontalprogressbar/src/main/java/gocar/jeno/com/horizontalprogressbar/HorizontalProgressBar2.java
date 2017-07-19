package gocar.jeno.com.horizontalprogressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * author : 宋佳
 * time   : 2017/07/12
 * desc   :
 * version: 1.0.0
 */

public class HorizontalProgressBar2 extends View {

    /**
     * 进度条背景颜色
     */
    private int bgColor = 0xFFe1e5e8;

    /**
     * 进度条颜色
     */
    private int progressColor = 0xFFf66b12;

    /**
     * 当前进度
     */
    private float currentProgress;

    /**
     * 绘制提示框的矩形
     */
    private RectF rectF = new RectF();

    /**
     * 绘制百分比的 框的矩形
     */
    private Rect textRect = new Rect();

    /**
     * 进度移动的距离
     */
    private float moveDis;

    /**
     * 画三角形的path
     */
    private Path path = new Path();

    private String textString = "0";

    private int mWidth;
    private int mHeight;


    private int progressPaintWidth;
    private int tipHeight;
    private int tipWidth;
    private int triangleHeight;
    private int roundRectRadius;
    private int textPaintSize;
    private int progressMarginTop;
    private int tipPaintWidth;
    private int mViewHeight;
    private Paint bgPaint;
    private Paint progressPaint;
    private Paint tipPaint;
    private Paint textPaint;

    public HorizontalProgressBar2(Context context) {
        this(context, null);
    }

    public HorizontalProgressBar2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProgressBar2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initPaint();
        initTextPaint();
    }


    //初始化
    private void init() {
        progressPaintWidth = dp2px(4);//进度条 画笔的宽度
        tipHeight = dp2px(15);//百分比提示框的高度
        tipWidth = dp2px(30); //百分比 提示框的宽度
        triangleHeight = dp2px(3);//三角形的高度
        roundRectRadius = dp2px(2);//圆角矩形的圆角半径
        textPaintSize = sp2px(10); // 百分比文字的 大小
        progressMarginTop = dp2px(8);//进度条距离提示框的高度
        tipPaintWidth = dp2px(1);//百分比提示框画笔的宽度

        //计算 View的真是的高度  整个
        mViewHeight = tipHeight + triangleHeight + progressMarginTop + progressPaintWidth + tipPaintWidth;
    }

    //初始化 画笔
    private void initPaint() {

        //设置 进度条的背景
        bgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bgPaint.setStrokeWidth(progressPaintWidth);//设置划线的宽度  进度条的默认半径
        bgPaint.setColor(bgColor);
        bgPaint.setAntiAlias(true);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        bgPaint.setStyle(Paint.Style.STROKE);//一种风格的


        //设置 进度条
        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setStrokeWidth(progressPaintWidth);//设置划线的宽度  进度条的默认半径
        progressPaint.setColor(progressColor);
        progressPaint.setAntiAlias(true);
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStyle(Paint.Style.STROKE);//一种风格的


        // 上面进度框的画笔设置
        tipPaint = new Paint();
        tipPaint.setStrokeWidth(tipPaintWidth);
        tipPaint.setColor(progressColor);//颜色和进度条一个颜色
        tipPaint.setAntiAlias(true);
        tipPaint.setStrokeCap(Paint.Cap.ROUND);
        tipPaint.setStyle(Paint.Style.FILL);//一种风格的


    }

    /**
     * 初始化 text 的画笔
     */
    private void initTextPaint() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(textPaintSize);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
    }

    /**
     * 测量宽度
     *
     * @param mode
     * @param width
     * @return
     */
    private int measureWidth(int mode, int width) {
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                mWidth = width;
                break;
        }
        return mWidth;
    }

    /**
     * 测量高度
     *
     * @param mode
     * @param height
     * @return
     */
    private int measureHeight(int mode, int height) {
        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                mHeight = mViewHeight;
                break;
            case MeasureSpec.EXACTLY:
                mHeight = height;
                break;
        }
        return mHeight;
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);



        setMeasuredDimension(measureWidth(widthMode, width), measureHeight(heightMode, height));
    }




    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //先绘制  这个 进度的背景条  就是灰色的部分
        /**
         * startX：起始端点的X坐标。

         startY：起始端点的Y坐标。

         stopX：终止端点的X坐标。

         stopY：终止端点的Y坐标。

         paint：绘制直线所使用的画笔。
         */
        canvas.drawLine(getPaddingLeft(), tipHeight + progressMarginTop + triangleHeight, getWidth(), progressMarginTop + triangleHeight + tipHeight, bgPaint);

        //继续绘制 进度条的

        canvas.drawLine(getPaddingLeft(), tipHeight + progressMarginTop + triangleHeight, currentProgress, progressMarginTop + triangleHeight + tipHeight, progressPaint);

        /**
         * 绘制 上面的矩形框
         */
        drawTipView(canvas);

        /**
         * 绘制文字
         */
        drawText(canvas, textString);
    }


    /**
     * 绘制文字 ...
     *
     * @param canvas
     * @param textString
     */
    private void drawText(Canvas canvas, String textString) {
        textRect.left = (int) moveDis;
        textRect.top = 0;
        textRect.right = (int) (tipWidth + moveDis);
        textRect.bottom = tipHeight;
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        int baseline = (textRect.bottom + textRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        //文字绘制到整个布局的中心位置
        canvas.drawText(textString + "%", textRect.centerX(), baseline, textPaint);
    }


    /**
     * 绘进度条上面的百分比的 view
     *
     * @param canvas
     */
    private void drawTipView(Canvas canvas) {
        //绘制进度条上面的圆角矩形
        drawRoundRect(canvas);
        //绘制圆角矩形下面的小三角
        drawTriangle(canvas);
    }

    //画一个 小三角
    private void drawTriangle(Canvas canvas) {
        path.lineTo(tipWidth / 2 + moveDis, tipHeight + triangleHeight); //中间向下的箭头的点的坐标
        path.moveTo(tipWidth / 2 - triangleHeight + moveDis, tipHeight); //   画开始三角的左边的坐标
        path.lineTo(tipWidth / 2 + triangleHeight + moveDis, tipHeight); // 最右边箭头点的坐标
        canvas.drawPath(path, tipPaint); //进行绘制  ...
        path.reset();
    }

    private void drawRoundRect(Canvas canvas) {
        //画一个正方形
        rectF.set(moveDis, 0, tipWidth + moveDis, tipHeight);//矩形 x 坐标。 矩形顶部的y坐标    矩形右边的x坐标。  矩形的底部的Y坐标
        canvas.drawRoundRect(rectF, roundRectRadius, roundRectRadius, tipPaint); //在画布上画一个 椭圆形
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
