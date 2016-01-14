package analysing_TXT_Datasets.Routes;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Extract_Routes_Data {

	public HashMap<String, String> getTypeMapWithVersionNumber()
			throws IOException {

		String folderName = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/";
		String csv_file = folderName + "routes.csv";
		FileInputStream fstream = new FileInputStream(csv_file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String firstLine = br.readLine();
		String strLine;

		List<Routes> routesList = new ArrayList<Routes>();

		while ((strLine = br.readLine()) != null) {

			String[] splitStr = strLine.split("\",\"");

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

		br.close();

		HashMap<String, ArrayList<Integer>> versionMap = new HashMap<String, ArrayList<Integer>>();
		HashMap<String, String> typeMap = new HashMap<String, String>();
		HashMap<String, String> typeMapWithVersionNumber = new HashMap<String, String>();

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

			if (typeMap.get(route.getRoute_id_part1()) == null) {
				typeMap.put(route.getRoute_id_part1(), route.getRoute_desc());
			} else {

			}
		}

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

	public static void main(String args[]) throws IOException {
		Extract_Routes_Data obj = new Extract_Routes_Data();
		HashMap<String, String> typeMapWithVersionNumber = obj
				.getTypeMapWithVersionNumber();
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