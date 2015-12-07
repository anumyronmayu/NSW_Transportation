package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Descriptor")
public class Descriptor {

	@XStreamAsAttribute
	private String CreationDateTime;

	private String CommonName;

	public String getCommonName() {
		return CommonName;
	}

	public void setCommonName(String commonName) {
		CommonName = commonName;
	}

	public String getCreationDateTime() {
		return CreationDateTime;
	}

	public void setCreationDateTime(String creationDateTime) {
		CreationDateTime = creationDateTime;
	}

}
