package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("RegularDayType")
public class RegularDayType {

	private DaysOfWeek DaysOfWeek;

	public DaysOfWeek getDaysOfWeek() {
		return DaysOfWeek;
	}

	public void setDaysOfWeek(DaysOfWeek daysOfWeek) {
		DaysOfWeek = daysOfWeek;
	}

}
