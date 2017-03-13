package nu.annat.andchart.drawer;

import nu.annat.andchart.data.DataPrep;
import nu.annat.andchart.options.BarChartOptions;
import nu.annat.andchart.data.BarDataPrep;

public class BarChart extends AbstractBarChart<BarDataPrep> {

    public BarChart(BarChartOptions options) {
        super(options);
        dataPrep = new BarDataPrep(options);
    }
}
