package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("DaysOfOperation")
public class DaysOfOperation {

	private DateRange DateRange;

	public DateRange getDateRange() {
		return DateRange;
	}

	public void setDateRange(DateRange dateRange) {
		DateRange = dateRange;
	}

}
