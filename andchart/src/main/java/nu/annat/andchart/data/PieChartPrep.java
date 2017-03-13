package nu.annat.andchart.data;

import android.graphics.Rect;

import nu.annat.andchart.options.PieChartOptions;

public class PieChartPrep extends DataPrep<PieChartOptions> {

    private float maxValue;

    public PieChartPrep(PieChartOptions options) {
        super(options);
    }

    @Override
    protected void prep(ChartData data, Rect drawArea) {
        if (data == null) return;
        super.prep(data, drawArea);
        PreparedChartData prepared = data.getPrepared();
        DataSet dataSet = data.getDataSets().get(0);
        maxValue = 0;
        for (DataPoint dataPoint : dataSet.getDataPoints()) {
            maxValue += dataPoint.getValue();
        }

        float startAngle = -90;
        for (DataPoint dataPoint : dataSet.getDataPoints()) {
            PieChartDataPoint prep = (PieChartDataPoint) dataPoint.getPrepared();
            prep.startAngle = startAngle;
            prep.length = (float) (dataPoint.getValue() / maxValue);
            startAngle += prep.length;
        }
    }

    @Override
    protected PreparedDataPoint getPreparedDataPoint() {
        return new PieChartDataPoint();
    }

    public static class PieChartDataPoint extends PreparedDataPoint {
        public float startAngle;
        public float length;
    }
}
