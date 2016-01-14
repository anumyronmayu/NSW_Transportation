package analysing_TXT_Datasets.Routes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utilities.Utilities;

public class App {

	public static void main(String[] args) throws IOException {

		String routes_CSV_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/routes.csv";
		List<String> fileLines = Utilities.readFile(routes_CSV_file, true);

		AnalyseData obj = new AnalyseData();
		List<Routes> routesList = obj.parseStrLines(fileLines);

		System.out.println(routesList.size());

		HashMap<String, String> typeMapWithVersionNumber = obj.getTypeMapWithVersionNumber(routesList);
		// HashMap<String, String> typeMapWithVersionNumber = obj.getColorMapWithVersionNumber(routesList);
		List<String> typeList = new ArrayList<String>();
		Set<Map.Entry<String, String>> entrySet1 = typeMapWithVersionNumber
				.entrySet();
		for (Map.Entry<String, String> entry : entrySet1) {
			if (!typeList.contains(entry.getValue())) {
				typeList.add(entry.getValue());
			}
		}
		for (String s : typeList) {
			System.out.println(s);
		}

	}

}
