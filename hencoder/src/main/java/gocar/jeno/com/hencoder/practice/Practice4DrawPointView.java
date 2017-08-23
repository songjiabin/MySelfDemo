package gocar.jeno.com.hencoder.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import gocar.jeno.com.hencoder.utils.UIUtil;

public class Practice4DrawPointView extends View {

    public Practice4DrawPointView(Context context) {
        super(context);
    }

    public Practice4DrawPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice4DrawPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPoint() 方法画点
//        一个圆点，一个方点
//        圆点和方点的切换使用 paint.setStrokeCap(cap)：`ROUND` 是圆点，`BUTT` 或 `SQUARE` 是方点

//        demo(canvas);


        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);




        paint.setStrokeWidth(UIUtil.dpToPx(60));

        paint.setStrokeCap(Paint.Cap.ROUND);//  圆 头


        canvas.drawPoint(UIUtil.dpToPx(30),getHeight()/2,paint);




        paint.setStrokeCap(Paint.Cap.SQUARE); //  方形 头
        paint.setStrokeWidth(UIUtil.dpToPx(60));
        canvas.drawPoint(getWidth()-UIUtil.dpToPx(30),getHeight()/2,paint);




    }

    private void demo(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(200);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeCap(Paint.Cap.ROUND);

        canvas.drawPoint(100, 200, paint);


        Paint paint1 = new Paint();
        paint1.setColor(Color.BLACK);
        paint1.setAntiAlias(true);
        paint1.setStrokeWidth(200);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(getWidth() - 100, 200, paint1);
    }
}
