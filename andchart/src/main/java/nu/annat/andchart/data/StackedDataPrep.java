package nu.annat.andchart.data;

import android.graphics.Rect;
import android.graphics.RectF;

import java.util.List;

import nu.annat.andchart.options.BarChartOptions;

import static nu.annat.andchart.data.BarDataPrep.*;

public class StackedDataPrep extends DataPrep<BarChartOptions> {

    public StackedDataPrep(BarChartOptions options) {
        super(options);
    }

    public void setData(ChartData data) {
        super.setData(data);
    }

    @Override
    protected void prep(ChartData data, Rect drawArea) {
        //super.prep(data, drawArea);

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


        float distPerSeries = options.groupDistance;
        float totalSeriesDist = distPerSeries * dataSetSize;
        float distPerValue = options.barDistance;
        float totalValuesDist = distPerValue * (dataPointSize + 1);

        int totalCnt = dataSetSize * dataPointSize;
        float barWidth = (drawArea.width() - totalValuesDist) / dataPointSize;
        double yScale = (drawArea.height()) / maxValue;

        float xOffset = distPerValue ;
        int cnt = 0;
        for (DataPoint dataPoint : data.getDataSets().get(0).getDataPoints()) {
            BarDataPoint prepared = (BarDataPoint) dataPoint.getPrepared();
            RectF position = prepared.position;
            position.top = 0;
            position.bottom = (float) (dataPoint.getValue() * yScale) - distPerSeries;
            position.left = xOffset + cnt * barWidth;
            position.right = position.left + barWidth;
            prepared.centerX = (position.left + position.right)/2;
            cnt++;
            xOffset += distPerValue;
        }


        for (int i = 1; i < data.getDataSets().size(); i++) {
            List<DataPoint> dataSetPrev = data.getDataSets().get(i - 1).getDataPoints();
            List<DataPoint> dataSetCurrent = data.getDataSets().get(i).getDataPoints();
            cnt = 0;
            xOffset = distPerValue;
            for (int i1 = 0; i1 < dataSetPrev.size(); i1++) {
                DataPoint dataPointPrev = dataSetPrev.get(i1);
                DataPoint dataPointCurrent = dataSetCurrent.get(i1);
                BarDataPoint preparedPrev = (BarDataPoint) dataPointPrev.getPrepared();
                BarDataPoint preparedCurrent = (BarDataPoint) dataPointCurrent.getPrepared();

                float yoffset = preparedPrev.position.top + preparedPrev.position.height() + distPerSeries;
                RectF position = preparedCurrent.position;

                position.top = yoffset;
                position.bottom = yoffset + (float) (dataPointCurrent.getValue() * yScale) - distPerSeries;
                position.left = xOffset +  cnt * barWidth;
                position.right = position.left + barWidth;
                preparedCurrent.centerX = (position.left + position.right)/2;
                cnt++;
                xOffset += distPerValue;
            }
        }
    }

    @Override
    protected PreparedDataPoint getPreparedDataPoint() {
        return new BarDataPoint();
    }



}

