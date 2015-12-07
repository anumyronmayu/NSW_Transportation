package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("JourneyPatternTimingLink")
public class JourneyPatternTimingLink {

	@XStreamAsAttribute
	private String id;

	private From From;
	private To To;
	private String RouteLinkRef;
	private String RunTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public From getFrom() {
		return From;
	}

	public void setFrom(From from) {
		From = from;
	}

	public To getTo() {
		return To;
	}

	public void setTo(To to) {
		To = to;
	}

	public String getRouteLinkRef() {
		return RouteLinkRef;
	}

	public void setRouteLinkRef(String routeLinkRef) {
		RouteLinkRef = routeLinkRef;
	}

	public String getRunTime() {
		return RunTime;
	}

	public void setRunTime(String runTime) {
		RunTime = runTime;
	}

}
