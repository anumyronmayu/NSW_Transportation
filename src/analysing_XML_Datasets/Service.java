package analysing_XML_Datasets;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Service")
public class Service {

	private String ServiceCode;
	private String PrivateCode;
	private List<Line> Lines;
	private OperatingPeriod OperatingPeriod;
	private OperatingProfile OperatingProfile;
	private ServiceClassification ServiceClassification;
	private String RegisteredOperatorRef;
	private StopRequirements StopRequirements;
	private String Mode;
	private String Description;
	private Note Note;
	private StandardService StandardService;

	public String getServiceCode() {
		return ServiceCode;
	}

	public void setServiceCode(String serviceCode) {
		ServiceCode = serviceCode;
	}

	public String getPrivateCode() {
		return PrivateCode;
	}

	public void setPrivateCode(String privateCode) {
		PrivateCode = privateCode;
	}

	public List<Line> getLines() {
		return Lines;
	}

	public void setLines(List<Line> lines) {
		Lines = lines;
	}

	public OperatingPeriod getOperatingPeriod() {
		return OperatingPeriod;
	}

	public void setOperatingPeriod(OperatingPeriod operatingPeriod) {
		OperatingPeriod = operatingPeriod;
	}

	public OperatingProfile getOperatingProfile() {
		return OperatingProfile;
	}

	public void setOperatingProfile(OperatingProfile operatingProfile) {
		OperatingProfile = operatingProfile;
	}

	public ServiceClassification getServiceClassification() {
		return ServiceClassification;
	}

	public void setServiceClassification(
			ServiceClassification serviceClassification) {
		ServiceClassification = serviceClassification;
	}

	public String getRegisteredOperatorRef() {
		return RegisteredOperatorRef;
	}

	public void setRegisteredOperatorRef(String registeredOperatorRef) {
		RegisteredOperatorRef = registeredOperatorRef;
	}

	public StopRequirements getStopRequirements() {
		return StopRequirements;
	}

	public void setStopRequirements(StopRequirements stopRequirements) {
		StopRequirements = stopRequirements;
	}

	public String getMode() {
		return Mode;
	}

	public void setMode(String mode) {
		Mode = mode;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Note getNote() {
		return Note;
	}

	public void setNote(Note note) {
		Note = note;
	}

	public StandardService getStandardService() {
		return StandardService;
	}

	public void setStandardService(StandardService standardService) {
		StandardService = standardService;
	}

}
