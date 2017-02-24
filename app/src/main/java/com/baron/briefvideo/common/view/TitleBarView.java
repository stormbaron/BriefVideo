package com.baron.briefvideo.common.view;


import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baron.briefvideo.R;

/**
 * 自定义的TitleBar控件
 *
 * @author baron 提供监听器接口，调用者可以实现监听器接口包含左右两个按钮的点击事件方法 然后绑定监听器
 */
public class TitleBarView extends LinearLayout {
    // 右侧按钮属性
    ImageButton btnRight;
    Drawable rightDrawable;
    String rightTxt;
    boolean rightable;
    // 左侧按钮属性
    Button btnLeft;
    Drawable leftDrawable;
    String leftTxt;
    boolean leftable;
    // 标题
    TextView tvTitle;
    String titleTxt;
    int backgroun_color;
    BtnClickListener listener = null;

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getStyle(context, attrs);
        init(context);
    }

    public void getStyle(Context context, AttributeSet attrs) {
        // 获取在attrs中设置的属性组
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.TitleBarView);
        // 右侧按钮属性
        rightTxt = array.getString(R.styleable.TitleBarView_rightText);
        rightDrawable = array
                .getDrawable(R.styleable.TitleBarView_rightDrawable);
        rightable = array.getBoolean(R.styleable.TitleBarView_rightable, true);
        // 左侧按钮属性
        leftTxt = array.getString(R.styleable.TitleBarView_leftText);
        leftDrawable = array.getDrawable(R.styleable.TitleBarView_leftDrawable);
        leftable = array.getBoolean(R.styleable.TitleBarView_leftable, true);
        // 标题
        titleTxt = array.getString(R.styleable.TitleBarView_titleText);

        //backgroun_color=array.getColor(R.styleable.TitleBarView_background, R.color.bg_title_bar);
    }

    /**
     * 初始化组件
     *
     * @param context
     */
    private void init(Context context) {
        final Context mycontext = context;
        LayoutInflater.from(context).inflate(R.layout.titlebar, this);
        LinearLayout titlebar_linearlayout = (LinearLayout) findViewById(R.id.titlebar_linearlayout);
        btnLeft = (Button) findViewById(R.id.btn_left);
        btnRight = (ImageButton) findViewById(R.id.btn_right);
        tvTitle = (TextView) findViewById(R.id.title);
        tvTitle.setText(titleTxt);
        btnLeft.setText(leftTxt);

        //titlebar_linearlayout.setBackgroundResource(backgroun_color);
        titlebar_linearlayout.setBackgroundColor(backgroun_color);
        if (leftable == false) {
            btnLeft.setVisibility(View.GONE);
        }

        if (rightable == false) {
            btnRight.setVisibility(View.GONE);
        }

        btnLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.leftClick();
                } else {
                    ((Activity) mycontext).finish();
                }
            }
        });
        btnRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.rightClick();
                }
            }
        });
    }

    /**
     * 提供加载自定义监听器方法
     *
     * @param listener
     */
    public void setTitleBarListener(BtnClickListener listener) {
        this.listener = listener;
    }

    /**
     * 按钮点击接口,调用者实现
     */
    public interface BtnClickListener {
        void leftClick();

        void rightClick();
    }

    /**
     * 设置Titlebar内容（Java代码中设置）
     *
     * @param leftDrabable  左侧背景
     * @param leftTxt       左侧文字
     * @param titleTxt      中间标题
     * @param rightDrawable
     * @param rightleftTxt  右侧文字
     */
    public void setTitleBarView(int leftDrabable, String leftTxt,
                                String titleTxt, int rightDrawable, String rightTxt) {
        tvTitle.setText(titleTxt);
        btnLeft.setText(leftTxt);
    }
}
