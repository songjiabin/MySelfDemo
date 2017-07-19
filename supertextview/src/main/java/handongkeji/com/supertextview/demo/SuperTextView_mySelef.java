package handongkeji.com.supertextview.demo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import handongkeji.com.supertextview.R;

/**
 * author : 宋佳
 * time   : 2017/07/10
 * desc   :   是在不想写了  好多啊
 * version: 1.0.0
 */

public class SuperTextView_mySelef extends RelativeLayout {


    private Context mContext;

    private int defaltHieght = 40;

    private int defaltMargin = 0; //图片距离左边的左边距

    private int defaultColor = 0xFF373737;//文字默认颜色

    private int defaultSize = 14;//默认字体的大小

    private int defaultMaxEms = 10;

    private static final int gravity_Left_Center = 0;//左对齐

    private static final int gravity_Center = 1;//中间

    private static final int gravity_Right_Center = 2;//右对齐

    private static final int default_Gravity = 1;

    private int mHeight;
    private Drawable mLeftDrawable;
    private ImageView leftIconIV;
    private LayoutParams leftImgParams;
    private int mLeftIconWidth;
    private int mLeftIconHeight;
    private int mLeftIconMarginLeft;
    private ImageView rightIconIV;
    private LayoutParams rightImgParams;
    private int mRightIconWidth;
    private int mRightIconHeight;
    private Drawable mRightDrawable;
    private int mRightIconMarginRight;
    private boolean mIsSupportRipple;//是否支持波纹呢
    private MyBaseTextView leftView;
    private LayoutParams leftTextViewParams;
    private int mLeftViewLeftMargin;
    private int mLeftViewRightMargin;
    private int mCenterSapnceHeight;
    private String mLeftViewTopTextViewString;
    private String mLeftViewCenterTextViewString;
    private String mLeftViewBottomTextViewString;
    private int mLeftViewTopColor;
    private int mLeftViewCenterColor;
    private int mLeftViewBottomColor;
    private int mLeftViewTopTextViewSize;
    private int mLeftViewCenterTextViewSize;
    private int mLeftViewBottomTextViewSize;
    private int mLeftViewTopLines;
    private int mLeftViewCenterLines;
    private int mLeftViewBottomLines;
    private int mLeftViewTopMaxEms;
    private int mLeftViewCenterMaxEms;
    private int mLeftViewBottomMaxEms;
    private boolean mLeftTopViewIsBold;
    private boolean mLeftCenterViewIsBold;
    private boolean mLeftBottomViewIsBold;
    private int mLeftViewGravity;
    private int mCenterViewGravity;
    private int mRightViewGravity;
    private Drawable mLeftViewDrawableLeft;
    private Drawable mLeftVietDrawableRight;
    private int mLeftViewDrawablePadding;
    private MyBaseTextView rightView;
    private LayoutParams rightTextViewParams;
    private int mRightViewLeftMargin;
    private int mRightViewRightMargin;
    private String mRightTopString;
    private String mRightCenterString;
    private String mRightBottomString;
    private int mRightTopColorString;
    private int mRightCenterColorString;
    private int mRightBottomColorString;


    public SuperTextView_mySelef(Context context) {
        this(context, null);
    }

