package handongkeji.com.supertextview.demo;

import android.content.Context;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * author : 宋佳
 * time   : 2017/07/10
 * desc   :
 * version: 1.0.0
 */

public class MyBaseTextView extends LinearLayout {

    private Context mContext;
    private LayoutParams topTVParams;
    private TextView topTextView;
    private LayoutParams centerTVParams;
    private TextView centerTextView;
    private LayoutParams bottomTVParams;
    private TextView bottomTextView;

    public MyBaseTextView(Context context) {
        this(context, null, 0);
    }

    public MyBaseTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyBaseTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        setOrientation(VERTICAL);
        initView();
    }

    private void initView() {
        initTopView();
        initCenterView();
        initBottomView();
    }

    private void initTopView() {
        if (topTVParams == null) {
            topTVParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
        if (topTextView == null) {
            topTextView = new TextView(mContext);
        }
        topTextView.setLayoutParams(topTVParams);
        topTextView.setVisibility(GONE);
        topTextView.setGravity(Gravity.CENTER);
        addView(topTextView);
    }

    private void initCenterView() {
        if (centerTVParams == null) {
            centerTVParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
        if (centerTextView == null) {
            centerTextView = new TextView(mContext);
        }
        centerTextView.setLayoutParams(centerTVParams);
        centerTextView.setVisibility(GONE);
        centerTextView.setGravity(Gravity.CENTER);
        addView(centerTextView);
    }

    private void initBottomView() {
        if (bottomTVParams == null) {
            bottomTVParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        }
        if (bottomTextView == null) {
            bottomTextView = new TextView(mContext);
        }
        bottomTextView.setLayoutParams(bottomTVParams);
        bottomTextView.setVisibility(GONE);
        bottomTextView.setGravity(Gravity.CENTER);
        addView(bottomTextView);
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

    private void setTextString(TextView textView, String textString) {
        if (!TextUtils.isEmpty(textString)) {
            textView.setText(textString);
            textView.setVisibility(VISIBLE);
        }
    }


    public void setTopTextString(String s) {
        setTextString(topTextView, s);
    }


    public void setCenterTextString(String s) {
        setTextString(centerTextView, s);
    }

    public void setBottomTextString(String s) {
        setTextString(bottomTextView, s);
    }


    /**
     * 给 三个 textview设置 上下距离
     *
     * @param centerSpaceHeight
     */
    public void setCenterSpaceHeight(int centerSpaceHeight) {
        topTVParams.setMargins(0, 0, 0, centerSpaceHeight / 2);
        centerTVParams.setMargins(0, centerSpaceHeight / 2, 0, centerSpaceHeight / 2);
        bottomTVParams.setMargins(0, centerSpaceHeight / 2, 0, 0);
    }


    /**
     * 设置 textview中 做大的数量 超过这个数量了进行 用 ... 表示
     * @param leftTopMaxEms
     * @param leftCenterMaxEms
     * @param leftBottomMaxEms
     */
    public void setMaxEms(int leftTopMaxEms, int leftCenterMaxEms, int leftBottomMaxEms) {
        topTextView.setEllipsize(TextUtils.TruncateAt.END);
        centerTextView.setEllipsize(TextUtils.TruncateAt.END);
        bottomTextView.setEllipsize(TextUtils.TruncateAt.END);

        topTextView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(leftTopMaxEms)});
        centerTextView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(leftCenterMaxEms)});
        bottomTextView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(leftBottomMaxEms)});
    }
}
