package nu.annat.andchart.data;

import android.graphics.Rect;
import android.graphics.RectF;

import nu.annat.andchart.options.BarChartOptions;

public class BarDataPrep<T extends BarChartOptions> extends DataPrep<T> {


    public BarDataPrep(T options) {
        super(options);
    }

    @Override
    public void setData(ChartData data) {
        super.setData(data);
    }

    @Override
    protected void prep(ChartData data, Rect drawArea) {
        super.prep(data, drawArea);

        int dataSetSize = data.getDataSets().size();
        int dataPointSize = 0;
        double maxValue = Double.MIN_VALUE;
        for (DataSet dataSet : data.getDataSets()) {
            dataPointSize = Math.max(dataPointSize, dataSet.getDataPoints().size());
            for (DataPoint dataPoint : dataSet.getDataPoints()) {
                maxValue = Math.max(maxValue, dataPoint.getValue());
            }
        }

        float barDistance = options.barDistance;
        float groupDistance = options.groupDistance;
        float totalGroupDist = barDistance * dataPointSize;
        float totalValuesDist = groupDistance * (dataSetSize * dataPointSize );

        int totalCnt = dataSetSize * dataPointSize;
        float barWidth = (drawArea.width() - totalGroupDist - totalValuesDist) / totalCnt;
        double yScale = drawArea.height() / (maxValue * 1.1);
        int seriesCnt = 0;
        float dist = (barDistance + groupDistance) / 2.0f; // start with half
        for (DataSet dataSet : data.getDataSets()) {
            int dataCnt = 0;
            for (DataPoint dataPoint : dataSet.getDataPoints()) {
                BarDataPrep.BarDataPoint prepared = (BarDataPrep.BarDataPoint) dataPoint.getPrepared();

                prepared.position.top = 0f;
                prepared.position.bottom = (float) (dataPoint.getValue() * yScale);
                dist = barDistance/2 + dataCnt * barDistance;
                dist += dataCnt * dataSetSize * groupDistance;
                dist += groupDistance * seriesCnt;
                prepared.position.left = dist + (dataCnt * dataSetSize + seriesCnt ) * barWidth;
                prepared.position.right = prepared.position.left + barWidth;

                dataCnt++;
                //dist += groupDistance;
            }
           // dist += distPerSeries;
            seriesCnt++;
        }
    }

    @Override
    protected PreparedDataPoint getPreparedDataPoint() {
        return new BarDataPrep.BarDataPoint();
    }

    public static class BarDataPoint extends PreparedDataPoint {
        public RectF position = new RectF();
    }
}
