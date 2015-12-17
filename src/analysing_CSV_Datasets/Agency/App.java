package analysing_CSV_Datasets.Agency;

import java.io.IOException;
import java.util.List;
import utilities.Utilities;

public class App {

	public static void main(String[] args) throws IOException {

		String agency_CSV_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/agency.csv";
		List<String> fileLines = Utilities.readFile(agency_CSV_file, true);

		AnalyseData obj = new AnalyseData();
		List<Agency> agencyList = obj.parseStrLines(fileLines);

		System.out.println(agencyList.size());

	}

}
