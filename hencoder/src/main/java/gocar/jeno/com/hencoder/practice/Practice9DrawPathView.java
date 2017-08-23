package gocar.jeno.com.hencoder.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形


//        demo(canvas);


        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);


        float width = getWidth();
        float height = getHeight();


        Path path = new Path();


        RectF rectF = new RectF();
        rectF.left = width / 2 - height / 4;
        rectF.right = width / 2;
        rectF.top = height / 4;
        rectF.bottom = height / 2;


        //右边的圆球
        RectF rectF1 = new RectF();
        rectF1.left = width / 2;
        rectF1.right = width / 2 + height / 4;
        rectF1.top = height / 4;
        rectF1.bottom = height / 2;




        path.arcTo(rectF, 135, 225, false);//是否抬笔
        path.arcTo(rectF1, -180, 225, false);

        path.lineTo(width / 2, height / 4*3);

        path.close();

        canvas.drawPath(path, paint);

//
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.FILL);
//        paint.setAntiAlias(true);
//        paint.setColor(Color.RED);
//
//        // 使用 path 对图形进行描述（这段描述代码不必看懂
//        Path path = new Path(); // 初始化 Path 对象
//        path.addArc(200, 200, 400, 400, -225, 225);
//        path.arcTo(400, 200, 600, 400, -180, 225, false);
//        path.lineTo(400, 542);
//        canvas.drawPath(path, paint); // 绘制出 path 描述的图形（心形），大功告成

    }

    private void demo(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);


        Path path = new Path();
        RectF rectFLeft = new RectF();
        RectF rectfRight = new RectF();


        // 矩形
        float x1 = (getWidth() - getHeight() / 2) / 2;
        float y1 = getHeight() / 4;
        float x2 = getWidth() / 2;
        float y2 = getHeight() / 2;
        rectFLeft.set(x1, y1, x2, y2);


        float xx1 = getWidth() / 2;
        float yy1 = getHeight() / 4;
        float xx2 = getWidth() - x1;
        float yy2 = getHeight() / 2;
        rectfRight.set(xx1, yy1, xx2, yy2);


        path.arcTo(rectFLeft, -225, 225, false);
        path.arcTo(rectfRight, -180, 225, false);
        path.lineTo(getWidth() / 2, getHeight() - y1);
        path.close();
        canvas.drawPath(path, paint);
    }
}
