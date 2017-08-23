package gocar.jeno.com.hencoder.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import gocar.jeno.com.hencoder.utils.UIUtil;

public class Practice6DrawLineView extends View {

    public Practice6DrawLineView(Context context) {
        super(context);
    }

    public Practice6DrawLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice6DrawLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawLine() 方法画直线

//        demo(canvas);


//        画直线
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(UIUtil.dpToPx(10));


        float startX = UIUtil.dpToPx(10);
        float startY = UIUtil.dpToPx(10);
        float endX = getWidth() - UIUtil.dpToPx(10);
        float endY = getHeight() - UIUtil.dpToPx(10);

        canvas.drawLine(startX,startY,endX,endY,paint);


    }

    private void demo(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(30);
        paint.setStyle(Paint.Style.FILL);


        canvas.drawLine(100, 100, getWidth() - 100, getHeight() - 100, paint);
    }
}
