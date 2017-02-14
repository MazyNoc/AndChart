package nu.annat.andchart.drawer;

import nu.annat.andchart.options.BarChartOptions;
import nu.annat.andchart.data.ChartData;
import nu.annat.andchart.data.StackedDataPrep;

public class StackedBarChart extends AbstractBarChart<StackedDataPrep> {

    public StackedBarChart(BarChartOptions options) {
        super(options);
        dataPrep = new StackedDataPrep(options);
    }

    @Override
    public void setData(ChartData data) {
        super.setData(data);
        dataPrep.setData(data);
    }
}
