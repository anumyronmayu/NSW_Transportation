package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("StopPoint")
public class StopPoint {

	@XStreamAsAttribute
	private String CreationDateTime;

	private String AtcoCode;
	private String PrivateCode;
	private Descriptor Descriptor;
	private AlternativeDescriptors AlternativeDescriptors;
	private Place Place;
	private StopClassification StopClassification;
	private String AdministrativeAreaRef;
	private String Notes;

	public String getCreationDateTime() {
		return CreationDateTime;
	}

	public void setCreationDateTime(String creationDateTime) {
		CreationDateTime = creationDateTime;
	}

	public String getAtcoCode() {
		return AtcoCode;
	}

	public void setAtcoCode(String atcoCode) {
		AtcoCode = atcoCode;
	}

	public String getPrivateCode() {
		return PrivateCode;
	}

	public void setPrivateCode(String privateCode) {
		PrivateCode = privateCode;
	}

	public Descriptor getDescriptor() {
		return Descriptor;
	}

	public void setDescriptor(Descriptor descriptor) {
		Descriptor = descriptor;
	}

	public AlternativeDescriptors getAlternativeDescriptors() {
		return AlternativeDescriptors;
	}

	public void setAlternativeDescriptors(
			AlternativeDescriptors alternativeDescriptors) {
		AlternativeDescriptors = alternativeDescriptors;
	}

	public Place getPlace() {
		return Place;
	}

	public void setPlace(Place place) {
		Place = place;
	}

	public StopClassification getStopClassification() {
		return StopClassification;
	}

	public void setStopClassification(StopClassification stopClassification) {
		StopClassification = stopClassification;
	}

	public String getAdministrativeAreaRef() {
		return AdministrativeAreaRef;
	}

	public void setAdministrativeAreaRef(String administrativeAreaRef) {
		AdministrativeAreaRef = administrativeAreaRef;
	}

	public String getNotes() {
		return Notes;
	}

	public void setNotes(String notes) {
		Notes = notes;
	}

}
