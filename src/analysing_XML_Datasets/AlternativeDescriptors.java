package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("AlternativeDescriptors")
public class AlternativeDescriptors {

	private Descriptor Descriptor;

	public Descriptor getDescriptor() {
		return Descriptor;
	}

	public void setDescriptor(Descriptor descriptor) {
		Descriptor = descriptor;
	}

}
