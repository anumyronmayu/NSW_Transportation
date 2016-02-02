package analysing_TXT_Datasets.Stops;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import analysing_TXT_Datasets.Routes.Routes;
import analysing_TXT_Datasets.Stop_Times.Stop_Times;

public class App {

	public static void main(String[] args) throws IOException, ParseException {

		analysing_TXT_Datasets.Routes.AnalyseData obj1 = new analysing_TXT_Datasets.Routes.AnalyseData();
		List<Routes> routesList = obj1.getRoutesList();
		System.out.println(routesList.size());

		analysing_TXT_Datasets.Stop_Times.AnalyseData obj2 = new analysing_TXT_Datasets.Stop_Times.AnalyseData();
		List<Stop_Times> stopTimesList = obj2.getStopTimesList();
		System.out.println(stopTimesList.size());

		analysing_TXT_Datasets.Stops.AnalyseData obj3 = new analysing_TXT_Datasets.Stops.AnalyseData();
		List<Stops> stopList = obj3.getStopsList();
		System.out.println(stopList.size());

	}

}
