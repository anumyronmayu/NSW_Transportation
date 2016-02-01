package analysing_TXT_Datasets.Shapes;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import analysing_TXT_Datasets.Routes.AnalyseData;
import analysing_TXT_Datasets.Routes.Routes;
import utilities.Utilities;

public class App {

	public static void main(String[] args) throws IOException {

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

		// parse Shapes
		String shapes_csv_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static/shapes.txt";
		List<String> shapesfileLines = Utilities
				.readFile(shapes_csv_file, true);
		analysing_TXT_Datasets.Shapes.AnalyseData analyseData = new analysing_TXT_Datasets.Shapes.AnalyseData();
		List<Shapes> shapesList = analyseData.parseStrLines(shapesfileLines);

		System.out.println(shapesList.size());

		// type
		List<Shapes> listOfSydneyBusesNetwork = new ArrayList<Shapes>();
		List<Shapes> listOfRegionalCoachesNetwork = new ArrayList<Shapes>();
		List<Shapes> listOfRegionalTrainsNetwork = new ArrayList<Shapes>();
		List<Shapes> listOfPrivatebusservices = new ArrayList<Shapes>();
		List<Shapes> listOfIllawarraBusesNetwork = new ArrayList<Shapes>();
		List<Shapes> listOfBlueMountainsBusesNetwork = new ArrayList<Shapes>();
		List<Shapes> listOfCentralCoastBusesNetwork = new ArrayList<Shapes>();
		List<Shapes> listOfTemporarybuses = new ArrayList<Shapes>();
		List<Shapes> listOfHunterBusesNetwork = new ArrayList<Shapes>();
		List<Shapes> listOfSydneyTrainsNetwork = new ArrayList<Shapes>();
		List<Shapes> listOfSydneyFerriesNetwork = new ArrayList<Shapes>();
		List<Shapes> listOfPrivateferryandfastferryservices = new ArrayList<Shapes>();
		List<Shapes> listOfSydneyLightRailNetwork = new ArrayList<Shapes>();
		List<Shapes> listOfIntercityTrainsNetwork = new ArrayList<Shapes>();
		List<Shapes> listOfNewcastleFerries = new ArrayList<Shapes>();

		List<List<Shapes>> allTransportationNetworks = new ArrayList<List<Shapes>>();
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

		for (Shapes shapes : shapesList) {

			String[] splitStr = shapes.getShape_id().split("\\.");
			String route_id = splitStr[0];

			if (typeMapWithVersionNumber.get(route_id) != null) {

				switch (typeMapWithVersionNumber.get(route_id)) {
				case "Sydney Buses Network":
					listOfSydneyBusesNetwork.add(shapes);
					break;
				case "Regional Trains and Coaches Network":
					if (colorMapWithVersionNumber.get(route_id)
							.equals("732A82")) {
						listOfRegionalCoachesNetwork.add(shapes);
					} else {
						listOfRegionalTrainsNetwork.add(shapes);
					}
					break;
				case "Private bus services":
					listOfPrivatebusservices.add(shapes);
					break;
				case "Illawarra Buses Network":
					listOfIllawarraBusesNetwork.add(shapes);
					break;
				case "Blue Mountains Buses Network":
					listOfBlueMountainsBusesNetwork.add(shapes);
					break;
				case "Central Coast Buses Network":
					listOfCentralCoastBusesNetwork.add(shapes);
					break;
				case "Temporary buses":
					listOfTemporarybuses.add(shapes);
					break;
				case "Hunter Buses Network":
					listOfHunterBusesNetwork.add(shapes);
					break;
				case "Sydney Trains Network":
					listOfSydneyTrainsNetwork.add(shapes);
					break;
				case "Sydney Ferries Network":
					listOfSydneyFerriesNetwork.add(shapes);
					break;
				case "Private ferry and fast ferry services":
					listOfPrivateferryandfastferryservices.add(shapes);
					break;
				case "Sydney Light Rail Network":
					listOfSydneyLightRailNetwork.add(shapes);
					break;
				case "Intercity Trains Network":
					listOfIntercityTrainsNetwork.add(shapes);
					break;
				case "Newcastle Ferries":
					listOfNewcastleFerries.add(shapes);
					break;
				default:
					System.out.println("No Such Type!!!");
				}

			}
		}

		String folderName = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/";
		FileWriter writer = new FileWriter(folderName
				+ "Analysis_Results/Shapes/Results.txt");

		int index = 0;

		for (List<Shapes> transportationNetworks : allTransportationNetworks) {

			String s = typeList.get(index);
			index++;
			writer.write("------------------" + s + ":\n");

			int[][] quantity = analyseData
					.calculateDensity(transportationNetworks);

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