    public SuperTextView_mySelef(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuperTextView_mySelef(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        defaultSize = sp2px(mContext, defaultSize);
        getAttr(attrs);
        initView();
    }

    private void initView() {
        initThis();
        initLeftIcon();
        initRightIcon();
        initLeftTextView();
        initRightTextView();
    }

    /**
     * 初始化 右边的  textview
     */
    private void initRightTextView() {
        if (rightView == null) {
            rightView = initBaseView(R.id.id_right_baseView);
        }
        if (rightTextViewParams == null) {
            rightTextViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        }
        rightTextViewParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        rightTextViewParams.addRule(RelativeLayout.LEFT_OF, R.id.id_rightIcon);//放到 右边图片的左侧
        rightTextViewParams.setMargins(mRightViewLeftMargin, 0, mRightViewRightMargin, 0);

        rightView.setLayoutParams(rightTextViewParams);
        rightView.setCenterSpaceHeight(mCenterSapnceHeight);


        setDefaultString(rightView, mRightTopString, mRightCenterString, mRightBottomString);
        setDefaultColor(rightView, mRightTopColorString, mRightCenterColorString, mRightBottomColorString);

        addView(rightView);
    }

    /**
     * 初始化 TextView  左边的
     * <p>
     * 左边的TextView 又可以分为上 中 下 三个地方可以放
     */
    private void initLeftTextView() {
        if (leftView == null) {
            leftView = initBaseView(R.id.id_left_baseView);
        }
        if (leftTextViewParams == null) {
            leftTextViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        }
        leftTextViewParams.addRule(RelativeLayout.RIGHT_OF, R.id.id_leftIcon); //注意要将这个布局放到 左边图片的右侧
        leftTextViewParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        leftTextViewParams.setMargins(mLeftViewLeftMargin, 0, mLeftViewRightMargin, 0);//边距
        leftView.setLayoutParams(leftTextViewParams);

        leftView.setCenterSpaceHeight(mCenterSapnceHeight);

        setDefaultString(leftView, mLeftViewTopTextViewString, mLeftViewCenterTextViewString, mLeftViewBottomTextViewString);//设置文字的字体
        setDefaultColor(leftView, mLeftViewTopColor, mLeftViewCenterColor, mLeftViewBottomColor); //设置 颜色
        setDefaultSize(leftView, mLeftViewTopTextViewSize, mLeftViewCenterTextViewSize, mLeftViewBottomTextViewSize); //设置 字体的大小
        setDefaultLines(leftView, mLeftViewTopLines, mLeftViewCenterLines, mLeftViewBottomLines);//设置最大的行数
        setDefaultMaxEms(leftView, mLeftViewTopMaxEms, mLeftViewCenterMaxEms, mLeftViewBottomMaxEms);//设置 textview中最大的文字数量 超过用 ... 表示
        setDefaultTextIsBold(leftView, mLeftTopViewIsBold, mLeftCenterViewIsBold, mLeftBottomViewIsBold);//设置 textview中的字体是否加粗
        setDefaultGravity(leftView, mLeftViewGravity);//设置 默认的布局排列方式
        setDefaultDrawable(leftView.getCenterTextView(), mLeftViewDrawableLeft, mLeftVietDrawableRight, mLeftViewDrawablePadding);
        addView(leftView);
    }

    /**
     * 设置 textview 的 drawableLeft  drawableRight  drawablePadding
     *
     * @param textView
     * @param drawableLeft
     * @param drawableRight
     * @param drawablePadding
     */
    public void setDefaultDrawable(TextView textView, Drawable drawableLeft, Drawable drawableRight, int drawablePadding) {
        if (drawableLeft != null || drawableRight != null) {
            textView.setVisibility(VISIBLE);
        }

        textView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, drawableRight, null);
        textView.setCompoundDrawablePadding(drawablePadding);
    }

    /**
     * 设置文字对其方式
     *
     * @param baseTextView baseTextView
     * @param gravity      对其方式
     */
    private void setDefaultGravity(MyBaseTextView baseTextView, int gravity) {
        if (baseTextView != null) {
            setGravity(baseTextView.getTopTextView(), gravity);
            setGravity(baseTextView.getCenterTextView(), gravity);
            setGravity(baseTextView.getBottomTextView(), gravity);
        }
    }


