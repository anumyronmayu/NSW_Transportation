package analysing_TXT_Datasets.Trips;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import utilities.Utilities;

public class AnalyseData {

	public HashMap<String, String> mapServiceIdToType(
			HashMap<String, List<String>> serviceIdToRouteIdListMap,
			HashMap<String, String> typeMapWithVersionNumber) {

		HashMap<String, String> serviceIdToTypeMap = new HashMap<String, String>();

		Set<Map.Entry<String, List<String>>> entrySetServiceIdToRouteIdListMap = serviceIdToRouteIdListMap
				.entrySet();
		for (Map.Entry<String, List<String>> entry : entrySetServiceIdToRouteIdListMap) {
			String serviceId = entry.getKey();
			List<String> routeIdList = entry.getValue();

			String type = "";

			for (String routeId : routeIdList) {
				if (typeMapWithVersionNumber.get(routeId) != null) {
					type = typeMapWithVersionNumber.get(routeId);
					break;
				}

			}

			if (type.equals("")) {
				System.out.println("One service id hasn't been mapped!");
				System.out.println(serviceId);
			}

			serviceIdToTypeMap.put(serviceId, type);
		}

		return serviceIdToTypeMap;
	}

	public HashMap<String, String> mapServiceIdToColor(
			HashMap<String, List<String>> serviceIdToRouteIdListMap,
			HashMap<String, String> colorMapWithVersionNumber) {

		HashMap<String, String> serviceIdToTypeMap = new HashMap<String, String>();

		Set<Map.Entry<String, List<String>>> entrySetServiceIdToRouteIdListMap = serviceIdToRouteIdListMap
				.entrySet();
		for (Map.Entry<String, List<String>> entry : entrySetServiceIdToRouteIdListMap) {
			String serviceId = entry.getKey();
			List<String> routeIdList = entry.getValue();

			String color = "";

			for (String routeId : routeIdList) {
				if (colorMapWithVersionNumber.get(routeId) != null) {
					color = colorMapWithVersionNumber.get(routeId);
					break;
				}
			}

			if (color.equals("")) {
				System.out.println("One service id hasn't been mapped!");
				System.out.println(serviceId);
			}

			serviceIdToTypeMap.put(serviceId, color);
		}

		return serviceIdToTypeMap;
	}

	public HashMap<String, List<String>> mapServiceIdToRouteIdList(
			List<Trips> tripsList) {

		HashMap<String, List<String>> serviceIdToRouteIdListMap = new HashMap<String, List<String>>();

		for (Trips trips : tripsList) {
			String serviceId = trips.getService_id();
			String routeId = trips.getRoute_id();

			if (serviceIdToRouteIdListMap.get(serviceId) == null) {
				List<String> routeIdList = new ArrayList<String>();
				routeIdList.add(routeId);
				serviceIdToRouteIdListMap.put(serviceId, routeIdList);
			} else {
				if (!serviceIdToRouteIdListMap.get(serviceId).contains(routeId)) {
					serviceIdToRouteIdListMap.get(serviceId).add(routeId);
				}

			}
		}

		return serviceIdToRouteIdListMap;

	}

	public List<Trips> getTripsList() throws IOException {

		String trips_CSV_file = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/trips.csv";
		List<String> fileLines = Utilities.readFile(trips_CSV_file, true);

		AnalyseData obj = new AnalyseData();
		List<Trips> tripList = obj.parseStrLines(fileLines);

		return tripList;
	}

	public List<Trips> parseStrLines(List<String> list) {

		List<Trips> tripList = new ArrayList<Trips>();

		for (String s : list) {

			String[] splitStr = s.split("\",\"");

			Trips trip = new Trips();

			trip.setRoute_id(splitStr[0].substring(1, splitStr[0].length()));
			trip.setService_id(splitStr[1]);
			trip.setTrip_id(splitStr[2]);
			trip.setShape_id(splitStr[3]);
			trip.setTrip_headsign(splitStr[4]);
			trip.setDirection_id(splitStr[5]);
			trip.setBlock_id(splitStr[6]);
			trip.setWheelchair_accessible(splitStr[7].substring(0,
					splitStr[7].length() - 1));

			tripList.add(trip);

		}

		return tripList;

	}
}
