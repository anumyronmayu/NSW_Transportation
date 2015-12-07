package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("ServiceClassification")
public class ServiceClassification {

	private OtherService OtherService;

	public OtherService getOtherService() {
		return OtherService;
	}

	public void setOtherService(OtherService otherService) {
		OtherService = otherService;
	}

}
