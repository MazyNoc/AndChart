package nu.annat.andchart.axis;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.utils.Insets;

public abstract class Axis {
    protected Paint paint = new Paint();

    protected void init() {
        paint.setColor(Color.BLACK);
    }

    public abstract void draw(Canvas canvas, ChartData data, Rect area);

    public Insets getInsets() {
        return new Insets(0, 0, 0, 40);
    }
}
