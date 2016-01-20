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
		String stops_times_CSV_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/Analysis_Results/stop_times_modified2.csv";
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

		analyseData.analysingATypeOfNetwork(folderName,
				tripIdToListOfStopTimesMap, listOfSydneyBusesNetwork,
				"SydneyBusesNetwork");
		analyseData.analysingATypeOfNetwork(folderName,
				tripIdToListOfStopTimesMap, listOfRegionalTrainsNetwork,
				"RegionalTrainsNetwork");
		analyseData.analysingATypeOfNetwork(folderName,
				tripIdToListOfStopTimesMap, listOfRegionalCoachesNetwork,
				"RegionalCoachesNetwork");
		analyseData.analysingATypeOfNetwork(folderName,
				tripIdToListOfStopTimesMap, listOfPrivatebusservices,
				"PrivateBusServices");
		analyseData.analysingATypeOfNetwork(folderName,
				tripIdToListOfStopTimesMap, listOfIllawarraBusesNetwork,
				"IllawarraBusesNetwork");
		analyseData.analysingATypeOfNetwork(folderName,
				tripIdToListOfStopTimesMap, listOfBlueMountainsBusesNetwork,
				"BlueMountainsBusesNetwork");
		analyseData.analysingATypeOfNetwork(folderName,
				tripIdToListOfStopTimesMap, listOfCentralCoastBusesNetwork,
				"CentralCoastBusesNetwork");
		analyseData.analysingATypeOfNetwork(folderName,
				tripIdToListOfStopTimesMap, listOfTemporarybuses,
				"Temporarybuses");
		analyseData.analysingATypeOfNetwork(folderName,
				tripIdToListOfStopTimesMap, listOfHunterBusesNetwork,
				"HunterBusesNetwork");
		analyseData.analysingATypeOfNetwork(folderName,
				tripIdToListOfStopTimesMap, listOfSydneyTrainsNetwork,
				"SydneyTrainsNetwork");
		analyseData.analysingATypeOfNetwork(folderName,
				tripIdToListOfStopTimesMap, listOfSydneyFerriesNetwork,
				"SydneyFerriesNetwork");
		analyseData.analysingATypeOfNetwork(folderName,
				tripIdToListOfStopTimesMap,
				listOfPrivateferryandfastferryservices,
				"PrivateFerryAndFastFerryServices");
		analyseData.analysingATypeOfNetwork(folderName,
				tripIdToListOfStopTimesMap, listOfSydneyLightRailNetwork,
				"SydneyLightRailNetwork");
		analyseData.analysingATypeOfNetwork(folderName,
				tripIdToListOfStopTimesMap, listOfIntercityTrainsNetwork,
				"IntercityTrainsNetwork");
		analyseData.analysingATypeOfNetwork(folderName,
				tripIdToListOfStopTimesMap, listOfNewcastleFerries,
				"NewCastleFerries");

	}
}