package nu.annat.andchart.data;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChartData {
	private List<Tick> ticks;
	private List<DataSet> dataSets;
	private DataPrep.PreparedChartData prepared;

	public ChartData() {
	}

	public ChartData(List<Tick> ticks, List<DataSet> dataSets) {
		this.ticks = ticks;
		this.dataSets = dataSets;
	}

	public void setTicks(List<Tick> ticks) {
		this.ticks = ticks;
	}

	public List<DataSet> getDataSets() {
		return dataSets == null ? Collections.EMPTY_LIST : dataSets;
	}

	public void setDataSets(DataSet... dataSets) {
		setDataSets(Arrays.asList(dataSets));
	}

	public void setDataSets(List<DataSet> dataSets) {
		this.dataSets = dataSets;
	}

	public void setPrepared(DataPrep.PreparedChartData prepared) {
		this.prepared = prepared;
	}

	public DataPrep.PreparedChartData getPrepared() {
		return prepared;
	}
}
