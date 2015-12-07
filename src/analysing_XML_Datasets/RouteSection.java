package analysing_XML_Datasets;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("RouteSection")
public class RouteSection {

	private List<RouteLink> RouteSection;

	public List<RouteLink> getRouteSection() {
		return RouteSection;
	}

	public void setRouteSection(List<RouteLink> routeSection) {
		RouteSection = routeSection;
	}

}
