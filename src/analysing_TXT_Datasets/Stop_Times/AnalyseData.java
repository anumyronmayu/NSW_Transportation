package analysing_TXT_Datasets.Stop_Times;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utilities.Utilities;

public class AnalyseData {

	public List<Stop_Times> parseStrLines(List<String> list)
			throws ParseException {

		List<Stop_Times> stopTimesList = new ArrayList<Stop_Times>();

		for (String s : list) {

			String[] splitStr = s.split("\",\"");

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

			stopTimesList.add(st);

		}

		return stopTimesList;

	}

	// Map trip id to list of stop times
	public HashMap<String, ArrayList<Stop_Times>> mapTripIdToStopTimesList(
			List<Stop_Times> stopTimesList) {

		HashMap<String, ArrayList<Stop_Times>> tripIdToListOfStopTimesMap = new HashMap<String, ArrayList<Stop_Times>>();

		for (int i = 0; i < stopTimesList.size(); i++) {

			Stop_Times st = stopTimesList.get(i);

			if (tripIdToListOfStopTimesMap.get(st.getTrip_id()) == null) {
				ArrayList<Stop_Times> stop_times_group = new ArrayList<Stop_Times>();
				stop_times_group.add(st);
				tripIdToListOfStopTimesMap.put(st.getTrip_id(),
						stop_times_group);
			} else {
				tripIdToListOfStopTimesMap.get(st.getTrip_id()).add(st);
			}

		}

		return tripIdToListOfStopTimesMap;

	}

	// trip_id => type
	public HashMap<String, String> mapTripIdToType(
			HashMap<String, ArrayList<Stop_Times>> tripIdToListOfStopTimesMap,
			HashMap<String, String> typeMapWithVersionNumber) {

		HashMap<String, String> tripIdToTypeMap = new HashMap<String, String>();
		Set<Map.Entry<String, ArrayList<Stop_Times>>> entrySetTripID = tripIdToListOfStopTimesMap
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
				tripIdToTypeMap.put(entry.getKey(),
						typeMapWithVersionNumber.get(route_id));
			}

		}

		return tripIdToTypeMap;
	}

	// trip_id => color
	public HashMap<String, String> mapTripIdToColor(
			HashMap<String, ArrayList<Stop_Times>> tripIdToListOfStopTimesMap,
			HashMap<String, String> colorMapWithVersionNumber) {

		HashMap<String, String> tripIdToColorMap = new HashMap<String, String>();
		Set<Map.Entry<String, ArrayList<Stop_Times>>> entrySetTripID = tripIdToListOfStopTimesMap
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

			if (colorMapWithVersionNumber.get(route_id) != null) {
				tripIdToColorMap.put(entry.getKey(),
						colorMapWithVersionNumber.get(route_id));
			}
		}

		return tripIdToColorMap;
	}

	public void analysingATypeOfNetwork(String folderName,
			HashMap<String, ArrayList<Stop_Times>> map,
			List<String> tripIdForEachType, String type) throws IOException {

		// list of velocities for all trip id
		List<ArrayList<Double>> velocitiesList = new ArrayList<ArrayList<Double>>();
		List<ArrayList<String>> routeAndBusNumberList = new ArrayList<ArrayList<String>>();

		for (String s : tripIdForEachType) {

			ArrayList<Stop_Times> stop_times_group = map.get(s);
			// velocities for a trip id
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

			velocitiesList.add(velocities);

			ArrayList<String> routeAndBusNumber = new ArrayList<String>();
			String[] splitStr = stop_times_group.get(0).getTrip_id().split("-");
			String[] splitStr1 = splitStr[0].split("\\.");
			routeAndBusNumber.add(splitStr1[0]);
			routeAndBusNumber.add(splitStr1[2]);
			if (splitStr.length == 4) {
				routeAndBusNumber.add(splitStr[1]);
			} else {
				routeAndBusNumber.add(splitStr[1] + "-" + splitStr[2]);
			}
			routeAndBusNumber.add(stop_times_group.get(0).getTrip_id());
			routeAndBusNumberList.add(routeAndBusNumber);
		}

		List<Double> averageVelocityList = new ArrayList<Double>();
		for (ArrayList<Double> velocitiesSequence : velocitiesList) {
			double sumVelocity = 0;
			for (double velocity : velocitiesSequence) {
				sumVelocity += velocity;
			}
			averageVelocityList.add(sumVelocity / velocitiesSequence.size());
		}

		DecimalFormat df = new DecimalFormat("#.##");

		List<String> results = new ArrayList<String>();

		Utilities.makeDir(folderName + "Analysis_Results/Stop_Times");

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
			String run = routeAndBusNumberList.get(i).get(0);
			String route = routeAndBusNumberList.get(i).get(1);
			String busNumber = routeAndBusNumberList.get(i).get(2);
			String tripId = routeAndBusNumberList.get(i).get(3);
			String result = ("Run: " + run + " Route: " + route
					+ " Bus Number: " + busNumber + " Average Velocity: "
					+ df.format(v) + "km/h");
			results.add(result);
			writer.write(result + "\n");
			writerCSV.write("\"" + route + "\",\"" + busNumber + "\",\""
					+ df.format(v) + "\"\n");
		}

		writer.close();
		writerCSV.close();

		double average = sum / averageVelocityList.size();

		System.out.println("Type: " + type + " Average Velocity: "
				+ df.format(average) + "km/h");

	}

}
