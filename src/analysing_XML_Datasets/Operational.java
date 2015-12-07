package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Operational")
public class Operational {

	private VehicleType VehicleType;

	public VehicleType getVehicleType() {
		return VehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		VehicleType = vehicleType;
	}

}
