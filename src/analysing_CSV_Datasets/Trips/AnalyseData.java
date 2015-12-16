package analysing_CSV_Datasets.Trips;

import java.util.ArrayList;
import java.util.List;

public class AnalyseData {

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
			trip.setWheelchair_accessible(splitStr[7]);

			tripList.add(trip);

		}

		return tripList;

	}
}
