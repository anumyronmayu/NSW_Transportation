package analysing_CSV_Datasets.Stops;

public class Stops {

	private String stop_id;
	private String stop_code;
	private String stop_name;
	private double stop_lat;
	private double stop_lon;
	private String location_type;
	private String parent_station;
	private String wheelchair_boarding;
	private String platform_code;

	public String getStop_id() {
		return stop_id;
	}

	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
	}

	public String getStop_code() {
		return stop_code;
	}

	public void setStop_code(String stop_code) {
		this.stop_code = stop_code;
	}

	public String getStop_name() {
		return stop_name;
	}

	public void setStop_name(String stop_name) {
		this.stop_name = stop_name;
	}

	public double getStop_lat() {
		return stop_lat;
	}

	public void setStop_lat(double stop_lat) {
		this.stop_lat = stop_lat;
	}

	public double getStop_lon() {
		return stop_lon;
	}

	public void setStop_lon(double stop_lon) {
		this.stop_lon = stop_lon;
	}

	public String getLocation_type() {
		return location_type;
	}

	public void setLocation_type(String location_type) {
		this.location_type = location_type;
	}

	public String getParent_station() {
		return parent_station;
	}

	public void setParent_station(String parent_station) {
		this.parent_station = parent_station;
	}

	public String getWheelchair_boarding() {
		return wheelchair_boarding;
	}

	public void setWheelchair_boarding(String wheelchair_boarding) {
		this.wheelchair_boarding = wheelchair_boarding;
	}

	public String getPlatform_code() {
		return platform_code;
	}

	public void setPlatform_code(String platform_code) {
		this.platform_code = platform_code;
	}

}
