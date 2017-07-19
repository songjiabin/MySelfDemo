package handongkeji.com.commonpopupwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import handongkeji.com.commonpopupwindow.mydemo.DpUtil;
import handongkeji.com.commonpopupwindow.mydemo.MyCommonPopularWindow;

public class MainActivity extends AppCompatActivity implements MyCommonPopularWindow.ViewInterface {

    private Button btn;
    private MyCommonPopularWindow myCommonPopularWindow;
    private Button btn_right;
    private Button btn_left;
    private Button btn_top;
    private Button btn_all;
    private Button btn_custom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myCommonPopularWindow != null && myCommonPopularWindow.isShowing())
                    return;
                myCommonPopularWindow = new MyCommonPopularWindow.Builder(MainActivity.this).setView(R.layout.layout_popular).setBackGroundLevel(0.5f)
                        .setOutsideTouchable(true)
                        .setWidthAndHeight(0, 0)
                        .setViewOnclickListener(MainActivity.this).create();

                //向下弹框

//                myCommonPopularWindow.showAsDropDown(v);

//          findViewById(android.R.id.content) 整个 viewGroup的跟布局
                int[] positions = new int[2];
                v.getLocationOnScreen(positions);
                myCommonPopularWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY, 0, positions[1] + v.getHeight());
            }
        });

        //向右弹框
        btn_right = (Button) findViewById(R.id.btn_right);
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myCommonPopularWindow != null && myCommonPopularWindow.isShowing())
                    return;
                myCommonPopularWindow = new MyCommonPopularWindow.Builder(MainActivity.this).setView(R.layout.layout_popular).setBackGroundLevel(0.5f)
                        .setOutsideTouchable(true)
                        .setWidthAndHeight(0, 0)
                        .setViewOnclickListener(MainActivity.this).create();
//                myCommonPopularWindow.showAsDropDown(v, v.getWidth(), -v.getHeight());

                //得到button的左上角坐标
                int[] positions = new int[2];
                v.getLocationOnScreen(positions);
                myCommonPopularWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY, positions[0] + v.getWidth(), positions[1]);

            }

        });

        //向左边弹框
        btn_left = (Button) findViewById(R.id.btn_left);
        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myCommonPopularWindow != null && myCommonPopularWindow.isShowing())
                    return;
                myCommonPopularWindow = new MyCommonPopularWindow.Builder(MainActivity.this).setView(R.layout.layout_popular).setBackGroundLevel(0.5f)
                        .setOutsideTouchable(true)
                        .setWidthAndHeight(0, 0)
                        .setViewOnclickListener(MainActivity.this).create();
//                myCommonPopularWindow.showAsDropDown(v, -myCommonPopularWindow.getWidth(), -v.getHeight());

                //得到button的左上角坐标
                int[] positions = new int[2];
                v.getLocationOnScreen(positions);
                myCommonPopularWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY, positions[0] - myCommonPopularWindow.getWidth(), positions[1]);
            }
        });


        //向上弹框
        btn_top = (Button) findViewById(R.id.btn_top);
        btn_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCommonPopularWindow = new MyCommonPopularWindow.Builder(MainActivity.this).setView(R.layout.layout_popular_2).setBackGroundLevel(0.5f)
                        .setOutsideTouchable(true)
                        .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                        .setViewOnclickListener(MainActivity.this).create();
//               myCommonPopularWindow.showAsDropDown(v, 0, -(myCommonPopularWindow.getHeight() + v.getMeasuredHeight()));

                //得到button的左上角坐标
                int[] positions = new int[2];
                v.getLocationOnScreen(positions);
                myCommonPopularWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY, positions[0], positions[1] - myCommonPopularWindow.getHeight());
            }
        });

        //全屏的弹框
        btn_all = (Button) findViewById(R.id.btn_all);
        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCommonPopularWindow = new MyCommonPopularWindow.Builder(MainActivity.this).setView(R.layout.layout_popular_2).setBackGroundLevel(0.5f)
                        .setOutsideTouchable(true)
                        .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                        .setViewOnclickListener(MainActivity.this).create();
                myCommonPopularWindow.showAtLocation(findViewById(android.R.id.content), Gravity.BOTTOM, 0, 0);
            }
        });

        //相对的具体于某个控件的弹框
        btn_custom = (Button) findViewById(R.id.btn_custom);
        btn_custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCommonPopularWindow = new MyCommonPopularWindow.Builder(MainActivity.this).setView(R.layout.layout_info).setBackGroundLevel(0.5f)
                        .setOutsideTouchable(true)
                        .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                        .setViewOnclickListener(MainActivity.this).create();
                myCommonPopularWindow.showAsDropDown(v, (int) (-myCommonPopularWindow.getWidth() + DpUtil.dp2px(MainActivity.this, 20)), -(myCommonPopularWindow.getHeight() + v.getMeasuredHeight()));

            }
        });


    }


    @Override
    public void getChildView(View view, int layoutResId) {
        switch (layoutResId) {
            case R.layout.layout_popular:
                TextView tv2 = (TextView) view.findViewById(R.id.tv2);
                tv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "TV2", Toast.LENGTH_SHORT).show();
                        if (myCommonPopularWindow != null) {
                            myCommonPopularWindow.dismiss();
                        }
                    }
                });
                break;

            default:
                break;
        }
    }
}
