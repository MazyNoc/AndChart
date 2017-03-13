package nu.annat.andchart.drawer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import nu.annat.andchart.data.DataPoint;
import nu.annat.andchart.data.PieChartPrep;
import nu.annat.andchart.options.PieChartOptions;

public class PieChartLayout extends ChartLayout<PieChartOptions, PieChartPrep> {

    private final Paint paint;

    public PieChartLayout(PieChartOptions options) {
        super(options);
        dataPrep = new PieChartPrep(options);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.getClipBounds(mainDrawArea);
        dataPrep.prepare(mainDrawArea);

        canvas.drawColor(Color.RED);

        float max = Math.min(canvas.getHeight(), canvas.getWidth());
        float scaleX = max / canvas.getWidth();
        float scaleY = max / canvas.getHeight();
        float left = canvas.getWidth() / 2 - (max / 2.0f);
        float top = canvas.getHeight() / 2 - (max / 2.0f);
        RectF rect = new RectF(left, top, canvas.getWidth() * scaleX + left, canvas.getHeight() * scaleY + top);


        for (DataPoint dataPoint : data.getDataSets().get(0).getDataPoints()) {
            PieChartPrep.PieChartDataPoint prepared = (PieChartPrep.PieChartDataPoint) dataPoint.getPrepared();
            // paint.setColor(dataPoint.getColor());
            //  canvas.drawArc(rect, prepared.startAngle, prepared.length, true, paint);
        }

    }

    @Override
    public void onChartLayout(int left, int top, int right, int bottom) {
        super.onChartLayout(left, top, right, bottom);
        mainDrawArea.set(left, top, right, bottom);
    }
}
