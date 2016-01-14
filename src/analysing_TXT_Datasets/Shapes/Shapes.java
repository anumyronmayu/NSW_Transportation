package analysing_TXT_Datasets.Shapes;

public class Shapes {

	private String shape_id;
	private double shape_pt_lat;
	private double shape_pt_lon;
	private int shape_pt_sequence;
	private double shape_dist_traveled;

	public String getShape_id() {
		return shape_id;
	}

	public void setShape_id(String shape_id) {
		this.shape_id = shape_id;
	}

	public double getShape_pt_lat() {
		return shape_pt_lat;
	}

	public void setShape_pt_lat(double shape_pt_lat) {
		this.shape_pt_lat = shape_pt_lat;
	}

	public double getShape_pt_lon() {
		return shape_pt_lon;
	}

	public void setShape_pt_lon(double shape_pt_lon) {
		this.shape_pt_lon = shape_pt_lon;
	}

	public int getShape_pt_sequence() {
		return shape_pt_sequence;
	}

	public void setShape_pt_sequence(int shape_pt_sequence) {
		this.shape_pt_sequence = shape_pt_sequence;
	}

	public double getShape_dist_traveled() {
		return shape_dist_traveled;
	}

	public void setShape_dist_traveled(double shape_dist_traveled) {
		this.shape_dist_traveled = shape_dist_traveled;
	}

}
