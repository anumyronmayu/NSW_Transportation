package analysing_TXT_Datasets.Stop_Times;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import analysing_TXT_Datasets.Routes.Routes;

public class App {

	public static void main(String[] args) throws IOException, ParseException {

		// route_id_part1 + maxVersion => type, color
		analysing_TXT_Datasets.Routes.AnalyseData obj1 = new analysing_TXT_Datasets.Routes.AnalyseData();
		List<Routes> routesList = obj1.getRoutesList();
		System.out.println(routesList.size());

		HashMap<String, String> typeMapWithVersionNumber = obj1
				.getTypeMapWithVersionNumber(routesList);
		HashMap<String, String> colorMapWithVersionNumber = obj1
				.getColorMapWithVersionNumber(routesList);

		// get Stop_Times
		analysing_TXT_Datasets.Stop_Times.AnalyseData obj2 = new analysing_TXT_Datasets.Stop_Times.AnalyseData();
		List<Stop_Times> stopTimesList = obj2.getStopTimesList();
		System.out.println(stopTimesList.size());

		// Map trip id to list of stop times
		HashMap<String, ArrayList<Stop_Times>> tripIdToListOfStopTimesMap = obj2
				.mapTripIdToStopTimesList(stopTimesList);

		// trip_id => type
		HashMap<String, String> tripIdToTypeMap = obj2.mapTripIdToType(
				tripIdToListOfStopTimesMap, typeMapWithVersionNumber);

		// trip_id => color
		HashMap<String, String> tripIdToColorMap = obj2.mapTripIdToColor(
				tripIdToListOfStopTimesMap, colorMapWithVersionNumber);

		// type, list of trip_id
		List<String> listOfSydneyBusesNetwork = new ArrayList<String>();
		List<String> listOfRegionalCoachesNetwork = new ArrayList<String>();
		List<String> listOfRegionalTrainsNetwork = new ArrayList<String>();
		List<String> listOfPrivatebusservices = new ArrayList<String>();
		List<String> listOfIllawarraBusesNetwork = new ArrayList<String>();
		List<String> listOfBlueMountainsBusesNetwork = new ArrayList<String>();
		List<String> listOfCentralCoastBusesNetwork = new ArrayList<String>();
		List<String> listOfTemporarybuses = new ArrayList<String>();
		List<String> listOfHunterBusesNetwork = new ArrayList<String>();
		List<String> listOfSydneyTrainsNetwork = new ArrayList<String>();
		List<String> listOfSydneyFerriesNetwork = new ArrayList<String>();
		List<String> listOfPrivateferryandfastferryservices = new ArrayList<String>();
		List<String> listOfSydneyLightRailNetwork = new ArrayList<String>();
		List<String> listOfIntercityTrainsNetwork = new ArrayList<String>();
		List<String> listOfNewcastleFerries = new ArrayList<String>();

		List<List<String>> allTransportationNetworks = new ArrayList<List<String>>();
		allTransportationNetworks.add(listOfSydneyBusesNetwork);
		allTransportationNetworks.add(listOfRegionalCoachesNetwork);
		allTransportationNetworks.add(listOfRegionalTrainsNetwork);
		allTransportationNetworks.add(listOfPrivatebusservices);
		allTransportationNetworks.add(listOfIllawarraBusesNetwork);
		allTransportationNetworks.add(listOfBlueMountainsBusesNetwork);
		allTransportationNetworks.add(listOfCentralCoastBusesNetwork);
		allTransportationNetworks.add(listOfTemporarybuses);
		allTransportationNetworks.add(listOfHunterBusesNetwork);
		allTransportationNetworks.add(listOfSydneyTrainsNetwork);
		allTransportationNetworks.add(listOfSydneyFerriesNetwork);
		allTransportationNetworks.add(listOfPrivateferryandfastferryservices);
		allTransportationNetworks.add(listOfSydneyLightRailNetwork);
		allTransportationNetworks.add(listOfIntercityTrainsNetwork);
		allTransportationNetworks.add(listOfNewcastleFerries);

		List<String> typeList = new ArrayList<String>();
		typeList.add("SydneyBusesNetwork");
		typeList.add("RegionalTrainsNetwork");
		typeList.add("RegionalCoachesNetwork");
		typeList.add("PrivateBusServices");
		typeList.add("IllawarraBusesNetwork");
		typeList.add("BlueMountainsBusesNetwork");
		typeList.add("CentralCoastBusesNetwork");
		typeList.add("Temporarybuses");
		typeList.add("HunterBusesNetwork");
		typeList.add("SydneyTrainsNetwork");
		typeList.add("SydneyFerriesNetwork");
		typeList.add("PrivateFerryAndFastFerryServices");
		typeList.add("SydneyLightRailNetwork");
		typeList.add("IntercityTrainsNetwork");
		typeList.add("NewCastleFerries");

		List<String> typeListShort = new ArrayList<String>();
		typeListShort.add("SBN");
		typeListShort.add("RTN");
		typeListShort.add("RCN");
		typeListShort.add("PBS");
		typeListShort.add("IBN");
		typeListShort.add("BMBN");
		typeListShort.add("CCBN");
		typeListShort.add("TB");
		typeListShort.add("HBN");
		typeListShort.add("STN");
		typeListShort.add("SFN");
		typeListShort.add("PFFFS");
		typeListShort.add("SLRN");
		typeListShort.add("ITN");
		typeListShort.add("NCF");

		Set<Map.Entry<String, String>> entrySetTypeMap = tripIdToTypeMap
				.entrySet();
		for (Map.Entry<String, String> entry : entrySetTypeMap) {
			switch (entry.getValue()) {
			case "Sydney Buses Network":
				listOfSydneyBusesNetwork.add(entry.getKey());
				break;
			case "Regional Trains and Coaches Network":
				if (tripIdToColorMap.get(entry.getKey()).equals("732A82")) {
					listOfRegionalCoachesNetwork.add(entry.getKey());
				} else {
					listOfRegionalTrainsNetwork.add(entry.getKey());
				}
				break;
			case "Private bus services":
				listOfPrivatebusservices.add(entry.getKey());
				break;
			case "Illawarra Buses Network":
				listOfIllawarraBusesNetwork.add(entry.getKey());
				break;
			case "Blue Mountains Buses Network":
				listOfBlueMountainsBusesNetwork.add(entry.getKey());
				break;
			case "Central Coast Buses Network":
				listOfCentralCoastBusesNetwork.add(entry.getKey());
				break;
			case "Temporary buses":
				listOfTemporarybuses.add(entry.getKey());
				break;
			case "Hunter Buses Network":
				listOfHunterBusesNetwork.add(entry.getKey());
				break;
			case "Sydney Trains Network":
				listOfSydneyTrainsNetwork.add(entry.getKey());
				break;
			case "Sydney Ferries Network":
				listOfSydneyFerriesNetwork.add(entry.getKey());
				break;
			case "Private ferry and fast ferry services":
				listOfPrivateferryandfastferryservices.add(entry.getKey());
				break;
			case "Sydney Light Rail Network":
				listOfSydneyLightRailNetwork.add(entry.getKey());
				break;
			case "Intercity Trains Network":
				listOfIntercityTrainsNetwork.add(entry.getKey());
				break;
			case "Newcastle Ferries":
				listOfNewcastleFerries.add(entry.getKey());
				break;
			default:
				System.out.println("No Such Type!!!");
			}
		}
		
		String folderName = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/";

		// calculate PDF/CDF curve per type
		for (int i = 0; i < typeList.size(); i++) {
			List<Double> speedListForEachType = obj2.getSpeedListForEachType(tripIdToListOfStopTimesMap, allTransportationNetworks.get(i));
			obj2.calculatePDFCurve(folderName, typeListShort.get(i), speedListForEachType, 0.5);
			obj2.calculateCDFCurve(folderName, typeListShort.get(i), speedListForEachType, 0.5);
		}
		
		/*
		// divide 24 hours into 144 timeslots, each timeslot is 10 minutes, calculate frequency of transportation for each type
		for (int i = 0; i < typeList.size(); i++) {
			obj2.arrivalDepartureTimeDistribution(folderName, tripIdToListOfStopTimesMap, allTransportationNetworks.get(i), typeListShort.get(i));
		}
		*/
		
		/*
		// PDF of average speed per operator number, route number
		HashMap<String, List<Double>> routeNumberToListOfSpeedMap = new HashMap<String, List<Double>>();
		HashMap<String, String> routeNumberToOperatorNumberMap = new HashMap<String, String>();

		for (int i = 0; i < typeList.size(); i++) {
			routeNumberToListOfSpeedMap = obj2.classifyRouteNumber(
					tripIdToListOfStopTimesMap,
					allTransportationNetworks.get(i));

			routeNumberToOperatorNumberMap = obj2
					.getRouteNumberToOperatorNumberMap(
							tripIdToListOfStopTimesMap,
							allTransportationNetworks.get(i));

			obj2.calculatePDFCurvePerOperatorRoute(folderName, typeList.get(i),
					typeListShort.get(i), routeNumberToListOfSpeedMap,
					routeNumberToOperatorNumberMap, 0.5);
		}
		*/
		
		/*
		// average speed for each operator number, route number
		for (int i = 0; i < typeList.size(); i++) {
			routeNumberToListOfSpeedMap = obj2.classifyRouteNumber(
					tripIdToListOfStopTimesMap,
					allTransportationNetworks.get(i));

			routeNumberToOperatorNumberMap = obj2
					.getRouteNumberToOperatorNumberMap(
							tripIdToListOfStopTimesMap,
							allTransportationNetworks.get(i));

			obj2.calculateAverageSpeedPerOperatorRoute(folderName, typeList.get(i),
					routeNumberToListOfSpeedMap, routeNumberToOperatorNumberMap);
		}
		*/
		
		/*
		for (int i = 0; i < typeList.size(); i++) {
			obj2.analysingATypeOfNetwork(folderName,
					tripIdToListOfStopTimesMap,
					allTransportationNetworks.get(i), typeList.get(i));
		}
		*/
	}
}