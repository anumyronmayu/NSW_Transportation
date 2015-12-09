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
/*
 * Version 2
 */
public class Extract_Stop_Times_Data2 {

	private static void makeDir(String dir) {
		new File(dir).mkdirs();
	}

	public static void main(String[] args) throws IOException, ParseException {

		String folderName = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/";
		String csv_file = folderName
				+ "Analysis_Results/stop_times_modified.csv";
		FileInputStream fstream = new FileInputStream(csv_file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine = br.readLine();

		List<Stop_Times> list = new ArrayList<Stop_Times>();
		HashMap<String, ArrayList<Stop_Times>> map = new HashMap<String, ArrayList<Stop_Times>>();

		int index = 0;

		while ((strLine = br.readLine()) != null) {
			index++;
			// System.out.println(strLine);

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

		List<ArrayList<Double>> velocitiesList = new ArrayList<ArrayList<Double>>();
		List<ArrayList<String>> routeAndBusNumberList = new ArrayList<ArrayList<String>>();

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

		Set<Map.Entry<String, ArrayList<Stop_Times>>> entrySet = map.entrySet();
		for (Map.Entry<String, ArrayList<Stop_Times>> entry : entrySet) {

			ArrayList<Stop_Times> stop_times_group = entry.getValue();
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
				// System.out.println(d);
				double v = d / time;
				// System.out.println(v);

				/*
				 * if (v > 1000) { for (Stop_Times st : stop_times_group) {
				 * System.out.println("TripId: " + st.getTrip_id() +
				 * ", Arrival Time: " + st.getArrival_time() +
				 * ", Departure Time: " + st.getDeparture_time() +
				 * ", Distance: " + st.getShape_dist_traveled()); } return; }
				 */

				velocities.add(v);

			}

			velocitiesList.add(velocities);

			ArrayList<String> routeAndBusNumber = new ArrayList<String>();
			String[] splitStr = stop_times_group.get(0).getTrip_id().split("-");
			String[] splitStr1 = splitStr[0].split("\\.");
			routeAndBusNumber.add(splitStr1[2]);
			if (splitStr.length == 4) {
				routeAndBusNumber.add(splitStr[1]);
			} else {
				routeAndBusNumber.add(splitStr[1] + "-" + splitStr[2]);
			}
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

		makeDir(folderName + "Analysis_Results/Stop_Times");

		FileWriter writer = new FileWriter(folderName
				+ "Analysis_Results/Stop_Times/Stop_Times_Analysis_Results.txt");
		FileWriter writerCSV = new FileWriter(
				folderName
						+ "Analysis_Results/Stop_Times/Stop_Times_Analysis_Results_csv.csv");
		writerCSV.write("\"Route\",\"BusNumber\",\"AverageVelocity\"\n");
		for (int i = 0; i < averageVelocityList.size(); i++) {
			double v = averageVelocityList.get(i);
			String route = routeAndBusNumberList.get(i).get(0);
			String busNumber = routeAndBusNumberList.get(i).get(1);
			String result = ("Route: " + route + " Bus Number: " + busNumber
					+ " Average Velocity: " + df.format(v) + "km/h");
			results.add(result);
			writer.write(result + "\n");
			writerCSV.write("\"" + route + "\",\"" + busNumber + "\",\""
					+ df.format(v) + "\"\n");
			// System.out.println(result);
		}
		writer.close();
		writerCSV.close();

	}
}