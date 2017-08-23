package gocar.jeno.com.hencoder.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import gocar.jeno.com.hencoder.R;
import gocar.jeno.com.hencoder.utils.UIUtil;

import static gocar.jeno.com.hencoder.utils.UIUtil.dpToPx;

public class Practice10HistogramView extends View {

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图


//        demo(canvas);


        //直方图


        //1.先画两个线

        float width = getMeasuredWidth();
        float height = getMeasuredHeight();


        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(UIUtil.dpToPx(1));

        //画 两条直线
        Path path = new Path();
        path.moveTo(UIUtil.dpToPx(10), UIUtil.dpToPx(10));
        path.lineTo(UIUtil.dpToPx(10), getHeight() - 3 * UIUtil.dpToPx(10));
        path.lineTo(width - UIUtil.dpToPx(10), height - 3 * UIUtil.dpToPx(10));

        canvas.drawPath(path, paint);

        // 开始画 柱状图
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);


        //设置 柱状图的间隔

        float jiange = UIUtil.dpToPx(10);//间隔

        float everyWidt = (width - UIUtil.dpToPx(10) - UIUtil.dpToPx(10) - 8 * jiange) / 7;//每个 柱状图的宽度


        for (int i = 1; i <= 7; i++) {

            RectF rectf = new RectF();
            rectf.left = UIUtil.dpToPx(10)+jiange * i + (i - 1) * everyWidt;
            rectf.right = jiange * i + (i - 1) * everyWidt + everyWidt;
            rectf.bottom = getHeight() - 3 * UIUtil.dpToPx(10);
            rectf.top = rectf.bottom - i * UIUtil.dpToPx(10);;
            canvas.drawRect(rectf, paint);
        }


        Paint paintText = new Paint();
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(UIUtil.spToPx(15));
        Rect textBound = new Rect();
        String title = getResources().getString(R.string.title_draw_histogram);
        paintText.getTextBounds(title, 0, title.length(), textBound);
        canvas.drawText(title, getWidth() / 2 - textBound.width() / 2, getHeight() - dpToPx(5), paintText);



    }

    private void demo(Canvas canvas) {
        Paint paintLine = new Paint();
        paintLine.setColor(Color.WHITE);
        paintLine.setStrokeWidth(dpToPx(1));

        float[] f = {
                dpToPx(20), dpToPx(20),
                dpToPx(20), getHeight() - dpToPx(20) * 2,

                dpToPx(20), getHeight() - dpToPx(20) * 2,
                getWidth() - dpToPx(20), getHeight() - dpToPx(20) * 2,
        };

        canvas.drawLines(f, paintLine);

        //下面开始计算宽度了  定义间隔是固定的为 10dp
        int interval = UIUtil.dpToPx(10);

        //计算出每个柱状图的宽度
        int HistogramWith = (getWidth() - dpToPx(20) * 2 - 8 * interval) / 7;
        //开始绘制 图形
        paintLine.setColor(Color.GREEN);
        for (int i = 1; i <= 7; i++) {
            RectF rect = new RectF();
            rect.left = dpToPx(20) + i * interval + (i - 1) * HistogramWith;
            rect.right = rect.left + HistogramWith;
            rect.bottom = getHeight() - dpToPx(20) * 2;
            rect.top = rect.bottom - i * UIUtil.dpToPx(10);
            canvas.drawRect(rect, paintLine);
        }


        //先画下面的文字
        Paint paintText = new Paint();
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(UIUtil.spToPx(15));
        Rect textBound = new Rect();
        String title = getResources().getString(R.string.title_draw_histogram);
        paintText.getTextBounds(title, 0, title.length(), textBound);
        canvas.drawText(title, getWidth() / 2 - textBound.width() / 2, getHeight() - dpToPx(10), paintText);


        //给柱状图下面放上文字
        paintText.setTextSize(UIUtil.spToPx(12));
        String[] text = {"Froyo", "GB", "IC", "JB", "Kitkat", "L", "M"};
        for (int i = 1; i <= 7; i++) {
            String str = text[i - 1];
            paintText.getTextBounds(str, 0, str.length(), textBound);
            canvas.drawText(str, dpToPx(20) + i * interval + (i - 1) * HistogramWith + (HistogramWith - textBound.width()) / 2, getHeight() - dpToPx(20) * 2 + textBound.height(), paintText);
        }
    }
}
