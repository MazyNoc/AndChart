package nu.annat.andchart;

public interface Configuration {
	float scaleToPixels(int dp);
	int scaleToRoundedPixels(int dp);
	float scaledValue(int sp);
}
