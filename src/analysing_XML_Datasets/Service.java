package analysing_XML_Datasets;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Service")
public class Service {

	private String ServiceCode;
	private String PrivateCode;
	
	private List<Line> Lines;
}
