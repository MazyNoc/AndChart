package nu.annat.andchart.drawer;

import android.graphics.Canvas;
import android.graphics.Rect;

import nu.annat.andchart.options.ChartOptions;
import nu.annat.andchart.Configuration;
import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.data.DataPrep;

public class ChartLayout<T extends ChartOptions, PREP extends DataPrep> {

    protected ChartData data;
    protected PREP dataPrep;
    protected T options;

    protected Rect mainDrawArea = new Rect();

    public ChartLayout(T options) {
        this.options = options;
    }

    public void setData(ChartData data) {
        this.data = data;
    }

    public void setOptions(T options) {
        this.options = options;
    }

    public void setPrep(PREP prep) {
        this.dataPrep = prep;
    }

    public void draw(ChartData data, Canvas canvas) {
    }

    public void ensureInit(Configuration configuration) {

    }
}
