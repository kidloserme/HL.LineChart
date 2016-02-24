/*
 * Copyright 2013-2015 duolabao.com All right reserved. This software is the
 * confidential and proprietary information of duolabao.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with duolabao.com.
 */

package com.helong.mydraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 类YAxesView的实现描述：
 * 绘制Y轴线以及Y轴线上的坐标值
 *
 * @author HELONG 2016/1/23 12:46
 */
public class YAxesView extends View {

    private LineParameters lineParameters;

    Paint paint;

    public YAxesView(Context context) {
        super(context);
        init(context);
    }

    public YAxesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public YAxesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        lineParameters = new LineParameters();
        paint = new Paint();
        setPaint();
    }


    /**
     * 设置画笔参数
     */
    void setPaint() {
        paint.setColor(lineParameters.getyAxesColor());
        paint.setStrokeWidth(lineParameters.getAxesLineWidth());
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(lineParameters.getyAxesTextSize());
    }

    public LineParameters getLineParameters() {
        return lineParameters;
    }

    public void setLineParameters(LineParameters lineParameters) {
        this.lineParameters = lineParameters;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        String maxValueStrinh = String.valueOf(lineParameters.getVerticalNum() * lineParameters.getVerticalUnitValue());

        //设置Y轴视图的宽度，通过计算最大值的宽度然后加10，保证一定的空间
        int maxWidth = (int) (paint.measureText(maxValueStrinh) + 0.5f) + lineParameters.getAxesLineWidth() + 10;
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(maxWidth, sizeHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (lineParameters.getDrawMap().size() <= 0) {
            return;
        }
        setPaint();

        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight() - lineParameters.getxAxesMarginBottom();
        int drawHeight = measuredHeight - lineParameters.getyAxesMarginTop();
        int avgHeight = drawHeight / lineParameters.getVerticalNum();
        for (int i = 0; i < lineParameters.getVerticalNum(); i++) {
            paint.setColor(lineParameters.getyAxesTextColor());
            float textSize = paint.getTextSize();
            String drawValue=String.valueOf(lineParameters.getVerticalUnitValue() * (i + 1));
            canvas.drawText(drawValue,
                    measuredWidth-paint.measureText(drawValue)-5,
                    measuredHeight - avgHeight * (i + 1) + textSize / 3, paint);
        }
        paint.setColor(lineParameters.getyAxesColor());
        canvas.drawLine(getMeasuredWidth(), measuredHeight + lineParameters.getAxesLineWidth() / 4, getMeasuredWidth(), lineParameters.getyAxesMarginTop() - lineParameters.getGridLineWeight(), paint);
    }
}
