package com.example.swimminggo.view.swimmer.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.swimminggo.R;
import com.example.swimminggo.constant.ExerciseConstant;
import com.example.swimminggo.models.Style;
import com.example.swimminggo.models.Swimmer;
import com.example.swimminggo.models.Time;
import com.example.swimminggo.presenter.ChartPresenter;
import com.example.swimminggo.presenter.presenterImpl.ChartPresenterImpl;
import com.example.swimminggo.singleton.CurrentDistance;
import com.example.swimminggo.singleton.CurrentStyle;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class SwimmerMonthChartFragment extends Fragment {
    Spinner spnMonth, spnYear;
    Button btnViewChart;
    LineChartView lineChartView;
    ChartPresenter chartPresenter;
    View view;
    int swimmerId;

    public SwimmerMonthChartFragment(int swimmerId) {
        this.swimmerId = swimmerId;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_swimmer_month_chart, container, false);
        initComponent();
        initDatabase();
        action();
        return view;
    }

    private void initDatabase() {
        ArrayList<String> months = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"));
        spnMonth.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, months));
        ArrayList<String> years = new ArrayList<>();
        for (int i = 2025; i >= 2000; i--)
            years.add(i + "");

        spnYear.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, years));
    }

    private void initComponent() {
        chartPresenter = new ChartPresenterImpl(this);
        spnMonth = view.findViewById(R.id.spn_month);
        spnYear = view.findViewById(R.id.spn_year);
        btnViewChart = view.findViewById(R.id.btn_view_chart);
        lineChartView = view.findViewById(R.id.lineChart);
    }

    private void action() {
        btnViewChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chartPresenter.onGetDataByMonth(CurrentStyle.getInstance().getStyle().getId(),
                        CurrentDistance.getInstance().getDistance().getId(),
                        swimmerId,
                        Integer.parseInt(spnMonth.getSelectedItem().toString()),
                        Integer.parseInt(spnYear.getSelectedItem().toString()));
            }
        });
    }

    public void setupLineChart(List<String> axisData, List<Integer> yAxisData) {
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();
        Line line = new Line(yAxisValues);
        for (int i = 0; i < axisData.size(); i++) {
            axisValues.add(i, new AxisValue(i).setLabel(axisData.get(i)));
        }

        for (int i = 0; i < yAxisData.size(); i++) {
            yAxisValues.add(new PointValue(i, yAxisData.get(i)));
        }
        List lines = new ArrayList();
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        Axis yAxis = new Axis();
        data.setAxisYLeft(yAxis);
        data.setAxisXBottom(axis);

        axis.setTextColor(Color.parseColor("#9C27B0"));
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        viewport.top = maxTime(CurrentDistance.getInstance().getDistance().getValue());
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);

        lineChartView.setLineChartData(data);
    }

    private int maxTime(int distance){
        switch (distance){
            case 50 : return new Time(1).toMillisec();
            case 100 : return new Time(2).toMillisec();
            case 200 : return new Time(4).toMillisec();
            case 500 : return new Time(10).toMillisec();
            case 1000 : return new Time(20).toMillisec();
        }
        return new Time(30).toMillisec();
    }



}
