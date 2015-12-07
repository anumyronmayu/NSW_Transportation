package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("StopRequirements")
public class StopRequirements {

	private String NoNewStopsRequired;

	public String getNoNewStopsRequired() {
		return NoNewStopsRequired;
	}

	public void setNoNewStopsRequired(String noNewStopsRequired) {
		NoNewStopsRequired = noNewStopsRequired;
	}

}
