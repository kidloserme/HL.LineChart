package com.helong.mydraw;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    static final String FILL_COLOR = "#8000AB81";
    static final String LINE_COLOR = "#00AB81";
    static final String TEXT_COLOR = "#444444";
    static final String AXES_COLOR = "#FF0000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LineChartView lineChartView = (LineChartView) findViewById(R.id.chart_view);
        TreeMap<String, String> drawMap = new TreeMap<>();
        for (int i = 0; i < 30; i++) {
            drawMap.put("x_" + i, String.valueOf(i * 10 + 10));
        }
        lineChartView.getLineParameters()
                .setDrawMap(drawMap)
                .setLineColor(Color.parseColor(LINE_COLOR))
                .setyAxesColor(Color.parseColor(AXES_COLOR))
                .setyAxesTextColor(Color.parseColor(TEXT_COLOR))
                .setyAxesTextSize(30)
                .setxAxesColor(Color.parseColor(AXES_COLOR))
                .setxAxesTextColor(Color.parseColor(TEXT_COLOR))
                .setxAxesTextSize(30)
                .setFillColor(Color.parseColor(FILL_COLOR))
                .setLineVaueTextSize(30);
        lineChartView.refreshChartView();
    }
}