    /**
     * 文字对其方式
     *
     * @param textView textView
     * @param gravity  对其方式
     */
    private void setGravity(TextView textView, int gravity) {
        switch (gravity) {
            case gravity_Left_Center:
                textView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL); //左对齐
                break;
            case gravity_Center:
                textView.setGravity(Gravity.CENTER);
                break;
            case gravity_Right_Center:
                textView.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL); //右对齐
                break;
        }
    }

    /***
     * textview 中的文字是否加粗
     *
     * @param baseTextView
     * @param leftViewTop
     * @param leftViewCenter
     * @param leftViewBottom
     */
    private void setDefaultTextIsBold(MyBaseTextView baseTextView, boolean leftViewTop, boolean leftViewCenter, boolean leftViewBottom) {
        if (baseTextView != null) {
            baseTextView.getTopTextView().getPaint().setFakeBoldText(leftViewTop);
            baseTextView.getCenterTextView().getPaint().setFakeBoldText(leftViewCenter);
            baseTextView.getBottomTextView().getPaint().setFakeBoldText(leftViewBottom);
        }
    }

    /**
     * @param baseTextView
     * @param leftTopMaxEms
     * @param leftCenterMaxEms
     * @param leftBottomMaxEms
     */
    private void setDefaultMaxEms(MyBaseTextView baseTextView, int leftTopMaxEms, int leftCenterMaxEms, int leftBottomMaxEms) {
        if (baseTextView != null) {
            baseTextView.setMaxEms(leftTopMaxEms, leftCenterMaxEms, leftBottomMaxEms);
        }
    }


    /**
     * 设置默认lines
     *
     * @param baseTextView baseTextView
     * @param leftTopLines leftTopLines
     * @param leftLines    leftLines
     * @param bottomLines  bottomLines
     */
    private void setDefaultLines(MyBaseTextView baseTextView, int leftTopLines, int leftLines, int bottomLines) {
        if (baseTextView != null) {
            baseTextView.getTopTextView().setLines(leftTopLines);
            baseTextView.getCenterTextView().setLines(leftLines);
            baseTextView.getBottomTextView().setLines(bottomLines);
        }

    }

    /**
     * 设置最字体的大小
     *
     * @param myBaseTextView
     * @param topSize
     * @param centerSize
     * @param bottomSize
     */
    private void setDefaultSize(MyBaseTextView myBaseTextView, int topSize, int centerSize, int bottomSize) {
        if (myBaseTextView != null) {
            myBaseTextView.getTopTextView().setTextSize(TypedValue.COMPLEX_UNIT_PX, topSize); //将值变为 px
            myBaseTextView.getCenterTextView().setTextSize(TypedValue.COMPLEX_UNIT_PX, centerSize);
            myBaseTextView.getBottomTextView().setTextSize(TypedValue.COMPLEX_UNIT_PX, bottomSize);
        }
    }

    /**
     * 设置默认的颜色
     *
     * @param baseTextView
     * @param topColor
     * @param centerColor
     * @param bottomColor
     */
    private void setDefaultColor(MyBaseTextView baseTextView, int topColor, int centerColor, int bottomColor) {
        if (baseTextView != null) {
            baseTextView.getTopTextView().setTextColor(topColor);
            baseTextView.getCenterTextView().setTextColor(centerColor);
            baseTextView.getBottomTextView().setTextColor(bottomColor);
        }
    }

    /**
     * 设置 textView 的默认字体
     */
    private void setDefaultString(MyBaseTextView baseTextView, String topTextString, String centerTextString, String bottomTextString) {
        if (baseTextView != null) {
            baseTextView.setTopTextString(topTextString);
            baseTextView.setCenterTextString(centerTextString);
            baseTextView.setBottomTextString(bottomTextString);
        }
    }


    /**
     * 初始化 RelativeLayout
     */
    private void initThis() {
        //点击水波的效果
        if (mIsSupportRipple) {
            this.setBackgroundResource(R.drawable.selector_white);
        }

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 初始化 右边的图片
     */
    private void initRightIcon() {
        if (rightIconIV == null) {
            rightIconIV = new ImageView(mContext);
        }
        rightImgParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightImgParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE); //左边
        rightImgParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE); //上下居中
        rightIconIV.setScaleType(ImageView.ScaleType.FIT_CENTER);
        rightIconIV.setId(R.id.id_rightIcon);
        rightIconIV.setLayoutParams(rightImgParams);
        if (mRightIconWidth != 0 && mRightIconHeight != 0) {
            rightImgParams.width = mRightIconWidth;
            rightImgParams.height = mRightIconHeight;
        }
        if (mRightDrawable != null) {
            rightImgParams.setMargins(0, 0, mRightIconMarginRight, 0); //设置边距
            rightIconIV.setImageDrawable(mLeftDrawable);
        }
        addView(rightIconIV);
    }

    /**
     * 初始化 左边的图片
     */
    private void initLeftIcon() {
        if (leftIconIV == null) {
            leftIconIV = new ImageView(mContext);
        }
        leftImgParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftImgParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE); //左边
        leftImgParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE); //上下居中
        if (mLeftIconWidth != 0 && mLeftIconHeight != 0) {
            leftImgParams.width = mLeftIconWidth;
            leftImgParams.height = mLeftIconHeight;
        }
        leftIconIV.setScaleType(ImageView.ScaleType.FIT_CENTER);
        leftIconIV.setId(R.id.id_leftIcon);
        leftIconIV.setLayoutParams(leftImgParams);
        if (mLeftDrawable != null) {
            leftImgParams.setMargins(mLeftIconMarginLeft, 0, 0, 0); //设置边距
            leftIconIV.setImageDrawable(mLeftDrawable);
        }
        addView(leftIconIV);
    }

    /**
     * 进行
     *
     * @param attrs
     */
    private void getAttr(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.SuperTextView_mySelef);
        mHeight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mySelef_sHeight, dip2px(mContext, defaltHieght));
        mLeftDrawable = typedArray.getDrawable(R.styleable.SuperTextView_mySelef_sLeftDrawable);
        mLeftIconWidth = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mySelef_sLeftIconWidth, 0);
        mLeftIconHeight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mySelef_sLeftIconHeight, 0);
        mLeftIconMarginLeft = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mySelef_sLeftIconLeftMargin, defaltMargin);
        mRightIconWidth = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mySelef_sRightIconWidth, defaltMargin);
        mRightIconHeight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mySelef_sRightIconHeight, defaltMargin);
        mRightDrawable = typedArray.getDrawable(R.styleable.SuperTextView_mySelef_sRightDrawable);
        mRightIconMarginRight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mySelef_sRightIconRightMargin, defaltMargin);
        mIsSupportRipple = typedArray.getBoolean(R.styleable.SuperTextView_mySelef_siSupportRipple, true);
        mLeftViewLeftMargin = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mySelef_sLeftViewLeftMargin, defaltMargin);//左边距
        mLeftViewRightMargin = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mySelef_sRightViewLeftMargin, defaltMargin);

        mCenterSapnceHeight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mySelef_sCenterSpanceHeight, defaltMargin);

        mLeftViewTopTextViewString = typedArray.getString(R.styleable.SuperTextView_mySelef_sLeftView_topTextViewString);
        mLeftViewCenterTextViewString = typedArray.getString(R.styleable.SuperTextView_mySelef_sLeftView_centerTextViewString);
        mLeftViewBottomTextViewString = typedArray.getString(R.styleable.SuperTextView_mySelef_sLeftView_bottomTextViewString);

        mLeftViewTopColor = typedArray.getColor(R.styleable.SuperTextView_mySelef_sLeftView_topTextViewColor, defaultColor);
        mLeftViewCenterColor = typedArray.getColor(R.styleable.SuperTextView_mySelef_sLeftView_centerTextViewColor, defaultColor);
        mLeftViewBottomColor = typedArray.getColor(R.styleable.SuperTextView_sLeftBottomTextColor, defaultColor);

        mLeftViewTopTextViewSize = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mySelef_sLeftView_topTextViewSize, defaultSize);
        mLeftViewCenterTextViewSize = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mySelef_sLeftView_centerTextViewSize, defaultSize);
        mLeftViewBottomTextViewSize = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mySelef_sLeftView_bottomTextViewSize, defaultSize);


        mLeftViewTopLines = typedArray.getInt(R.styleable.SuperTextView_mySelef_sLeftView_topLines, 1);
        mLeftViewCenterLines = typedArray.getInt(R.styleable.SuperTextView_mySelef_sLeftView_centerLines, 1);
        mLeftViewBottomLines = typedArray.getInt(R.styleable.SuperTextView_mySelef_sLeftView_bottomLines, 1);

        mLeftViewTopMaxEms = typedArray.getInt(R.styleable.SuperTextView_mySelef_sLeftView_topMaxEms, defaultMaxEms);
        mLeftViewCenterMaxEms = typedArray.getInt(R.styleable.SuperTextView_mySelef_sLeftView_centerMaxEms, defaultMaxEms);
        mLeftViewBottomMaxEms = typedArray.getInt(R.styleable.SuperTextView_mySelef_sLeftView_bottomMaxEms, defaultMaxEms);


        mLeftTopViewIsBold = typedArray.getBoolean(R.styleable.SuperTextView_mySelef_sLeftView_topTextViewIsBold, false);//是否加粗呢
        mLeftCenterViewIsBold = typedArray.getBoolean(R.styleable.SuperTextView_mySelef_sLeftView_centerTextViewIsBold, false);//是否加粗呢
        mLeftBottomViewIsBold = typedArray.getBoolean(R.styleable.SuperTextView_mySelef_sLeftView_bottomTextViewIsBold, false);//是否加粗呢

        mLeftViewGravity = typedArray.getInt(R.styleable.SuperTextView_mySelef_sLeftViewGravity2, default_Gravity);//得到左边View的 布局排版
        mCenterViewGravity = typedArray.getInt(R.styleable.SuperTextView_mySelef_sCenterViewGravity2, default_Gravity);//得到左边View的 布局排版
        mRightViewGravity = typedArray.getInt(R.styleable.SuperTextView_mySelef_sRightViewGravity2, default_Gravity);//得到左边View的 布局排版


        mLeftViewDrawableLeft = typedArray.getDrawable(R.styleable.SuperTextView_mySelef_sLeftViewDrawableLeft);//得到左边 textview的 drawableLeft
        mLeftVietDrawableRight = typedArray.getDrawable(R.styleable.SuperTextView_mySelef_sLeftViewDrawableRight);  //得到左边 textview的 drawableRight

        mLeftViewDrawablePadding = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mySelef_sLeftViewDrawablePadding, defaltMargin);  /*drawable padding */


        mRightViewLeftMargin = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mySelef_sRightViewMarginLeft, defaltMargin); // 右边Textview的 左边距
        mRightViewRightMargin = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mySelef_sRightViewMarginRight, defaltMargin); //右边TextView的 右边距

        mRightTopString = typedArray.getString(R.styleable.SuperTextView_mySelef_sRightViewTopString);
        mRightCenterString = typedArray.getString(R.styleable.SuperTextView_mySelef_sRightViewCenterString);
        mRightBottomString = typedArray.getString(R.styleable.SuperTextView_mySelef_sRightViewBottomString);


        mRightTopColorString=typedArray.getColor(R.styleable.SuperTextView_mySelef_sRightTopColorString,defaultColor);
        mRightCenterColorString=typedArray.getColor(R.styleable.SuperTextView_mySelef_sRightCenterColorString,defaultColor);
        mRightBottomColorString=typedArray.getColor(R.styleable.SuperTextView_mySelef_sRightBottomColorString,defaultColor);

    }

    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * 得到 textview
     *
     * @param id
     * @return
     */
    private MyBaseTextView initBaseView(int id) {
        MyBaseTextView myBaseTextView = new MyBaseTextView(mContext);
        myBaseTextView.setId(id);
        return myBaseTextView;
    }
}
