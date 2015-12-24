package analysing_CSV_Datasets.Calendar_Dates;

import java.util.ArrayList;
import java.util.List;

public class AnalyseData {

	public List<Calendar_Dates> parseStrLines(List<String> list) {

		List<Calendar_Dates> calendarDatesList = new ArrayList<Calendar_Dates>();

		for (String s : list) {

			String[] splitStr = s.split("\",\"");

			Calendar_Dates calendar_dates = new Calendar_Dates();

			calendar_dates.setService_id(splitStr[0].substring(1,
					splitStr[0].length()));
			calendar_dates.setDate(splitStr[1]);
			calendar_dates.setException_type(splitStr[2].substring(0,
					splitStr[2].length() - 1));

			calendarDatesList.add(calendar_dates);

		}

		return calendarDatesList;

	}

}
