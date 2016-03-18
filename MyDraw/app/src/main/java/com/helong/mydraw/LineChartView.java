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
import android.widget.LinearLayout;

import java.util.Set;
import java.util.TreeMap;

/**
 * 类LineChartView的实现描述：
 * 对外的折线图视图，将此View添加到相关的容器中即可绘制折线图
 *
 * @author HELONG 2016/1/23 13:09
 */
public class LineChartView extends LinearLayout {

    /**
     * Y坐标轴视图
     */
    YAxesView yAxesView;

    /**
     * 图表视图
     */
    LineHorizontalScrollView charView;

    /**
     * 绘制图表所需要的参数
     */
    LineParameters lineParameters;

    public LineParameters getLineParameters() {
        return lineParameters;
    }

    public void setLineParameters(LineParameters lineParameters) {
        this.lineParameters = lineParameters;
    }

    /**
     * 计算Y坐标轴最大值
     */
    void calculateYAxesMaxValue() {
        /**
         * 需要绘制的Map集合中最大的数
         */
        double maxValue = 0;
        if (lineParameters.getDrawMap() != null) {
            Set<String> keys = lineParameters.getDrawMap().keySet();
            for (String key : keys) {
                String v = lineParameters.getDrawMap().get(key);
                double value = Double.valueOf(v);
                if (value > maxValue) {
                    maxValue = value;
                }
            }
        }

        if (maxValue < 10) {
            lineParameters.setyAxesMaxValue(10);
            return;
        }
        double ceil=Math.pow(10,String.valueOf((int)maxValue).length()-1);
        lineParameters.setyAxesMaxValue(((int) (maxValue / ceil + 0.5f) + 1) * ceil);
    }

    public LineChartView(Context context) {
        super(context);
        init(context);
    }

    public LineChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化视图
     *
     * @param context
     */
    private void init(Context context) {
        setOrientation(HORIZONTAL);
        yAxesView = new YAxesView(context);
        charView = new LineHorizontalScrollView(context);
        addView(yAxesView);
        addView(charView);
        lineParameters = new LineParameters();
        updateParams();
    }

    /**
     * 设置需要绘制的数据集合
     *
     * @param drawMap
     */
    public void setDrawMap(TreeMap<String, String> drawMap) {
        lineParameters.setDrawMap(drawMap);
    }

    /**
     * 刷新视图
     */
    public void refreshChartView() {
        updateParams();
        charView.setLineParameters(lineParameters);
        charView.refreshChart();
        yAxesView.setLineParameters(lineParameters);
        yAxesView.requestLayout();
    }

    void updateParams() {
        calculateYAxesMaxValue();
        lineParameters.setVerticalUnitValue((int) (lineParameters.getyAxesMaxValue() / lineParameters.getVerticalNum() + 0.5f));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
