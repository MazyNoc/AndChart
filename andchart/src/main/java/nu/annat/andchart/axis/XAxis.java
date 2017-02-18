package nu.annat.andchart.axis;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.data.DataPoint;
import nu.annat.andchart.data.DataSet;
import nu.annat.andchart.utils.Insets;

public class XAxis extends Axis {

    private Paint.FontMetrics fontMetrics;

    @Override
    public void init(ChartData data) {
        super.init(data);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(124);

        fontMetrics = paint.getFontMetrics();

    }

    @Override
    public void draw(Canvas canvas, Rect area) {
        canvas.drawLine(area.left, area.top, area.right, area.top, paint);
        for (DataSet dataSet : data.getDataSets()) {
            for (DataPoint dataPoint : dataSet.getDataPoints()) {
                float centerY = dataPoint.getPrepared().centerY;
                canvas.drawText(dataPoint.getLabel(), centerY, area.top - fontMetrics.top, paint);
            }
        }
        //  canvas.drawText("0", 0, 0, paint);
    }


    @Override
    public Insets getInsets() {
        float totalHeigh = -fontMetrics.top + fontMetrics.bottom;
        return super.getInsets().addBottom((int) totalHeigh);
    }
}
