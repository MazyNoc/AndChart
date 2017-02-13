package nu.annat.andchart.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DataSet {

	private List<DataPoint> dataPoints;
	private DataPrep.PreparedDataSet prepared;

	public DataSet(List<DataPoint> dataPoints) {
		this.dataPoints = dataPoints;
	}

	public DataSet(DataPoint... dataPoints) {
		this.dataPoints = new ArrayList<>(Arrays.asList(dataPoints));
	}

	public List<DataPoint> getDataPoints() {
		return dataPoints == null ? Collections.EMPTY_LIST : dataPoints;
	}

	public void setPrepared(DataPrep.PreparedDataSet prepared) {
		this.prepared = prepared;
	}

	public DataPrep.PreparedDataSet getPrepared() {
		return prepared;
	}
}
