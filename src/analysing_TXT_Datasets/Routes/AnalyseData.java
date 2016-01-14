package analysing_TXT_Datasets.Routes;

import java.util.ArrayList;
import java.util.List;

public class AnalyseData {

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
