package handongkeji.com.supertextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

/**
 * author : 宋佳
 * time   : 2017/07/07
 * desc   :
 * version: 1.0.0
 */

public class SuperTextView extends RelativeLayout {


    private static final int TYPE_CHECKBOX = 0;  //checkBox
    private static final int TYPE_SWITCH = 1;   // switch

    private Context mContext;
    private int defaultSize = 15;  //默认字体的大小
    private int default_Margin = 10; //默认的间距大小
    private int defaultColor = 0xFF373737;//文字默认颜色
    private String mLeftTextString;
    private String mLeftTopString;
    private boolean mUseRipple; //是否开启水波的效果
    private ImageView leftIconIV;
    private LayoutParams leftImgParams;
    private int mLeftIconWidht; //左边图片的宽度
    private int mLeftiIconHeight;//左边图片的高度

    private Drawable mLeftIconRes;//左边的图片资源
    private int mLeftIconMarginLeft;// 左边距
    private int mRightViewType;

    private CheckBox rightCheckBox;//右边checkbox
    private LayoutParams rightCheckBoxParams;
    private int mRightCheckBoxMarginRight; //checkBox 右边距
    private Drawable mRightCheckBoxBg;
    private boolean mIsCheckouted; //是否选中
    private Switch mSwitch;
    private LayoutParams mSwitchParams;
    private int mRightSwitchMarginRight;
    private boolean mIsSwitched;
    private String mTextOff;
    private String mTextOn;
    private int mSwitchMinWidth;
    private int mSwitchPadding;
    private Drawable mThumbResource;
    private int mThumbTextPadding;
    private Drawable mTrackResource;
    private OnSuperTextViewClickListener onSuperTextViewClickListener;
    private ImageView rightIconIV;
    private LayoutParams rightImgParams;
    private int mRightIconWidht;
    private int mRightiIconHeight;
    private Drawable mRightIconRes;
    private int mRightIconMarginRight;
    private BaseTextView leftView;
    private LayoutParams leftBaseViewParams;
    private int mLeftViewWidth;
    private int mLeftViewMarginLeft;
    private int mLeftViewMarginRight;
    private int mCenterSpaceHeight;
    private String mLeftTopTextString;
    private String mCenterTextString;
    private String mBottomTextString;
    private int mLeftTopTextColor;
    private int mLeftCenterTextColor;
    private int mLeftBottomTextColor;
    private int mLeftTopTextSize;
    private int mLeftCenterTextSize;
    private int mLeftBottomTextSize;
    private int mLeftTopLines;
    private int mLeftCenterLines;
    private int mLeftBottomLines;
    private int mLeftTopMaxEms;
    private int mLeftCenterMaxEms;
    private int mLeftBottomMaxEms;
    private boolean mLeftTopTextIsBold;
    private boolean mLeftCenterTextIsBold;
    private boolean mLeftBottomTextIsBold;
    private int default_Gravity = -1;
    private int mLeftGravity;


    private static final int gravity_Left_Center = 0;
    private static final int gravity_Center = 1;
    private static final int gravity_Right_Center = 2;
    private int mCenterGravity;
    private int mRightGravity;
    private Drawable mLeftTvDrawableLeft;
    private Drawable mLeftTvDrawableRight;
    private Drawable mCenterTvDrawableLeft;
    private Drawable mCenterTvDrawableRight;
    private Drawable mRightTvDrawableLeft;
    private Drawable mRightTvDrawableRight;
    private int mTextViewDrawablePadding;
    private BaseTextView centerView;
    private LayoutParams centerBaseViewParams;

    public SuperTextView(Context context) {
        this(context, null);
    }

