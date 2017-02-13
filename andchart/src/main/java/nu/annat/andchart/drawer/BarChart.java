package nu.annat.andchart.drawer;

import android.graphics.Canvas;

import nu.annat.andchart.data.BarDataPrep;
import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.data.DataPoint;
import nu.annat.andchart.data.DataPrep;
import nu.annat.andchart.data.DataSet;

public class BarChart extends AxisChart {

	@Override
	public void setData(ChartData data) {
		super.setData(data);
		DataPrep dataPrep = new BarDataPrep();
		dataPrep.setData(data);
		dataPrep.prepare();
	}

	@Override
	public void draw(ChartData data, Canvas canvas) {
		super.draw(data, canvas);

		DataPrep.PreparedChartData prepData = data.getPrepared();
		float distPerSeries = 0;
		float totalSeriesDist = distPerSeries * (prepData.series + 1);
		float distPerValue = 20;
		float totalValuesDist = distPerValue * (prepData.getTotalValues() + prepData.series);

		yScale = (mainDrawArea.height()) / 1.10;
		xScale = (mainDrawArea.width() - totalSeriesDist - totalValuesDist);

		int seriesCnt = 1;
		for (DataSet set : data.getDataSets()) {
			int dataCnt = 1;
			for (DataPoint dataPoint : set.getDataPoints()) {
				BarDataPrep.BarDataPoint prepared = (BarDataPrep.BarDataPoint) dataPoint.getPrepared();
				dataRect.set(0, 0, (float) prepared.xScale, (float) prepared.yScale);
				dataRect.offset((float) prepared.xOffset, -(float) prepared.yOffset);
				scaleRect(dataRect, xScale, yScale);
				dataRect.offset((seriesCnt * distPerSeries) + (dataCnt * distPerValue), 0);
				dataRect.offset(mainDrawArea.left, mainDrawArea.bottom - dataRect.height());
				canvas.drawRect(dataRect, rectPaint);
				dataCnt++;
			}
			seriesCnt++;
		}
	}
}
