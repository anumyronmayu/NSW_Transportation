package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("JourneyPattern")
public class JourneyPattern {

	@XStreamAsAttribute
	private String id;

	private String Direction;
	private Operational Operational;
	private String Description;
	private String RouteRef;
	private String JourneyPatternSectionRefs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDirection() {
		return Direction;
	}

	public void setDirection(String direction) {
		Direction = direction;
	}

	public Operational getOperational() {
		return Operational;
	}

	public void setOperational(Operational operational) {
		Operational = operational;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getRouteRef() {
		return RouteRef;
	}

	public void setRouteRef(String routeRef) {
		RouteRef = routeRef;
	}

	public String getJourneyPatternSectionRefs() {
		return JourneyPatternSectionRefs;
	}

	public void setJourneyPatternSectionRefs(String journeyPatternSectionRefs) {
		JourneyPatternSectionRefs = journeyPatternSectionRefs;
	}

}
