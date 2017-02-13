package nu.annat.andchart.drawer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import nu.annat.andchart.Configuration;
import nu.annat.andchart.data.ChartData;

public class AxisChart extends ChartLayout {

	protected Paint xAxisPaint;
	protected Paint yAxisPaint;
	protected Paint rectPaint;
	protected Paint xAxisTickPaint;
	protected Paint yAxisTickPaint;
	protected float yAxisTickRotation;


	protected RectF dataRect = new RectF();
	Rect xAxisArea = new Rect();
	Rect yAxisArea = new Rect();
	private int xAxisDistance;
	private int yAxisDistance;
	protected double yScale;
	protected double xScale;


	private void drawYAxis(Canvas canvas, Rect yAxisArea) {
		canvas.drawLine(yAxisArea.right, yAxisArea.top, yAxisArea.right, yAxisArea.bottom, yAxisPaint);
		float step = mainDrawArea.height() / 10f;
		for (int i = 0; i < 10; i++) {
			canvas.drawText("" + i, 0, mainDrawArea.bottom - yAxisDistance - (i * step), yAxisTickPaint);
		}
	}

	private void drawXAxis(Canvas canvas, Rect xAxisArea) {
		float y = xAxisArea.top;
		canvas.drawLine(xAxisArea.left, y, xAxisArea.right, y, xAxisPaint);
		float step = mainDrawArea.width() / 10f;
		for (int i = 0; i < 10; i++) {
		//	canvas.drawText("" + i, 0, mainDrawArea.bottom - xAxisDistance - (i * step), xAxisTickPaint);
		}
	}

	@Override
	public void draw(ChartData data, Canvas canvas) {
		super.draw(data, canvas);
		canvas.getClipBounds(mainDrawArea);

		yAxisArea.set(0, 0, yAxisDistance, canvas.getHeight());
		drawYAxis(canvas, yAxisArea);

		xAxisArea.set(0, canvas.getHeight() - xAxisDistance, canvas.getWidth(), canvas.getHeight());
		drawXAxis(canvas, xAxisArea);

		mainDrawArea.bottom = mainDrawArea.bottom - xAxisArea.height();
		mainDrawArea.left = mainDrawArea.left + yAxisArea.right;
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
		rectPaint.setStyle(Paint.Style.STROKE);

		xAxisDistance = configuration.scaleToRoundedPixels(20);
		yAxisDistance = configuration.scaleToRoundedPixels(20);
	}

	public static RectF scaleRect(RectF dataRect, double xScale, double yScale) {
		dataRect.bottom *= yScale;
		dataRect.top *= yScale;
		dataRect.left *= xScale;
		dataRect.right *= xScale;
		return dataRect;
	}
}
