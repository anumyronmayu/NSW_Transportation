package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("RouteLink")
public class RouteLink {

	@XStreamAsAttribute
	private String id;

	private From From;
	private To To;
	private String Direction;

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

	public String getDirection() {
		return Direction;
	}

	public void setDirection(String direction) {
		Direction = direction;
	}

}
