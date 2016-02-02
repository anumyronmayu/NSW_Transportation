package analysing_TXT_Datasets.Stops;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import utilities.Utilities;

public class AnalyseData {

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
