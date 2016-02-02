package analysing_TXT_Datasets.Stops;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import analysing_TXT_Datasets.Routes.Routes;
import analysing_TXT_Datasets.Stop_Times.Stop_Times;

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

		// Map trip id to list of stop times
		analysing_TXT_Datasets.Stop_Times.AnalyseData obj2 = new analysing_TXT_Datasets.Stop_Times.AnalyseData();
		List<Stop_Times> stopTimesList = obj2.getStopTimesList();
		System.out.println(stopTimesList.size());

		HashMap<String, ArrayList<Stop_Times>> tripIdToListOfStopTimesMap = obj2
				.mapTripIdToStopTimesList(stopTimesList);

		// trip_id => type
		HashMap<String, String> tripIdToTypeMap = obj2.mapTripIdToType(
				tripIdToListOfStopTimesMap, typeMapWithVersionNumber);

		// trip_id => color
		HashMap<String, String> tripIdToColorMap = obj2.mapTripIdToColor(
				tripIdToListOfStopTimesMap, colorMapWithVersionNumber);

		// stop_id => trip_id
		HashMap<String, String> stopIdToTripIdMap = obj2
				.mapStopIdToTripId(stopTimesList);

		// Map stop id to type
		analysing_TXT_Datasets.Stops.AnalyseData obj3 = new analysing_TXT_Datasets.Stops.AnalyseData();
		List<Stops> stopList = obj3.getStopsList();
		System.out.println(stopList.size());

		//
		// type
		List<Stops> listOfSydneyBusesNetwork = new ArrayList<Stops>();
		List<Stops> listOfRegionalCoachesNetwork = new ArrayList<Stops>();
		List<Stops> listOfRegionalTrainsNetwork = new ArrayList<Stops>();
		List<Stops> listOfPrivatebusservices = new ArrayList<Stops>();
		List<Stops> listOfIllawarraBusesNetwork = new ArrayList<Stops>();
		List<Stops> listOfBlueMountainsBusesNetwork = new ArrayList<Stops>();
		List<Stops> listOfCentralCoastBusesNetwork = new ArrayList<Stops>();
		List<Stops> listOfTemporarybuses = new ArrayList<Stops>();
		List<Stops> listOfHunterBusesNetwork = new ArrayList<Stops>();
		List<Stops> listOfSydneyTrainsNetwork = new ArrayList<Stops>();
		List<Stops> listOfSydneyFerriesNetwork = new ArrayList<Stops>();
		List<Stops> listOfPrivateferryandfastferryservices = new ArrayList<Stops>();
		List<Stops> listOfSydneyLightRailNetwork = new ArrayList<Stops>();
		List<Stops> listOfIntercityTrainsNetwork = new ArrayList<Stops>();
		List<Stops> listOfNewcastleFerries = new ArrayList<Stops>();

		List<List<Stops>> allTransportationNetworks = new ArrayList<List<Stops>>();
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

		for (Stops stops : stopList) {

			String stopId = stops.getStop_id();
			String tripId = stopIdToTripIdMap.get(stopId);

			if (tripIdToTypeMap.get(tripId) != null) {

				switch (tripIdToTypeMap.get(tripId)) {
				case "Sydney Buses Network":
					listOfSydneyBusesNetwork.add(stops);
					break;
				case "Regional Trains and Coaches Network":
					if (tripIdToColorMap.get(tripId).equals("732A82")) {
						listOfRegionalCoachesNetwork.add(stops);
					} else {
						listOfRegionalTrainsNetwork.add(stops);
					}
					break;
				case "Private bus services":
					listOfPrivatebusservices.add(stops);
					break;
				case "Illawarra Buses Network":
					listOfIllawarraBusesNetwork.add(stops);
					break;
				case "Blue Mountains Buses Network":
					listOfBlueMountainsBusesNetwork.add(stops);
					break;
				case "Central Coast Buses Network":
					listOfCentralCoastBusesNetwork.add(stops);
					break;
				case "Temporary buses":
					listOfTemporarybuses.add(stops);
					break;
				case "Hunter Buses Network":
					listOfHunterBusesNetwork.add(stops);
					break;
				case "Sydney Trains Network":
					listOfSydneyTrainsNetwork.add(stops);
					break;
				case "Sydney Ferries Network":
					listOfSydneyFerriesNetwork.add(stops);
					break;
				case "Private ferry and fast ferry services":
					listOfPrivateferryandfastferryservices.add(stops);
					break;
				case "Sydney Light Rail Network":
					listOfSydneyLightRailNetwork.add(stops);
					break;
				case "Intercity Trains Network":
					listOfIntercityTrainsNetwork.add(stops);
					break;
				case "Newcastle Ferries":
					listOfNewcastleFerries.add(stops);
					break;
				default:
					System.out.println("No Such Type!!!");
				}

			}
		}

		String folderName = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/";
		FileWriter writer = new FileWriter(folderName
				+ "Analysis_Results/Stops/Results.txt");

		int index = 0;

		for (List<Stops> transportationNetworks : allTransportationNetworks) {

			System.out.println(transportationNetworks.size());

			String s = typeList.get(index);
			index++;
			writer.write("------------------" + s + ":\n");

			int[][] quantity = obj3.calculateDensity(transportationNetworks);

			for (int i = 0; i < quantity.length; i++) {
				for (int j = 0; j < quantity.length; j++) {
					writer.write(j + " " + (quantity.length - 1 - i) + " "
							+ quantity[i][j] + "\n");
				}
			}
		}

		writer.close();

	}

}
