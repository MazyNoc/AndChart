package nu.annat.andchart.data;

import java.util.List;

public class StackedDataPrep extends BarDataPrep {

	public static class StackedChartData extends PreparedChartData {
	}

	public static class StackedDataSet extends PreparedDataSet {
		public double xScale;
		public double yScale;
		public double xOffset;
		public double yOffset;
	}

	public void setData(ChartData data) {
		super.setData(data);
	}

	@Override
	protected void prep(ChartData data) {
		super.prep(data);

		int dataSetSize = data.getDataSets().size();
		int dataPointSize = data.getDataSets().get(0).getDataPoints().size();
		double maxValue = Double.MIN_VALUE;

		for (int i = 0; i < data.getDataSets().get(0).getDataPoints().size(); i++) {
			double height = 0;
			for (int i1 = 0; i1 < data.getDataSets().size(); i1++) {
				DataPoint dataPoint = data.getDataSets().get(i1).getDataPoints().get(i);
				height += dataPoint.getValue();
			}
			maxValue = Math.max(maxValue, height);
		}

		// try it out with mapping to total 0->1
		int totalCnt = dataSetSize * dataPointSize;
		double xScale = 1.0 / dataPointSize;
		double yScale = 1.0 / maxValue;

		int cnt = 0;
		for (DataPoint dataPoint : data.getDataSets().get(0).getDataPoints()) {
			BarDataPoint prepared = (BarDataPoint) dataPoint.getPrepared();
			prepared.xOffset = xScale * cnt;
			prepared.xScale = xScale;
			prepared.yOffset = 0;
			prepared.yScale = yScale * dataPoint.getValue();
			cnt++;
		}

		for (int i = 1; i < data.getDataSets().size(); i++) {
			List<DataPoint> dataSetPrev = data.getDataSets().get(i - 1).getDataPoints();
			List<DataPoint> dataSetCurrent = data.getDataSets().get(i).getDataPoints();
			for (int i1 = 0; i1 < dataSetPrev.size(); i1++) {
				DataPoint dataPointPrev = dataSetPrev.get(i1);
				DataPoint dataPointCurrent = dataSetCurrent.get(i1);
				BarDataPoint preparedPrev = (BarDataPoint) dataPointPrev.getPrepared();
				BarDataPoint preparedCurrent = (BarDataPoint) dataPointCurrent.getPrepared();

				preparedCurrent.xOffset = xScale * i1;
				preparedCurrent.xScale = xScale;
				preparedCurrent.yOffset = preparedPrev.yOffset + preparedPrev.yScale;
				preparedCurrent.yScale = yScale * dataPointCurrent.getValue();
			}
		}

//		int seriesCnt = 0;
//		for (DataSet dataSet : data.getDataSets()) {
//			int dataCnt = 0;
//			for (DataPoint dataPoint : dataSet.getDataPoints()) {
//				BarDataPrep.BarDataPoint prepared = (BarDataPrep.BarDataPoint) dataPoint.getPrepared();
//				prepared.xOffset = xScale * (dataCnt * dataSetSize) + xScale * seriesCnt;
//				prepared.xScale = xScale;
//				prepared.yOffset = 0;
//				prepared.yScale = yScale * dataPoint.getValue();
//				dataCnt++;
//			}
//			seriesCnt++;
//		}
	}



}

