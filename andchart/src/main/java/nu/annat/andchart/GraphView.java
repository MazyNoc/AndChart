package nu.annat.andchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.drawer.ChartLayout;

public class GraphView extends View {
	protected ChartData data;

	private ChartLayout chartLayout;

	public GraphView(Context context) {
		super(context);
	}

	public GraphView(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
	public GraphView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	public void setOptions(ChartOptions options){

	}

	public void setData(ChartData data) {
		this.data = data;
		invalidate();
//
//		if (this.data != null) {
//			newData = data;
//			startAnimate();
//		} else {
//			this.data = data;
//			invalidate();
//		}
	}

	public void setChartLayout(ChartLayout chartLayout) {
		this.chartLayout = chartLayout;
		invalidate();
	}

	public ChartLayout getChartLayout() {
		return chartLayout;
	}

	protected void getMainDrawArea(Canvas canvas, Rect mainDrawArea) {
		canvas.getClipBounds(mainDrawArea);
	}

	protected void ensureInit() {
		if(chartLayout !=null){
			chartLayout.setData(data);
			chartLayout.ensureInit(new ContextConfiguration(getContext()));
		}
	}

	private void startAnimate() {

	}

	@Override
	protected void onDraw(Canvas canvas) {

		if (isInEditMode()) {
			setBackgroundColor(Color.RED);
		}
		super.onDraw(canvas); // draws the background

		if(chartLayout !=null && data!=null){
			ensureInit();
			chartLayout.draw(data, canvas);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

}
