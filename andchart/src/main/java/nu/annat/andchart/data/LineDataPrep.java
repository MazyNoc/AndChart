package nu.annat.andchart.data;

import android.graphics.PointF;
import android.graphics.Rect;

import nu.annat.andchart.options.LineChartOptions;

public class LineDataPrep extends DataPrep<LineChartOptions> {

    public LineDataPrep(LineChartOptions options) {
        super(options);
    }

    @Override
    protected void prep(ChartData data, Rect drawArea) {
        super.prep(data, drawArea);


        PreparedChartData dataPrepared = data.getPrepared();
        float yScale = (float) (drawArea.height() / dataPrepared.minMaxValues.max);
        float xScale = drawArea.width() / (dataPrepared.datumPerSeries - 1);

        for (DataSet dataSet : data.getDataSets()) {
            int dataCnt = 0;
            for (DataPoint dataPoint : dataSet.getDataPoints()) {
                LineDataPoint prepared = (LineDataPoint) dataPoint.getPrepared();
                prepared.position.x = xScale * dataCnt++;
                prepared.position.y = (float) (yScale * dataPoint.getValue());
            }
        }
    }

    @Override
    protected PreparedDataPoint getPreparedDataPoint() {
        return new LineDataPoint();
    }

    public static class LineDataPoint extends PreparedDataPoint {
        public PointF position = new PointF();
    }
}
