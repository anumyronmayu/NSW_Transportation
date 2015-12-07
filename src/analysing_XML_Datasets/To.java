package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("To")
public class To {

	@XStreamAsAttribute
	private String SequenceNumber;

	private String StopPointRef;
	private String Activity;
	private String TimingStatus;

	public String getStopPointRef() {
		return StopPointRef;
	}

	public void setStopPointRef(String stopPointRef) {
		StopPointRef = stopPointRef;
	}

	public String getSequenceNumber() {
		return SequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		SequenceNumber = sequenceNumber;
	}

	public String getActivity() {
		return Activity;
	}

	public void setActivity(String activity) {
		Activity = activity;
	}

	public String getTimingStatus() {
		return TimingStatus;
	}

	public void setTimingStatus(String timingStatus) {
		TimingStatus = timingStatus;
	}

}
