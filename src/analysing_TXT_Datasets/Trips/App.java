package analysing_TXT_Datasets.Trips;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import analysing_TXT_Datasets.Routes.Routes;

public class App {

	public static void main(String[] args) throws IOException {

		// route_id_part1 + maxVersion => type, color
		analysing_TXT_Datasets.Routes.AnalyseData obj1 = new analysing_TXT_Datasets.Routes.AnalyseData();
		List<Routes> routesList = obj1.getRoutesList();
		System.out.println(routesList.size());

		HashMap<String, String> typeMap = obj1.getTypeMap(routesList);
		HashMap<String, String> colorMap = obj1.getColorMap(routesList);

		// get trip list
		analysing_TXT_Datasets.Trips.AnalyseData obj2 = new analysing_TXT_Datasets.Trips.AnalyseData();
		List<Trips> tripList = obj2.getTripsList();
		System.out.println(tripList.size());

		HashMap<String, List<String>> serviceIdToRouteIdListMap = obj2
				.mapServiceIdToRouteIdList(tripList);

		HashMap<String, String> serviceIdToTypeMap = obj2.mapServiceIdToType(
				serviceIdToRouteIdListMap, typeMap);
		HashMap<String, String> serviceIdToColorMap = obj2.mapServiceIdToColor(
				serviceIdToRouteIdListMap, colorMap);

		System.out.println(serviceIdToTypeMap.get("TA+b00mi+8"));
		System.out.println(serviceIdToColorMap.get("TA+b00mi+8"));

	}

}
