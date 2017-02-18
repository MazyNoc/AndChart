package nu.annat.andchart.drawer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import nu.annat.andchart.Configuration;
import nu.annat.andchart.axis.Axis;
import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.data.DataPrep;
import nu.annat.andchart.options.AxisChartOptions;
import nu.annat.andchart.options.AxisOptions;
import nu.annat.andchart.utils.Insets;

public class AxisChart<T extends AxisChartOptions, PREP extends DataPrep> extends ChartLayout<T, PREP> {

    protected Paint xAxisPaint;
    protected Paint yAxisPaint;
    protected Paint rectPaint;
    protected Paint xAxisTickPaint;
    protected Paint yAxisTickPaint;
    protected float yAxisTickRotation;
    protected RectF dataRect = new RectF();
    protected double yScale;
    protected double xScale;
    Rect xAxisArea = new Rect();
    Rect yAxisArea = new Rect();
    private Insets insets = new Insets();

    public AxisChart(T options) {
        super(options);
    }

    @Override
    public void setData(ChartData data) {
        super.setData(data);
        initAxis(options.xAxis);
        initAxis(options.yAxis);
    }

    private void initAxis(AxisOptions axisOptions) {
        if(axisOptions.getPainter()!=null)
            axisOptions.getPainter().init(data);
    }

    private void drawYAxis(Canvas canvas, Rect yAxisArea) {
        Axis painter = options.yAxis.getPainter();
        if (painter != null) {
            painter.draw(canvas, yAxisArea);
        }
    }

    private void drawXAxis(Canvas canvas, Rect xAxisArea) {
        Axis painter = options.xAxis.getPainter();
        if (painter != null) {
            painter.draw(canvas, xAxisArea);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.getClipBounds(mainDrawArea);

        //yAxisArea.set(0, 0, yAxisDistance, canvas.getHeight());
        drawYAxis(canvas, yAxisArea);

       // xAxisArea.set(0, canvas.getHeight() - 124, canvas.getWidth(), canvas.getHeight());
        drawXAxis(canvas, xAxisArea);

        mainDrawArea.bottom = mainDrawArea.bottom - xAxisArea.height();
        mainDrawArea.left = mainDrawArea.left + yAxisArea.right;
        dataPrep.prepare(mainDrawArea);
        drawMainArea(data, canvas);
    }

    @Override
    public void onChartMeasure(int left, int top, int right, int bottom) {

        xAxisArea.left = 0;
        xAxisArea.top = 0;
        xAxisArea.right = right-left;
        xAxisArea.bottom = bottom-top;

        boolean append = true;

        insetAxis(insets, options.xAxis.getPainter(), append);
        xAxisArea.top = xAxisArea.bottom - insets.bottom;
        insetAxis(insets, options.yAxis.getPainter(), append);


    }

    private void insetAxis(Insets insets, Axis painter, boolean append) {
        if (painter != null) {
            if (append)
                insets.append(painter.getInsets());
            else
                insets.max(painter.getInsets());
        }
    }

    public void drawMainArea(ChartData data, Canvas canvas) {
        super.draw(canvas);


    }

    public void ensureInit(Configuration configuration) {
        super.ensureInit(configuration);

        xAxisPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        xAxisPaint.setColor(Color.BLACK);

        yAxisPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        yAxisPaint.setStrokeWidth(configuration.scaleToPixels(1));
        yAxisPaint.setColor(Color.RED);

        xAxisTickPaint = new Paint(xAxisPaint);
        xAxisTickPaint.setTextSize(configuration.scaledValue(12));

        yAxisTickPaint = new Paint(xAxisPaint);
        yAxisTickPaint.setTextSize(configuration.scaledValue(12));

        rectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        rectPaint.setColor(Color.BLUE);
        rectPaint.setStrokeWidth(20);
        rectPaint.setStyle(Paint.Style.FILL);

    }

}
