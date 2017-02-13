package nu.annat.andchart;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class ContextConfiguration implements Configuration {
	private final Context context;
	private final DisplayMetrics displayMetrics;

	public ContextConfiguration(Context context) {
		this.context = context;
		this.displayMetrics = context.getResources().getDisplayMetrics();
	}

	@Override
	public float scaleToPixels(int dp) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
	}

	@Override
	public int scaleToRoundedPixels(int dp) {
		return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics));
	}

	@Override
	public float scaledValue(int sp) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, displayMetrics);
	}
}
