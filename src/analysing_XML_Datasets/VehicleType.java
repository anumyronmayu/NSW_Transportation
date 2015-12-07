package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("VehicleType")
public class VehicleType {

	private String VehicleTypeCode;
	private String Description;

	public String getVehicleTypeCode() {
		return VehicleTypeCode;
	}

	public void setVehicleTypeCode(String vehicleTypeCode) {
		VehicleTypeCode = vehicleTypeCode;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
