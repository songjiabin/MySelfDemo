package gocar.jeno.com.hencoder.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice7DrawRoundRectView extends View {

    public Practice7DrawRoundRectView(Context context) {
        super(context);
    }

    public Practice7DrawRoundRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice7DrawRoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawRoundRect() 方法画圆角矩形

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);


        RectF rectF = new RectF();
        rectF.set(20, 50, getWidth()-20, getHeight() - 50);

        canvas.drawRoundRect(rectF, 150, 150, paint);


//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        paint.setColor(Color.BLACK);
//
//        int screenWidth = UIUtil.getScreenWidth(getContext());
//        int height = getHeight();
//        RectF rectF = new RectF(screenWidth / 4, height / 4, screenWidth / 4 * 3, height / 4 * 3);
//        canvas.drawRoundRect(rectF, UIUtil.dpToPx(10), UIUtil.dpToPx(10), paint);



    }
}
