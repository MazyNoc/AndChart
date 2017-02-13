package nu.annat.andchart.utils;

public class MinMax {
	public double min = Double.MAX_VALUE;
	public double max = Double.MIN_VALUE;

	public void append(double value) {
		min = Math.min(min, value);
		max = Math.max(max, value);
	}

	public void append(MinMax value) {
		min = Math.min(min, value.min);
		max = Math.max(max, value.max);
	}
}
