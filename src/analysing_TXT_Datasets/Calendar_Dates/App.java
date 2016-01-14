package analysing_TXT_Datasets.Calendar_Dates;

import java.io.IOException;
import java.util.List;

import utilities.Utilities;

public class App {

	public static void main(String[] args) throws IOException {

		String calendar_dates_CSV_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/calendar_dates.csv";
		List<String> fileLines = Utilities.readFile(calendar_dates_CSV_file, true);

		AnalyseData obj = new AnalyseData();
		List<Calendar_Dates> calendarDatesList = obj.parseStrLines(fileLines);

		System.out.println(calendarDatesList.size());

	}

}
