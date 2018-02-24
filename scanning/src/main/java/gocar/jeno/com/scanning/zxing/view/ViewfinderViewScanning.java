package gocar.jeno.com.scanning.zxing.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;

import com.google.zxing.ResultPoint;

import java.util.Collection;
import java.util.HashSet;

import gocar.jeno.com.scanning.R;
import gocar.jeno.com.scanning.utils.UIUtil;
import gocar.jeno.com.scanning.zxing.camera.CameraManager;

/**
 * Created by songjiabin1 on 2016/10/8.
 */

public class ViewfinderViewScanning extends ViewfinderView {


    /**
     * 刷新界面的时间
     */
    private static final long ANIMATION_DELAY = 10L;
    private static final int OPAQUE = 0xFF;
    private Context context;


    /**
     * 四个绿色边角对应的长度
     */
    private int ScreenRate;


    /**
     * 四个绿色边角对应的宽度
     */
    private static final int CORNER_WIDTH = 5;

    /**
     * 扫描框中的中间线的宽度
     */
    private static final int MIDDLE_LINE_WIDTH = 6;

    /**
     * 扫描框中的中间线的与扫描框左右的间隙
     */
    private static final int MIDDLE_LINE_PADDING = 5;

    /**
     * 中间那条线每次刷新移动的距离
     */
    private static final int SPEEN_DISTANCE = 5;

    /**
     * 手机的屏幕密度
     */
    private static float density;
    /**
     * 字体大小
     */
    private static final int TEXT_SIZE = 16;
    /**
     * 字体距离扫描框下面的距离
     */
    private static final int TEXT_PADDING_TOP = 50;

    /**
     * 画笔对象的引用
     */
    private Paint paint;

    /**
     * 中间滑动线的最顶端位置
     */
    private int slideTop;

    /**
     * 中间滑动线的最底端位置
     */
    private int slideBottom;
    private final int maskColor;
    private final int resultColor;
    private Collection<ResultPoint> possibleResultPoints;
    private Collection<ResultPoint> lastPossibleResultPoints;

    private final int resultPointColor;

    private String currentCode = "";//当前码

    private Bitmap resultBitmap;
    private String currentSize = "";


    public ViewfinderViewScanning(Context context) {
        this(context, null);
    }

    public ViewfinderViewScanning(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        density = context.getResources().getDisplayMetrics().density;
        //将像素转换成dp
        ScreenRate = (int) (15 * density);
        paint = new Paint();
        Resources resources = getResources();
        maskColor = resources.getColor(R.color.viewfinder_mask);
        resultColor = resources.getColor(R.color.result_view);
        resultPointColor = resources.getColor(R.color.possible_result_points);
        possibleResultPoints = new HashSet<ResultPoint>(5);


    }

    @Override
    public void onDraw(Canvas canvas) {

        //中间的扫描框，你要修改扫描框的大小，去CameraManager里面修改
        Rect frame = CameraManager.get().getFramingRect();
        if (frame == null) {
            return;
        }

        //初始化中间线滑动的最上边和最下边
        if (!isFirst) {
            isFirst = true;
            slideTop = frame.top;
            slideBottom = frame.bottom;
        }

        //获取屏幕的宽和高
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        paint.setColor(resultBitmap != null ? resultColor : maskColor);

        //画出扫描框外面的阴影部分，共四个部分，扫描框的上面到屏幕上面，扫描框的下面到屏幕下面
        //扫描框的左边面到屏幕左边，扫描框的右边到屏幕右边
        canvas.drawRect(0, 0, width, frame.top, paint);
        canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
        canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1,
                paint);
        canvas.drawRect(0, frame.bottom + 1, width, height, paint);

