package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("VehicleJourney")
public class VehicleJourney {

	private String PrivateCode;
	private String DestinationDisplay;
	private Operational Operational;
	private String VehicleJourneyCode;
	private String ServiceRef;
	private String LineRef;
	private String JourneyPatternRef;
	private String DepartureTime;

	public String getPrivateCode() {
		return PrivateCode;
	}

	public void setPrivateCode(String privateCode) {
		PrivateCode = privateCode;
	}

	public String getDestinationDisplay() {
		return DestinationDisplay;
	}

	public void setDestinationDisplay(String destinationDisplay) {
		DestinationDisplay = destinationDisplay;
	}

	public Operational getOperational() {
		return Operational;
	}

	public void setOperational(Operational operational) {
		Operational = operational;
	}

	public String getVehicleJourneyCode() {
		return VehicleJourneyCode;
	}

	public void setVehicleJourneyCode(String vehicleJourneyCode) {
		VehicleJourneyCode = vehicleJourneyCode;
	}

	public String getServiceRef() {
		return ServiceRef;
	}

	public void setServiceRef(String serviceRef) {
		ServiceRef = serviceRef;
	}

	public String getLineRef() {
		return LineRef;
	}

	public void setLineRef(String lineRef) {
		LineRef = lineRef;
	}

	public String getJourneyPatternRef() {
		return JourneyPatternRef;
	}

	public void setJourneyPatternRef(String journeyPatternRef) {
		JourneyPatternRef = journeyPatternRef;
	}

	public String getDepartureTime() {
		return DepartureTime;
	}

	public void setDepartureTime(String departureTime) {
		DepartureTime = departureTime;
	}

}
