package nu.annat.andchart.axis;

import android.graphics.Canvas;
import android.graphics.Rect;

import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.utils.Insets;

public class XAxis extends Axis {

    @Override
    protected void init() {
        super.init();
        paint.setTextSize(12);
    }

    @Override
    public void draw(Canvas canvas, ChartData data, Rect area) {
        init();
        canvas.drawLine(area.left, area.top, area.right, area.top, paint);

        canvas.drawText("0", 0, area.top , paint);
        canvas.drawText("0", 0, 0, paint);
    }


    @Override
    public Insets getInsets() {
        return super.getInsets().addBottom(40);
    }
}
