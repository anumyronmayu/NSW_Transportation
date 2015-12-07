package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("OperatingProfile")
public class OperatingProfile {

	private RegularDayType RegularDayType;
	private SpecialDaysOperation SpecialDaysOperation;

	public RegularDayType getRegularDayType() {
		return RegularDayType;
	}

	public void setRegularDayType(RegularDayType regularDayType) {
		RegularDayType = regularDayType;
	}

	public SpecialDaysOperation getSpecialDaysOperation() {
		return SpecialDaysOperation;
	}

	public void setSpecialDaysOperation(
			SpecialDaysOperation specialDaysOperation) {
		SpecialDaysOperation = specialDaysOperation;
	}

}
