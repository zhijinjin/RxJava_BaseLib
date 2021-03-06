package com.zjj.widgetlibrary.toolbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.TintTypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.zjj.widgetlibrary.R;


/**
 * Created by kimziv on 2017/10/25.
 */

public class TitleToolbar extends BaseToolbar implements View.OnClickListener{
    private LinearLayoutCompat mTitleLayout;
    private TextView mTitleTextView;
    private CharSequence mTitleText;
    private boolean mTitleVisible;

    private TextView mSubtitleTextView;
    private CharSequence mSubTitleText;
    private boolean mSubTitleVisible;

    private TextView mCloseTextView;
    private CharSequence mCloseText;
    private boolean mCloseVisible;

    private TextView mBackTextView;
    private CharSequence mBackText;
    private boolean mBackVisible;

    private TextView mRightTextView;
    private CharSequence mRightText;
    private boolean mRightVisible;

    private static final int DEFAULT_BACK_MARGIN_RIGHT = 8;
    private static final int DEFAULT_RIGHT_MARGIN_RIGHT = 8;

    public TitleToolbar(Context context) {
        super(context);
    }

    public TitleToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TitleToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        super.initialize(context, attrs, defStyleAttr);
    }
    @SuppressLint("RestrictedApi")
    @Override
    protected void initCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        //noinspection RestrictedApi
        TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                android.support.v7.appcompat.R.styleable.Toolbar, defStyleAttr, 0);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleToolbar);

        //标题
        if (!isChild(mTitleLayout)) {

            mTitleLayout = new LinearLayoutCompat(context);
            mTitleLayout.setOrientation(LinearLayoutCompat.VERTICAL);
            mTitleLayout.setGravity(typedArray.getInt(
                    R.styleable.TitleToolbar_title_gravity, Gravity.CENTER_VERTICAL));

            addView(mTitleLayout, new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, Gravity.CENTER));
        }

        //主标题
        if (!isChild(mTitleTextView, mTitleLayout)) {
            mTitleTextView = new TextView(context);
            mTitleTextView.setSingleLine();
            mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);//省略号在结尾
            mTitleTextView.setGravity(Gravity.CENTER);

            //设置文字外观
            int titleTextAppearance = a.getResourceId(android.support.v7.appcompat.R.styleable.Toolbar_titleTextAppearance, 0);
            if (titleTextAppearance != 0) {
                mTitleTextView.setTextAppearance(context, titleTextAppearance);
            }

            //字体颜色
            if (a.hasValue(android.support.v7.appcompat.R.styleable.Toolbar_titleTextColor)) {
                //noinspection RestrictedApi
                int titleColor = a.getColor(android.support.v7.appcompat.R.styleable.Toolbar_titleTextColor, Color.WHITE);
                mTitleTextView.setTextColor(titleColor);
            }

            //字体大小
            if (typedArray.hasValue(R.styleable.TitleToolbar_titleTextSize)) {
                mTitleTextView.setTextSize(
                        typedArray.getDimensionPixelSize(R.styleable.TitleToolbar_backTextSize, 0));
            }
            //noinspection RestrictedApi
            setTitle(a.getText(android.support.v7.appcompat.R.styleable.Toolbar_title));
            setTitleVisible(typedArray.getBoolean(R.styleable.TitleToolbar_titleVisible, true));

            mTitleLayout.addView(mTitleTextView,
                    new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }

        //副标题
        if (!isChild(mSubtitleTextView, mTitleLayout)) {
            mSubtitleTextView = new TextView(context);
            mSubtitleTextView.setSingleLine();
            mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
            mSubtitleTextView.setGravity(Gravity.CENTER);

            //noinspection RestrictedApi
            int subTextAppearance = a.getResourceId(android.support.v7.appcompat.R.styleable.Toolbar_subtitleTextAppearance, 0);
            if (subTextAppearance != 0) {
                mSubtitleTextView.setTextAppearance(context, subTextAppearance);
            }
            //noinspection RestrictedApi
            if (a.hasValue(android.support.v7.appcompat.R.styleable.Toolbar_subtitleTextColor)) {
                //noinspection RestrictedApi
                int subTitleColor = a.getColor(android.support.v7.appcompat.R.styleable.Toolbar_subtitleTextColor, Color.WHITE);
                mSubtitleTextView.setTextColor(subTitleColor);
            }

            if (typedArray.hasValue(R.styleable.TitleToolbar_subtitleTextSize)) {
                mSubtitleTextView.setTextSize(
                        typedArray.getDimensionPixelSize(
                                R.styleable.TitleToolbar_subtitleTextSize, 0));
            }
            //noinspection RestrictedApi
            setSubtitle(a.getText(android.support.v7.appcompat.R.styleable.Toolbar_subtitle));
            setSubtitleVisible(
                    typedArray.getBoolean(R.styleable.TitleToolbar_subtitleVisible, false));

            mTitleLayout.addView(mSubtitleTextView,
                    new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }

        //返回键
        if (!isChild(mBackTextView)) {
            mBackTextView = new TextView(context);
            mBackTextView.setId(R.id.back);
            mBackTextView.setSingleLine();
            mBackTextView.setEllipsize(TextUtils.TruncateAt.END);
            mBackTextView.setGravity(Gravity.CENTER_VERTICAL);

            int backTextAppearance =
                    typedArray.getResourceId(R.styleable.TitleToolbar_backTextAppearance, 0);
            if (backTextAppearance != 0) {
                mBackTextView.setTextAppearance(context, backTextAppearance);
            }

            if (typedArray.hasValue(R.styleable.TitleToolbar_backTextColor)) {
                int backTextColor =
                        typedArray.getColor(R.styleable.TitleToolbar_backTextColor, Color.WHITE);
                mBackTextView.setTextColor(backTextColor);
            }

            if (typedArray.hasValue(R.styleable.TitleToolbar_backTextSize)) {
                mBackTextView.setTextSize(
                        typedArray.getDimensionPixelSize(R.styleable.TitleToolbar_backTextSize, 0));
            }

            Drawable drawable = typedArray.getDrawable(R.styleable.TitleToolbar_backIcon);
            if (drawable != null) {
                mBackTextView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
            }

            setBackText(typedArray.getText(R.styleable.TitleToolbar_backText));
            setBackVisible(typedArray.getBoolean(R.styleable.TitleToolbar_backVisible, false));

            mBackTextView.setClickable(true);
            mBackTextView.setOnClickListener(this);

            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT, Gravity.LEFT | Gravity.CENTER_VERTICAL);

            layoutParams.rightMargin = typedArray.getDimensionPixelSize(
                    R.styleable.TitleToolbar_backMarginRight, dp2px(DEFAULT_BACK_MARGIN_RIGHT));

            addView(mBackTextView, layoutParams);
        }

        //关闭
        if (!isChild(mCloseTextView)) {
            mCloseTextView = new TextView(context);
            mCloseTextView.setId(R.id.close);
            mCloseTextView.setSingleLine();
            mCloseTextView.setEllipsize(TextUtils.TruncateAt.END);
            mCloseTextView.setGravity(Gravity.CENTER_VERTICAL);

            int closeTextAppearance =
                    typedArray.getResourceId(R.styleable.TitleToolbar_closeTextAppearance, 0);

            if (closeTextAppearance != 0) {
                mCloseTextView.setTextAppearance(context, closeTextAppearance);
            }

            if (typedArray.hasValue(R.styleable.TitleToolbar_closeTextColor)) {
                int closeTextColor =
                        typedArray.getColor(R.styleable.TitleToolbar_closeTextColor, Color.WHITE);
                mCloseTextView.setTextColor(closeTextColor);
            }

            if (typedArray.hasValue(R.styleable.TitleToolbar_closeTextSize)) {
                mCloseTextView.setTextSize(
                        typedArray.getDimensionPixelSize(
                                R.styleable.TitleToolbar_closeTextSize, 0));
            }

            setCloseText(typedArray.getText(R.styleable.TitleToolbar_closeText));
            setCloseVisible(typedArray.getBoolean(R.styleable.TitleToolbar_closeVisible, false));

            mCloseTextView.setClickable(true);
            mCloseTextView.setOnClickListener(this);

            addView(mCloseTextView, new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT, Gravity.LEFT | Gravity.CENTER_VERTICAL));
        }

        //右键
        if (!isChild(mRightTextView)) {
            mRightTextView = new TextView(context);
            mRightTextView.setId(R.id.right);
            mRightTextView.setSingleLine();
            mRightTextView.setEllipsize(TextUtils.TruncateAt.END);
            mRightTextView.setGravity(Gravity.CENTER_VERTICAL);

            int rightTextAppearance =
                    typedArray.getResourceId(R.styleable.TitleToolbar_rightTextAppearance, 0);
            if (rightTextAppearance != 0) {
                mRightTextView.setTextAppearance(context, rightTextAppearance);
            }

            if (typedArray.hasValue(R.styleable.TitleToolbar_rightTextColor)) {
                int rightTextColor =
                        typedArray.getColor(R.styleable.TitleToolbar_rightTextColor, Color.WHITE);
                mRightTextView.setTextColor(rightTextColor);
            }

            if (typedArray.hasValue(R.styleable.TitleToolbar_rightTextSize)) {
                mRightTextView.setTextSize(
                        typedArray.getDimensionPixelSize(R.styleable.TitleToolbar_rightTextSize, 0));
            }

            Drawable drawable = typedArray.getDrawable(R.styleable.TitleToolbar_rightIcon);
            if (drawable != null) {
                mRightTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
            }

            setRightText(typedArray.getText(R.styleable.TitleToolbar_rightText));
            setRightVisible(typedArray.getBoolean(R.styleable.TitleToolbar_rightVisible, false));

            mRightTextView.setClickable(true);
            mRightTextView.setOnClickListener(this);

            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                    LayoutParams.MATCH_PARENT, Gravity.RIGHT | Gravity.CENTER_VERTICAL);

            layoutParams.rightMargin = typedArray.getDimensionPixelSize(
                    R.styleable.TitleToolbar_rightMarginRight, dp2px(DEFAULT_RIGHT_MARGIN_RIGHT));

            addView(mRightTextView, layoutParams);
        }

        typedArray.recycle();
        //noinspection RestrictedApi
        a.recycle();
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitleText = title;
        if (mTitleTextView != null) {
            mTitleTextView.setText(title);
        }
    }

    @Override
    public CharSequence getTitle() {
        return mTitleText;
    }

    @Override
    public void setTitleTextAppearance(Context context, int resId) {
        if (mTitleTextView != null) {
            mTitleTextView.setTextAppearance(context, resId);
        }
    }

    @Override
    public void setTitleTextColor(int color) {
        if (mTitleTextView != null) {
            mTitleTextView.setTextColor(color);
        }
    }

    public void setTitleVisible(boolean visible) {
        mTitleVisible = visible;
        mTitleTextView.setVisibility(mTitleVisible ? VISIBLE : GONE);
    }

    public boolean getTitleVisible() {
        return mTitleVisible;
    }

    @Override
    public void setSubtitle(CharSequence subtitle) {
        mSubTitleText = subtitle;
        if (mSubtitleTextView != null) {
            mSubtitleTextView.setText(subtitle);
        }
    }

    @Override
    public CharSequence getSubtitle() {
        return mSubTitleText;
    }

    @Override
    public void setSubtitleTextAppearance(Context context, int resId) {
        if (mSubtitleTextView != null) {
            mSubtitleTextView.setTextAppearance(context, resId);
        }
    }

    @Override
    public void setSubtitleTextColor(int color) {
        if (mSubtitleTextView != null) {
            mSubtitleTextView.setTextColor(color);
        }
    }

    public void setSubtitleVisible(boolean visible) {
        mSubTitleVisible = visible;
        mSubtitleTextView.setVisibility(visible ? VISIBLE : GONE);
    }

    public boolean getSubtitleVisible() {
        return mSubTitleVisible;
    }

    /**  close text config **/

    public void setCloseText(int resId) {
        setCloseText(getContext().getText(resId));
    }

    public void setCloseText(CharSequence closeText) {
        mCloseText = closeText;
        if (mCloseTextView != null) {
            mCloseTextView.setText(closeText);
        }
    }

    public CharSequence getCloseText() {
        return mCloseText;
    }

    public void setCloseTextColor(int color) {
        mCloseTextView.setTextColor(color);
    }

    public void setCloseVisible(boolean visible) {
        mCloseVisible = visible;
        mCloseTextView.setVisibility(mCloseVisible ? VISIBLE : GONE);
    }

    public boolean isCloseVisible() {
        return mCloseVisible;
    }
    /**  back text config **/
    public void setBackText(int resId) {
        setBackText(getContext().getText(resId));
    }

    public void setBackText(CharSequence backText) {
        mBackText = backText;
        if (mBackTextView != null) {
            mBackTextView.setText(backText);
        }
    }

    public CharSequence getBackText() {
        return mBackText;
    }

    public void setBackTextColor(int color) {
        mBackTextView.setTextColor(color);
    }

    public void setBackVisible(boolean visible) {
        mBackVisible = visible;
        mBackTextView.setVisibility(mBackVisible ? VISIBLE : GONE);
    }

    public boolean isBackVisible() {
        return mBackVisible;
    }

    /**  right text config **/
    public void setRightText(int resId) {
        setRightText(getContext().getText(resId));
    }

    public void setRightText(CharSequence rightText) {
        mRightText = rightText;
        if (mRightTextView != null) {
            mRightTextView.setText(rightText);
        }
    }

    public CharSequence getRightText() {
        return mRightText;
    }

    public void setRightTextColor(int color) {
        mRightTextView.setTextColor(color);
    }

    public void setRightVisible(boolean visible) {
        mRightVisible = visible;
        mRightTextView.setVisibility(mRightVisible ? VISIBLE : GONE);
    }

    public boolean isRightVisible() {
        return mRightVisible;
    }

    @Override
    public void onClick(View v) {
        if (mOnOptionItemClickListener != null) {
            mOnOptionItemClickListener.onOptionItemClick(v);
        }
    }
}
