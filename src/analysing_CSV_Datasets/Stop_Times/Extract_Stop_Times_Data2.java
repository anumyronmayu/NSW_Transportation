package analysing_CSV_Datasets.Stop_Times;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import analysing_CSV_Datasets.Routes.*;

public class Extract_Stop_Times_Data2 {

	private static void makeDir(String dir) {
		new File(dir).mkdirs();
	}

	private void analysingATypeOfNetwork(String folderName,
			HashMap<String, ArrayList<Stop_Times>> map, List<String> typeList,
			String type) throws IOException {

		List<ArrayList<Double>> velocitiesListSydneyBusesNetwork = new ArrayList<ArrayList<Double>>();
		List<ArrayList<String>> routeAndBusNumberListSydneyBusesNetwork = new ArrayList<ArrayList<String>>();

		for (String s : typeList) {
			ArrayList<Stop_Times> stop_times_group = map.get(s);
			ArrayList<Double> velocities = new ArrayList<Double>();

			for (int i = 1; i < stop_times_group.size(); i++) {
				double time = ((double) (stop_times_group.get(i)
						.getArrival_time().getTime() - stop_times_group
						.get(i - 1).getDeparture_time().getTime())) / 1000 / 60 / 60;// hour
				if (time == 0) {
					continue;
				}
				double d = (stop_times_group.get(i).getShape_dist_traveled() - stop_times_group
						.get(i - 1).getShape_dist_traveled()) / 1000;
				double v = d / time;
				velocities.add(v);
			}
			velocitiesListSydneyBusesNetwork.add(velocities);

			ArrayList<String> routeAndBusNumber = new ArrayList<String>();
			String[] splitStr = stop_times_group.get(0).getTrip_id().split("-");
			String[] splitStr1 = splitStr[0].split("\\.");
			routeAndBusNumber.add(splitStr1[2]);
			if (splitStr.length == 4) {
				routeAndBusNumber.add(splitStr[1]);
			} else {
				routeAndBusNumber.add(splitStr[1] + "-" + splitStr[2]);
			}
			routeAndBusNumberListSydneyBusesNetwork.add(routeAndBusNumber);
		}

		List<Double> averageVelocityList = new ArrayList<Double>();
		for (ArrayList<Double> velocitiesSequence : velocitiesListSydneyBusesNetwork) {
			double sumVelocity = 0;
			for (double velocity : velocitiesSequence) {
				sumVelocity += velocity;
			}
			averageVelocityList.add(sumVelocity / velocitiesSequence.size());
		}

		DecimalFormat df = new DecimalFormat("#.##");

		List<String> results = new ArrayList<String>();

		makeDir(folderName + "Analysis_Results/Stop_Times");

		FileWriter writer = new FileWriter(folderName
				+ "Analysis_Results/Stop_Times/Stop_Times_Analysis_Results_"
				+ type + ".txt");
		FileWriter writerCSV = new FileWriter(folderName
				+ "Analysis_Results/Stop_Times/Stop_Times_Analysis_Results_"
				+ type + "_csv.csv");
		writerCSV.write("\"Route\",\"BusNumber\",\"AverageVelocity\"\n");
		double sum = 0;
		for (int i = 0; i < averageVelocityList.size(); i++) {
			double v = averageVelocityList.get(i);
			sum += v;
			String route = routeAndBusNumberListSydneyBusesNetwork.get(i)
					.get(0);
			String busNumber = routeAndBusNumberListSydneyBusesNetwork.get(i)
					.get(1);
			String result = ("Route: " + route + " Bus Number: " + busNumber
					+ " Average Velocity: " + df.format(v) + "km/h");
			results.add(result);
			writer.write(result + "\n");
			writerCSV.write("\"" + route + "\",\"" + busNumber + "\",\""
					+ df.format(v) + "\"\n");
		}
		writer.close();
		writerCSV.close();
		double average = sum / averageVelocityList.size();

		System.out.println("Type: " + type + "Average Velocity: "
				+ df.format(average) + "km/h");

	}

