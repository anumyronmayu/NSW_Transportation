package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Route")
public class Route {

	@XStreamAsAttribute
	private String id;

	private String PrivateCode;
	private String Description;
	private String RouteSectionRef;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrivateCode() {
		return PrivateCode;
	}

	public void setPrivateCode(String privateCode) {
		PrivateCode = privateCode;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getRouteSectionRef() {
		return RouteSectionRef;
	}

	public void setRouteSectionRef(String routeSectionRef) {
		RouteSectionRef = routeSectionRef;
	}

	
}
