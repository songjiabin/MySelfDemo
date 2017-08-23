package gocar.jeno.com.waveview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * author : 宋佳
 * time   : 2017/08/15
 * desc   :
 * version: 1.0.0
 */

public class WaveView extends View {


    private Paint paint;


    /**
     * 通过 A Math.sin(ω  x + φ ) + K)  来进行画正余弦的图像
     * <p>
     * java 中的API 是同过    y=A*sin(ωx+φ)+k    来进行的
     * <p>
     * <p>
     * A 代表的是 振幅偏移。 波形在Y轴上的最大与最小值的差距越大
     * W 角速度 控制正弦周期（单位角度内震动的次数）
     * φ 初相 反应在坐标上则为图片左右移动。 这里通过不断的变化 φ  达到 波浪移动效果
     * k 偏距，反映在坐标系上则为图像的上移或下移。
     */


    private float φ;
    private ValueAnimator valueAnimator;

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Float A = 10f;
        float ω = 50f;
        float K = 100f;

        float y;
        φ += 0.03f;
        Path path = new Path();


        path.moveTo(0, getHeight());
        for (float x = 0; x <= getWidth(); x += 20) {
            y = (float) (A * Math.sin(ω * x + φ) + K);
            path.lineTo(x, getHeight() - y);
        }


//        path.lineTo(getWidth(), 0);
//        path.lineTo(0, 0);
//        path.close();


        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.close();


        canvas.drawPath(path, paint);
    }


    public void initAnimation() {
        valueAnimator = ValueAnimator.ofInt(0, getWidth());
        valueAnimator.setDuration(1000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                /**
                 * 刷新页面调取onDraw方法，通过变更φ 达到移动效果
                 */
                invalidate();

            }
        });
        valueAnimator.start();
    }


}
