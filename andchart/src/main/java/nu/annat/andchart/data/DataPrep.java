package nu.annat.andchart.data;

import android.graphics.RectF;

public class DataPrep {
	public static class PreparedChartData {
		public int series;
		public int datumPerSeries;

		public int getTotalValues() {
			return series * datumPerSeries;
		}
	}

	public static class PreparedDataSet {

	}

	public static class PreparedDataPoint {

	}

	private ChartData data;

	public void setData(ChartData data) {
		this.data = data;
		if (data == null) return;
		addPrepData(data);
	}

	public void prepare() {
		prep(data);
	}

	public void setDrawArea(RectF drawArea) {
	}

	protected void prep(ChartData data) {
		PreparedChartData prepared = data.getPrepared();
		prepared.series = data.getDataSets().size();
		for (DataSet dataSet : data.getDataSets()) {
			prepared.datumPerSeries = Math.max(prepared.datumPerSeries, dataSet.getDataPoints().size());
		}
	}

	protected void addPrepData(ChartData data) {
		data.setPrepared(getPreparedChart());
		for (DataSet dataSet : data.getDataSets()) {
			dataSet.setPrepared(getPreparedDataSet());
			for (DataPoint dataPoint : dataSet.getDataPoints()) {
				dataPoint.setPrepared(getPreparedDataPoint());
			}
		}
	}

	protected PreparedDataSet getPreparedDataSet() {
		return new PreparedDataSet();
	}

	protected PreparedChartData getPreparedChart() {
		return new PreparedChartData();
	}

	protected PreparedDataPoint getPreparedDataPoint() {
		return new PreparedDataPoint();
	}
}