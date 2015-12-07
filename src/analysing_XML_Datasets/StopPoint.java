package analysing_XML_Datasets;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("StopPoint")
public class StopPoint {

	@XStreamAsAttribute
	private String CreationDateTime;

	private AtcoCode atcoCode;
	private PrivateCode privateCode;
	private Descriptor descriptor;
	private AlternativeDescriptors alternativeDescriptors;
	private Place place;
	private StopClassification stopClassification;
	private AdministrativeAreaRef administrativeAreaRef;
	private Notes notes;

	public String getCreationDateTime() {
		return CreationDateTime;
	}

	public void setCreationDateTime(String creationDateTime) {
		CreationDateTime = creationDateTime;
	}

	public AtcoCode getAtcoCode() {
		return atcoCode;
	}

	public void setAtcoCode(AtcoCode atcoCode) {
		this.atcoCode = atcoCode;
	}

	public PrivateCode getPrivateCode() {
		return privateCode;
	}

	public void setPrivateCode(PrivateCode privateCode) {
		this.privateCode = privateCode;
	}

	public Descriptor getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(Descriptor descriptor) {
		this.descriptor = descriptor;
	}

	public AlternativeDescriptors getAlternativeDescriptors() {
		return alternativeDescriptors;
	}

	public void setAlternativeDescriptors(
			AlternativeDescriptors alternativeDescriptors) {
		this.alternativeDescriptors = alternativeDescriptors;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public StopClassification getStopClassification() {
		return stopClassification;
	}

	public void setStopClassification(StopClassification stopClassification) {
		this.stopClassification = stopClassification;
	}

	public AdministrativeAreaRef getAdministrativeAreaRef() {
		return administrativeAreaRef;
	}

	public void setAdministrativeAreaRef(
			AdministrativeAreaRef administrativeAreaRef) {
		this.administrativeAreaRef = administrativeAreaRef;
	}

	public Notes getNotes() {
		return notes;
	}

	public void setNotes(Notes notes) {
		this.notes = notes;
	}

}
