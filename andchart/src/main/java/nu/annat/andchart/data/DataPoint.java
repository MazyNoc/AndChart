package nu.annat.andchart.data;

import java.util.List;

public class DataPoint {
	String label;
	Double value;
	Integer color;
	List<DataSet> childData;
	private DataPrep.PreparedDataPoint prepared;

	public DataPoint(String label, Double value) {
		this(label, value, null, null);
	}

	public DataPoint(String label, Double value, Integer color, List<DataSet> childData) {
		this.label = label;
		this.value = value;
		this.color = color;
		this.childData = childData;
	}

	public double getValue() {
		return value;
	}

	public Integer getColor(){
		return color;
	}

	public void setPrepared(DataPrep.PreparedDataPoint prepared) {
		this.prepared = prepared;
	}

	public DataPrep.PreparedDataPoint getPrepared() {
		return prepared;
	}
}
