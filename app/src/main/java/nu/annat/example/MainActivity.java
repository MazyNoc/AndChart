package nu.annat.example;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import nu.annat.andchart.axis.XAxis;
import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.data.DataPoint;
import nu.annat.andchart.data.DataSet;
import nu.annat.andchart.drawer.BarChart;
import nu.annat.andchart.drawer.LineChart;
import nu.annat.andchart.drawer.StackedBarChart;
import nu.annat.andchart.options.AxisOptions;
import nu.annat.andchart.options.BarChartOptions;
import nu.annat.andchart.options.LineChartOptions;
import nu.annat.example.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    boolean altData = false;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandler(this);

        BarChartOptions options = new BarChartOptions();
        options.barDistance = 80;
        options.groupDistance = 4;
        options.xAxis = new AxisOptions();
        options.yAxis = new AxisOptions();

        binding.chart1.setChartLayout(new BarChart(options));
        binding.chart1.setData(getData());

        binding.chart2.setChartLayout(new BarChart(options));
        binding.chart2.setData(getData2());


        BarChartOptions options2 = new BarChartOptions();
        options2.barDistance = 20;
        options2.groupDistance = 4;
        options2.xAxis = new AxisOptions();
        options2.yAxis = new AxisOptions();

        binding.chart3.setChartLayout(new StackedBarChart(options2));
        binding.chart3.setData(getData3());

        LineChartOptions lineChartOptions = new LineChartOptions();
        lineChartOptions.xAxis = new AxisOptions();
        lineChartOptions.yAxis = new AxisOptions();
        lineChartOptions.xAxis.setPainter(new XAxis());
        lineChartOptions.smooth = false;
        lineChartOptions.pointRadius = 20;
        binding.chart4.setChartLayout(new LineChart(lineChartOptions));
        binding.chart4.setData(getData2());

    }

    public ChartData getData() {
        ChartData data = new ChartData();

        List<DataPoint> dataPoints = new ArrayList<>();
        dataPoints.add(new DataPoint("a", 10.0));
        dataPoints.add(new DataPoint("b", 11.0));
        dataPoints.add(new DataPoint("c", 21.0));
        DataSet dataSet = new DataSet(null, new ColorDrawable(Color.BLUE), dataPoints);
        data.setDataSets(dataSet);
        return data;
    }

    public ChartData getDataV2() {
        ChartData data = new ChartData();

        List<DataPoint> dataPoints = new ArrayList<>();
        dataPoints.add(new DataPoint("a", 5.0));
        dataPoints.add(new DataPoint("b", 20.0));
        dataPoints.add(new DataPoint("c", 12.0));
        DataSet dataSet = new DataSet(null, new ColorDrawable(Color.BLUE), dataPoints);
        data.setDataSets(dataSet);
        return data;
    }

    public ChartData getData2() {
        ChartData data = new ChartData();

        List<DataPoint> dataPoints = new ArrayList<>();
        dataPoints.add(new DataPoint("a", 10.0));
        dataPoints.add(new DataPoint("b", 11.0));
        dataPoints.add(new DataPoint("c", 21.0));
        dataPoints.add(new DataPoint("d", 15.0));
//		dataPoints.add(new DataPoint("e", 3.0));
//		dataPoints.add(new DataPoint("f", 5.0));
        DataSet dataSet = new DataSet(null, new ColorDrawable(Color.GREEN), dataPoints);

        List<DataPoint> dataPoints2 = new ArrayList<>();
        dataPoints2.add(new DataPoint("a", 11.0));
        dataPoints2.add(new DataPoint("b", 12.0));
        dataPoints2.add(new DataPoint("c", 22.0));
        dataPoints2.add(new DataPoint("d", 15.0));
//		dataPoints2.add(new DataPoint("e", 3.0));
//		dataPoints2.add(new DataPoint("f", 5.0));
//		dataPoints2.add(new DataPoint("g", 7.0));
        DataSet dataSet2 = new DataSet(null, new ColorDrawable(Color.BLUE), dataPoints2);

        List<DataPoint> dataPoints3 = new ArrayList<>();
        dataPoints3.add(new DataPoint("a", 1.0));
        dataPoints3.add(new DataPoint("b", 2.0));
        dataPoints3.add(new DataPoint("c", 2.0));
        dataPoints3.add(new DataPoint("d", 15.0));
//		dataPoints2.add(new DataPoint("e", 3.0));
//		dataPoints2.add(new DataPoint("f", 5.0));
//		dataPoints2.add(new DataPoint("g", 7.0));
        DataSet dataSet3 = new DataSet(null, new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[]{Color.TRANSPARENT, Color.MAGENTA}), dataPoints3);

        data.setDataSets(dataSet, dataSet2, dataSet3);
        return data;
    }

    public ChartData getData3() {
        ChartData data = new ChartData();

        List<DataPoint> dataPoints = new ArrayList<>();
        dataPoints.add(new DataPoint("a", 10.0));
        dataPoints.add(new DataPoint("b", 11.0));
        dataPoints.add(new DataPoint("b", 11.0));
        dataPoints.add(new DataPoint("b", 32.0));
        DataSet dataSet = new DataSet(null, new ColorDrawable(Color.BLUE), dataPoints);

        List<DataPoint> dataPoints2 = new ArrayList<>();
        dataPoints2.add(new DataPoint("a", 11.0));
        dataPoints2.add(new DataPoint("b", 12.0));
        dataPoints2.add(new DataPoint("b", 8.0));
        dataPoints2.add(new DataPoint("b", 5.0));
        DataSet dataSet2 = new DataSet(null, new ColorDrawable(Color.GREEN), dataPoints2);

        List<DataPoint> dataPoints3 = new ArrayList<>();
        dataPoints3.add(new DataPoint("a", 11.0));
        dataPoints3.add(new DataPoint("b", 12.0));
        dataPoints3.add(new DataPoint("b", 8.0));
        dataPoints3.add(new DataPoint("b", 5.0));
        DataSet dataSet3 = new DataSet(null, new ColorDrawable(Color.RED), dataPoints3);

        data.setDataSets(dataSet, dataSet2, dataSet3);
        return data;
    }

    public void onChartClicked(View view) {
        if (altData)
            binding.chart1.setData(getData());
        else
            binding.chart1.setData(getDataV2());
        altData = !altData;
    }
}
