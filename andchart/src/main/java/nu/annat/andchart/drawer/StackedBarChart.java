package nu.annat.andchart.drawer;

import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.data.DataPrep;
import nu.annat.andchart.data.StackedDataPrep;

public class StackedBarChart extends BarChart {

	@Override
	public void setData(ChartData data) {
		super.setData(data);
		DataPrep dataPrep = new StackedDataPrep();
		dataPrep.setData(data);
		dataPrep.prepare();
	}
}
