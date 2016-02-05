package analysing_TXT_Datasets.Calendar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import analysing_TXT_Datasets.Routes.Routes;
import analysing_TXT_Datasets.Trips.Trips;

public class App {

	public static void main(String[] args) throws IOException {

		// route id => type, color
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

		// get calendar list
		analysing_TXT_Datasets.Calendar.AnalyseData obj3 = new analysing_TXT_Datasets.Calendar.AnalyseData();
		List<Calendar> calendarList = obj3.getCalendarList();
		System.out.println(calendarList.size());

		// type
		List<Calendar> listOfSydneyBusesNetwork = new ArrayList<Calendar>();
		List<Calendar> listOfRegionalCoachesNetwork = new ArrayList<Calendar>();
		List<Calendar> listOfRegionalTrainsNetwork = new ArrayList<Calendar>();
		List<Calendar> listOfPrivatebusservices = new ArrayList<Calendar>();
		List<Calendar> listOfIllawarraBusesNetwork = new ArrayList<Calendar>();
		List<Calendar> listOfBlueMountainsBusesNetwork = new ArrayList<Calendar>();
		List<Calendar> listOfCentralCoastBusesNetwork = new ArrayList<Calendar>();
		List<Calendar> listOfTemporarybuses = new ArrayList<Calendar>();
		List<Calendar> listOfHunterBusesNetwork = new ArrayList<Calendar>();
		List<Calendar> listOfSydneyTrainsNetwork = new ArrayList<Calendar>();
		List<Calendar> listOfSydneyFerriesNetwork = new ArrayList<Calendar>();
		List<Calendar> listOfPrivateferryandfastferryservices = new ArrayList<Calendar>();
		List<Calendar> listOfSydneyLightRailNetwork = new ArrayList<Calendar>();
		List<Calendar> listOfIntercityTrainsNetwork = new ArrayList<Calendar>();
		List<Calendar> listOfNewcastleFerries = new ArrayList<Calendar>();

		List<List<Calendar>> allTransportationNetworks = new ArrayList<List<Calendar>>();
		allTransportationNetworks.add(listOfSydneyBusesNetwork);
		allTransportationNetworks.add(listOfRegionalCoachesNetwork);
		allTransportationNetworks.add(listOfRegionalTrainsNetwork);
		allTransportationNetworks.add(listOfPrivatebusservices);
		allTransportationNetworks.add(listOfIllawarraBusesNetwork);
		allTransportationNetworks.add(listOfBlueMountainsBusesNetwork);
		allTransportationNetworks.add(listOfCentralCoastBusesNetwork);
		allTransportationNetworks.add(listOfTemporarybuses);
		allTransportationNetworks.add(listOfHunterBusesNetwork);
		allTransportationNetworks.add(listOfSydneyTrainsNetwork);
		allTransportationNetworks.add(listOfSydneyFerriesNetwork);
		allTransportationNetworks.add(listOfPrivateferryandfastferryservices);
		allTransportationNetworks.add(listOfSydneyLightRailNetwork);
		allTransportationNetworks.add(listOfIntercityTrainsNetwork);
		allTransportationNetworks.add(listOfNewcastleFerries);

		List<String> typeListShort = new ArrayList<String>();
		typeListShort.add("SBN");
		typeListShort.add("RTN");
		typeListShort.add("RCN");
		typeListShort.add("PBS");
		typeListShort.add("IBN");
		typeListShort.add("BMBN");
		typeListShort.add("CCBN");
		typeListShort.add("TB");
		typeListShort.add("HBN");
		typeListShort.add("STN");
		typeListShort.add("SFN");
		typeListShort.add("PFFFS");
		typeListShort.add("SLRN");
		typeListShort.add("ITN");
		typeListShort.add("NCF");

		List<String> typeList = new ArrayList<String>();
		typeList.add("SydneyBusesNetwork");
		typeList.add("RegionalTrainsNetwork");
		typeList.add("RegionalCoachesNetwork");
		typeList.add("PrivateBusServices");
		typeList.add("IllawarraBusesNetwork");
		typeList.add("BlueMountainsBusesNetwork");
		typeList.add("CentralCoastBusesNetwork");
		typeList.add("Temporarybuses");
		typeList.add("HunterBusesNetwork");
		typeList.add("SydneyTrainsNetwork");
		typeList.add("SydneyFerriesNetwork");
		typeList.add("PrivateFerryAndFastFerryServices");
		typeList.add("SydneyLightRailNetwork");
		typeList.add("IntercityTrainsNetwork");
		typeList.add("NewCastleFerries");

		for (Calendar calendar : calendarList) {

			String serviceId = calendar.getService_id();

			if (serviceIdToTypeMap.get(serviceId) != null) {

				switch (serviceIdToTypeMap.get(serviceId)) {
				case "Sydney Buses Network":
					listOfSydneyBusesNetwork.add(calendar);
					break;
				case "Regional Trains and Coaches Network":
					if (serviceIdToColorMap.get(serviceId).equals("732A82")) {
						listOfRegionalCoachesNetwork.add(calendar);
					} else {
						listOfRegionalTrainsNetwork.add(calendar);
					}
					break;
				case "Private bus services":
					listOfPrivatebusservices.add(calendar);
					break;
				case "Illawarra Buses Network":
					listOfIllawarraBusesNetwork.add(calendar);
					break;
				case "Blue Mountains Buses Network":
					listOfBlueMountainsBusesNetwork.add(calendar);
					break;
				case "Central Coast Buses Network":
					listOfCentralCoastBusesNetwork.add(calendar);
					break;
				case "Temporary buses":
					listOfTemporarybuses.add(calendar);
					break;
				case "Hunter Buses Network":
					listOfHunterBusesNetwork.add(calendar);
					break;
				case "Sydney Trains Network":
					listOfSydneyTrainsNetwork.add(calendar);
					break;
				case "Sydney Ferries Network":
					listOfSydneyFerriesNetwork.add(calendar);
					break;
				case "Private ferry and fast ferry services":
					listOfPrivateferryandfastferryservices.add(calendar);
					break;
				case "Sydney Light Rail Network":
					listOfSydneyLightRailNetwork.add(calendar);
					break;
				case "Intercity Trains Network":
					listOfIntercityTrainsNetwork.add(calendar);
					break;
				case "Newcastle Ferries":
					listOfNewcastleFerries.add(calendar);
					break;
				default:
					System.out.println("No Such Type!!!");
				}
			} else {
				System.out.println("One service ID cannot be matched!");
			}
		}

		String folderName = "/Users/Myron/Documents/2015_nswtransport/GTFS/full_greater_sydney_gtfs_static_csv/";

		for (int i = 0; i < typeList.size(); i++) {
			obj3.getWeekDistribution(folderName,
					allTransportationNetworks.get(i), typeListShort.get(i));
		}

	}
}
