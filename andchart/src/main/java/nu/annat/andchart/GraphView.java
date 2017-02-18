package nu.annat.andchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.drawer.ChartLayout;
import nu.annat.andchart.options.ChartOptions;

public class GraphView extends View {
	protected ChartData data;
	private ChartData newData;

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
		ensureInit();
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
		ensureInit();
		invalidate();
	}

	public ChartLayout getChartLayout() {
		return chartLayout;
	}

	protected void getMainDrawArea(Canvas canvas, Rect mainDrawArea) {
		canvas.getClipBounds(mainDrawArea);
	}

	protected void ensureInit() {
		if(chartLayout !=null && data!=null){
			chartLayout.setData(data);
			chartLayout.ensureInit(new ContextConfiguration(getContext()));
		}
	}

	private void startAnimate() {
//		PreparedData prep = chartLayout.prepData(newData);
//		chartLayout.draw(slerp(oldPrepData, prepData, 0.5));
//		ObjectAnimator.ofFloat(this, "dataAnimPosition", 0,1);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas); // draws the background

		if(chartLayout !=null && data!=null){
			chartLayout.draw(canvas);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		super.onLayout(changed, left, top, right, bottom);
		if (chartLayout!=null){
			chartLayout.onChartMeasure(left, top, right, bottom);
		}
	}
}
