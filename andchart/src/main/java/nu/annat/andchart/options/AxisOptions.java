package nu.annat.andchart.options;

import android.graphics.Typeface;

public class AxisOptions {

    public float textSize = 122;
    public Typeface typeface;

    public float getTextSize() {
        return textSize;
    }

    public AxisOptions setTextSize(float textSize) {
        this.textSize = textSize;
        return this;
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public AxisOptions setTypeface(Typeface typeface) {
        this.typeface = typeface;
        return this;
    }
}
