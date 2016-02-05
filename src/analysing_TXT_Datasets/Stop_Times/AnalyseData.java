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

	public void analysingATypeOfNetwork(String folderName,
			HashMap<String, ArrayList<Stop_Times>> map,
			List<String> tripIdForEachType, String type) throws IOException {

		List<ArrayList<String>> informationList = new ArrayList<ArrayList<String>>();

		int maxSequence = 0;

		// Analyze
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

			if (velocities.size() > maxSequence) {
				maxSequence = velocities.size();
			}

			double sumVelocity = 0;

			for (double velocity : velocities) {
				sumVelocity += velocity;
			}

			ArrayList<String> information = new ArrayList<String>();

			String[] splitStr = stop_times_group.get(0).getTrip_id().split("-");
			String[] splitStr1 = splitStr[0].split("\\.");

			information.add(splitStr1[0]); // run
			information.add(splitStr1[2]); // operator number

			// route number
			if (splitStr.length == 4) {
				information.add(splitStr[1]);
			} else {
				information.add(splitStr[1] + "-" + splitStr[2]);
			}

			information.add(stop_times_group.get(0).getTrip_id()); // trip id

			// average speed
			information.add(String.valueOf(sumVelocity / velocities.size()));

			for (double speed : velocities) {
				information.add(String.valueOf(speed));
			}

			informationList.add(information);
		}

		// take down results
		DecimalFormat df = new DecimalFormat("#.##");

		List<String> results = new ArrayList<String>();

		Utilities.makeDir(folderName + "Analysis_Results/Stop_Times");

		FileWriter writer = new FileWriter(folderName
				+ "Analysis_Results/Stop_Times/Stop_Times_Analysis_Results_"
				+ type + ".txt");
		FileWriter writerCSV = new FileWriter(folderName
				+ "Analysis_Results/Stop_Times/Stop_Times_Analysis_Results_"
				+ type + "_csv.csv");

		writerCSV
				.write("\"OperatorNumber\",\"RouteNumber\",\"Run\",\"AverageSpeed(km/h)\"");
		for (int i = 0; i < maxSequence; i++) {
			writerCSV.write(",\"s" + (i + 1) + "\"");
		}
		writerCSV.write("\n");

		double sum = 0;

		for (int i = 0; i < informationList.size(); i++) {

			String run = informationList.get(i).get(0);
			String operatorNumber = informationList.get(i).get(1);
			String routeNumber = informationList.get(i).get(2);
			// String tripId = informationList.get(i).get(3);

			double v = Double.parseDouble(informationList.get(i).get(4));
			sum += v;

			String result = ("Operator Number: " + operatorNumber
					+ ", Route Number: " + routeNumber + ", Run: " + run
					+ ", Average Velocity: " + df.format(v) + "km/h");
			for (int j = 5; j <= informationList.get(i).size() - 1; j++) {
				result += ", s" + (j - 4) + ": "
						+ informationList.get(i).get(j);
			}
			results.add(result);
			writer.write(result + "\n");

			String csvString = "\"" + operatorNumber + "\",\"" + routeNumber
					+ "\",\"" + run + "\",\"" + df.format(v) + "\"";
			for (int j = 5; j <= informationList.get(i).size() - 1; j++) {
				csvString += ",\"" + informationList.get(i).get(j) + "\"";
			}
			writerCSV.write(csvString + "\n");
		}

		writer.close();
		writerCSV.close();

		double average = sum / informationList.size();

		System.out.println("Type: " + type + " Average Velocity: "
				+ df.format(average) + "km/h");

	}

	public int[] arrivalDepartureTimeDistribution(String folderName,
			HashMap<String, ArrayList<Stop_Times>> tripIdToListOfStopTimesMap,
			List<String> tripIdListForEachType, String typeShort)
			throws IOException {

		int[] arrivalDepartureTimeDistributionArray = new int[144];
		for (int i = 0; i < 144; i++) {
			arrivalDepartureTimeDistributionArray[i] = 0;
		}

		for (String tripId : tripIdListForEachType) {
			ArrayList<Stop_Times> stopTimesList = tripIdToListOfStopTimesMap
					.get(tripId);

			Stop_Times stopTimesFirst = stopTimesList.get(0);
			Stop_Times stopTimesLast = stopTimesList
					.get(stopTimesList.size() - 1);

			Date arrivalTime = stopTimesFirst.getArrival_time();
			int minsArrival = (int) (arrivalTime.getTime() / 1000 / 60 + 600);
			int indexArrival = (minsArrival / 10) % 144;

			Date departureTime = stopTimesLast.getDeparture_time();
			int minsDeparture = (int) (departureTime.getTime() / 1000 / 60 + 600);
			int indexDeparture = (minsDeparture / 10) % 144;

			if (indexArrival < indexDeparture) {
				for (int i = indexArrival; i <= indexDeparture; i++) {
					arrivalDepartureTimeDistributionArray[i]++;
				}
			} else {
				for (int i = indexArrival; i <= arrivalDepartureTimeDistributionArray.length - 1; i++) {
					arrivalDepartureTimeDistributionArray[i]++;
				}
				for (int i = 0; i <= indexDeparture; i++) {
					arrivalDepartureTimeDistributionArray[i]++;
				}
			}
		}

		Utilities
				.makeDir(folderName
						+ "Analysis_Results/Stop_Times/ArrivalDepartureTimeDistribution");

		FileWriter writer = new FileWriter(
				folderName
						+ "Analysis_Results/Stop_Times/ArrivalDepartureTimeDistribution/"
						+ typeShort + ".txt");

		writer.write("\"Timeslot\",\"Quantity\"\n");

		for (int i = 0; i < 144; i++) {
			writer.write("\"" + i + "\"," + "\""
					+ arrivalDepartureTimeDistributionArray[i] + "\"\n");
		}

		writer.close();

		return arrivalDepartureTimeDistributionArray;
	}

	public void calculateAverageSpeedPerOperatorRoute(String folderName,
			String type,
			HashMap<String, List<Double>> routeNumberToListOfSpeedMap,
			HashMap<String, String> routeNumberToOperatorNumberMap)
			throws IOException {

		Utilities.makeDir(folderName
				+ "Analysis_Results/Stop_Times_Average_Speed");

		FileWriter writer = new FileWriter(folderName
				+ "Analysis_Results/Stop_Times_Average_Speed/" + type + ".txt");
		writer.write("\"Operator Number\",\"Route Number\",\"Average Speed\"\n");

		DecimalFormat df = new DecimalFormat("#.##");

		Set<Map.Entry<String, List<Double>>> entrySetRouteNumberToListOfSpeedMap = routeNumberToListOfSpeedMap
				.entrySet();
		for (Map.Entry<String, List<Double>> entry : entrySetRouteNumberToListOfSpeedMap) {

			String routeNumber = entry.getKey();
			List<Double> speedList = entry.getValue();

			double sum = 0;
			for (double d : speedList) {
				sum += d;
			}
			double averageSpeed = sum / speedList.size();

			String operatorNumber = routeNumberToOperatorNumberMap
					.get(routeNumber);

			writer.write("\"" + operatorNumber + "\",");
			writer.write("\"" + routeNumber + "\",");
			writer.write("\"" + df.format(averageSpeed) + "\"");
			writer.write("\n");

		}

		writer.close();
	}

	public void calculateCDFCurve(String folderName, String typeShort,
			List<Double> speedListForEachType, double distance)
			throws IOException {

		Utilities.makeDir(folderName
				+ "Analysis_Results/Stop_Times/Stop_Times_CDF_PerType");

		DecimalFormat df = new DecimalFormat("#.##");

		HashMap<Integer, Integer> indexToNumMap = new HashMap<Integer, Integer>();

		double sMax = Double.MIN_VALUE;
		double sMin = Double.MAX_VALUE;

		// sMin, sMax
		for (double d : speedListForEachType) {
			if (d < sMin)
				sMin = d;
			if (d > sMax)
				sMax = d;
		}

		int indexMax = 0;

		for (double d : speedListForEachType) {
			int index = (int) ((d - sMin) / distance);
			if (index > indexMax) {
				indexMax = index;
			}
			if (indexToNumMap.get(index) == null) {
				indexToNumMap.put(index, 1);
			} else {
				indexToNumMap.put(index, indexToNumMap.get(index) + 1);
			}
		}

		FileWriter writer = new FileWriter(folderName
				+ "Analysis_Results/Stop_Times/Stop_Times_CDF_PerType/"
				+ typeShort + ".txt");

		writer.write("\"Speed\",\"Probability\"\n");

		double cumulativeProbability = 0;

		for (int i = 0; i <= indexMax; i++) {
			double middle = sMin + i * distance + 0.5 * distance;
			double probability;
			if (indexToNumMap.get(i) == null) {
				probability = 0.0;
			} else {
				probability = (double) indexToNumMap.get(i)
						/ (double) speedListForEachType.size();
			}
			cumulativeProbability += probability;
			writer.write("\"" + df.format(middle) + "\",");
			writer.write("\"" + cumulativeProbability + "\"");
			writer.write("\n");
		}

		writer.close();

	}

	public void calculatePDFCurve(String folderName, String typeShort,
			List<Double> speedListForEachType, double distance)
			throws IOException {

		Utilities.makeDir(folderName
				+ "Analysis_Results/Stop_Times/Stop_Times_PDF_PerType");

		DecimalFormat df = new DecimalFormat("#.##");

		HashMap<Integer, Integer> indexToNumMap = new HashMap<Integer, Integer>();

		double sMax = Double.MIN_VALUE;
		double sMin = Double.MAX_VALUE;

		// sMin, sMax
		for (double d : speedListForEachType) {
			if (d < sMin)
				sMin = d;
			if (d > sMax)
				sMax = d;
		}

		int indexMax = 0;

		for (double d : speedListForEachType) {
			int index = (int) ((d - sMin) / distance);
			if (index > indexMax) {
				indexMax = index;
			}
			if (indexToNumMap.get(index) == null) {
				indexToNumMap.put(index, 1);
			} else {
				indexToNumMap.put(index, indexToNumMap.get(index) + 1);
			}
		}

		FileWriter writer = new FileWriter(folderName
				+ "Analysis_Results/Stop_Times/Stop_Times_PDF_PerType/"
				+ typeShort + ".txt");

		writer.write("\"Speed\",\"Probability\"\n");

		for (int i = 0; i <= indexMax; i++) {
			double middle = sMin + i * distance + 0.5 * distance;
			double probability;
			if (indexToNumMap.get(i) == null) {
				probability = 0.0;
			} else {
				probability = (double) indexToNumMap.get(i)
						/ (double) speedListForEachType.size();
			}
			writer.write("\"" + df.format(middle) + "\",");
			writer.write("\"" + probability + "\"");
			writer.write("\n");
		}

		writer.close();

	}

	public List<Double> getSpeedListForEachType(
			HashMap<String, ArrayList<Stop_Times>> tripIdToListOfStopTimesMap,
			List<String> tripIdForEachType) {

		List<Double> speedListForEachType = new ArrayList<Double>();

		// trip id => average speed
		for (String tripId : tripIdForEachType) {

			ArrayList<Stop_Times> stop_times_group = tripIdToListOfStopTimesMap
					.get(tripId);

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

			double sumVelocity = 0;
			for (double velocity : velocities) {
				sumVelocity += velocity;
			}
			double averageSpeed = sumVelocity / velocities.size();

			speedListForEachType.add(averageSpeed);

		}

		return speedListForEachType;

	}

	public void calculatePDFCurvePerOperatorRoute(String folderName,
			String type, String typeShort,
			HashMap<String, List<Double>> routeNumberToListOfSpeedMap,
			HashMap<String, String> routeNumberToOperatorNumberMap,
			double distance) throws IOException {

		Utilities.makeDir(folderName + "Analysis_Results/Stop_Times_PDF/"
				+ type);

		DecimalFormat df = new DecimalFormat("#.##");

		Set<Map.Entry<String, List<Double>>> entrySetRouteNumberToListOfSpeedMap = routeNumberToListOfSpeedMap
				.entrySet();
		for (Map.Entry<String, List<Double>> entry : entrySetRouteNumberToListOfSpeedMap) {

			String routeNumber = entry.getKey();
			List<Double> speedList = entry.getValue();

			HashMap<Integer, Integer> indexToNumMap = new HashMap<Integer, Integer>();

			double sMax = Double.MIN_VALUE;
			double sMin = Double.MAX_VALUE;

			// sMin, sMax
			for (double d : speedList) {
				if (d < sMin)
					sMin = d;
				if (d > sMax)
					sMax = d;
			}

			int indexMax = 0;

			for (double d : speedList) {
				int index = (int) ((d - sMin) / distance);
				if (index > indexMax) {
					indexMax = index;
				}
				if (indexToNumMap.get(index) == null) {
					indexToNumMap.put(index, 1);
				} else {
					indexToNumMap.put(index, indexToNumMap.get(index) + 1);
				}
			}

			String operatorNumber = routeNumberToOperatorNumberMap
					.get(routeNumber);
			String fileName = typeShort + "-" + operatorNumber + "-"
					+ routeNumber;
			FileWriter writer = new FileWriter(folderName
					+ "Analysis_Results/Stop_Times_PDF/" + type + "/"
					+ fileName + ".txt");

			writer.write("\"Speed\",\"Probability\"\n");

			for (int i = 0; i <= indexMax; i++) {
				double middle = sMin + i * distance + 0.5 * distance;
				double probability;
				if (indexToNumMap.get(i) == null) {
					probability = 0.0;
				} else {
					probability = (double) indexToNumMap.get(i)
							/ (double) speedList.size();
				}
				writer.write("\"" + df.format(middle) + "\",");
				writer.write("\"" + probability + "\"");
				writer.write("\n");
			}

			writer.close();

		}
	}

	public HashMap<String, List<Double>> classifyRouteNumber(
			HashMap<String, ArrayList<Stop_Times>> tripIdToListOfStopTimesMap,
			List<String> tripIdForEachType) {

		// route number -> speed list
		HashMap<String, List<Double>> routeNumberToListOfSpeedMap = new HashMap<String, List<Double>>();

		int maxSequence = 0;

		// Analyze
		for (String s : tripIdForEachType) {

			ArrayList<Stop_Times> stop_times_group = tripIdToListOfStopTimesMap
					.get(s);

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

			if (velocities.size() > maxSequence) {
				maxSequence = velocities.size();
			}

			double sumVelocity = 0;

			for (double velocity : velocities) {
				sumVelocity += velocity;
			}

			String[] splitStr = stop_times_group.get(0).getTrip_id().split("-");

			String routeNumber = "";
			double averageSpeed = sumVelocity / velocities.size();

			// route number
			if (splitStr.length == 4) {
				routeNumber = splitStr[1];
			} else {
				routeNumber = splitStr[1] + "-" + splitStr[2];
			}

			if (routeNumberToListOfSpeedMap.get(routeNumber) == null) {
				List<Double> listOfSpeed = new ArrayList<Double>();
				listOfSpeed.add(averageSpeed);
				routeNumberToListOfSpeedMap.put(routeNumber, listOfSpeed);
			} else {
				routeNumberToListOfSpeedMap.get(routeNumber).add(averageSpeed);
			}

		}

		return routeNumberToListOfSpeedMap;
	}

	public HashMap<String, String> getRouteNumberToOperatorNumberMap(
			HashMap<String, ArrayList<Stop_Times>> map,
			List<String> tripIdForEachType) {

		HashMap<String, String> routeNumberToOperatorNumberMap = new HashMap<String, String>();

		for (String s : tripIdForEachType) {

			ArrayList<Stop_Times> stop_times_group = map.get(s);

			String[] splitStr = stop_times_group.get(0).getTrip_id().split("-");
			String[] splitStr1 = splitStr[0].split("\\.");

			String operatorNumber = splitStr1[2];

			String routeNumber;
			if (splitStr.length == 4) {
				routeNumber = splitStr[1];
			} else {
				routeNumber = splitStr[1] + "-" + splitStr[2];
			}

			routeNumberToOperatorNumberMap.put(routeNumber, operatorNumber);

		}

		return routeNumberToOperatorNumberMap;
	}

	public List<Stop_Times> getStopTimesList() throws IOException,
			ParseException {
		String stops_times_CSV_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/Analysis_Results/stop_times_modified.csv";
		List<String> stopTimesFileLines = Utilities.readFile(
				stops_times_CSV_file, true);

		AnalyseData analyseData = new AnalyseData();
		List<Stop_Times> stopTimesList = analyseData
				.parseStrLines(stopTimesFileLines);

		return stopTimesList;
	}

	public HashMap<String, String> mapStopIdToTripId(
			List<Stop_Times> stopTimesList) {

		HashMap<String, String> stopIdToTripIdMap = new HashMap<String, String>();

		for (Stop_Times stopTimes : stopTimesList) {
			if (stopIdToTripIdMap.get(stopTimes.getStop_id()) == null) {
				stopIdToTripIdMap.put(stopTimes.getStop_id(),
						stopTimes.getTrip_id());
			}
		}

		return stopIdToTripIdMap;

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
}