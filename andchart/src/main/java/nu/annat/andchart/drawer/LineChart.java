package nu.annat.andchart.drawer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.data.DataPoint;
import nu.annat.andchart.data.DataPrep;
import nu.annat.andchart.data.DataSet;
import nu.annat.andchart.data.LineDataPrep;
import nu.annat.andchart.options.LineChartOptions;

public class LineChart extends AbstractLineChart<LineChartOptions, LineDataPrep> {

    private PointF tempPoint = new PointF();
    private PointF lastPoint = new PointF();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public LineChart(LineChartOptions options) {
        super(options);
        dataPrep = new LineDataPrep(options);
    }

    @Override
    public void setData(ChartData data) {
        super.setData(data);
        dataPrep.setData(data);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

    }

    @Override
    public void drawMainArea(ChartData data, Canvas canvas) {
        super.drawMainArea(data, canvas);
        Path p = new Path();
        DataPrep.PreparedChartData prepData = data.getPrepared();
        for (DataSet dataSet : data.getDataSets()) {
            if (options.smooth)
                prepareSmoothLine(p, dataSet);
            else
                prepareLine(p, dataSet);

            canvas.drawPath(p, paint);

            if (options.pointRadius > 0) {
                for (DataPoint dataPoint : dataSet.getDataPoints()) {
                    LineDataPrep.LineDataPoint prepared = (LineDataPrep.LineDataPoint) dataPoint.getPrepared();
                    tempPoint.set(mainDrawArea.left + prepared.position.x, mainDrawArea.bottom - prepared.position.y);
                    canvas.drawCircle(tempPoint.x, tempPoint.y, options.pointRadius, paint);
                }
            }
        }

    }

    /* wrong calc.. the line never passes the points.*/
    private void prepareSmoothLine(Path p, DataSet dataSet) {

        PointF currPoint;
        PointF prevPoint;
        PointF nextPoint;


        p.rewind();

        paint.setColor(dataSet.getColor());
        int size = dataSet.getDataPoints().size();
        for (int i = 0; i < size; i++) {
            currPoint = ((LineDataPrep.LineDataPoint) dataSet.getDataPoints().get(i - 1).getPrepared()).position;
            if (i == 0) {
                nextPoint = ((LineDataPrep.LineDataPoint) dataSet.getDataPoints().get(i + 1).getPrepared()).position;
                tempPoint.x = ((nextPoint.x - currPoint.x) / 3);
                tempPoint.y = ((nextPoint.y - currPoint.y) / 3);

            } else if (i == size - 1) {
                prevPoint = ((LineDataPrep.LineDataPoint) dataSet.getDataPoints().get(i - 1).getPrepared()).position;
                tempPoint.x = (( currPoint.x - prevPoint.x) / 3);
                tempPoint.y = (( currPoint.y - prevPoint.y) / 3);
            } else {
                prevPoint = ((LineDataPrep.LineDataPoint) dataSet.getDataPoints().get(i - 1).getPrepared()).position;
                nextPoint = ((LineDataPrep.LineDataPoint) dataSet.getDataPoints().get(i + 1).getPrepared()).position;
                tempPoint.x = (( nextPoint.x - prevPoint.x) / 3);
                tempPoint.y = (( nextPoint.y - prevPoint.y) / 3);
            }
        }

        tempPoint.set(mainDrawArea.left + tempPoint.x, mainDrawArea.bottom - tempPoint.y);
        if (p.isEmpty())
            p.moveTo(tempPoint.x, tempPoint.y);
        else
            p.quadTo(lastPoint.x, lastPoint.y, (tempPoint.x + lastPoint.x) / 2, (tempPoint.y + lastPoint.y) / 2);


//
//        for (DataPoint dataPoint : dataSet.getDataPoints()) {
//            LineDataPrep.LineDataPoint prepared = (LineDataPrep.LineDataPoint) dataPoint.getPrepared();
//            tempPoint.set(mainDrawArea.left + prepared.position.x, mainDrawArea.bottom - prepared.position.y);
//            if (p.isEmpty())
//                p.moveTo(tempPoint.x, tempPoint.y);
//            else
//                p.quadTo(lastPoint.x, lastPoint.y, (tempPoint.x + lastPoint.x) / 2, (tempPoint.y + lastPoint.y) / 2);
//            lastPoint.set(tempPoint.x, tempPoint.y);
//
//        }
//        p.lineTo(lastPoint.x, lastPoint.y);
    }

    private void prepareLine(Path p, DataSet dataSet) {
        p.rewind();
        paint.setColor(dataSet.getColor());
        for (DataPoint dataPoint : dataSet.getDataPoints()) {
            LineDataPrep.LineDataPoint prepared = (LineDataPrep.LineDataPoint) dataPoint.getPrepared();
            tempPoint.set(mainDrawArea.left + prepared.position.x, mainDrawArea.bottom - prepared.position.y);
            if (p.isEmpty())
                p.moveTo(tempPoint.x, tempPoint.y);
            else
                p.lineTo(tempPoint.x, tempPoint.y);
        }
    }
}
