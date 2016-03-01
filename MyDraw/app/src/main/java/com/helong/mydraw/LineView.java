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
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 类MyView的实现描述：
 * <p/>
 * 绘制折线图处Y坐标轴之外的其他元素
 *
 * @author HELONG 2016/1/21 20:50
 */
public class LineView extends View {

    private int defaultColor = 0;

    private float defaultStrokeWidth = 0;

    private int chartWidth = 0;

    private LineParameters lineParameters;


    /**
     * 各横坐标之间距离
     */
    private int distance = 100;
    /**
     * 比率，绘制折线图时候，Y轴方向上的绘制比率，就是1像素代表的数值大小，
     * 计算方法：Y轴的最大值除真实绘图区域像素数
     */
    private double rate = 1;

    private Paint paint;

    public LineView(Context context) {
        super(context);
        init(context);
    }

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    void init(Context context) {
        defaultColor = Color.parseColor("#000000");
        lineParameters = new LineParameters();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(defaultColor);
        defaultStrokeWidth = paint.getStrokeWidth();
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

        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        rate = (sizeHeight - lineParameters.getyAxesMarginTop() - lineParameters.getxAxesMarginBottom()) / lineParameters.getyAxesMaxValue();

        View parentView = (View) getParent();
        //因为一屏显示的数量是指父容器视图中，所以使用父容器的宽度
        int parentWidth = parentView.getMeasuredWidth();
        this.distance = (int) (parentWidth / (float) lineParameters.getHorizontalNum() + 0.5f);
        chartWidth = (lineParameters.getDrawMap().size()) * this.distance + lineParameters.getChartMarginRight();

        setMeasuredDimension(chartWidth, sizeHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(distance == 0) return;

        /**
         *绘制纵横向背景线
         */
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        /**
         * Y轴最大数字和数字0之间的距离
         */
        int drawYAxesHeight = measuredHeight - lineParameters.getxAxesMarginBottom() - lineParameters.getyAxesMarginTop();
        int drawLineAreaHeight = measuredHeight - lineParameters.getxAxesMarginBottom();
        int avgHeight = drawYAxesHeight / lineParameters.getVerticalNum();
        paint.setColor(lineParameters.getGridLineColor());
        paint.setStrokeWidth(lineParameters.getGridLineWeight());
        //绘制纵向背景线（每条线从下往上绘制）
        for (int i = 0; i < measuredWidth / distance; i++) {
            float startX = (i + 1) * distance;
            float startY = drawLineAreaHeight;
            float stopX = startX;
            float stopY = lineParameters.getyAxesMarginTop();
            canvas.drawLine(startX, startY, stopX, stopY, paint);
        }
        //绘制横向背景线（每条线从左向右绘制）
        for (int i = 0; i < lineParameters.getVerticalNum(); i++) {
            float startX = 0;
            float startY = avgHeight * i + lineParameters.getyAxesMarginTop();
            canvas.drawLine(startX, startY, (float) measuredWidth - lineParameters.getChartMarginRight(), startY, paint);
        }

        /**
         * 绘制X坐标轴
         */
        int bottomY = getBottom() - lineParameters.getxAxesMarginBottom();
        paint.setColor(lineParameters.getxAxesColor());
        paint.setStrokeWidth(lineParameters.getAxesLineWidth() / 2);
        canvas.drawLine(0, bottomY, chartWidth - lineParameters.getChartMarginRight(), bottomY, paint);


        if (lineParameters.getDrawMap().size() <= 0) {
            return;
        }

        /**
         * 开始绘制图表区域
         */
        int x = 0;
        int lastX = 0, lastY = getBottom() - lineParameters.getxAxesMarginBottom();
        List<Point> circlePoint = new ArrayList<>();
        Set<String> keys = lineParameters.getDrawMap().keySet();
        for (String key : keys) {
            x += distance;
            double value = Double.parseDouble(lineParameters.getDrawMap().get(key));
            int newX = x;
            int newY = (bottomY - (int) (value * rate + 0.5f));

            /**
             * 绘制阴影区域部分
             */
            Path path = new Path();
            path.moveTo(lastX, bottomY);
            path.lineTo(lastX, lastY);
            path.lineTo(newX, newY);
            path.lineTo(newX, bottomY);
            paint.setColor(lineParameters.getFillColor());
            canvas.drawPath(path, paint);

            /**
             * 绘制折线部分
             */
            paint.setColor(lineParameters.getLineColor());
            paint.setStrokeWidth(lineParameters.getLineWeight());
            canvas.drawLine(lastX, lastY, newX, newY, paint);
            circlePoint.add(new Point(newX, newY));

            /**
             * 绘制数值，数值距离折线15像素
             */
            paint.setStrokeWidth(defaultStrokeWidth);
            paint.setTextSize(lineParameters.getLineVaueTextSize());
            canvas.drawText(value + "", x - (paint.measureText(value + "")), newY - 10, paint);

            /**
             * 绘制X坐标轴字体（从当前坐标左移文字长度一半的距离开始绘制）
             */
            paint.setColor(lineParameters.getxAxesTextColor());
            paint.setTextSize(lineParameters.getxAxesTextSize());
            paint.setStrokeWidth(defaultStrokeWidth);
            canvas.drawText(key, x - (paint.measureText(key) / 2),
                    bottomY + paint.getTextSize() + 5,//lineParameters.getxAxesMarginBottom(),
                    paint);

            lastX = newX;
            lastY = newY;
        }

        /**
         * 绘制数值附近的圆点,半径大小为10像素
         */
        paint.setColor(lineParameters.getLineColor());
        paint.setTextSize(lineParameters.getLineVaueTextSize());
        for (Point point : circlePoint) {
            paint.setStrokeWidth(2);
            paint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(point.x, point.y, 5, paint);

            //设置为0 ，暂时隐藏
//            paint.setStrokeWidth(3);
//            paint.setStyle(Paint.Style.STROKE);
//            canvas.drawCircle(point.x, point.y, 0, paint);
        }

        /**
         * 重置参数
         */
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(defaultColor);
        paint.setStrokeWidth(defaultStrokeWidth);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }
}
