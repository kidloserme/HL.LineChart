/*
 * Copyright 2013-2015 duolabao.com All right reserved. This software is the
 * confidential and proprietary information of duolabao.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with duolabao.com.
 */

package com.helong.mydraw;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

/**
 * 类MyHorizontalScrollView的实现描述：
 * 包裹折线图，让折线图可以滚动
 *
 * @author HELONG 2016/1/21 21:34
 */
public class LineHorizontalScrollView extends HorizontalScrollView {

    private LineView myView;
    private LineParameters lineParameters;
    /**
     * 刚绘制图表的时候，将图表滚动到最左边，也就是显示图表的最右边
     * 一旦手指触碰图标区域，图表再更新就不自动显示图表最右边
     * 如果手指头不碰图表区域，每次刷新，都会将图标最右边显示出来
     */
    private boolean touch = false;

    public LineHorizontalScrollView(Context context) {
        super(context);
        init(context);
    }

    public LineHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LineHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        setFillViewport(false);
        setHorizontalScrollBarEnabled(false);
        lineParameters = new LineParameters();
        myView = new LineView(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        myView.setLayoutParams(layoutParams);
        myView.setLineParameters(lineParameters);
        addView(myView);
    }

    public LineParameters getLineParameters() {
        return lineParameters;
    }

    public void setLineParameters(LineParameters lineParameters) {
        this.lineParameters = lineParameters;
    }

    public void refreshChart() {
        myView.setLineParameters(lineParameters);
        myView.requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(sizeWidth, sizeHeight);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        touch = true;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (touch) return;
        int w = myView.getMeasuredWidth();
        int width=getMeasuredWidth();
        scrollTo(w-width, 0);
    }
}