        if (resultBitmap != null) {
            // Draw the opaque result bitmap over the scanning rectangle
            paint.setAlpha(OPAQUE);
            canvas.drawBitmap(resultBitmap, frame.left, frame.top, paint);
        } else {
            //画扫描框边上的角，总共8个部分
            paint.setColor(Color.GREEN);
            canvas.drawRect(frame.left, frame.top, frame.left + ScreenRate,
                    frame.top + CORNER_WIDTH, paint);
            canvas.drawRect(frame.left, frame.top, frame.left + CORNER_WIDTH, frame.top
                    + ScreenRate, paint);
            canvas.drawRect(frame.right - ScreenRate, frame.top, frame.right,
                    frame.top + CORNER_WIDTH, paint);
            canvas.drawRect(frame.right - CORNER_WIDTH, frame.top, frame.right, frame.top
                    + ScreenRate, paint);
            canvas.drawRect(frame.left, frame.bottom - CORNER_WIDTH, frame.left
                    + ScreenRate, frame.bottom, paint);
            canvas.drawRect(frame.left, frame.bottom - ScreenRate,
                    frame.left + CORNER_WIDTH, frame.bottom, paint);
            canvas.drawRect(frame.right - ScreenRate, frame.bottom - CORNER_WIDTH,
                    frame.right, frame.bottom, paint);
            canvas.drawRect(frame.right - CORNER_WIDTH, frame.bottom - ScreenRate,
                    frame.right, frame.bottom, paint);
        }
        slideTop += SPEEN_DISTANCE;
        if (slideTop >= frame.bottom) {
            slideTop = frame.top;
        }
        Rect lineRect = new Rect();
        lineRect.left = frame.left;
        lineRect.right = frame.right;
        lineRect.top = slideTop;
        lineRect.bottom = slideTop + 18;
        canvas.drawBitmap(((BitmapDrawable) (getResources().getDrawable(R.mipmap.qrcode_scan_line))).getBitmap(), null, lineRect, paint);


        //画扫描框下面的字
        paint.setColor(Color.WHITE);
        paint.setTextSize(TEXT_SIZE * density);
        paint.setAlpha(0x40);
        paint.setTypeface(Typeface.create("System", Typeface.NORMAL));
        String text = "将取景框对准二维码";
        float textWidth = paint.measureText(text);
        paint.setColor(Color.parseColor("#ffffff"));
//        canvas.drawText(text, (width - textWidth) / 2, (float) ((float) TEXT_PADDING_TOP * density), paint);
        canvas.drawText(text, (width - textWidth) / 2, frame.top - UIUtil.dpToPx(40), paint);


        //画扫描框下面的字
        paint.setColor(Color.WHITE);
        paint.setTextSize(TEXT_SIZE * density);
        paint.setAlpha(0x40);
        paint.setTypeface(Typeface.create("System", Typeface.NORMAL));
        String text1 = "即可自动扫描";
        float textWidth1 = paint.measureText(text1);
        paint.setColor(Color.parseColor("#ffffff"));
//        canvas.drawText(text, (width - textWidth) / 2, (float) ((float) TEXT_PADDING_TOP * density), paint);
        canvas.drawText(text1, (width - textWidth1) / 2, frame.top - UIUtil.dpToPx(20), paint);


        //当前码
        paint.setColor(Color.WHITE);
        paint.setTextSize(TEXT_SIZE * density);
        paint.setAlpha(0x40);
        paint.setTypeface(Typeface.create("System", Typeface.NORMAL));
        String textCurrent = "当前码： " + currentCode;
        paint.setColor(Color.parseColor("#ffffff"));
        float textWidth2 = paint.measureText(textCurrent);
        canvas.drawText(textCurrent, (width - textWidth2) / 2, frame.bottom + UIUtil.dpToPx(40), paint);


        //已经扫码
        paint.setColor(Color.WHITE);
        paint.setTextSize(TEXT_SIZE * density);
        paint.setAlpha(0x40);
        paint.setTypeface(Typeface.create("System", Typeface.NORMAL));
        String textSize = "已扫条码： " + currentSize;
        paint.setColor(Color.parseColor("#ffffff"));
        float textWidth3 = paint.measureText(textSize);
        canvas.drawText(textSize, (width - textWidth3) / 2, frame.bottom + UIUtil.spToPx(80), paint);


        Collection<ResultPoint> currentPossible = possibleResultPoints;
        Collection<ResultPoint> currentLast = lastPossibleResultPoints;
        if (currentPossible.isEmpty()) {
            lastPossibleResultPoints = null;
        } else {
            possibleResultPoints = new HashSet<ResultPoint>(5);
            lastPossibleResultPoints = currentPossible;
            paint.setAlpha(OPAQUE);
            paint.setColor(resultPointColor);
            for (ResultPoint point : currentPossible) {
                canvas.drawCircle(frame.left + point.getX(), frame.top
                        + point.getY(), 6.0f, paint);
            }
        }
        if (currentLast != null) {
            paint.setAlpha(OPAQUE / 2);
            paint.setColor(resultPointColor);
            for (ResultPoint point : currentLast) {
                canvas.drawCircle(frame.left + point.getX(), frame.top
                        + point.getY(), 3.0f, paint);
            }
        }


        //只刷新扫描框的内容，其他地方不刷新
        postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top,
                frame.right, frame.bottom);


    }

    //设置当前码  当前条码的个数
    public void setCurrentCode(String code, String size) {
        this.currentCode = code;
        this.currentSize = size;
        invalidate();

    }


}
