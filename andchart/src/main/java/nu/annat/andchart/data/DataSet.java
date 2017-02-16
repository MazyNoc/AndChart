package nu.annat.andchart.data;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataSet {

	private final Drawable drawable;
	private final Integer color;
	private List<DataPoint> dataPoints;
	private Object identifier;
	private DataPrep.PreparedDataSet prepared;

	public DataSet(Drawable drawable, List<DataPoint> dataPoints) {
		this.drawable = drawable;
		if(drawable instanceof ColorDrawable){
			color = ((ColorDrawable) drawable).getColor();
		} else {
			color = null;
		}
		this.dataPoints = dataPoints;
	}
	public DataSet(int color, List<DataPoint> dataPoints) {
		this.drawable = new ColorDrawable(color);
		this.color = color;
		this.dataPoints = dataPoints;
	}

	public DataSet(Drawable drawable, DataPoint... dataPoints) {
		this(drawable, new ArrayList<>(Arrays.asList(dataPoints)));
	}

	public List<DataPoint> getDataPoints() {
		return dataPoints == null ? Collections.<DataPoint>emptyList() : dataPoints;
	}

	public void setPrepared(DataPrep.PreparedDataSet prepared) {
		this.prepared = prepared;
	}

	public DataPrep.PreparedDataSet getPrepared() {
		return prepared;
	}

	public Drawable getDrawable() {
		return drawable;
	}

	public int getColor() {
		if(color == null) return Color.RED;
		return color;
	}
}
