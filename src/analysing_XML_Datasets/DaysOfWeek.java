package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("DaysOfWeek")
public class DaysOfWeek {

	private String MondayToSunday;

	public String getMondayToSunday() {
		return MondayToSunday;
	}

	public void setMondayToSunday(String mondayToSunday) {
		MondayToSunday = mondayToSunday;
	}

}
