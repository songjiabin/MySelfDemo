package gocar.jeno.com.hencoder.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {

    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private Paint paint4;

    public Practice2DrawCircleView(Context context) {
        super(context);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        paint1 = new Paint();
        paint1.setColor(Color.BLACK);
        paint1.setAntiAlias(true);
        paint1.setStyle(Paint.Style.FILL);


        paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);


        paint3 = new Paint();
        paint3.setStyle(Paint.Style.FILL);
        paint3.setColor(Color.BLUE);
        paint3.setAntiAlias(true);


        paint4 = new Paint();
        paint4.setStrokeWidth(20);
        paint4.setColor(Color.BLACK);
        paint4.setStyle(Paint.Style.STROKE);


    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆


//        draw1(canvas);


        float screenWidth = getMeasuredWidth();
        float screenHeight = getMeasuredHeight();


        canvas.drawCircle(screenWidth / 4, screenHeight / 4, 150, paint1);

        canvas.drawCircle(screenWidth / 4 * 3, screenHeight / 4, 150, paint2);

        canvas.drawCircle(screenWidth / 4, screenHeight / 4 * 3, 150, paint3);

        canvas.drawCircle(screenWidth / 4 * 3, screenHeight / 4 * 3, 150, paint4);


    }

    private void draw1(Canvas canvas) {
        canvas.drawCircle(200, 200, 100, paint1);

        canvas.drawCircle(500, 200, 100, paint2);

        canvas.drawCircle(200, 500, 100, paint3);

        canvas.drawCircle(500, 500, 100, paint4);
    }
}
