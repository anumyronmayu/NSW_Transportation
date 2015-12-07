package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("StopClassification")
public class StopClassification {

	private String StopType;

	private OffStreet OffStreet;

	public String getStopType() {
		return StopType;
	}

	public void setStopType(String stopType) {
		StopType = stopType;
	}

	public OffStreet getOffStreet() {
		return OffStreet;
	}

	public void setOffStreet(OffStreet offStreet) {
		OffStreet = offStreet;
	}

}
