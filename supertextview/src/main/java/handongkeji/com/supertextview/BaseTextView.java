package handongkeji.com.supertextview;

import android.content.Context;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * author : 宋佳
 * time   : 2017/07/07
 * desc   : 基础的 textView
 * version: 1.0.0
 */

public class BaseTextView extends LinearLayout {


    private Context mContext;
    private LinearLayout.LayoutParams topTVParams;
    private LinearLayout.LayoutParams centerTVParams;
    private LinearLayout.LayoutParams bottomTVParams;
    private TextView topTextView;
    private TextView centerTextView;
    private TextView bottomTextView;

    public BaseTextView(Context context) {
        this(context, null);
    }

    public BaseTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        this.setOrientation(VERTICAL); //纵向布局
        initView();
    }


    private void initView() {
        initTopView();
        initCenterView();
        initBottomView();
    }

    private void initCenterView() {
        if (centerTVParams == null) {
            centerTVParams = getParams(centerTVParams);
        }
        if (centerTextView == null) {
            centerTextView = initTextView(centerTVParams, centerTextView);
        }
    }

    private void initBottomView() {
        if (bottomTVParams == null) {
            bottomTVParams = getParams(bottomTVParams);
        }
        if (bottomTextView == null) {
            bottomTextView = initTextView(bottomTVParams, topTextView);
        }
    }


    private void initTopView() {
        if (topTVParams == null) {
            topTVParams = getParams(topTVParams);
        }
        if (topTextView == null) {
            topTextView = initTextView(topTVParams, topTextView);
        }
    }

    private TextView initTextView(LinearLayout.LayoutParams params, TextView textView) {
        textView = getTextView(textView, params);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    /**
     * 初始化textView
     *
     * @param textView     对象
     * @param layoutParams 对象
     * @return 返回
     */
    public TextView getTextView(TextView textView, LinearLayout.LayoutParams layoutParams) {
        if (textView == null) {
            textView = new TextView(mContext);
            textView.setLayoutParams(layoutParams);
            textView.setVisibility(GONE);
        }
        return textView;
    }

    public LayoutParams getParams(LayoutParams params) {
        if (params == null) {
            params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
        return params;
    }


    /**
     * 给 textview赋值
     *
     * @param textView
     * @param textString
     */
    private void setTextString(TextView textView, String textString) {
        if (!TextUtils.isEmpty(textString)) {
            textView.setText(textString);
            textView.setVisibility(VISIBLE);
        }
    }

    /**
     * 设置 top textView
     *
     * @param s
     */
    public void setTopTextString(String s) {
        setTextString(topTextView, s);
    }

    /**
     * 设置 bottom textView
     *
     * @param s
     */
    public void setBottomTextString(String s) {
        setTextString(bottomTextView, s);
    }

    /**
     * 设置 center textView
     *
     * @param s
     */
    public void setCenterTextString(String s) {
        setTextString(centerTextView, s);
    }

    public TextView getTopTextView() {
        return topTextView;
    }

    public TextView getCenterTextView() {
        return centerTextView;
    }

    public TextView getBottomTextView() {
        return bottomTextView;
    }

    /**
     * 如果在这个 ll 中有三个textview 之间的间隔
     *
     * @param centerSpaceHeight
     */
    public void setCenterSpaceHeight(int centerSpaceHeight) {
        topTVParams.setMargins(0, 0, 0, centerSpaceHeight / 2);
        centerTVParams.setMargins(0, centerSpaceHeight / 2, 0, centerSpaceHeight / 2);
        bottomTVParams.setMargins(0, centerSpaceHeight / 2, 0, 0);
    }

    public void setMaxEms(int topMaxEms, int centerMaxEms, int bottomMaxEms) {

        topTextView.setEllipsize(TextUtils.TruncateAt.END);
        centerTextView.setEllipsize(TextUtils.TruncateAt.END);//设置省略号在结尾
        bottomTextView.setEllipsize(TextUtils.TruncateAt.END);

        topTextView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(topMaxEms)}); //限制最大的数字的数量
        centerTextView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(centerMaxEms)});//限制最大的数字的数量
        bottomTextView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(bottomMaxEms)});//限制最大的数字的数量
    }

}
