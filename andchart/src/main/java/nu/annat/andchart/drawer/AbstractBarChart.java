package nu.annat.andchart.drawer;

import android.graphics.Canvas;
import android.graphics.Rect;

import nu.annat.andchart.options.BarChartOptions;
import nu.annat.andchart.data.BarDataPrep;
import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.data.DataPoint;
import nu.annat.andchart.data.DataPrep;
import nu.annat.andchart.data.DataSet;

public abstract class AbstractBarChart<PREP extends DataPrep> extends AxisChart<BarChartOptions, PREP> {

    public AbstractBarChart(BarChartOptions options) {
        super(options);
    }
    private Rect tempRect = new Rect();
    @Override
    public void setData(ChartData data) {
        super.setData(data);
        dataPrep.setData(data);
    }

    @Override
    public void drawMainArea(ChartData data, Canvas canvas) {
        super.drawMainArea(data, canvas);
        DataPrep.PreparedChartData prepData = data.getPrepared();

        float distPerSeries =options.groupDistance;
        float totalSeriesDist = distPerSeries * (prepData.series + 1);
        float distPerValue = options.barDistance;
        float totalValuesDist = distPerValue * (prepData.getTotalValues() + 1);

        yScale = (mainDrawArea.height()) / 1.10;
        xScale = (mainDrawArea.width() - totalSeriesDist - totalValuesDist);

        int seriesCnt = 1;
        for (DataSet set : data.getDataSets()) {
            int dataCnt = 1;
            for (DataPoint dataPoint : set.getDataPoints()) {
                BarDataPrep.BarDataPoint prepared = (BarDataPrep.BarDataPoint) dataPoint.getPrepared();

                dataRect.set(prepared.position);
                dataRect.offset(mainDrawArea.left, mainDrawArea.bottom - dataRect.bottom - dataRect.top);
                dataRect.round(tempRect);
                set.getDrawable().setBounds(tempRect);
                set.getDrawable().draw(canvas);
                //canvas.drawRect(dataRect, rectPaint);
                dataCnt++;
            }
            seriesCnt++;
        }
    }
}
