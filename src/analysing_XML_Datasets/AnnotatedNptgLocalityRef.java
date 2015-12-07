package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("AnnotatedNptgLocalityRef")
public class AnnotatedNptgLocalityRef {

	@XStreamAlias("NptgLocalityRef")
	private String NptgLocalityRef;

	@XStreamAlias("LocalityName")
	private String LocalityName;

	public String getNptgLocalityRef() {
		return NptgLocalityRef;
	}

	public void setNptgLocalityRef(String nptgLocalityRef) {
		NptgLocalityRef = nptgLocalityRef;
	}

	public String getLocalityName() {
		return LocalityName;
	}

	public void setLocalityName(String localityName) {
		LocalityName = localityName;
	}

}
