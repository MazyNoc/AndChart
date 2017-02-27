package nu.annat.andchart.axis;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;

import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.data.DataPoint;
import nu.annat.andchart.data.DataSet;
import nu.annat.andchart.options.AxisOptions;
import nu.annat.andchart.utils.Insets;


public class XAxisPainter extends AxisPainter {

    private Paint.FontMetrics fontMetrics;
    private TextPaint paint;

    public XAxisPainter(AxisOptions options) {
        super(options);

        paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(options.getTextSize());
        paint.setTypeface(Typeface.create("sans-serif-thin", 0));

        fontMetrics = paint.getFontMetrics();
    }

    @Override
    public void init(ChartData data) {
        super.init(data);

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawLine(drawRect.left, drawRect.top, drawRect.right, drawRect.top, paint);
        for (DataSet dataSet : data.getDataSets()) {
            for (DataPoint dataPoint : dataSet.getDataPoints()) {
                float centerX = dataPoint.getPrepared().centerX;
                centerX = centerX - (paint.measureText(dataPoint.getLabel()) / 2);
                canvas.save();
                canvas.translate(centerX, drawRect.top - fontMetrics.top);
                canvas.drawText(dataPoint.getLabel(), 0, 0, paint);
                canvas.restore();
            }
            break;
        }
        //  canvas.drawText("0", 0, 0, paint);
    }

    @Override
    public void onLayout(int left, int top, int right, int bottom) {
        drawRect.left = left;
        drawRect.top = (int) (bottom - getTotalHeight());
        drawRect.right = right;
        drawRect.bottom = bottom;

        // calculate all lengths.

    }

    @Override
    public Insets getInsets() {
        float totalHeigh = getTotalHeight();
        return super.getInsets().addBottom((int) totalHeigh);
    }

    private float getTotalHeight() {
        return -fontMetrics.top + fontMetrics.bottom;
    }
}
