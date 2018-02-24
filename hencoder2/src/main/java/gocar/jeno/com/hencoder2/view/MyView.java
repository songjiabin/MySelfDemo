package gocar.jeno.com.hencoder2.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import gocar.jeno.com.hencoder2.R;
import gocar.jeno.com.hencoder2.UIUtil;

import static android.graphics.BitmapFactory.decodeResource;

/**
 * author : 宋佳
 * time   : 2017/08/23
 * desc   :
 * version: 1.0.0
 */

public class MyView extends View {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        demo1(canvas);


//        demo2(canvas);


//        demo3(canvas);

        //绘制圆形的bitmap


//        demo4(canvas);
//        demo5(canvas);


//        Paint paint = new Paint();
//        paint.setColorFilter(new LightingColorFilter(0x00ffff, 0x003000));
//        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher), 300, 300, paint);

        Paint paint=new Paint();
        ColorMatrix colorMatrix=new ColorMatrix();


    }

    private void demo5(Canvas canvas) {
        //几个 shader 混合   混合着色器


        Paint paint = new Paint();

        // 第一个 Shader：头像的 Bitmap
        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.icon);
        Shader shader1 = new BitmapShader(bitmap1, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);

        // 第二个 Shader：从上到下的线性渐变（由透明到黑色）
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Shader shader2 = new BitmapShader(bitmap2, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);


        // ComposeShader：结合两个 Shader
        Shader shader = new ComposeShader(shader1, shader2, PorterDuff.Mode.DST_OUT);
        paint.setShader(shader);


        canvas.drawCircle(300, 300, 300, paint);
    }

    private void demo4(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);

        Shader shader = new BitmapShader(decodeResource(getResources(), R.mipmap.tab_job_selected), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);

        canvas.drawCircle(100, 100, 50, paint);
    }

    /**
     * 扫描渐变
     * SweepGradient
     *
     * @param canvas
     */
    private void demo3(Canvas canvas) {
        Paint paint = new Paint();
        Shader shader = new SweepGradient(300, 300, Color.RED, Color.GREEN);//扫描的中心坐标，扫描的起始颜色，扫描的终止颜色
        paint.setShader(shader);
        canvas.drawCircle(300, 300, 200, paint);
    }

    /**
     * RadialGradient
     *
     * @param canvas
     */
    private void demo2(Canvas canvas) {
        Paint paint = new Paint();
        Shader shader = new RadialGradient(300, 300, 200, Color.RED, Color.GREEN, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawCircle(300, 300, 200, paint);
    }

    /**
     * LinearGradient 线性渐变
     *
     * @param canvas
     */
    private void demo1(Canvas canvas) {
        Paint paint = new Paint();
        // 两个渐变的端点。两个颜色分别是端点的颜色。  Shader.TileMode 中有三个模式
        //  1.CLAMP  2.MIRROR (镜像模式) 3.REPEAT (重复模式)
        Shader shader = new LinearGradient(300, 100, 300, 500, Color.RED,
                Color.GREEN, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        canvas.drawCircle(300, 300, 200, paint);


        Paint paint1 = new Paint();
        paint1.setStrokeWidth(UIUtil.dpToPx(20));
        paint1.setStrokeCap(Paint.Cap.ROUND);
        paint1.setColor(Color.GRAY);
        canvas.drawPoint(300, 100, paint1);
        canvas.drawPoint(300, 500, paint1);
    }
}
