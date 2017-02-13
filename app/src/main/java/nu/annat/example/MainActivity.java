package nu.annat.example;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.data.DataPoint;
import nu.annat.andchart.data.DataSet;
import nu.annat.andchart.drawer.BarChart;
import nu.annat.andchart.drawer.StackedBarChart;
import nu.annat.example.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
		binding.chart1.setChartLayout(new BarChart());
		binding.chart1.setData(getData());

		binding.chart2.setChartLayout(new BarChart());
		binding.chart2.setData(getData2());

		binding.chart3.setChartLayout(new StackedBarChart());
		binding.chart3.setData(getData3());
	}

	public ChartData getData() {
		ChartData data = new ChartData();

		List<DataPoint> dataPoints = new ArrayList<>();
		dataPoints.add(new DataPoint("a", 10.0));
		dataPoints.add(new DataPoint("b", 11.0));
		dataPoints.add(new DataPoint("c", 21.0));
		DataSet dataSet = new DataSet(dataPoints);
		data.setDataSets(dataSet);
		return data;
	}

	public ChartData getData2() {
		ChartData data = new ChartData();

		List<DataPoint> dataPoints = new ArrayList<>();
		dataPoints.add(new DataPoint("a", 10.0));
		dataPoints.add(new DataPoint("b", 11.0));
		dataPoints.add(new DataPoint("c", 21.0));
//		dataPoints.add(new DataPoint("d", 15.0));
//		dataPoints.add(new DataPoint("e", 3.0));
//		dataPoints.add(new DataPoint("f", 5.0));
		DataSet dataSet = new DataSet(dataPoints);

		List<DataPoint> dataPoints2 = new ArrayList<>();
		dataPoints2.add(new DataPoint("a", 11.0));
		dataPoints2.add(new DataPoint("b", 12.0));
		dataPoints2.add(new DataPoint("c", 22.0));
//		dataPoints2.add(new DataPoint("d", 15.0));
//		dataPoints2.add(new DataPoint("e", 3.0));
//		dataPoints2.add(new DataPoint("f", 5.0));
//		dataPoints2.add(new DataPoint("g", 7.0));
		DataSet dataSet2 = new DataSet(dataPoints2);

		List<DataPoint> dataPoints3 = new ArrayList<>();
		dataPoints3.add(new DataPoint("a", 1.0));
		dataPoints3.add(new DataPoint("b", 2.0));
		dataPoints3.add(new DataPoint("c", 2.0));
//		dataPoints2.add(new DataPoint("d", 15.0));
//		dataPoints2.add(new DataPoint("e", 3.0));
//		dataPoints2.add(new DataPoint("f", 5.0));
//		dataPoints2.add(new DataPoint("g", 7.0));
		DataSet dataSet3 = new DataSet(dataPoints3);

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
		DataSet dataSet = new DataSet(dataPoints);

		List<DataPoint> dataPoints2 = new ArrayList<>();
		dataPoints2.add(new DataPoint("a", 11.0));
		dataPoints2.add(new DataPoint("b", 12.0));
		dataPoints2.add(new DataPoint("b", 8.0));
		dataPoints2.add(new DataPoint("b", 5.0));
		DataSet dataSet2 = new DataSet(dataPoints2);

		data.setDataSets(dataSet, dataSet2);
		return data;
	}
}
