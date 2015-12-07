package analysing_XML_Datasets;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("JourneyPatternSections")
public class JourneyPatternSections {

	private List<JourneyPatternTimingLink> JourneyPatternSection;

	public List<JourneyPatternTimingLink> getJourneyPatternSection() {
		return JourneyPatternSection;
	}

	public void setJourneyPatternSection(
			List<JourneyPatternTimingLink> journeyPatternSection) {
		JourneyPatternSection = journeyPatternSection;
	}

}