	public static void main(String[] args) throws IOException, ParseException {

		Extract_Stop_Times_Data2 obj = new Extract_Stop_Times_Data2();

		// route_id => type
		Extract_Routes_Data routeData = new Extract_Routes_Data();
		HashMap<String, String> typeMapWithVersionNumber = routeData
				.getTypeMapWithVersionNumber();

		// read csv file
		String folderName = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/";
		String csv_file = folderName
				+ "Analysis_Results/stop_times_modified.csv";
		FileInputStream fstream = new FileInputStream(csv_file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine = br.readLine();

		// strLine => obj
		int index = 0;
		List<Stop_Times> list = new ArrayList<Stop_Times>();
		while ((strLine = br.readLine()) != null) {
			index++;

			String[] splitStr = strLine.split("\",\"");

			Stop_Times st = new Stop_Times();

			st.setTrip_id(splitStr[0].substring(1, splitStr[0].length()));

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
			Date date1 = simpleDateFormat.parse(splitStr[1]);
			st.setArrival_time(date1);
			Date date2 = simpleDateFormat.parse(splitStr[2]);
			st.setDeparture_time(date2);

			st.setStop_id(splitStr[3]);

			st.setStop_sequence(Integer.parseInt(splitStr[4]));

			st.setStop_headsign(splitStr[5]);

			st.setPickup_type(splitStr[6]);

			st.setDrop_off_type(splitStr[7]);

			st.setShape_dist_traveled(Double.parseDouble(splitStr[8].substring(
					0, splitStr[8].length() - 1)));

			list.add(st);
		}

		System.out.println("index: " + index);
		System.out.println("list size: " + list.size());

		br.close();

		// trip_id => List<Stop_Times>
		HashMap<String, ArrayList<Stop_Times>> map = new HashMap<String, ArrayList<Stop_Times>>();
		for (int i = 0; i < list.size(); i++) {
			Stop_Times st = list.get(i);
			if (map.get(st.getTrip_id()) == null) {
				ArrayList<Stop_Times> stop_times_group = new ArrayList<Stop_Times>();
				stop_times_group.add(st);
				map.put(st.getTrip_id(), stop_times_group);
			} else {
				map.get(st.getTrip_id()).add(st);
			}
		}

		// trip_id => type
		HashMap<String, String> typeMap = new HashMap<String, String>();
		Set<Map.Entry<String, ArrayList<Stop_Times>>> entrySetTripID = map
				.entrySet();
		for (Map.Entry<String, ArrayList<Stop_Times>> entry : entrySetTripID) {
			String[] splitStr = entry.getKey().split("-");
			String route_id;

			if (splitStr.length == 4) {
				String[] splitStr1 = splitStr[0].split("\\.");
				String[] splitStr2 = splitStr[3].split("\\.");
				route_id = splitStr1[2] + "-" + splitStr[1] + "-" + splitStr[2]
						+ "-" + splitStr2[0];
			} else {
				String[] splitStr1 = splitStr[0].split("\\.");
				String[] splitStr2 = splitStr[4].split("\\.");
				route_id = splitStr1[2] + "-" + splitStr[1] + "-" + splitStr[2]
						+ "-" + splitStr[3] + "-" + splitStr2[0];
			}

			if (typeMapWithVersionNumber.get(route_id) != null) {
				typeMap.put(entry.getKey(),
						typeMapWithVersionNumber.get(route_id));
			}
		}

		// type, list of trip_id
		List<String> listOfSydneyBusesNetwork = new ArrayList<String>();
		List<String> listOfRegionalTrainsandCoachesNetwork = new ArrayList<String>();
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

		Set<Map.Entry<String, String>> entrySetTypeMap = typeMap.entrySet();
		for (Map.Entry<String, String> entry : entrySetTypeMap) {
			switch (entry.getValue()) {
			case "Sydney Buses Network":
				listOfSydneyBusesNetwork.add(entry.getKey());
				break;
			case "Regional Trains and Coaches Network":
				listOfRegionalTrainsandCoachesNetwork.add(entry.getKey());
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
		obj.analysingATypeOfNetwork(folderName, map, listOfSydneyBusesNetwork,
				"SydneyBusesNetwork");
		obj.analysingATypeOfNetwork(folderName, map,
				listOfRegionalTrainsandCoachesNetwork,
				"RegionalTrainsAndCoachesNetwork");
		obj.analysingATypeOfNetwork(folderName, map, listOfPrivatebusservices,
				"PrivateBusServices");
		obj.analysingATypeOfNetwork(folderName, map,
				listOfIllawarraBusesNetwork, "IllawarraBusesNetwork");
		obj.analysingATypeOfNetwork(folderName, map,
				listOfBlueMountainsBusesNetwork, "BlueMountainsBusesNetwork");
		obj.analysingATypeOfNetwork(folderName, map,
				listOfCentralCoastBusesNetwork, "CentralCoastBusesNetwork");
		obj.analysingATypeOfNetwork(folderName, map, listOfTemporarybuses,
				"Temporarybuses");
		obj.analysingATypeOfNetwork(folderName, map, listOfHunterBusesNetwork,
				"HunterBusesNetwork");
		obj.analysingATypeOfNetwork(folderName, map, listOfSydneyTrainsNetwork,
				"SydneyTrainsNetwork");
		obj.analysingATypeOfNetwork(folderName, map,
				listOfSydneyFerriesNetwork, "SydneyFerriesNetwork");
		obj.analysingATypeOfNetwork(folderName, map,
				listOfPrivateferryandfastferryservices,
				"PrivateFerryAndFastFerryServices");
		obj.analysingATypeOfNetwork(folderName, map,
				listOfSydneyLightRailNetwork, "SydneyLightRailNetwork");
		obj.analysingATypeOfNetwork(folderName, map,
				listOfIntercityTrainsNetwork, "IntercityTrainsNetwork");
		obj.analysingATypeOfNetwork(folderName, map, listOfNewcastleFerries,
				"NewCastleFerries");

	}
}