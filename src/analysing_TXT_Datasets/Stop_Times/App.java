package analysing_TXT_Datasets.Stop_Times;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utilities.Utilities;
import analysing_TXT_Datasets.Routes.AnalyseData;
import analysing_TXT_Datasets.Routes.Routes;

public class App {

	public static void main(String[] args) throws IOException, ParseException {

		// route_id_part1 + maxVersion => type, color
		String routes_CSV_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/routes.csv";
		List<String> fileLines = Utilities.readFile(routes_CSV_file, true);

		AnalyseData obj = new AnalyseData();
		List<Routes> routesList = obj.parseStrLines(fileLines);

		System.out.println(routesList.size());

		HashMap<String, String> typeMapWithVersionNumber = obj
				.getTypeMapWithVersionNumber(routesList);
		HashMap<String, String> colorMapWithVersionNumber = obj
				.getColorMapWithVersionNumber(routesList);

		// parse Stop_Times
		String stops_times_CSV_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/Analysis_Results/stop_times_modified.csv";
		List<String> stopTimesFileLines = Utilities.readFile(
				stops_times_CSV_file, true);

		analysing_TXT_Datasets.Stop_Times.AnalyseData analyseData = new analysing_TXT_Datasets.Stop_Times.AnalyseData();
		List<Stop_Times> stopTimesList = analyseData
				.parseStrLines(stopTimesFileLines);

		System.out.println(stopTimesList.size());

		// Map trip id to list of stop times
		HashMap<String, ArrayList<Stop_Times>> tripIdToListOfStopTimesMap = analyseData
				.mapTripIdToStopTimesList(stopTimesList);

		// trip_id => type
		HashMap<String, String> tripIdToTypeMap = analyseData.mapTripIdToType(
				tripIdToListOfStopTimesMap, typeMapWithVersionNumber);

		// trip_id => color
		HashMap<String, String> tripIdToColorMap = analyseData
				.mapTripIdToColor(tripIdToListOfStopTimesMap,
						colorMapWithVersionNumber);

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

		// Analyzing SydneyBusesNetwork
		String folderName = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/";

		HashMap<String, List<Double>> routeNumberToListOfSpeedMap = new HashMap<String, List<Double>>();
		HashMap<String, String> routeNumberToOperatorNumberMap = new HashMap<String, String>();

		for (int i = 0; i < typeList.size(); i++) {
			routeNumberToListOfSpeedMap = analyseData.classifyRouteNumber(
					tripIdToListOfStopTimesMap,
					allTransportationNetworks.get(i));

			routeNumberToOperatorNumberMap = analyseData
					.getRouteNumberToOperatorNumberMap(
							tripIdToListOfStopTimesMap,
							allTransportationNetworks.get(i));

			analyseData
					.calculateAverageSpeed(folderName, typeList.get(i),
							routeNumberToListOfSpeedMap,
							routeNumberToOperatorNumberMap);
		}

		/*for (int i = 0; i < typeList.size(); i++) {
			routeNumberToListOfSpeedMap = analyseData.classifyRouteNumber(
					tripIdToListOfStopTimesMap,
					allTransportationNetworks.get(i));

			routeNumberToOperatorNumberMap = analyseData
					.getRouteNumberToOperatorNumberMap(
							tripIdToListOfStopTimesMap,
							allTransportationNetworks.get(i));

			analyseData.calculatePDFCurve(folderName, typeList.get(i),
					typeListShort.get(i), routeNumberToListOfSpeedMap,
					routeNumberToOperatorNumberMap, 0.5);
		}*/

		/*for (int i = 0; i < typeList.size(); i++) {
			analyseData.analysingATypeOfNetwork(folderName,
					tripIdToListOfStopTimesMap,
					allTransportationNetworks.get(i), typeList.get(i));
		}*/
	}
}