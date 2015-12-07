package analysing_CSV_Datasets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Extract_Stop_Times_Data {

	public static void main(String[] args) throws IOException, ParseException {

		String csv_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/stop_times.csv";
		FileInputStream fstream = new FileInputStream(csv_file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine = br.readLine();

		List<Stop_Times> list = new ArrayList<Stop_Times>();

		while ((strLine = br.readLine()) != null) {

			// System.out.println(strLine);

			String[] splitStr = strLine.split(",");

			Stop_Times st = new Stop_Times();
			st.setTrip_id(splitStr[0].substring(1, splitStr[0].length() - 1));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
			if (!splitStr[1].substring(1, splitStr[1].length() - 1).equals("")) {
				Date date1 = simpleDateFormat.parse(splitStr[1].substring(1,
						splitStr[1].length() - 1));
				st.setArrival_time(date1);
			} else {
				st.setArrival_time(list.get(list.size() - 1).getArrival_time());
				System.out.println(strLine);
			}
			if (!splitStr[2].substring(1, splitStr[2].length() - 1).equals("")) {
				Date date2 = simpleDateFormat.parse(splitStr[2].substring(1,
						splitStr[2].length() - 1));
				st.setDeparture_time(date2);
			} else {
				st.setDeparture_time(list.get(list.size() - 1)
						.getDeparture_time());
				System.out.println(strLine);
			}
			st.setStop_id(splitStr[3].substring(1, splitStr[3].length() - 1));
			st.setStop_sequence(Integer.parseInt(splitStr[4].substring(1,
					splitStr[4].length() - 1)));
			st.setStop_headsign(splitStr[5].substring(1,
					splitStr[5].length() - 1));
			st.setPickup_type(splitStr[6].substring(1, splitStr[6].length() - 1));
			st.setDrop_off_type(splitStr[7].substring(1,
					splitStr[7].length() - 1));
			if (!splitStr[8].substring(1, splitStr[8].length() - 1).equals("")) {
				st.setShape_dist_traveled(Double.parseDouble(splitStr[8]
						.substring(1, splitStr[8].length() - 1)));
			} else {
				st.setShape_dist_traveled(list.get(list.size() - 1)
						.getShape_dist_traveled());
				System.out.println(strLine);
			}

			list.add(st);
		}

		br.close();

		List<ArrayList<Double>> velocitiesList = new ArrayList<ArrayList<Double>>();
		ArrayList<Double> velocities = new ArrayList<Double>();
		List<ArrayList<String>> routeAndBusNumberList = new ArrayList<ArrayList<String>>();

		for (int i = 0; i < list.size(); i++) {

			Stop_Times st = list.get(i);
			int stop_sequence = st.getStop_sequence();

			if (stop_sequence != 1) {
				double time = (double) (list.get(i).getArrival_time().getTime() - list
						.get(i - 1).getDeparture_time().getTime()) / 1000 / 60 / 60;// hour
				double d = (list.get(i).getShape_dist_traveled() - list.get(
						i - 1).getShape_dist_traveled()) / 1000;
				double v = d / time;
				velocities.add(v);
			}

			if (i == list.size() - 1
					|| stop_sequence >= list.get(i + 1).getStop_sequence()) {
				// Last node
				ArrayList<Double> velocitiesCopy = new ArrayList<Double>();
				for (Double d : velocities) {
					velocitiesCopy.add(d);
				}
				velocitiesList.add(velocitiesCopy);
				ArrayList<String> routeAndBusNumber = new ArrayList<String>();

				String[] splitStr = st.getTrip_id().split("-");
				String[] splitStr1 = splitStr[0].split("\\.");
				routeAndBusNumber.add(splitStr1[2]);
				routeAndBusNumber.add(splitStr[1]);
				routeAndBusNumberList.add(routeAndBusNumber);
				velocities.clear();
			}
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

		for (int i = 0; i < averageVelocityList.size(); i++) {
			double v = averageVelocityList.get(i);
			String route = routeAndBusNumberList.get(i).get(0);
			String busNumber = routeAndBusNumberList.get(i).get(1);
			String result = ("Route: " + route + " Bus Number: " + busNumber
					+ " Average Velocity: " + df.format(v) + "km/h");
			results.add(result);
			System.out.println(result);
		}
		
		String resultsPath = "Stop_Times_Analysis_Results.txt";
		FileWriter fw = new FileWriter(resultsPath);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(results);
		bw.close();

	}
}