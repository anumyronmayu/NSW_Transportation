package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("OffStreet")
public class OffStreet {

	private Rail Rail;

	public Rail getRail() {
		return Rail;
	}

	public void setRail(Rail rail) {
		Rail = rail;
	}

}
