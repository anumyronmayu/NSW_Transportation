package analysing_XML_Datasets;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("TransXChange")
public class TransXChange {

	@XStreamAsAttribute
	private String CreationDateTime;

	@XStreamAsAttribute
	private String ModificationDateTime;

	@XStreamAsAttribute
	private String Modification;

	@XStreamAsAttribute
	private String FileName;

	@XStreamAsAttribute
	private int RevisionNumber;

	@XStreamAsAttribute
	private String SchemaVersion;

	@XStreamAsAttribute
	@XStreamAlias("xml:lang")
	private String xml_lang;

	@XStreamAsAttribute
	@XStreamAlias("xsi:schemaLocation")
	private String xsi_schemaLocation;

	@XStreamAsAttribute
	@XStreamAlias("xmlns:xsi")
	private String xmlns_xsi;

	@XStreamAsAttribute
	private String xmlns;

	private NptgLocalities NptgLocalities;

	private List<StopPoint> StopPoints;

	private RouteSections RouteSections;

	private List<Route> Routes;

	private JourneyPatternSections JourneyPatternSections;

	private List<Operator> Operators;

	private List<Service> Services;

	private List<VehicleJourney> VehicleJourneys;

	public String getCreationDateTime() {
		return CreationDateTime;
	}

	public void setCreationDateTime(String creationDateTime) {
		CreationDateTime = creationDateTime;
	}

	public String getModificationDateTime() {
		return ModificationDateTime;
	}

	public void setModificationDateTime(String modificationDateTime) {
		ModificationDateTime = modificationDateTime;
	}

	public String getModification() {
		return Modification;
	}

	public void setModification(String modification) {
		Modification = modification;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public int getRevisionNumber() {
		return RevisionNumber;
	}

	public void setRevisionNumber(int revisionNumber) {
		RevisionNumber = revisionNumber;
	}

	public String getSchemaVersion() {
		return SchemaVersion;
	}

	public void setSchemaVersion(String schemaVersion) {
		SchemaVersion = schemaVersion;
	}

	public String getXml_lang() {
		return xml_lang;
	}

	public void setXml_lang(String xml_lang) {
		this.xml_lang = xml_lang;
	}

	public String getXsi_schemaLocation() {
		return xsi_schemaLocation;
	}

	public void setXsi_schemaLocation(String xsi_schemaLocation) {
		this.xsi_schemaLocation = xsi_schemaLocation;
	}

	public String getXmlns_xsi() {
		return xmlns_xsi;
	}

	public void setXmlns_xsi(String xmlns_xsi) {
		this.xmlns_xsi = xmlns_xsi;
	}

	public String getXmlns() {
		return xmlns;
	}

	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}

	public NptgLocalities getNptgLocalities() {
		return NptgLocalities;
	}

	public void setNptgLocalities(NptgLocalities nptgLocalities) {
		this.NptgLocalities = nptgLocalities;
	}

	public List<StopPoint> getStopPoints() {
		return StopPoints;
	}

	public void setStopPoints(List<StopPoint> stopPoints) {
		StopPoints = stopPoints;
	}

	public RouteSections getRouteSections() {
		return RouteSections;
	}

	public void setRouteSections(RouteSections routeSections) {
		RouteSections = routeSections;
	}

	public List<Route> getRoutes() {
		return Routes;
	}

	public void setRoutes(List<Route> routes) {
		Routes = routes;
	}

	public JourneyPatternSections getJourneyPatternSections() {
		return JourneyPatternSections;
	}

	public void setJourneyPatternSections(
			JourneyPatternSections journeyPatternSections) {
		JourneyPatternSections = journeyPatternSections;
	}

	public List<Operator> getOperators() {
		return Operators;
	}

	public void setOperators(List<Operator> operators) {
		Operators = operators;
	}

	public List<Service> getServices() {
		return Services;
	}

	public void setServices(List<Service> services) {
		Services = services;
	}

	public List<VehicleJourney> getVehicleJourneys() {
		return VehicleJourneys;
	}

	public void setVehicleJourneys(List<VehicleJourney> vehicleJourneys) {
		VehicleJourneys = vehicleJourneys;
	}

}
