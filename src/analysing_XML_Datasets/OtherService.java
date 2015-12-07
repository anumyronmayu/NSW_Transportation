package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("OtherService")
public class OtherService {

	private String Description;

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
