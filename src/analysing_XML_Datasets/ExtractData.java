package analysing_XML_Datasets;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ExtractData {

	/*
	 * Assumption: 
	 * 1. Each RouteSections has only one RouteSection 
	 * 2. RouteSection attribute id cannot be accessed
	 * 3. Each JourneyPatternSections has only one JourneyPatternSection
	 * 4. JourneyPatternSection attribute id cannot be accessed
	 */

	public static void main(String[] args) throws IOException {

		String file = "/Users/Myron/Downloads/test.xml";

		FileInputStream ft = new FileInputStream(file);
		String reqXml = IOUtils.toString(ft);

		XStream xs = new XStream(new DomDriver());

		xs.processAnnotations(new Class[] { TransXChange.class,
				NptgLocalities.class, AnnotatedNptgLocalityRef.class,
				StopPoint.class, Descriptor.class,
				AlternativeDescriptors.class, Place.class, Location.class,
				StopClassification.class, OffStreet.class, Rail.class,
				RouteSections.class, RouteLink.class, From.class, To.class,
				Route.class, JourneyPatternSections.class, JourneyPatternTimingLink.class,
				Operator.class,
				Service.class, VehicleJourney.class, AtcoCode.class,
				PrivateCode.class, Descriptor.class,
				AlternativeDescriptors.class, Place.class,
				StopClassification.class, AdministrativeAreaRef.class,
				Notes.class });

		Object obj = xs.fromXML(reqXml);

		TransXChange txc = (TransXChange) obj;

		System.out.println(txc.getCreationDateTime());
		System.out.println(txc.getModificationDateTime());
		System.out.println(txc.getModification());
		System.out.println(txc.getFileName());
		System.out.println(txc.getRevisionNumber());
		System.out.println(txc.getSchemaVersion());
		System.out.println(txc.getXml_lang());
		System.out.println(txc.getXsi_schemaLocation());
		System.out.println(txc.getXmlns_xsi());
		System.out.println(txc.getXmlns());
		System.out.println(txc.getNptgLocalities()
				.getAnnotatedNptgLocalityRef().getNptgLocalityRef());
		System.out.println(txc.getNptgLocalities()
				.getAnnotatedNptgLocalityRef().getLocalityName());

		System.out.println(txc.getStopPoints().get(0).getCreationDateTime());
		System.out.println(txc.getStopPoints().get(0).getAtcoCode());
		System.out.println(txc.getStopPoints().get(0).getPrivateCode());
		System.out.println(txc.getStopPoints().get(0)
				.getAdministrativeAreaRef());
		System.out.println(txc.getStopPoints().get(0).getNotes());
		System.out.println(txc.getStopPoints().get(0).getDescriptor()
				.getCommonName());
		System.out.println(txc.getStopPoints().get(0)
				.getAlternativeDescriptors().getDescriptor()
				.getCreationDateTime());
		System.out.println(txc.getStopPoints().get(0)
				.getAlternativeDescriptors().getDescriptor().getCommonName());
		System.out.println(txc.getStopPoints().get(0).getPlace()
				.getNptgLocalityRef());
		System.out.println(txc.getStopPoints().get(0).getPlace().getSuburb());
		System.out.println(txc.getStopPoints().get(0).getPlace().getLocation()
				.getLongitude());
		System.out.println(txc.getStopPoints().get(0).getPlace().getLocation()
				.getLatitude());
		System.out.println(txc.getStopPoints().get(0).getStopClassification()
				.getStopType());
		System.out.println(txc.getStopPoints().get(0).getStopClassification()
				.getOffStreet().getRail().getPlatform());

		System.out.println(txc.getRouteSections().getRouteSection().get(0)
				.getId());
		System.out.println(txc.getRouteSections().getRouteSection().get(0).getDirection());
		System.out.println(txc.getRouteSections().getRouteSection().get(0).getFrom().getStopPointRef());
		System.out.println(txc.getRouteSections().getRouteSection().get(0).getTo().getStopPointRef());
		
		System.out.println(txc.getRoutes().get(0).getId());
		System.out.println(txc.getRoutes().get(0).getPrivateCode());
		System.out.println(txc.getRoutes().get(0).getDescription());
		System.out.println(txc.getRoutes().get(0).getRouteSectionRef());
		
		System.out.println(txc.getJourneyPatternSections().getJourneyPatternSection().get(0).getId());
		System.out.println(txc.getJourneyPatternSections().getJourneyPatternSection().get(0).getRouteLinkRef());
		System.out.println(txc.getJourneyPatternSections().getJourneyPatternSection().get(0).getRunTime());
		System.out.println(txc.getJourneyPatternSections().getJourneyPatternSection().get(0).getFrom().getActivity());
		System.out.println(txc.getJourneyPatternSections().getJourneyPatternSection().get(0).getFrom().getSequenceNumber());
		System.out.println(txc.getJourneyPatternSections().getJourneyPatternSection().get(0).getFrom().getStopPointRef());
		System.out.println(txc.getJourneyPatternSections().getJourneyPatternSection().get(0).getFrom().getTimingStatus());
		System.out.println(txc.getJourneyPatternSections().getJourneyPatternSection().get(0).getTo().getActivity());
		System.out.println(txc.getJourneyPatternSections().getJourneyPatternSection().get(0).getTo().getSequenceNumber());
		System.out.println(txc.getJourneyPatternSections().getJourneyPatternSection().get(0).getTo().getStopPointRef());
		System.out.println(txc.getJourneyPatternSections().getJourneyPatternSection().get(0).getTo().getTimingStatus());
		
		System.out.println(txc.getOperators().get(0).getId());
		System.out.println(txc.getOperators().get(0).getOperatorCode());
		System.out.println(txc.getOperators().get(0).getOperatorShortName());
		System.out.println(txc.getOperators().get(0).getOperatorNameOnLicence());
		System.out.println(txc.getOperators().get(0).getTradingName());
		
	}

}
