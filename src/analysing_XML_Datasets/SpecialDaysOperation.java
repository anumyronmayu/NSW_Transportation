package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("SpecialDaysOperation")
public class SpecialDaysOperation {

	private DaysOfOperation DaysOfOperation;

	public DaysOfOperation getDaysOfOperation() {
		return DaysOfOperation;
	}

	public void setDaysOfOperation(DaysOfOperation daysOfOperation) {
		DaysOfOperation = daysOfOperation;
	}

}
