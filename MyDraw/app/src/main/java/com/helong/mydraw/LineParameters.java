/*
 * Copyright 2013-2015 duolabao.com All right reserved. This software is the
 * confidential and proprietary information of duolabao.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with duolabao.com.
 */

package com.helong.mydraw;

import android.graphics.Color;

import java.util.TreeMap;

/**
 * 类LineParameters的实现描述：
 * 折线图涉及到的参数，如果需要订制更细的需求，可以在此处添加参数，比如背景线的颜色宽度等
 *
 * @author HELONG 2016/1/23 15:13
 */
public class LineParameters {


    public LineParameters() {
        drawMap = new TreeMap<>();
    }

    private int lineWeight = 3;//折线部分的粗细

    /**
     * 坐标轴宽度
     */
    private int axesLineWidth = 3;

    /**
     * 需要绘制的数据集合
     */
    private TreeMap<String, String> drawMap;

    /**
     * 折线上方数字字体大小
     */
    private int lineVaueTextSize = 30;

    /**
     * 图标距离最右面的距离
     */
    private int chartMarginRight = 30;
    /**
     * 折线阴影部分颜色
     */
    private int fillColor;
    /**
     * 折线颜色
     */
    private int lineColor;

    /**
     * X坐标轴颜色
     */
    private int xAxesColor;
    /**
     * X坐标文字颜色
     */
    private int xAxesTextColor;

    /**
     * X坐标数字字体大小
     */
    private int xAxesTextSize = 30;

    /**
     * Y坐标轴颜色
     */
    private int yAxesColor;

    /**
     * Y坐标文字颜色
     */
    private int yAxesTextColor;

    /**
     * Y坐标文字大小
     */
    private int yAxesTextSize = 30;


    /**
     * 纵向平分数量，就是屏幕能够展示的Y轴平分的份数
     */
    private int verticalNum = 5;
    /**
     * 横向平分数量，就是屏幕能够展示的X轴平分的份数
     */
    private int horizontalNum = 7;
    /**
     * Y轴单位值
     */
    private int verticalUnitValue = 50;

    /**
     * Y轴坐标最大值
     * yAxesMaxValue以50为单位，如果小于50则最大值为50，介于50到100取100，以此类推
     */
    private double yAxesMaxValue = 0;
    /**
     * X坐标轴距离底部的高度
     */
    private int xAxesMarginBottom = 50;
    /**
     * Y坐标轴最大值距离视图顶部的距离
     */
    private int yAxesMarginTop = 20;


    /**
     * 网格颜色
     */
    private int gridLineColor;

    /**
     * 网格粗细
     */
    private int gridLineWeight;


    public int getxAxesTextSize() {
        return xAxesTextSize;
    }

    public LineParameters setxAxesTextSize(int xAxesTextSize) {
        this.xAxesTextSize = xAxesTextSize;
        return this;
    }

    public int getLineVaueTextSize() {
        return lineVaueTextSize;
    }

    public LineParameters setLineVaueTextSize(int lineVaueTextSize) {
        this.lineVaueTextSize = lineVaueTextSize;
        return this;
    }

    public int getChartMarginRight() {
        return chartMarginRight;
    }

    public LineParameters setChartMarginRight(int chartMarginRight) {
        this.chartMarginRight = chartMarginRight;
        return this;
    }

    public int getFillColor() {
        return fillColor == 0 ? defauttColor() : fillColor;
    }

    public LineParameters setFillColor(int fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public int getLineColor() {
        return lineColor == 0 ? defauttColor() : lineColor;
    }

    public LineParameters setLineColor(int lineColor) {
        this.lineColor = lineColor;
        return this;
    }

    public int getxAxesColor() {
        return xAxesColor == 0 ? defauttColor() : xAxesColor;
    }

    public LineParameters setxAxesColor(int xAxesColor) {
        this.xAxesColor = xAxesColor;
        return this;
    }

    public int getxAxesTextColor() {
        return xAxesTextColor == 0 ? defauttColor() : xAxesTextColor;
    }

    public LineParameters setxAxesTextColor(int xAxesTextColor) {
        this.xAxesTextColor = xAxesTextColor;
        return this;
    }

    public int getVerticalNum() {
        return verticalNum;
    }

    /**
     * Hahahha
     *
     * @param verticalNum
     * @return
     */
    public LineParameters setVerticalNum(int verticalNum) {
        this.verticalNum = verticalNum;
        return this;
    }

    public int getHorizontalNum() {
        return horizontalNum;
    }

    public LineParameters setHorizontalNum(int horizontalNum) {
        this.horizontalNum = horizontalNum;
        return this;
    }

    public int getVerticalUnitValue() {
        return verticalUnitValue;
    }

    public LineParameters setVerticalUnitValue(int verticalUnitValue) {
        this.verticalUnitValue = verticalUnitValue;
        return this;
    }

    public double getyAxesMaxValue() {
        return yAxesMaxValue;
    }

    public LineParameters setyAxesMaxValue(double yAxesMaxValue) {
        this.yAxesMaxValue = yAxesMaxValue;
        return this;
    }

    public int getxAxesMarginBottom() {
        return xAxesMarginBottom;
    }

    public LineParameters setxAxesMarginBottom(int xAxesMarginBottom) {
        this.xAxesMarginBottom = xAxesMarginBottom;
        return this;
    }

    public int getyAxesMarginTop() {
        return yAxesMarginTop;
    }

    public LineParameters setyAxesMarginTop(int yAxesMarginTop) {
        this.yAxesMarginTop = yAxesMarginTop;
        return this;
    }

    public TreeMap<String, String> getDrawMap() {
        return drawMap == null ? new TreeMap<String, String>() : drawMap;
    }

    public LineParameters setDrawMap(TreeMap<String, String> drawMap) {
        this.drawMap = drawMap;
        return this;
    }

    public int getyAxesColor() {
        return yAxesColor == 0 ? defauttColor() : yAxesColor;
    }

    public LineParameters setyAxesColor(int yAxesColor) {
        this.yAxesColor = yAxesColor;
        return this;
    }

    public int getyAxesTextColor() {
        return yAxesTextColor == 0 ? defauttColor() : yAxesTextColor;
    }

    public LineParameters setyAxesTextColor(int yAxesTextColor) {
        this.yAxesTextColor = yAxesTextColor;
        return this;
    }

    public int getyAxesTextSize() {
        return yAxesTextSize;
    }

    public LineParameters setyAxesTextSize(int yAxesTextSize) {
        this.yAxesTextSize = yAxesTextSize;
        return this;
    }

    public int getAxesLineWidth() {
        return axesLineWidth;
    }

    public LineParameters setAxesLineWidth(int axesLineWidth) {
        this.axesLineWidth = axesLineWidth;
        return this;
    }

    private int defauttColor() {
        return Color.parseColor("#00AB81");
    }


    public int getGridLineColor() {
        return gridLineColor == 0 ? defauttColor(): gridLineColor;
    }

    public LineParameters setGridLineColor(int gridLineColor) {
        this.gridLineColor = gridLineColor;
        return this;
    }

    public int getGridLineWeight() {
        return gridLineWeight == 0 ? 1 : gridLineWeight;
    }

    public LineParameters setGridLineWeight(int gridLineWeight) {
        this.gridLineWeight = gridLineWeight;
        return this;
    }

    public int getLineWeight() {
        return lineWeight;
    }

    public LineParameters setLineWeight(int lineWeight) {
        this.lineWeight = lineWeight;
        return this;
    }
}
