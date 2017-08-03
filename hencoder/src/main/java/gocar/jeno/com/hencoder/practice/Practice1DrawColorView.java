package gocar.jeno.com.hencoder.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice1DrawColorView extends View {

    public Practice1DrawColorView(Context context) {
        super(context);
    }

    public Practice1DrawColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice1DrawColorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawColor() 方法把 View 涂成黄色
//        黄色： Color.YELLOW

        ////这类颜色填充方法一般用于在绘制之前设置底色，或者在绘制之后为界面设置半透明蒙版（注意绘制顺序）
        canvas.drawColor(Color.YELLOW);

        //上述方法可以使用下面方法替代
//        canvas.drawRGB(255, 255, 0);
//        canvas.drawARGB(255, 255, 255, 0);
    }
}
