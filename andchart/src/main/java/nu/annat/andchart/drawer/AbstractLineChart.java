package nu.annat.andchart.drawer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.data.DataPrep;
import nu.annat.andchart.options.AxisChartOptions;
import nu.annat.andchart.options.ChartOptions;

public abstract class AbstractLineChart<T extends AxisChartOptions, PREP extends DataPrep> extends AxisChart<T, PREP> {

    private PointF tempPoint = new PointF();
    private PointF lastPoint = new PointF();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public AbstractLineChart(T options) {
        super(options);
    }


    @Override
    public void drawMainArea(ChartData data, Canvas canvas) {
        super.drawMainArea(data, canvas);
    }
}
