package analysing_TXT_Datasets.Stops;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utilities.Utilities;

public class AnalyseData {

	public int[][] calculateDensity(List<Stops> list) {

		double latMin = Double.MAX_VALUE;
		double latMax = Double.MIN_VALUE;
		double lonMin = Double.MAX_VALUE;
		double lonMax = Double.MIN_VALUE;

		for (Stops s : list) {

			double lat = s.getStop_lat() * -1;
			double lon = s.getStop_lon();

			if (lat < latMin) {
				latMin = lat;
			}
			if (lat > latMax) {
				latMax = lat;
			}
			if (lon < lonMin) {
				lonMin = lon;
			}
			if (lon > lonMax) {
				lonMax = lon;
			} // get lat, lon

		}

		int N = 100;
		int[][] quantity = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				quantity[i][j] = 0;
			}
		}

		String id = " ";

		for (Stops s : list) {
			double lat = s.getStop_lat() * -1;
			double lon = s.getStop_lon();

			double latcopy = (latMax - latMin) / N;
			double loncopy = (lonMax - lonMin) / N;

			int i = (int) Math.floor((lat - latMin) / latcopy);
			int j = (int) Math.floor((lon - lonMin) / loncopy);

			String stopId = s.getStop_id();

			if (id != stopId) {
				if (i == N && j == N) {
					quantity[i - 1][j - 1]++;
				} else if (i == N) {
					quantity[i - 1][j]++;
				} else if (j == N) {
					quantity[i][j - 1]++;
				} else {
					quantity[i][j]++;
				}// quantity
			}

			id = stopId;

		}

		return quantity;

	}

	public List<Stops> getStopsList() throws IOException {

		String stops_CSV_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/stops.csv";
		List<String> fileLines = Utilities.readFile(stops_CSV_file, true);

		AnalyseData obj = new AnalyseData();
		List<Stops> stopList = obj.parseStrLines(fileLines);

		return stopList;
	}

	public List<Stops> parseStrLines(List<String> list) {

		List<Stops> stopList = new ArrayList<Stops>();
		for (String s : list) {
			String[] splitStr = s.split("\",\"");

			Stops stop = new Stops();

			stop.setStop_id(splitStr[0].substring(1, splitStr[0].length()));

			stop.setStop_code(splitStr[1]);
			stop.setStop_name(splitStr[2]);
			stop.setStop_lat(Double.parseDouble(splitStr[3]));
			stop.setStop_lon(Double.parseDouble(splitStr[4]));
			stop.setLocation_type(splitStr[5]);
			stop.setParent_station(splitStr[6]);
			stop.setWheelchair_boarding(splitStr[7]);
			stop.setPlatform_code(splitStr[8].substring(0,
					splitStr[8].length() - 1));

			stopList.add(stop);
		}

		return stopList;

	}

}
