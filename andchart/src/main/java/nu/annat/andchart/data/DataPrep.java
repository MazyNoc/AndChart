package nu.annat.andchart.data;

import android.graphics.Rect;
import android.graphics.RectF;

import nu.annat.andchart.options.ChartOptions;
import nu.annat.andchart.utils.MinMax;

public class DataPrep<T extends ChartOptions> {
    protected T options;
    private ChartData data;

    public DataPrep(T options) {
        this.options = options;
    }

    public void setData(ChartData data) {
        this.data = data;
        if (data == null) return;
        addPrepData(data);
    }

    public void setOptions(T options) {
        this.options = options;
    }

    public void prepare(Rect drawArea) {
        prep(data, drawArea);
    }

    public void setDrawArea(RectF drawArea) {
    }

    protected void prep(ChartData data, Rect drawArea) {
        PreparedChartData prepared = data.getPrepared();
        prepared.series = data.getDataSets().size();
        prepared.minMaxValues = new MinMax();
        for (DataSet dataSet : data.getDataSets()) {
            prepared.datumPerSeries = Math.max(prepared.datumPerSeries, dataSet.getDataPoints().size());
            for (DataPoint dataPoint : dataSet.getDataPoints()) {
                prepared.minMaxValues.append(dataPoint.getValue());
            }

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

    public static class PreparedChartData {
        public int series;
        public int datumPerSeries;
        public MinMax minMaxValues;

        public int getTotalValues() {
            return series * datumPerSeries;
        }
    }

    public static class PreparedDataSet {

    }

    public static class PreparedDataPoint {
        public float centerX;
        public float centerY;
    }
}
