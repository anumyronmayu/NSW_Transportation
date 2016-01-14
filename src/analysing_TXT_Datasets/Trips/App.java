package analysing_TXT_Datasets.Trips;

import java.io.IOException;
import java.util.List;
import utilities.Utilities;

public class App {

	public static void main(String[] args) throws IOException {

		String trips_CSV_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/trips.csv";
		List<String> fileLines = Utilities.readFile(trips_CSV_file, true);

		AnalyseData obj = new AnalyseData();
		List<Trips> tripList = obj.parseStrLines(fileLines);

		System.out.println(tripList.size());

	}

}
