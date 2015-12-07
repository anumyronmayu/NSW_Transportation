package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("OperatingPeriod")
public class OperatingPeriod {

	private String StartDate;
	private String EndDate;

	public String getStartDate() {
		return StartDate;
	}

	public void setStartDate(String startDate) {
		StartDate = startDate;
	}

	public String getEndDate() {
		return EndDate;
	}

	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

}
