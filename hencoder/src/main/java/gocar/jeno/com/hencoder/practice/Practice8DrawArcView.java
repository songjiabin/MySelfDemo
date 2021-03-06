package gocar.jeno.com.hencoder.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import gocar.jeno.com.hencoder.utils.UIUtil;

public class Practice8DrawArcView extends View {

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形

//        demo(canvas);


        //画 扇形

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);


        RectF rectF = new RectF();
        rectF.left = UIUtil.dpToPx(10);
        rectF.right = getWidth() - UIUtil.dpToPx(10);
        rectF.top = UIUtil.dpToPx(10);
        rectF.bottom = getHeight() - UIUtil.dpToPx(10);


        canvas.drawArc(rectF, -110, 110, true, paint);

        canvas.drawArc(rectF, 30, 120, false, paint);


        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(UIUtil.dpToPx(2));


        canvas.drawArc(rectF, 170, 60, false, paint);


    }

    private void demo(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);


        RectF rectF = new RectF();
        rectF.set(30, 30, getWidth() - 30, getHeight() - 30);

        canvas.drawArc(rectF, -110, 100, true, paint);


        canvas.drawArc(rectF, 10, 140, false, paint);


        Paint paint1 = new Paint();
        paint1.setColor(Color.BLACK);
        paint1.setStyle(Paint.Style.STROKE);


        canvas.drawArc(rectF, 180, 60, false, paint1);
    }
}
