package nu.annat.andchart.data;

public class BarDataPrep extends DataPrep {



	public static class BarDataPoint extends PreparedDataPoint {
		public double xScale = 1;
		public double yScale = 1;
		public double xOffset = 0;
		public double yOffset = 0;
	}

	@Override
	public void setData(ChartData data) {
		super.setData(data);
	}

	@Override
	protected void prep(ChartData data) {
		super.prep(data);

		int dataSetSize = data.getDataSets().size();
		int dataPointSize = 0;
		double maxValue = Double.MIN_VALUE;
		for (DataSet dataSet : data.getDataSets()) {
			dataPointSize = Math.max(dataPointSize, dataSet.getDataPoints().size());
			for (DataPoint dataPoint : dataSet.getDataPoints()) {
				maxValue = Math.max(maxValue, dataPoint.getValue());
			}
		}

		// try it out with mapping to total 0->1
		int totalCnt = dataSetSize * dataPointSize;
		double xScale = 1.0 / totalCnt;
		double yScale = 1.0 / maxValue;

		int seriesCnt = 0;
		for (DataSet dataSet : data.getDataSets()) {
			int dataCnt = 0;
			for (DataPoint dataPoint : dataSet.getDataPoints()) {
				BarDataPrep.BarDataPoint prepared = (BarDataPrep.BarDataPoint) dataPoint.getPrepared();
				prepared.xOffset = xScale * (dataCnt * dataSetSize) + xScale * seriesCnt;
				prepared.xScale = xScale;
				prepared.yOffset = 0;
				prepared.yScale = yScale * dataPoint.getValue();
				dataCnt++;
			}
			seriesCnt++;
		}
	}


	@Override
	protected PreparedDataPoint getPreparedDataPoint() {
		return new BarDataPrep.BarDataPoint();
	}
}
