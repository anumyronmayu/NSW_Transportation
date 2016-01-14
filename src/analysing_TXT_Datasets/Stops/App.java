package analysing_TXT_Datasets.Stops;

import java.io.IOException;
import java.util.List;

import utilities.Utilities;

public class App {

	public static void main(String[] args) throws IOException {

		String stops_CSV_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/stops.csv";
		List<String> fileLines = Utilities.readFile(stops_CSV_file, true);

		AnalyseData obj = new AnalyseData();
		List<Stops> stopList = obj.parseStrLines(fileLines);

		System.out.println(stopList.size());

	}

}
