package nu.annat.andchart.drawer;

import android.graphics.Canvas;
import android.graphics.Rect;

import nu.annat.andchart.Configuration;
import nu.annat.andchart.data.ChartData;

public class ChartLayout {

	protected ChartData data;

	protected Rect mainDrawArea = new Rect();

	public void setData(ChartData data) {
		this.data = data;
	}

	public void draw(ChartData data, Canvas canvas) {
		this.data = data;
	}

	public void ensureInit(Configuration configuration){

	}
}