    public SuperTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SuperTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        defaultSize = sp2px(context, defaultSize);// 默认的字体的大小
        default_Margin = dip2px(context, default_Margin);
        getAttr(attrs);
        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        initSuperTextView();
        initLeftIcon();
        initRightIcon();
        initLeftTextView();
        initRightTextView();
        initCentterTextView();
        //查看  是显示 checkout 还是显示 switch
        switch (mRightViewType) {
            case TYPE_CHECKBOX:
                initRightCheckBox();
                break;
            case TYPE_SWITCH:
                initRightSwitch();
                break;
            default:
                break;
        }
    }

    /**
     * 初始化 中间的文字
     */
    private void initCentterTextView() {
        if (centerView == null) {
            centerView = initBaseView(R.id.sCenterViewId);
        }
        centerBaseViewParams = getParams(centerBaseViewParams);
        centerBaseViewParams.addRule(RelativeLayout.CENTER_IN_PARENT, TRUE);
        centerBaseViewParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);

        //默认情况下  中间的View整体剧中显示，设置左对齐或者右对齐的话使用下边属性
        if (mCenterGravity != gravity_Center) {
            centerBaseViewParams.addRule(RIGHT_OF, R.id.sLeftViewId);
            centerBaseViewParams.addRule(LEFT_OF, R.id.sRightViewId);
        }

        

        addView(centerView);
    }

    /**
     * 初始化 左边的文字
     */
    private void initLeftTextView() {
        if (leftView == null) {
            leftView = initBaseView(R.id.sLeftViewId);
        }
        leftBaseViewParams = getParams(leftBaseViewParams);
        leftBaseViewParams.addRule(RelativeLayout.RIGHT_OF, R.id.sLeftImgId);//放到有左边图片的右边
        leftBaseViewParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
        if (mLeftViewWidth != 0) {
            leftBaseViewParams.width = dip2px(mContext, mLeftViewWidth);
        }
        leftBaseViewParams.setMargins(mLeftViewMarginLeft, 0, mLeftViewMarginRight, 0);//设置 textview的边距
        leftView.setLayoutParams(leftBaseViewParams);
        leftView.setCenterSpaceHeight(mCenterSpaceHeight);//设置 间隔
        setDefaultString(leftView, mLeftTopTextString, mLeftTextString, mBottomTextString);//设置文字
        setDefaultColor(leftView, mLeftTopTextColor, mLeftCenterTextColor, mLeftBottomTextColor);//设置三个 textview的颜色
        setDefaultSize(leftView, mLeftTopTextSize, mLeftCenterTextSize, mLeftBottomTextSize);//设置字体的大小
        setDefaultLines(leftView, mLeftTopLines, mLeftCenterLines, mLeftBottomLines); //设置做多行数
        setDefaultMaxEms(leftView, mLeftTopMaxEms, mLeftCenterMaxEms, mLeftBottomMaxEms);//设置 TextView 中字体最大数量
        setDefaultTextIsBold(leftView, mLeftTopTextIsBold, mLeftCenterTextIsBold, mLeftBottomTextIsBold);//设置 textview 中字体的粗细
        setDefaultGravity(leftView, mLeftGravity);
        setDefaultDrawable(leftView.getCenterTextView(), mLeftTvDrawableLeft, mLeftTvDrawableRight, mTextViewDrawablePadding);
        addView(leftView);
    }


    /**
     * 设置textView的drawable
     *
     * @param textView        对象
     * @param drawableLeft    左边图标
     * @param drawableRight   右边图标
     * @param drawablePadding 图标距离文字的间距
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
    private void setDefaultGravity(BaseTextView baseTextView, int gravity) {
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
                textView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                break;
            case gravity_Center:
                textView.setGravity(Gravity.CENTER);
                break;
            case gravity_Right_Center:
                textView.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                break;
        }
    }

    /**
     * 字体是否加粗
     *
     * @param baseTextView   baseTextView
     * @param topTextBold    上边字体加粗
     * @param centerTextBold 中间字体加粗
     * @param bottomTextBold 下边字体加粗
     */
    private void setDefaultTextIsBold(BaseTextView baseTextView, boolean topTextBold, boolean centerTextBold, boolean bottomTextBold) {
        if (baseTextView != null) {
            baseTextView.getTopTextView().getPaint().setFakeBoldText(topTextBold);
            baseTextView.getCenterTextView().getPaint().setFakeBoldText(centerTextBold);
            baseTextView.getBottomTextView().getPaint().setFakeBoldText(bottomTextBold);
        }
    }


    /**
     * 设置默认maxEms
     *
     * @param baseTextView baseTextView
     * @param topMaxEms    topMaxEms
     * @param centerMaxEms centerMaxEms
     * @param bottomMaxEms bottomMaxEms
     */
    private void setDefaultMaxEms(BaseTextView baseTextView, int topMaxEms, int centerMaxEms, int bottomMaxEms) {
        if (baseTextView != null) {
            baseTextView.setMaxEms(topMaxEms, centerMaxEms, bottomMaxEms);
        }

    }


    /**
     * 设置TextView 的行高
     *
     * @param baseTextView
     * @param mLeftTopLines
     * @param mLeftCenterLines
     * @param mLeftBottomLines
     */
    private void setDefaultLines(BaseTextView baseTextView, int mLeftTopLines, int mLeftCenterLines, int mLeftBottomLines) {
        if (baseTextView != null) {
            baseTextView.getTopTextView().setLines(mLeftTopLines);
            baseTextView.getCenterTextView().setLines(mLeftCenterLines);
            baseTextView.getBottomTextView().setLines(mLeftBottomLines);
        }
    }


    /**
     * 设置textview 的size 大小
     *
     * @param baseTextView
     * @param topTextSize
     * @param centerTextSize
     * @param bottomTextSize
     */
    private void setDefaultSize(BaseTextView baseTextView, int topTextSize, int centerTextSize, int bottomTextSize) {
        if (baseTextView != null) {
            baseTextView.getTopTextView().setTextSize(TypedValue.COMPLEX_UNIT_PX, topTextSize);
            baseTextView.getCenterTextView().setTextSize(TypedValue.COMPLEX_UNIT_PX, centerTextSize);
            baseTextView.getBottomTextView().setTextSize(TypedValue.COMPLEX_UNIT_PX, bottomTextSize);
        }
    }


    /**
     * 设置默认的值 string
     */

    private void setDefaultString(BaseTextView baseTextView, String topTextStrign, String leftTextString, String bottomTextString) {
        if (baseTextView != null) {
            baseTextView.setTopTextString(topTextStrign);
            baseTextView.setCenterTextString(leftTextString);
            baseTextView.setBottomTextString(bottomTextString);
        }
    }

    /**
     * 设置字体的颜色
     *
     * @param baseTextView
     * @param topTextColor
     * @param centerTextColor
     * @param bottomTextColor
     */
    private void setDefaultColor(BaseTextView baseTextView, int topTextColor, int centerTextColor, int bottomTextColor) {
        if (baseTextView != null) {
            baseTextView.getTopTextView().setTextColor(topTextColor);
            baseTextView.getCenterTextView().setTextColor(centerTextColor);
            baseTextView.getBottomTextView().setTextColor(bottomTextColor);
        }
    }


    /**
     * 初始化 右边的文字
     */
    private void initRightTextView() {

    }


    private BaseTextView initBaseView(int id) {
        BaseTextView baseTextView = new BaseTextView(mContext);
        baseTextView.setId(id);
        return baseTextView;
    }


    /**
     * 右侧显示图片
     */
    private void initRightIcon() {
        if (rightIconIV == null) {
            rightIconIV = new ImageView(mContext);
            rightImgParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            rightImgParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
            switch (mRightViewType) {
                case TYPE_CHECKBOX:
                    rightImgParams.addRule(RelativeLayout.LEFT_OF, R.id.sRightCheckBoxId);//进行显示到右边 它的左边是id是  sRightCheckBoxId的控件
                    break;
                case TYPE_SWITCH:
                    rightImgParams.addRule(RelativeLayout.LEFT_OF, R.id.sRightSwitchId);
                    break;
                default:
                    rightImgParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, TRUE);//否则显示在整个布局的右侧
            }
        }
        //设置 图片的宽高
        if (mRightiIconHeight != 0 && mRightIconWidht != 0) {
            rightImgParams.width = mRightIconWidht;
            rightImgParams.height = mRightiIconHeight;
        }

        rightIconIV.setScaleType(ImageView.ScaleType.FIT_CENTER);
        rightIconIV.setId(R.id.sRightImgId);
        rightIconIV.setLayoutParams(rightImgParams);
        if (mRightIconRes != null) {
            rightImgParams.setMargins(0, 0, mRightIconMarginRight, 0);
            rightIconIV.setImageDrawable(mRightIconRes);
        }
        this.addView(rightIconIV);
    }

    /**
     * 添加右侧的 switch
     */
    private void initRightSwitch() {
        if (mSwitch == null) {
            mSwitch = new Switch(mContext);
            mSwitchParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            mSwitchParams.addRule(ALIGN_PARENT_RIGHT, TRUE); //右侧
            mSwitchParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);
            mSwitchParams.setMargins(0, 0, mRightSwitchMarginRight, 0);//设置 switch  右边距
            mSwitch.setId(R.id.sRightSwitchId);//id
            mSwitch.setLayoutParams(mSwitchParams);
        }
        mSwitch.setChecked(mIsSwitched);
        if (!TextUtils.isEmpty(mTextOff)) {
            mSwitch.setTextOff(mTextOff);
        }
        if (!TextUtils.isEmpty(mTextOn)) {
            mSwitch.setTextOn(mTextOn);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            if (mSwitchMinWidth != 0) {
                mSwitch.setSwitchMinWidth(mSwitchMinWidth); //设置 switch 最小的宽度
            }
            if (mSwitchPadding != 0) {
                mSwitch.setSwitchPadding(mSwitchPadding);   //设置 switch padding
            }
            if (mThumbResource != null) {
                mSwitch.setThumbDrawable(mThumbResource);  //设置 swithc 的背景 开
            }
            if (mThumbResource != null) {
                mSwitch.setTrackDrawable(mTrackResource);  // 设置 switch
            }
            if (mThumbTextPadding != 0) {
                mSwitch.setThumbTextPadding(mThumbTextPadding);
            }
        }

        addView(mSwitch);
    }

    /**
     * 添加右侧的 checkout
     */
    private void initRightCheckBox() {
        if (rightCheckBox == null) {
            rightCheckBox = new CheckBox(mContext);
            rightCheckBoxParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            rightCheckBoxParams.addRule(ALIGN_PARENT_RIGHT, TRUE);//在右边
            rightCheckBoxParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE);//上下居中
            rightCheckBoxParams.setMargins(0, 0, mRightCheckBoxMarginRight, 0);
            rightCheckBox.setId(R.id.sRightCheckBoxId);
            rightCheckBox.setLayoutParams(rightCheckBoxParams);
            if (mRightCheckBoxBg != null) {
                rightCheckBox.setGravity(CENTER_IN_PARENT);
                rightCheckBox.setButtonDrawable(mRightCheckBoxBg); //给 checkOut 设置 背景
            }
            rightCheckBox.setChecked(mIsCheckouted);
        }
        this.addView(rightCheckBox);
    }

    /**
     * 初始化左边的图片
     */
    private void initLeftIcon() {
        if (leftIconIV == null) {
            //当左边的图片时空的时候 新建ImageView
            leftIconIV = new ImageView(mContext);
        }
        leftImgParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        leftImgParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, TRUE);//在界面中添加属性 这个是放到 整个布局的左边
        leftImgParams.addRule(RelativeLayout.CENTER_VERTICAL, TRUE); //  使用证个布局上下居中
        //如果给左边图标的宽高 不为空（0）的话
        if (mLeftIconWidht != 0 && mLeftiIconHeight != 0) {
            leftImgParams.width = mLeftIconWidht;
            leftImgParams.height = mLeftiIconHeight;
        }
        leftIconIV.setScaleType(ImageView.ScaleType.FIT_CENTER);
        leftIconIV.setId(R.id.sLeftImgId);
        leftIconIV.setLayoutParams(leftImgParams);
        if (mLeftIconRes != null) {
            //有左边的图片资源
            leftImgParams.setMargins(mLeftIconMarginLeft, 0, 0, 0);
            leftIconIV.setImageDrawable(mLeftIconRes);
        }
        this.addView(leftIconIV);
    }

    /**
     * 初始化 SuperTextView
     */
    private void initSuperTextView() {
        if (mUseRipple) {
            //开启水波的效果  就是点击后的效果
            this.setBackgroundResource(R.drawable.selector_white);
        }

        //给 其设置了 点击事件才能进行
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onSuperTextViewClickListener != null) {
                    onSuperTextViewClickListener.onClickListener();
                }
            }
        });
    }

    /**
     * 得到从xml中定义的属性
     *
     * @param attrs
     */
    private void getAttr(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.SuperTextView);
        mLeftTextString = typedArray.getString(R.styleable.SuperTextView_sLeftTextString);
        mLeftTopString = typedArray.getString(R.styleable.SuperTextView_sLeftTopTextString);//左上文字字符串
        mUseRipple = typedArray.getBoolean(R.styleable.SuperTextView_sUseRipple, true);
        mLeftIconWidht = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftIconWidth, 0);
        mLeftiIconHeight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftIconHeight, 0);
        mLeftIconRes = typedArray.getDrawable(R.styleable.SuperTextView_sLeftIconRes);//图片资源
        mLeftIconMarginLeft = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftIconMarginLeft, default_Margin);

        mRightIconWidht = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sRightIconWidth, 0);
        mRightiIconHeight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sRightIconheight, 0);
        mRightIconRes = typedArray.getDrawable(R.styleable.SuperTextView_sRightIconRes);//图片资源
        mRightIconMarginRight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sRightIconMarginRight, default_Margin);//距离右边的距离


        mRightViewType = typedArray.getInt(R.styleable.SuperTextView_sRightVieType, -1);//右边显示的类型
        mRightCheckBoxMarginRight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sRightCheckBoxMarginRight, default_Margin);
        mRightCheckBoxBg = typedArray.getDrawable(R.styleable.SuperTextView_sRightCheckBoxRes);
        mIsCheckouted = typedArray.getBoolean(R.styleable.SuperTextView_sIsChecked, false);

        mRightSwitchMarginRight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sRightSwitchMarginRight, default_Margin);
        mIsSwitched = typedArray.getBoolean(R.styleable.SuperTextView_sIsSwitched, false);
        mTextOff = typedArray.getString(R.styleable.SuperTextView_sTextOff);
        mTextOn = typedArray.getString(R.styleable.SuperTextView_sTextOn);

        mSwitchMinWidth = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sSwitchMinWidth, 0);
        mSwitchPadding = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sSwitchPadding, 0);
        mThumbTextPadding = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sThumbTextPadding, 0);
        mThumbResource = typedArray.getDrawable(R.styleable.SuperTextView_sThumbResource);
        mTrackResource = typedArray.getDrawable(R.styleable.SuperTextView_sTrackResource);


        mLeftViewWidth = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_mLeftViewWidth, 0);
        mLeftViewMarginLeft = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftViewMarginLeft, default_Margin);
        mLeftViewMarginRight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftViewMarginRight, default_Margin);

        mCenterSpaceHeight = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sCenterSpaceHeight, default_Margin);
        mLeftTopTextString = typedArray.getString(R.styleable.SuperTextView_sLeftTopTextString);//得到 textview 的值
        mCenterTextString = typedArray.getString(R.styleable.SuperTextView_sLeftCenterTextString); //得到 textview 中间的值
        mBottomTextString = typedArray.getString(R.styleable.SuperTextView_sLeftBottomTextString); // 得到 textview 下面的值

        mLeftTopTextColor = typedArray.getColor(R.styleable.SuperTextView_sLeftTopTextColor, defaultColor);
        mLeftCenterTextColor = typedArray.getColor(R.styleable.SuperTextView_sLeftCenterTextColor, defaultColor);
        mLeftBottomTextColor = typedArray.getColor(R.styleable.SuperTextView_sLeftBottomTextColor, defaultColor);


        mLeftTopTextSize = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftTopTextSize, defaultSize);
        mLeftCenterTextSize = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftCenterTextSize, defaultSize);
        mLeftBottomTextSize = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sLeftBottomTextSize, defaultSize);


        //行数
        mLeftTopLines = typedArray.getInt(R.styleable.SuperTextView_sLeftTopLines, 1);//得到textview的行数
        mLeftCenterLines = typedArray.getInt(R.styleable.SuperTextView_sLeftCenterLines, 1);//得到textview的行数
        mLeftBottomLines = typedArray.getInt(R.styleable.SuperTextView_sLeftBottomLines, 1);//得到textview的行数

        //得到TextView 中输入的字的数量
        mLeftTopMaxEms = typedArray.getIndex(R.styleable.SuperTextView_sLeftTopMaxEms);
        mLeftCenterMaxEms = typedArray.getIndex(R.styleable.SuperTextView_sLeftCenterMaxEms);
        mLeftBottomMaxEms = typedArray.getIndex(R.styleable.SuperTextView_sLeftBottomMaxEms);

        //TextView中的 字体是否加粗呢
        mLeftTopTextIsBold = typedArray.getBoolean(R.styleable.SuperTextView_sLeftTopTextIsBold, false);
        mLeftCenterTextIsBold = typedArray.getBoolean(R.styleable.SuperTextView_sLeftCenterIsBold, false);
        mLeftBottomTextIsBold = typedArray.getBoolean(R.styleable.SuperTextView_sLeftBottomIsBold, false);//是否加粗呢

        // 字体的 gravity
        mLeftGravity = typedArray.getInt(R.styleable.SuperTextView_sLeftViewGravity, default_Gravity);
        mCenterGravity = typedArray.getInt(R.styleable.SuperTextView_sCenterViewGravity, default_Gravity);
        mRightGravity = typedArray.getInt(R.styleable.SuperTextView_sRightViewGravity, default_Gravity);


        mLeftTvDrawableLeft = typedArray.getDrawable(R.styleable.SuperTextView_sLeftTvDrawableLeft);
        mLeftTvDrawableRight = typedArray.getDrawable(R.styleable.SuperTextView_sLeftTvDrawableRight);// 左边textview drawableRight -- >
        mCenterTvDrawableLeft = typedArray.getDrawable(R.styleable.SuperTextView_sCenterTvDrawableLeft);
        mCenterTvDrawableRight = typedArray.getDrawable(R.styleable.SuperTextView_sCenterTvDrawableRight);
        mRightTvDrawableLeft = typedArray.getDrawable(R.styleable.SuperTextView_sRightTvDrawableLeft);
        mRightTvDrawableRight = typedArray.getDrawable(R.styleable.SuperTextView_sRightTvDrawableRight);


        mTextViewDrawablePadding = typedArray.getDimensionPixelSize(R.styleable.SuperTextView_sTextViewDrawablePadding, default_Margin);

        typedArray.recycle();
    }


    /**
     * 初始化Params
     *
     * @param params params
     * @return params
     */
    private LayoutParams getParams(LayoutParams params) {
        if (params == null) {
            params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        }
        return params;
    }

    /**
     * 单位转换工具类    sp  --  px
     *
     * @param context 上下文对象
     * @param spValue 值
     * @return 返回值
     */
    private int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * 单位转换工具类   dp -- px
     *
     * @param context  上下文对象
     * @param dipValue 值
     * @return 返回值
     */
    private int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * superTextView 的点击事件  整个 RelativeLayout
     */
    public interface OnSuperTextViewClickListener {
        void onClickListener();
    }

    public void setOnSuperTextViewClickListener(OnSuperTextViewClickListener onSuperTextViewClickListener) {
        this.onSuperTextViewClickListener = onSuperTextViewClickListener;
    }
}
