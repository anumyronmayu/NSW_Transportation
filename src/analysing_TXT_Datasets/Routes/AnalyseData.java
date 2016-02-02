package analysing_TXT_Datasets.Routes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utilities.Utilities;

public class AnalyseData {

	public List<Routes> getRoutesList() throws IOException {

		String routes_CSV_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/routes.csv";
		List<String> fileLines = Utilities.readFile(routes_CSV_file, true);

		AnalyseData obj = new AnalyseData();
		List<Routes> routesList = obj.parseStrLines(fileLines);

		return routesList;

	}

	public HashMap<String, String> getColorMapWithVersionNumber(
			List<Routes> routesList) throws IOException {

		// route_id_part1 => list<version>
		HashMap<String, ArrayList<Integer>> versionMap = new HashMap<String, ArrayList<Integer>>();

		// route_id_part1 => route_color
		HashMap<String, String> colorMap = new HashMap<String, String>();

		// route_id_part1 + maxVersion => route_color
		HashMap<String, String> colorMapWithVersionNumber = new HashMap<String, String>();

		// get versionMap
		for (Routes route : routesList) {
			if (versionMap.get(route.getRoute_id_part1()) == null) {
				ArrayList<Integer> versionList = new ArrayList<Integer>();
				versionList.add(Integer.parseInt(route.getRoute_id_part2()));
				versionMap.put(route.getRoute_id_part1(), versionList);
			} else if (!versionMap.get(route.getRoute_id_part1()).contains(
					Integer.parseInt(route.getRoute_id_part2()))) {
				versionMap.get(route.getRoute_id_part1()).add(
						Integer.parseInt(route.getRoute_id_part2()));
			}

			// get colorMap
			if (colorMap.get(route.getRoute_id_part1()) == null) {
				colorMap.put(route.getRoute_id_part1(), route.getRoute_color());
			}

		}

		// get typeMapWithVersionNumber, colorMapWithVersionNumber
		Set<Map.Entry<String, ArrayList<Integer>>> entrySet = versionMap
				.entrySet();
		for (Map.Entry<String, ArrayList<Integer>> entry : entrySet) {
			int max = 0;
			for (int i : entry.getValue()) {
				if (i > max) {
					max = i;
				}
			}

			colorMapWithVersionNumber.put(
					entry.getKey() + "-" + String.valueOf(max),
					colorMap.get(entry.getKey()));

		}

		return colorMapWithVersionNumber;
	}

	public HashMap<String, String> getTypeMapWithVersionNumber(
			List<Routes> routesList) throws IOException {

		// route_id_part1 => list<version>
		HashMap<String, ArrayList<Integer>> versionMap = new HashMap<String, ArrayList<Integer>>();

		// route_id_part1 => route_desc
		HashMap<String, String> typeMap = new HashMap<String, String>();

		// route_id_part1 + maxVersion => route_desc
		HashMap<String, String> typeMapWithVersionNumber = new HashMap<String, String>();

		// get versionMap
		for (Routes route : routesList) {
			if (versionMap.get(route.getRoute_id_part1()) == null) {
				ArrayList<Integer> versionList = new ArrayList<Integer>();
				versionList.add(Integer.parseInt(route.getRoute_id_part2()));
				versionMap.put(route.getRoute_id_part1(), versionList);
			} else if (!versionMap.get(route.getRoute_id_part1()).contains(
					Integer.parseInt(route.getRoute_id_part2()))) {
				versionMap.get(route.getRoute_id_part1()).add(
						Integer.parseInt(route.getRoute_id_part2()));
			}

			// get typeMap
			if (typeMap.get(route.getRoute_id_part1()) == null) {
				typeMap.put(route.getRoute_id_part1(), route.getRoute_desc());
			}

		}

		// get typeMapWithVersionNumber, colorMapWithVersionNumber
		Set<Map.Entry<String, ArrayList<Integer>>> entrySet = versionMap
				.entrySet();
		for (Map.Entry<String, ArrayList<Integer>> entry : entrySet) {
			int max = 0;
			for (int i : entry.getValue()) {
				if (i > max) {
					max = i;
				}
			}

			typeMapWithVersionNumber.put(
					entry.getKey() + "-" + String.valueOf(max),
					typeMap.get(entry.getKey()));

		}

		return typeMapWithVersionNumber;
	}

	public List<Routes> parseStrLines(List<String> list) {

		List<Routes> routesList = new ArrayList<Routes>();

		for (String s : list) {

			String[] splitStr = s.split("\",\"");

			Routes route = new Routes();

			route.setRoute_id(splitStr[0].substring(1));

			String[] splitStr1 = route.getRoute_id().split("-");
			if (splitStr1.length == 4) {
				route.setRoute_id_part1(splitStr1[0] + "-" + splitStr1[1] + "-"
						+ splitStr1[2]);
				route.setRoute_id_part2(splitStr1[3]);
			} else if (splitStr1.length == 5) {
				route.setRoute_id_part1(splitStr1[0] + "-" + splitStr1[1] + "-"
						+ splitStr1[2] + "-" + splitStr1[3]);
				route.setRoute_id_part2(splitStr1[4]);
			} else {
				System.out.println("Not 4 or 5!!!");
			}

			route.setAgency_id(splitStr[1]);
			route.setRoute_short_name(splitStr[2]);
			route.setRoute_long_name(splitStr[3]);
			route.setRoute_desc(splitStr[4]);
			route.setRoute_type(splitStr[5]);
			route.setRoute_color(splitStr[6]);
			route.setRoute_text_color(splitStr[7].substring(0,
					splitStr[7].length() - 1));

			routesList.add(route);

		}

		return routesList;

	}
}