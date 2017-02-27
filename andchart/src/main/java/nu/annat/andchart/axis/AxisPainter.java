package nu.annat.andchart.axis;

import android.graphics.Canvas;
import android.graphics.Rect;

import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.options.AxisOptions;
import nu.annat.andchart.utils.Insets;

public abstract class AxisPainter {
    protected ChartData data;
    protected AxisOptions options;
    protected Rect drawRect = new Rect();

    public AxisPainter(AxisOptions options) {
        this.options = options;
    }

    public void init(ChartData data) {
        this.data = data;
    }

    public abstract void draw(Canvas canvas);

    public Insets getInsets() {
        return new Insets();
    }

    public abstract void onLayout(int left, int top, int right, int bottom);
}
