package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("NptgLocalities")
public class NptgLocalities {

	private AnnotatedNptgLocalityRef AnnotatedNptgLocalityRef;

	public AnnotatedNptgLocalityRef getAnnotatedNptgLocalityRef() {
		return AnnotatedNptgLocalityRef;
	}

	public void setAnnotatedNptgLocalityRef(
			AnnotatedNptgLocalityRef annotatedNptgLocalityRef) {
		AnnotatedNptgLocalityRef = annotatedNptgLocalityRef;
	}

}
