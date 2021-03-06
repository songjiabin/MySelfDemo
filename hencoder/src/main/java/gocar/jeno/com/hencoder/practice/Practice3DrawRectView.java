package gocar.jeno.com.hencoder.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice3DrawRectView extends View {

    public Practice3DrawRectView(Context context) {
        super(context);
    }

    public Practice3DrawRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice3DrawRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawRect() 方法画矩形

//        demo1(canvas);

        //进行画正方形

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);


        int heght = getMeasuredHeight();
        int widht = getMeasuredWidth();

        float top = heght / 4;
        float left = widht / 2 - top;
        float right = widht / 2 + top;
        float bottom = heght / 4 * 3;

        canvas.drawRect(left, top, right, bottom, paint);


    }

    private void demo1(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);


        float xStart = (getWidth() - getHeight() / 2) / 2;
        float yStart = (getHeight()) / 4;

        float xEnd = getWidth() - xStart;
        float yEnd = getHeight() - yStart;


        canvas.drawRect(xStart, yStart, xEnd, yEnd, paint);
    }

    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

}
