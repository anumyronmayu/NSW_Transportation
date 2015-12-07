package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("StandardService")
public class StandardService {

	private String Origin;
	private String Destination;

	private JourneyPattern JourneyPattern;

	public String getOrigin() {
		return Origin;
	}

	public void setOrigin(String origin) {
		Origin = origin;
	}

	public String getDestination() {
		return Destination;
	}

	public void setDestination(String destination) {
		Destination = destination;
	}

	public JourneyPattern getJourneyPattern() {
		return JourneyPattern;
	}

	public void setJourneyPattern(JourneyPattern journeyPattern) {
		JourneyPattern = journeyPattern;
	}

}
